package grammars.FOOL;

import grammars.FOOL.FOOLParser.*;
import ast.*;

import java.util.ArrayList;

public class FOOLVisitorImpl extends FOOLBaseVisitor<Node> {
	
	public Node visitProg(ProgContext ctx) {
		
		ArrayList<Node> blocks = new ArrayList<Node>();
		
		for (int i=0; i < ctx.block().size(); i++) {
			blocks.add(visit(ctx.block(i)));
		}
		
		//simply return the result of the visit to the inner exp
		return new ProgNode(blocks);
		
	}
	
	public Node visitSingleExp(SingleExpContext ctx) {
		
		//simply return the result of the visit to the inner exp
		return new BlockSingleExpNode(visit(ctx.exp()));
		
	}
	
	public Node visitExp(ExpContext ctx) {
		
		//this could be enhanced
		
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			if (ctx.operator.getType() == FOOLLexer.PLUS)
				return new PlusNode(visit(ctx.left), visit(ctx.right));
			else
				return new SubNode(visit(ctx.left), visit(ctx.right));
		}
		//return null;
		
	}
	
	public Node visitTerm(TermContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if (ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		} else{
			//it is a binary expression, you should visit left and right
			if (ctx.operator.getType() == FOOLLexer.TIMES)
				return new TimesNode(visit(ctx.left), visit(ctx.right));
			else
				return new DivNode(visit(ctx.left), visit(ctx.right));

		}

	}
	
	public Node visitFactor(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}/*else{
			//it is a binary expression, you should visit left and right
			return new EqualNode(visit(ctx.left), visit(ctx.right));
		}*/
		return null;
	}
	
	public Node visitAtom(FactorContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}/*else{
			//it is a binary expression, you should visit left and right
			//return new EqualNode(visit(ctx.left), visit(ctx.right));
		}*/
		return null;
	}
	
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal
		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntValNode(Integer.parseInt(ctx.INTEGER().getText()));
	}
	
	public Node visitIfExp(FOOLParser.IfExpContext ctx){
		
		if (ctx.elseBranch == null)
			return new IfExpNode(visit(ctx.cond), visit(ctx.thenBranch));
		else
			return new IfExpNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch));
	}
	
	public Node visitBoolVal(FOOLParser.BoolValContext ctx){
		return new BoolValNode(Boolean.parseBoolean(ctx.BOOLVAL().getText()));
	}

	@Override
	public Node visitBaseExp(BaseExpContext ctx) {

		//this is actually nothing in the sense that for the ast the parenthesis are not relevant
		//the thing is that the structure of the ast will ensure the operational order by giving
		//a larger depth (closer to the leafs) to those expressions with higher importance

		//this is actually the default implementation for this method in the FOOLBaseVisitor class
		//therefore it can be safely removed here

		return visit (ctx.exp());

	}

	@Override
	public Node visitVarExp(VarExpContext ctx) {

		//this corresponds to a variable access
		return new IdNode(ctx.ID().getText());

	}



}