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
	
	public Node copyInstance() {
		ParserRuleContext ctx = new ParserRuleContext();
		ctx.copyFrom(this.ctx);
		ArrayList<Node> argsCopy = new ArrayList<>(this.args);
		for (Node n: this.args)
			argsCopy.add(n.copyInstance());
		FunExpNode copy = new FunExpNode(this.id, argsCopy, this.isExp, ctx);
		copy.entry = SymbolTableEntry.copyInstance(this.entry);
		copy.callNestingLevel = this.callNestingLevel;
		copy.classID = this.classID;
		return copy;
	}
	
	public void setClassID(String classID){
		this.classID=classID;
	}
	
	
	public String toPrint(String s) {
		StringBuilder msg = new StringBuilder();
		msg.append(s).append("Function Call Node: ").append(this.id).append("(");
		
		if (this.args != null && !this.args.isEmpty()) {
			for (Node b : this.args)
				msg.append("\n").append(s).append(b.toPrint("\t"));
			msg.append("\n").append(s).append(")");
		}
		else
			msg.append(")");
		
		return msg.toString();
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		HashSet<String> res = new HashSet<>();
		
		if (env.getDefiningClass() != null) classID = env.getDefiningClass();
		
		if (classID != null) {
			SymbolTableEntry classEntry = env.getClassEntry(classID);
			ClassType  classType= (ClassType) classEntry.getType() ;
			ArrayList<Node> methods = classType.getMethodsList(true, false);
			Node found = null;
			
			for (Node m : methods)
				if (m.getID().equals(id)) found = m;
			
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
					res.add("Function call " + this.id + "() with wrong number of parameters is not allowed at line "
							+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
			}
		}
		return res;
	}
	
	public Node typeCheck() throws Exception{
		FunType funType = (FunType)entry.getType();
		ArrayList<Node> parlisttype = funType.getParList();
		
		for (int i=0; i<parlisttype.size(); i++)
			if(!Helpers.subtypeOf(args.get(i).typeCheck(), parlisttype.get(i)))
				throw new TypeCheckException("Fun Call (par: " + args.get(i).toPrint(""), ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return funType.getReturnType();
	}
	
	public String codeGeneration() {
		StringBuilder parAssembly = new StringBuilder();
		for (int i = args.size()-1; i >= 0; i--)
			parAssembly.append(args.get(i).codeGeneration());
		
		if (classID != null) {
			return  "lfp\n" +
					parAssembly.toString() +
					"lfp\n" +
					"lw\n" +
					"cp\n" +
					"lw\n" +
					"push " + entry.getOffset() + "\n" +
					"add\n" +
					"jsmeth\n" +
					"js\n";
		}
		
		return  "lfp\n" +
				parAssembly.toString() +
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
	
	public SymbolTableEntry getSTEntry() {
		return this.entry;
	}
	
}