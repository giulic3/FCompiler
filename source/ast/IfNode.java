package ast;


import ast.types.BaseType;
import ast.types.BoolType;
import grammars.FOOL.FOOLParser;
import utils.Environment;
import utils.SemanticError;

import java.util.ArrayList;

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

			String ifExp = s + "If Node:\n" +
					cond.toPrint(s + "\tCond: ") + "\n" +
					s + "\tThen Branch:\n" + th.toPrint(s + "\t\t");
			if (el != null)
				ifExp += "\n" + s + "\tElse Branch:\n" + el.toPrint(s + "\t\t");

			return ifExp;
		}
		/* ifStm */
		else {

			String thStmsString = "";
			String elStmsString = "";

			for (Node stm : thStms) {
				thStmsString += stm.toPrint("")+"\n";
			}

			for (Node stm : elStms) {
				elStmsString += stm.toPrint("")+"\n";
			}

			String ifStm = s + "If Node:\n" +
					cond.toPrint(s + "\tCond: ") + "\n" +
					s + "\tThen Branch:\n" + thStmsString;
			if (elStms != null)
				ifStm += "\n" + s + "\tElse Branch:\n" + elStmsString;

			return ifStm;
		}
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		//create the result
		ArrayList<SemanticError> res = new ArrayList<SemanticError>();
		
		return res;
	}
	
	@Override
	public BaseType typeCheck() {
		return new BoolType();
	}
	
	@Override
	public String codeGeneration() {
		return null;
	}
	
}
