package ast;

import ast.types.ClassType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class MethodDecNode extends FunDecNode {
	
	private String classID;
	
	public MethodDecNode(FunDecNode funNode, String classID) {
		super(funNode.name, funNode.type, funNode.decList, funNode.parList, funNode.body, funNode.ctx);
		this.classID = classID;
	}
	
	/*public MethodDecNode (String name, Node type, ArrayList<Node> decList, ArrayList<Node> parList, ArrayList<Node> bodyList, String classID, ParserRuleContext ctx) {
		super(name, type, decList, parList, bodyList, ctx);
		this.classID = classID;
	}*/
	
	public void setInsideClass(String val) {
		this.classID = val;
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		
		//create result list
		HashSet<String> res = new HashSet<String>();
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		entry.setClassName(classID);
		
		String methodID = "Class$" + classID +"$m$"+ name;
		
		// this handles methods
		SymbolTableEntry classEntry = env.getClassEntry(this.classID);
		if (classEntry != null) {
			ClassType classNode = (ClassType)classEntry.getType();
			ArrayList<Node> inheritedMethods = classNode.getMethodsList(true);
			
			for (Node m:inheritedMethods) {
				MethodDecNode method = (MethodDecNode)m;
				// if current method has same name of one inherited, overriding should be checked; if is overriding (same parameters and return type) is ok otherwise error
				if (method.getID().equals(this.name)) {
					if (method.parList.size() != this.parList.size())
						res.add("Method overloading (wrong number of parameters) '" + this.toPrint("") + "' is not allowed at line "
								+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
					else {
						for (int i = 0; i< parList.size(); i++) {
							VarNode inheritedMethodPar = (VarNode)method.parList.get(i);
							VarNode curMethodPar = (VarNode)this.parList.get(i);
							if (inheritedMethodPar.getType().getClass() != curMethodPar.getType().getClass())
								res.add("Method overloading (wrong parameter type) '" + this.toPrint("") + "' is not allowed at line "
										+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
						}
					}
					if (method.type.getClass() != this.type.getClass())
						res.add("Method overloading (wrong return type) '" + this.toPrint("") + "' is not allowed at line "
								+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				}
			}
		}
		
		if(!env.getFunSecondCheck()) {
			if (hm.put(methodID, entry) != null)
				res.add("Method name " + name + " already declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		env.pushScope();
		
		HashMap<String, SymbolTableEntry> methodContentHM = env.getSymTable().get(env.getNestingLevel());
		
		ArrayList<Node> parTypes = new ArrayList<>();
		int paroffset = 1;
		
		for (Node par : parList) {
			VarNode arg = (VarNode)par;
			parTypes.add(arg.getType());
			SymbolTableEntry parEntry = new SymbolTableEntry(env.getNestingLevel(), paroffset++, arg.getType());
			
			if (methodContentHM.put(arg.getId(), parEntry) != null)
				res.add("Parameter name " + arg.getId() + " already declared at line: " + arg.getCtx().start.getLine() + ":" + arg.getCtx().start.getCharPositionInLine() + "\n");
		}
		
		for (Node dec : decList) {
			env.setOffset(env.getOffset() - 2);
			res.addAll(dec.checkSemantics(env));
		}
		
		for (Node b : body)
			res.addAll(b.checkSemantics(env));
		
		env.popScope();
		
		return res;
	}
	
	@Override
	public String toPrint(String s) {
		
		String parlstr = "";
		String declstr = "";
		
		if (parList != null && !parList.isEmpty()) {
			for (Node par : parList)
				parlstr += "\n" + par.toPrint(s + "\t\t");
			parlstr+="\n"+s+"\t";
		}
		
		if (decList != null && !decList.isEmpty()) {
			declstr = "\n"+s+"\tFun Decs:";
			for (Node dec : decList)
				declstr += "\n" + dec.toPrint(s + "\t\t");
		}
		
		return s+"Method Dec Node (in class " + classID + "): " +type.toPrint("") + " " + name +"("
				+parlstr+")"
				+declstr;
		//+body.toPrint(s+"  ") ;
	}
	
	@Override
	public Node typeCheck () {
		return null;
	}
	
	@Override
	public String codeGeneration() {
		return "";
	}
	
	@Override
	public String getID() {
		return name;
	}
}