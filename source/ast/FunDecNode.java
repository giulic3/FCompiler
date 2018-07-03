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

	public FunDecNode (String id, Node type, ArrayList<Node> declist, ArrayList<Node> parlist, Node body) {
		this.id = id;
		this.type = type;
		this.declist = declist;
		this.parlist=parlist;
		this.body = body;
	}
	
	@Override
	public ArrayList<SemanticError> checkSemantics(Environment env) {

		//create result list
		ArrayList<SemanticError> res = new ArrayList<>();


		return res;
	}

	public String toPrint(String s) {

		String parlstr = "";
		String declstr = "";

		if (parlist!=null && !parlist.isEmpty()) {
			for (Node par : parlist)
				parlstr += "\n" + par.toPrint(s + "\t\t");
			parlstr+="\n"+s+"\t";
		}

		if (declist != null) {
			declstr = s+"\tFun Decs:";
			for (Node dec : declist)
				declstr += "\n" + dec.toPrint(s + "\t\t");
		}

		return s+"Fun Dec Node: " +type.toPrint("") + " " + id +"("
				+parlstr+")\n"
				+declstr;
				//+body.toPrint(s+"  ") ;
	}

	//valore di ritorno non utilizzato
	public Node typeCheck () {

		return null;
	}

	public String codeGeneration() {

		return "";
	}

}