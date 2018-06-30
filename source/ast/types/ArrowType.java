package ast.types;
import java.util.ArrayList;

import utils.Environment;

/* function type */
public class ArrowType implements BaseType {
	
	private ArrayList<BaseType> parlist;
	private BaseType ret;
	
	public ArrowType (ArrayList<BaseType> p, BaseType r) {
		parlist = p;
		ret = r;
	}
	
	public String toPrint() {
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


	
}  