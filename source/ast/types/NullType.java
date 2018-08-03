package ast.types;

import ast.Node;
import utils.Environment;
import java.util.HashSet;

public class NullType extends ClassType {
	
	/**
	 *
	 * Nodo indicativo del tipo NULL, è sottotipo di una classe perchè si intende che si
	 * possa assegnare null ad un oggetto per perdere il riferimento all'area di memoria
	 * che contiene l'istanza
	 *
	 * */
	public NullType(){
		super(null, null, null,null, null);
	}
	
	public String toPrint(String indent) {
		return indent + "Null Type";
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		return this;
	}
	
	public String codeGeneration() {
		return null;
	}
	
	public String getID() {
		return "Null Type";
	}
	
	
}
