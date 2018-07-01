package ast;


import grammars.FOOL.FOOLParser;

public class IfNode extends Node {
	
	private Node cond;
	private Node th;
	private Node el;
	
	public IfNode(Node cond, Node th, Node el) {
		this.cond = cond;
		this.th = th;
		this.el = el;
	}
}{
}
