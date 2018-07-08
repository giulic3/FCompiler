package ast;

import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockClassDecNode implements Node {
	private String id;
	private String ext;
	private ArrayList<Node> fields;
	private ArrayList<Node> methods;
	private ParserRuleContext ctx;
	
	
	/* se non ci sono parametri par sarà null, se non è estesa ext sarà null */
	
	public BlockClassDecNode(String id, String ext, ArrayList<Node> fields, ArrayList<Node> methods, ParserRuleContext ctx) {
		this.id=id;
		this.ext=ext;
		this.fields=fields;
		this.methods=methods;
		this.ctx=ctx;
	}
	
	public String toPrint(String s){
		String msg = "\n"+s+"Class Dec Node: " + this.id;
		if(ext!=null){
			msg+=" extends " + ext;
		}
		msg+=" {";
		if(fields!=null) {
			msg+="\n"+s+"\tParams:";
			for (Node b : fields) {
				msg += "\n " + s + b.toPrint("\t\t");
			}
		}
		msg += "\n"+s+"\tMethods:";
		for (Node b : methods) {
			msg += "\n " + s + b.toPrint("\t\t");
		}
		msg+="\n"+s+"}";
		return msg;
	}
	
	public ArrayList<Node> getMethods() {
		return methods;
	}
	
	public ArrayList<Node> getFields() {
		return fields;
	}
	
	public Node typeCheck(){return null;}
	
	public String codeGeneration(){return null;}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		
		//create result list
		ArrayList<SemanticError> res = new ArrayList<>();
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),this); //separo introducendo "entry"
		
		if ( hm.put("Class$"+id,entry) != null )
			res.add(new SemanticError("Class id "+id+" already declared at line: "+ctx.start.getLine()+":"+ctx.start.getCharPositionInLine()+"\n"));
		else {
			env.pushScope();
			
			HashMap<String, SymbolTableEntry> fun_hm = env.getSymTable().get(env.getNestingLevel());
			ArrayList<Node> parTypes = new ArrayList<Node>();
			int paroffset=1;
			
			for (Node par : fields) {
				VarNode arg = (VarNode) par;
				parTypes.add(arg.getType());
				if ( fun_hm.put("Class$"+id+"$"+arg.getId(),new SymbolTableEntry(env.getNestingLevel(),paroffset++,arg.getType())) != null  )
					res.add(new SemanticError("Parameter id "+arg.getId()+" already declared at line: "+arg.getCtx().start.getLine()+":"+arg.getCtx().start.getCharPositionInLine()+"\n"));
			}
			
			for (Node dec : methods) {
				env.setOffset(env.getOffset()-2);
				res.addAll(dec.checkSemantics(env));
			}
			env.popScope();
		}
		return res;
	}
	
	public String getID() {
		return id;
	}
}

