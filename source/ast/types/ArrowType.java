package ast.types;
import java.util.ArrayList;

import ast.Node;
import utils.Environment;
import utils.SemanticError;

/* function type */
public class ArrowType implements Node {
	
	private ArrayList<Node> parlist;
	private BaseType ret;
	
	public ArrowType (ArrayList<Node> p, BaseType r) {
		parlist = p;
		ret = r;
	}
	
	public String toPrint(String indent) {
		String parlstr = "";
		
		for (BaseType par:parlist)
			parlstr += par.toPrint();
		
		return "ArrowType\n" + parlstr;// + ret.toPrint(" ->") ;
	}
	
	public BaseType getRet() {
		return ret;
	}
	
	public ArrayList<BaseType> getParList () {
		return parlist;
	}


	public TypeEnum getType() {
		return TypeEnum.ARROW;
	}
	
	public BaseType typeCheck() {
		return null;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public ArrayList<SemanticError> checkSemantics(Environment env) {
		return null;
	}

	
}  