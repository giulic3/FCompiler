package FOOL;

import FOOLBaseVisitor;
import FOOLLexer;
import FOOLParser;
import ast.*;
import ast.type.*

import java.util.ArrayList;

public class FOOLVisitorImpl extends FOOLBaseVisitor<Node> {
	
	public Node visitSingleExp(SingleExpContext ctx) {
		
		//simply return the result of the visit to the inner exp
		return visit(ctx.exp());
		
	}
	
	public Node visitExp(ExpContext ctx) {
		
		//this could be enhanced
		
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			//return new PlusNode(visit(ctx.left), visit(ctx.right));
		}
		
	}
	
	public Node visitTerm(TermContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			//return new MultNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	public Node visitFactor(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			//return new EqualNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	public Node visitAtom(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			//it is a binary expression, you should visit left and right
			//return new EqualNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal
		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntNode(Integer.parseInt(ctx.INTEGER().getText()));
	}
	
	
	
	
	
}