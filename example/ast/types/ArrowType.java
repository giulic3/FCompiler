package ast;
import java.util.ArrayList;

import util.Environment;
import util.SemanticError;

/* function type */
public class ArrowTypeNode implements Node {
	
	private ArrayList<Node> parlist;
	private Node ret;
	
	public ArrowTypeNode (ArrayList<Node> p, Node r) {
		parlist = p;
		ret = r;
	}
	
	public String toPrint(String s) {
		String parlstr = "";
		
		for (Node par:parlist)
			parlstr += par.toPrint(s + " ");
		
		return s + "FunType\n" + parlstr + ret.toPrint(s + " ->") ;
	}
	
	public Node getRet() {
		return ret;
	}
	
	public ArrayList<Node> getParList () {
		return parlist;
	}
	
	@Override
	public HashSet<String> checkSemantics(Environment env) {
		// TODO Auto-generated method stub
		return new HashSet<String>();
	}
	
	//non utilizzato
	public Node typeCheck () {
		return null;
	}
	
	//non utilizzato
	public String codeGeneration() {
		return "";
	}
	
}  