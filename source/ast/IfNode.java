package ast;


import ast.types.BoolType;
import utils.Environment;
;

import java.util.ArrayList;
import java.util.HashSet;

/* used both for ifExp and ifStm*/
public class IfNode implements Node {

	private Node cond;
	private Node th;
	private Node el;
	private ArrayList<Node> thStms;
	private ArrayList<Node> elStms;

	/* ifExp */
	public IfNode(Node cond, Node th) {
		this.cond = cond;
		this.th = th;
		this.el = null;
		this.thStms = null;
		this.elStms = null;

	}

	public IfNode(Node cond, Node th, Node el) {
		this.cond = cond;
		this.th = th;
		this.el = el;
		this.thStms = null;
		this.elStms = null;
	}

	/* ifStm */
	public IfNode(Node cond, ArrayList<Node> thStms) {
		this.cond = cond;
		this.th = null;
		this.el = null;
		this.thStms = thStms;
		this.elStms = null;
	}

	public IfNode(Node cond, ArrayList<Node> thStms, ArrayList<Node> elStms) {
		this.cond = cond;
		this.th = null;
		this.el = null;
		this.thStms = thStms;
		this.elStms = elStms;
	}
	
	@Override
	public String toPrint(String s) {

		/* ifExp */
		if (th != null) {

			String ifExp = s + "If Exp Node:\n" + s + "\tCond:\n" +
					cond.toPrint(s + "\t\t") + "\n" +
					s + "\tThen Branch:\n" + th.toPrint(s + "\t\t");
			if (el != null)
				ifExp += "\n" + s + "\tElse Branch:\n" + el.toPrint(s + "\t\t");

			return ifExp;
		}
		/* ifStm */
		else {

			String thStmsString = "";

			for (Node stm : thStms) {
				thStmsString += "\n" + stm.toPrint(s+"\t\t");
			}

			String ifStm = s + "If Stms Node:\n" + s + "\tCond:\n" +
					cond.toPrint(s+"\t\t") + "\n" +
					s + "\tThen Branch:" + thStmsString;
			
			if (elStms != null) {
				String elStmsString = "";
				
				for (Node stm : elStms) {
					elStmsString += "\n" + stm.toPrint(s + "\t\t");
				}
				ifStm += "\n" + s + "\tElse Branch:" + elStmsString;
			}

			return ifStm;
		}
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();
		
		res.addAll(cond.checkSemantics(env));
		
		// IfExp
		if (th != null)
			res.addAll(th.checkSemantics(env));
		// IfStms
		else {
			for (Node stm: thStms)
				res.addAll(stm.checkSemantics(env));
		}
		
		if (el != null)
			res.addAll(el.checkSemantics(env));
		
		if (elStms != null)
			for (Node stm: elStms)
				res.addAll(stm.checkSemantics(env));
		
		return res;
	}
	
	@Override
	public Node typeCheck() {
		return new BoolType();
	}
	
	@Override
	public String codeGeneration() {
		return null;
	}
	
	// Method to retrieve string identifier of an object
	// In nodes where identifier is not significant, null is returned
	public String getID() {
		return null;
	}
}
