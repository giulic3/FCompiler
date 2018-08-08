package ast.types;

import java.util.ArrayList;
import java.util.HashSet;
import ast.Node;
import utils.Environment;


public class FunType implements Node {
	
	private ArrayList<Node> parTypesList;
	private Node returnType;
	
	/**
	 *
	 * Nodo indicativo del tipo di una Funzione, notazione (T1xT2x.....xTn) --> T.
	 * Dove T1,T2,...,Tn sono i tipi dei rispettivi parametri mentre T è
	 * il tipo di ritorno della funzione
	 *
	 * */
	public FunType(ArrayList<Node> p, Node r) {
		parTypesList = p;
		returnType = r;
	}
	
	public Node copyInstance() {
		ArrayList<Node> par = new ArrayList<>(this.parTypesList);
		for (Node n: this.parTypesList)
			par.add(n.copyInstance());
		return new FunType(par, this.returnType.copyInstance());
	}
	
	public Node getReturnType() {
		return returnType;
	}
	
	public ArrayList<Node> getParList () {
		return parTypesList;
	}
	
	public String toPrint(String indent) {
		StringBuilder parlstr = new StringBuilder();
		parlstr.append("(");
		
		for (Node par:parTypesList)
			parlstr.append(par.toPrint(" "));

		parlstr.append(")").append(" -> ").append(returnType.toPrint(""));

		return parlstr.toString();
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return returnType;
	}
	
	public String codeGeneration() {
		return null;
	}

	public String getID() {
		return null;
	}
	
	public void updateReturnType(Node rt) {
		this.returnType = rt;
	}
	
}  