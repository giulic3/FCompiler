package ast;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

public class FunDecNode implements Node {

	private String id;
	private Node type;

	private ArrayList<Node> parlist;
	private ArrayList<Node> declist;
	private ArrayList<Node> body;
	private ParserRuleContext ctx;
	private String classID = "";

	public FunDecNode (String id, Node type, ArrayList<Node> declist, ArrayList<Node> parlist, ArrayList<Node> body, ParserRuleContext ctx) {
		this.ctx=ctx;
		this.id = id;
		this.type = type;
		this.declist = declist;
		this.parlist=parlist;
		this.body = body;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<>();
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		
		String funID = this.classID + id;
		
		// this handles methods
		if (!classID.isEmpty()) {
			SymbolTableEntry classEntry = env.getActiveDec(this.classID);
			if (classEntry != null) {
				BlockClassDecNode classNode = (BlockClassDecNode)classEntry.getType();
				ArrayList<Node> inheritedMethods = classNode.getInheritedMethods(classNode.getSuperclassID(), env);
				for (Node m:inheritedMethods) {
					FunDecNode method = (FunDecNode)m;
					// if current method has same name of one inherited, overriding should be checked; if is overriding (same parameters and return type) is ok otherwise error
					if (method.getID().equals(this.id)) {
						if (method.parlist.size() != this.parlist.size())
							res.add(new SemanticError("Method overloading (wrong number of parameters) '" + this.toPrint("") + "' is not allowed at line "
									+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
						else {
							for (int i=0; i<parlist.size(); i++) {
								VarNode inheritedMethodPar = (VarNode)method.parlist.get(i);
								VarNode curMethodPar = (VarNode)this.parlist.get(i);
								if (inheritedMethodPar.getType().getClass() != curMethodPar.getType().getClass())
									res.add(new SemanticError("Method overloading (wrong parameter type) '" + this.toPrint("") + "' is not allowed at line "
											+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
							}
						}
						if (method.type.getClass() != this.type.getClass())
							res.add(new SemanticError("Method overloading (wrong return type) '" + this.toPrint("") + "' is not allowed at line "
									+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
					}
				}
			}
		}
		
		if(!env.getFunSecondCheck()) {
			if (hm.put(funID, entry) != null)
				res.add(new SemanticError("Fun id " + id + " already declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n"));
		}
		else {
			env.pushScope();
			
			HashMap<String, SymbolTableEntry> fun_hm = env.getSymTable().get(env.getNestingLevel());
			
			ArrayList<Node> parTypes = new ArrayList<Node>();
			int paroffset=1;
			
			for (Node par : parlist) {
				VarNode arg = (VarNode) par;
				parTypes.add(arg.getType());
				if ( fun_hm.put(arg.getId(),new SymbolTableEntry(env.getNestingLevel(),paroffset++,arg.getType())) != null  )
					res.add(new SemanticError("Parameter id "+arg.getId()+" already declared at line: "+arg.getCtx().start.getLine()+":"+arg.getCtx().start.getCharPositionInLine()+"\n"));
			}
			
			for (Node dec : declist) {
				env.setOffset(env.getOffset()-2);
				res.addAll(dec.checkSemantics(env));
			}
			
			for (Node b : body) {
				res.addAll(b.checkSemantics(env));
			}
			
			env.popScope();
		}
		
		return res;
	}

	public String toPrint(String s) {

		String parlstr = "";
		String declstr = "";

		if (parlist!=null && !parlist.isEmpty()) {
			for (Node par : parlist)
				parlstr += "\n" + par.toPrint(s + "\t\t");
			parlstr+="\n"+s+"\t";
		}

		if (parlist!=null && !parlist.isEmpty()) {
			declstr = "\n"+s+"\tFun Decs:";
			for (Node dec : declist)
				declstr += "\n" + dec.toPrint(s + "\t\t");
		}

		return s+"Fun Dec Node: " +type.toPrint("") + " " + id +"("
				+parlstr+")"
				+declstr;
				//+body.toPrint(s+"  ") ;
	}
	
	public void setInsideClass(String val) {
		this.classID = "Class$" + val;
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {

		return null;
	}

	public String codeGeneration() {
		return "";
	}
	
	public String getID() {
		return id;
	}

}