package ast;

import ast.types.ClassType;
import ast.types.FunType;
import org.antlr.v4.runtime.ParserRuleContext;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import utils.TypeCheckException;

import java.util.ArrayList;
import java.util.HashSet;

/* function invocation */

public class FunExpNode implements Node {
	
	protected String id;
	protected ArrayList<Node> args;
	protected SymbolTableEntry entry = null;
	protected int callNestingLevel;
	protected boolean isExp;
	protected ParserRuleContext ctx;
	protected String classID=null;
	
	
	public FunExpNode(String ID, ArrayList<Node> args, boolean isExp, ParserRuleContext ctx){
		this.ctx=ctx;
		this.id = ID;
		this.args=args;
		this.isExp = isExp;
	}
	
	public void setClassID(String classID){
		this.classID=classID;
	}
	
	
	public String toPrint(String s) {
		String msg = s+"Function Call Node: " + this.id + "(";
		
		if (this.args != null && !this.args.isEmpty()) {
			for (Node b : this.args) {
				msg += "\n " + s + b.toPrint("\t");
			}
			msg += "\n" + s + ")";
		} else
			msg += ")";
		
		return msg;
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<String>();
		
		if (env.getDefiningClass() != null) classID = env.getDefiningClass();
		
		ArrayList<Node> methods = new ArrayList<>();
		
		if(classID != null){
			SymbolTableEntry classEntry = env.getClassEntry(classID);
			ClassType  classType= (ClassType) classEntry.getType() ;
			methods = classType.getMethodsList(true, false);
			Node found = null;
			
			for(Node m : methods){
				if(m.getID().equals(id)) found=m;
			}
			
			if (found != null) {
				this.entry = ((MethodDecNode) found).getSTEntry();
				
				FunType funType = (FunType) this.entry.getType();
				if (funType.getParList().size() != args.size())
					res.add("Method " + this.id + " call with wrong number of parameters is not allowed at line "
							+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
				else
					for (Node arg : args)
						res.addAll(arg.checkSemantics(env));
			}
			else {
				res.add("Method " + this.id + " not declared at line "
						+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
		}
		else {
			SymbolTableEntry entry = env.getActiveDec("Function$" + id);
			if (entry == null)
				res.add("Fun " + id + " not declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			else {
				this.entry = entry;
				this.callNestingLevel = env.getNestingLevel();
				
				for (Node arg : args)
					res.addAll(arg.checkSemantics(env));
				
				FunType funType = (FunType) this.entry.getType();
				if (funType.getParList().size() != args.size())
					res.add("Function " + this.id + " call with wrong number of parameters is not allowed at line "
							+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
		}
		return res;
	}
	
	public Node typeCheck() throws Exception{
		
		FunType funType = (FunType)entry.getType();
		ArrayList<Node> parlisttype = funType.getParList();
		
		for(int i=0; i<parlisttype.size(); i++) {
			if(!Helpers.subtypeOf(args.get(i).typeCheck(), parlisttype.get(i)))
				throw new TypeCheckException("Fun Call (par: " + args.get(i).toPrint(""), ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return funType.getReturnType();
	}
	
	public String codeGeneration() {
		// TODO: da controllare
		String parAssembly = "";
		for (int i = args.size()-1; i >= 0; i--)
			parAssembly += args.get(i).codeGeneration();
		
		if (classID != null) {
			return  "lfp\n" +
					parAssembly +
					"lfp\n" +
					"lw\n" +
					"cp\n" +
					"lw\n" +
					"push " + entry.getOffset() + "\n" +
					//"lw\n" +
					//"lfp\n" +
					"add\n" +
					"jsmeth\n" +
					"js\n";
		}
		
		return  "lfp\n" +
				parAssembly +
				"lfp\n" +
				Helpers.getActivationRecordCode(callNestingLevel, entry.getNestingLevel()) +
				"push " + entry.getOffset() + "\n" +
				"lfp\n" +
				Helpers.getActivationRecordCode(callNestingLevel, entry.getNestingLevel()) +
				"add\n" +
				"lw\n" +
				"js\n";
	}
	
	public String getID() {
		return id;
	}
	
}