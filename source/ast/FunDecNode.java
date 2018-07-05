package ast;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Environment;
import utils.SemanticError;
import utils.SymbolTableEntry;

public class FunDecNode implements Node {

	private String id;
	private Node type;

	private ArrayList<Node> parlist;
	private ArrayList<Node> declist;
	private ArrayList<Node> body;

	public FunDecNode (String id, Node type, ArrayList<Node> declist, ArrayList<Node> parlist, ArrayList<Node> body) {
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
		
		HashMap<String, SymbolTableEntry> hm = env.getSymTable().get(env.getNestingLevel());
		env.setOffset(env.getOffset()-1);
		SymbolTableEntry entry = new SymbolTableEntry(env.getNestingLevel(),env.getOffset(),type); //separo introducendo "entry"
		
		if ( hm.put(id,entry) != null )
			res.add(new SemanticError("Fun id "+id+" already declared"));
		else {
			HashMap<String, SymbolTableEntry> fun_hm = new HashMap<String, SymbolTableEntry>();
			env.setNestingLevel(env.getNestingLevel()+1);
			env.getSymTable().add(fun_hm);
			
			ArrayList<Node> parTypes = new ArrayList<Node>();
			int paroffset=1;
			
			for (Node par : parlist) {
				VarNode arg = (VarNode) par;
				parTypes.add(arg.getType());
				if ( fun_hm.put(arg.getId(),new SymbolTableEntry(env.getNestingLevel(),paroffset++,arg.getType())) != null  )
					res.add(new SemanticError("Parameter id "+arg.getId()+" already declared"));
				
				res.addAll(par.checkSemantics(env));
			}
			for (Node dec : declist) {
				env.setOffset(env.getOffset()-2);
				res.addAll(dec.checkSemantics(env));
			}
			for (Node b : body) {
				res.addAll(b.checkSemantics(env));
			}
		}
		
		

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