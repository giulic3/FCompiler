package ast;


import ast.types.BoolType;
import ast.types.VoidType;
import utils.Environment;
import utils.Helpers;
import java.util.ArrayList;
import java.util.HashSet;

/* used both for ifExp and ifStm*/
public class IfNode implements Node {

	private Node cond;
	private Node th;
	private Node el;
	private ArrayList<Node> thStms;
	private ArrayList<Node> elStms;

	// IfExp
	public IfNode(Node cond, Node th, Node el) {
		this.cond = cond;
		this.th = th;
		this.el = el;
		this.thStms = new ArrayList<>();
		this.elStms = new ArrayList<>();
	}

	/* ifStm */
	public IfNode(Node cond, ArrayList<Node> thStms) {
		this.cond = cond;
		this.th = null;
		this.el = null;
		this.thStms = thStms;
		this.elStms = new ArrayList<>();;
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
			ifExp += "\n" + s + "\tElse Branch:\n" + el.toPrint(s + "\t\t");

			return ifExp;
		}
		/* ifStm */
		else {
			String thStmsString = "";
			String elStmsString = "";

			for (Node stm : thStms)
				thStmsString += "\n" + stm.toPrint(s+"\t\t");

			String ifStm = s + "If Stms Node:\n" + s + "\tCond:\n" +
					cond.toPrint(s+"\t\t") + "\n" +
					s + "\tThen Branch:" + thStmsString;
			
			for (Node stm : elStms)
				elStmsString += "\n" + stm.toPrint(s + "\t\t");
			if (!elStms.isEmpty()) ifStm += "\n" + s + "\tElse Branch:" + elStmsString;

			return ifStm;
		}
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		//create the result
		HashSet<String> res = new HashSet<String>();
		
		res.addAll(cond.checkSemantics(env));
		
		// IfExp
		if (th != null) {
			res.addAll(th.checkSemantics(env));
			res.addAll(el.checkSemantics(env));
		}
		// IfStms
		else {
			for (Node stm: thStms)
				res.addAll(stm.checkSemantics(env));
			
			for (Node stm: elStms)
				res.addAll(stm.checkSemantics(env));
		}
		
		return res;
	}
	
	@Override
	public Node typeCheck() throws Exception {
		
		if(!Helpers.subtypeOf(cond.typeCheck(), new BoolType())){
			throw new Exception("condition is not boolean");
		}
		
		// IfExp
		if (th != null) {
			if(Helpers.subtypeOf(th.typeCheck(),el.typeCheck())){
				return el.typeCheck();
			}
			if(Helpers.subtypeOf(el.typeCheck(),th.typeCheck())){
				return th.typeCheck();
			}
			throw new Exception("Incompatible expression types");
		}
		// IfStms
		else {
			for(Node ths : thStms)
				if(!Helpers.subtypeOf(ths.typeCheck(), new VoidType()))
					throw new Exception("not void statement");
			
			for(Node els : elStms)
				if(!Helpers.subtypeOf(els.typeCheck(), new VoidType()))
					throw new Exception("not void statement");
					
			return new VoidType();
		}
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
