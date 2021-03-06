package ast;

import ast.types.ClassType;
import ast.types.FunType;
import ast.types.VoidType;
import utils.Environment;
import utils.Helpers;
import utils.SymbolTableEntry;
import utils.TypeCheckException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class MethodDecNode extends FunDecNode {
	
	private String classID;
	private FunDecNode funObj; // Necessary to copy instance of MethodDec
	private ArrayList<Node> inheritedMethods;
	
	public MethodDecNode(FunDecNode funNode, String classID) {
		super(funNode.name, funNode.type, funNode.decList, funNode.parList, funNode.body, funNode.ctx);
		this.funObj = funNode;
		this.classID = classID;
	}
	
	public Node copyInstance() {
		MethodDecNode copy = new MethodDecNode((FunDecNode)this.funObj.copyInstance(), this.classID);
		if (this.inheritedMethods != null) {
			copy.inheritedMethods = new ArrayList<>(this.inheritedMethods);
			for (Node n : this.inheritedMethods)
				copy.inheritedMethods.add(n.copyInstance());
		}
		return copy;
	}
	
	public String toPrint(String s) {
		
		StringBuilder parlstr = new StringBuilder();
		StringBuilder declstr = new StringBuilder();
		
		if (parList != null && !parList.isEmpty()) {
			for (Node par : parList)
				parlstr.append("\n").append(par.toPrint(s + "\t\t"));
			
			parlstr.append("\n").append(s).append("\t");
		}
		
		if (decList != null && !decList.isEmpty()) {
			declstr.append("\n").append(s).append("\tFun Decs:");
			
			for (Node dec : decList)
				declstr.append("\n").append(dec.toPrint(s + "\t\t"));
		}
		
		return  s + "Method Dec Node (in class " + classID + "): " +
				type.toPrint("") + " " + name + "(" +
				parlstr.toString() + ")" +
				declstr.toString();
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		//create result list
		HashSet<String> res = new HashSet<>();
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		
		if (type instanceof FunType && ((FunType)type).getReturnType() instanceof ClassType) {
			Node rt = ((FunType)type).getReturnType();
			ClassType curType = (ClassType)rt;
			SymbolTableEntry classEntry = env.getClassEntry(curType.getID());
			((FunType)type).updateReturnType(classEntry.getType());
		}
		
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.increaseOffset(),type);
		entry.setClassName(classID);
		
		String methodID = "Class$" + classID +"$m$"+ name;
		
		// this handles methods
		SymbolTableEntry classEntry = env.getClassEntry(this.classID);
		if (classEntry != null) {
			ClassType classNode = (ClassType)classEntry.getType();
			inheritedMethods = classNode.getMethodsList(true, true);
			
			for (Node m:inheritedMethods) {
				MethodDecNode method = (MethodDecNode)m;
				// if current method has same name of one inherited, overriding should be checked; if is overriding (same parameters and return type) is ok otherwise error
				if (method.getID().equals(this.name)) {
					if (method.parList.size() != this.parList.size())
						res.add("Method overloading (wrong number of parameters) '" + this.name + "' is not allowed at line "
								+ ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
					else {
						// current method is overriding a superclass method, so we set the offset of the superclass
						entry.setOffset(method.getSTEntry().getOffset());
						env.decreaseOffset();
					}
				}
			}
		}
		
		if (!env.getFunSecondCheck()) {
			if (hm.put(methodID, entry) != null)
				res.add("Method name " + name + " already declared at line: " + ctx.start.getLine() + ":" + ctx.start.getCharPositionInLine() + "\n");
		}
		
		env.pushScope();
		int currentOffset = env.getOffset();
		
		env.setOffset(1);
		for (Node par : parList) {
			VarNode arg = (VarNode)par;
			res.addAll(arg.checkSemantics(env));
		}
		env.setOffset(currentOffset);
		
		this.funEntry = entry;
		
		int currOffset = env.getOffset();

		if (!decList.isEmpty()) env.setOffset(-2);
		
		if (env.getFunSecondCheck())
			for (Node dec : decList)
				res.addAll(dec.checkSemantics(env));
		
		env.setOffset(currOffset);
		
		if (env.getFunSecondCheck())
			for (Node b : body) {
				if(b instanceof FunExpNode)
					((FunExpNode)b).setClassID(classID);
				
				res.addAll(b.checkSemantics(env));
			}
		
		env.popScope();
		
		return res;
	}
	
	public Node typeCheck () throws Exception {
		for (Node m: inheritedMethods) {
			MethodDecNode method = (MethodDecNode) m;
			if (method.getID().equals(this.name)) {
				FunType currentType = (FunType)this.type;
				FunType methodType = (FunType)method.getSTEntry().getType();
				
				for (int i = 0; i < currentType.getParList().size(); i++)
					if (!Helpers.subtypeOf(methodType.getParList().get(i), currentType.getParList().get(i)))
						throw new TypeCheckException("Method Dec (parameter overloading not allowed)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
				
				if (!Helpers.subtypeOf(currentType.getReturnType(), methodType.getReturnType()))
					throw new TypeCheckException("Method Dec (return type overloading not allowed)", ctx.start.getLine(), ctx.start.getCharPositionInLine());
			}
		}
		
		for (Node d : decList) d.typeCheck();
		for (Node b : body) b.typeCheck();
		
		// check if the type of the last stms or exp in body is subtype of the function return type
		if (!Helpers.subtypeOf(body.get(body.size()-1).typeCheck(), ((FunType)type).getReturnType()))
			throw new TypeCheckException("Method Return", ctx.start.getLine(), ctx.start.getCharPositionInLine());
		
		return ((FunType)type).getReturnType();
	}
	
	public String codeGeneration() {
		StringBuilder decAssembly = new StringBuilder();
		StringBuilder decPopAssembly = new StringBuilder();
		StringBuilder parPopAssembly = new StringBuilder();
		StringBuilder bodyAssembly = new StringBuilder();
		String funLabel = Helpers.getDispatchTable(classID).get(funEntry.getOffset());
		
		FunType funcType = (FunType)type;
		String storeRetVal = funcType.getReturnType() instanceof VoidType ? "" : "srv\n";
		String loadRetVal = funcType.getReturnType() instanceof VoidType ? "" : "lrv\n";
		
		for (Node dec: decList) {
			decAssembly.append(dec.codeGeneration());
			decPopAssembly.append("pop\n");
		}
		
		for (Node par: parList)
			parPopAssembly.append("pop\n");
		
		for (Node n: body)
			bodyAssembly.append(n.codeGeneration());
		
		String funcCode = funLabel + ":\n" +
				"cfp\n" +
				"lra\n" +
				decAssembly.toString() +
				bodyAssembly.toString() +
				storeRetVal +
				decPopAssembly.toString() +
				"sra\n" +
				"pop\n" +
				parPopAssembly.toString() +
				"sfp\n" +
				loadRetVal +
				"lra\n" +
				"js\n";
		Helpers.appendFuncAssembly(funcCode);
		
		return funLabel + "\n";
	}
	
	public String getID() {
		return name;
	}
}
