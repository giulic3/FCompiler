package ast.types;

import ast.Node;
import utils.Environment;

import java.util.HashSet;

public class NullType extends ClassType {
	
	private String classID = null;
	
	/**
	 *
	 * Nodo indicativo del tipo NULL, è sottotipo di una classe perchè si intende che si
	 * possa assegnare null ad un oggetto per perdere il riferimento all'area di memoria
	 * che contiene l'istanza
	 *
	 * */
	//TODO: trovare soluzione migliore per il tipo null e controllare bene typechecking
	public NullType(){
		super(null, null, null,null, null);
	}
	
	public String toPrint(String indent) {
		return indent + "Null Type"; // TODO: to be updated
	}
	
	public HashSet<String> checkSemantics(Environment env) {
		// TODO: non dovrebbe servire
		return new HashSet<>();
	}
	
	public Node typeCheck() {
		// TODO: da implementare
		return this;
	}
	
	public String codeGeneration() {
		// TODO: da implementare
		return null;
	}
	
	public String getID() {
		return "Null Type";
	}
	
	public void setClassID(String id) {
		this.classID = id;
	}
	
	
}
