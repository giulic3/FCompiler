package grammars.FOOL;

import ast.types.BoolType;
import ast.types.IntType;
import grammars.FOOL.FOOLParser.*;
import ast.*;

import java.util.ArrayList;

public class FOOLVisitorImpl extends FOOLBaseVisitor<Node> {
	
	public Node visitProg(ProgContext ctx) {
		
		ArrayList<Node> blocks = new ArrayList<>();
		
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


	@Override
	public Node visitFundec(FundecContext ctx) {

		//initialize @res with the visits to the type and its ID
		FunDecNode res = new FunDecNode(ctx.ID().getText(), visit(ctx.type()));

		//add argument declarations
		for(VardecContext vc : ctx.vardec())
			res.addPar( new VarNode(vc.ID().getText(), visit( vc.type() ), null));

		//add body
		//create a list for the nested var declarations
		ArrayList<Node> innerDec = new ArrayList<>();

		//check whether there are actually nested decs
		if (ctx.varasm() != null){
			//if there are visit each dec and add it to the @innerDec list
			for(VarasmContext dc : ctx.varasm())
				innerDec.add(visit(dc.vardec()));
		}

		Node body = null;
		//get the exp body or the stms body
		if (ctx.exp() != null)
			body = visit(ctx.exp());

		else
			body = visit(ctx.stms());


		//add the body and the inner declarations to the function
		res.addDecBody(innerDec, body);

		return res;


	}


	public Node visitExp(ExpContext ctx) {
		
		//this could be enhanced
		
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		if(ctx.right == null){
			//it is a simple expression
			return visit( ctx.left );
		}else{
			if (ctx.operator.getType() == FOOLLexer.EQ)
				return new EqNode(visit(ctx.left), visit(ctx.right));
			else if (ctx.operator.getType() == FOOLLexer.LEQ)
				return new LeqNode(visit(ctx.left), visit(ctx.right));
			else
				return new GeqNode(visit(ctx.left), visit(ctx.right));
		}
		//return null;
		
	}
	
	public Node visitOperand(OperandContext ctx) {
		if (ctx.right == null)
			return visit(ctx.left);
		else {
			if (ctx.operator.getType() == FOOLLexer.PLUS)
				return new PlusNode(visit(ctx.left), visit(ctx.right));
			else
				return new SubNode(visit(ctx.left), visit(ctx.right));
		}
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
		}
		else {

			switch (ctx.operator.getType()) {
				case FOOLLexer.EQ:
					return new EqNode(visit(ctx.left), visit(ctx.right));
				case FOOLLexer.LEQ:
					return new LeqNode(visit(ctx.left), visit(ctx.right));
				case FOOLLexer.GEQ:
					return new GeqNode(visit(ctx.left), visit(ctx.right));
				case FOOLLexer.AND:
					return new AndNode(visit(ctx.left), visit(ctx.right));
				case FOOLLexer.OR:
					return new OrNode(visit(ctx.left), visit(ctx.right));
				default:
					return null; // TODO: temporary
			}
		}
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
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch));
		else
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch));
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
	
	
	@Override
	public Node visitFunExp(FunExpContext ctx) {
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		res = new FunExpNode(ctx.ID().getText(), args);
		
		return res;
	}
	
	@Override
	public Node visitMethodExp(MethodExpContext ctx) {
		
		/* control LPAR if is class field */
		
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		res = new MethodNode(ctx.object.getText(), ctx.methodName.getText(), args, true);
		
		return res;
	}
	
	@Override
	public Node visitNewExp(NewExpContext ctx) {
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		res = new NewExpNode(ctx.className.getText(), args);
		
		return res;
	}
	
	@Override
	public Node visitVarStmAssignment(FOOLParser.VarStmAssignmentContext ctx){
		Node res = new AssignmentNode(ctx.ID().toString(), visit(ctx.exp()));
		return res;
	}
	
	@Override
	public Node visitLetInStms(FOOLParser.LetInStmsContext ctx){
		
		Node res;
		
		ArrayList<Node> decs = new ArrayList<>();
		
		for(DecContext dec : ctx.let().dec())
			decs.add(visit(dec));
			
		res = new BlockLetInStmsNode(decs, visit(ctx.stms()));
		
		return res;
		
	}
	
	
	@Override
	public Node visitIfStm(FOOLParser.IfStmContext ctx){

		if (ctx.elseBranch == null)
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch));
		else
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch));
	}
	
	@Override
	public Node visitVarasm(FOOLParser.VarasmContext ctx){
		
		Node typeNode = visit(ctx.vardec().type());
		
		//visit the exp
		Node expNode = visit(ctx.exp());
		
		//build the varNode
		return new VarNode(ctx.vardec().ID().getText(), typeNode, expNode);
	}
	
	@Override
	public Node visitType(FOOLParser.TypeContext ctx){
		if(ctx.getText().equals("int"))
			return new IntType();
		else if(ctx.getText().equals("bool"))
			return new BoolType();
		
		//this will never happen thanks to the parser
		return null;
	}
}