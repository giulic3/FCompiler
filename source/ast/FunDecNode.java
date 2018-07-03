package ast;
import java.util.ArrayList;

import utils.Environment;
import utils.SemanticError;

public class FunDecNode implements Node {

	private String id;
	private Node type;

	private ArrayList<Node> parlist = new ArrayList<Node>();
	private ArrayList<Node> declist;
	private Node body;

	public FunDecNode (String id, Node type) {
		this.id = id;
		this.type = type;
	}

	public void addDecBody (ArrayList<Node> declist, Node body) {
		this.declist = declist;
		this.body = body;
	}

	public void addPar (Node p) {
		parlist.add(p);
	}

	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<>();


		return res;
	}

	// TODO: fix indentation
	public String toPrint(String s) {

		String parlstr = "";
		String declstr = "";

		if (parlist != null)
			for (Node par : parlist)
				parlstr += par.toPrint(s+"  ");

		if (declist != null)
			for (Node dec : declist)
				declstr += dec.toPrint(s+"  ");

		return s+"Fun:" + id +"\n"
				+type.toPrint(s+"  ")
				+parlstr
				+declstr
				+body.toPrint(s+"  ") ;
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {

		return null;
	}

	public String codeGeneration() {

		return "";
	}

}