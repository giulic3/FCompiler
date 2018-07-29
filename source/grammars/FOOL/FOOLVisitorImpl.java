package grammars.FOOL;

import ast.types.*;
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
/*
	public Node visitLetInExp(LetInExpContext ctx) {

		Node res;

		ArrayList<Node> decs = new ArrayList<>();

		for(DecContext dec : ctx.let().dec())
			decs.add(visit(dec));

		res = new BlockLetInExpNode(decs, visit(ctx.exp()));

		return res;
	}
	
	public Node visitSingleExp(SingleExpContext ctx) {
		
		//simply return the result of the visit to the inner exp
		return new BlockSingleExpNode(visit(ctx.exp()));
		
	}
*/

	@Override
	public Node visitFundec(FundecContext ctx) {

		//initialize @res with the visits to the type and its ID
		// res = new FunDecNode(ctx.ID().getText(), visit(ctx.type()));
		ArrayList<Node> pars = new ArrayList<>();
		ArrayList<Node> parTypes = new ArrayList<>();
		
		//add argument declarations
		for(VardecContext par : ctx.vardec()) {
			VarNode parNode = (VarNode)visit(par);
			pars.add(parNode);
			parTypes.add(parNode.getType());
			//res.addPar( new VarNode(vc.ID().getText(), visit( vc.type() ), null));
		}

		//add body
		//create a list for the nested var declarations
		ArrayList<Node> innerDec = new ArrayList<>();

		//check whether there are actually nested decs
		if (ctx.varasm() != null){
			//if there are visit each dec and add it to the @innerDec list
			for(VarasmContext dc : ctx.varasm())
				innerDec.add(visit(dc));
		}

		ArrayList<Node> body = new ArrayList<>();
		
		if (ctx.stms() != null)
			for (StmContext stm: ctx.stms().stm())
				body.add(visit(stm));
		
		if (ctx.exp() != null)
			body.add(visit(ctx.exp()));

		Node returnType = visit(ctx.type());

		FunType funType = new FunType(parTypes, returnType);
		
		return new FunDecNode(ctx.ID().getText(), funType, innerDec, pars, body, ctx);
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
				return new EqNode(visit(ctx.left), visit(ctx.right), ctx);
			else if (ctx.operator.getType() == FOOLLexer.LEQ)
				return new LeqNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new GeqNode(visit(ctx.left), visit(ctx.right), ctx);
		}
		//return null;
		
	}
	
	public Node visitOperand(OperandContext ctx) {
		if (ctx.right == null)
			return visit(ctx.left);
		else {
			if (ctx.operator.getType() == FOOLLexer.PLUS)
				return new PlusNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new SubNode(visit(ctx.left), visit(ctx.right), ctx);
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
				return new TimesNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new DivNode(visit(ctx.left), visit(ctx.right), ctx);

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
			if (ctx.operator.getType() == FOOLLexer.AND)
				return new AndNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new OrNode(visit(ctx.left), visit(ctx.right), ctx);
		}
	}
	
	public Node visitAtom(AtomContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		
		if ((ctx.NOT() != null) && (ctx.MINUS() != null))
			return new NotNode(new MinusNode(visit(ctx.val), ctx), ctx);
		else if (ctx.NOT() != null)
			return new NotNode(visit(ctx.val), ctx);
		else if (ctx.MINUS() != null)
			return new MinusNode(visit(ctx.val), ctx);
		else
			return visit(ctx.val);
	}
	
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal
		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntValNode(Integer.parseInt(ctx.INTEGER().getText()), ctx);
	}
	
	public Node visitNullVal(NullValContext ctx) {
		return new NullNode();
	}
	
	public Node visitIfExp(FOOLParser.IfExpContext ctx){
		
		return new IfNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch), ctx);
	}
	
	public Node visitBoolVal(BoolValContext ctx){
		return new BoolValNode(Boolean.parseBoolean(ctx.BOOLVAL().getText()));
	}

	@Override
	public Node visitBaseExp(BaseExpContext ctx) {

		//this is actually nothing in the sense that for the ast the parenthesis are not relevant
		//the thing is that the structure of the ast will ensure the operational order by giving
		//a larger depth (closer to the leafs) to those expressions with higher importance

		//this is actually the default implementation for this method in the FOOLBaseVisitor class
		//therefore it can be safely removed here

		return visit(ctx.exp());
	}

	@Override
	public Node visitVarExp(VarExpContext ctx) {

		//this corresponds to a variable access
		return visit(ctx.var());
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
		
		res = new FunExpNode(ctx.ID().getText(), args, true, ctx);
		
		return res;
	}
	
	@Override
	public Node visitMethodExp(MethodExpContext ctx) {
		
		ArrayList<Node> args = new ArrayList<>();
		
		Node objectNode = visit(ctx.object);
		
		if (ctx.LPAR() == null) {
			IdNode fieldNode = new IdNode(ctx.memberName.getText(), ctx);
			return new ClassFieldNode(objectNode, fieldNode, true, ctx);
		}
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		// TODO: check passed context for error line numbers
		FunExpNode methodNode = new FunExpNode(ctx.memberName.getText(), args, true, ctx);
		return new ClassMethodNode(objectNode, methodNode);
	}
	
	@Override
	public Node visitNewExp(NewExpContext ctx) {
		
		return new NewExpNode(ctx.className.getText(), ctx);
	}
	
	@Override
	public Node visitVarStmAssignment(VarStmAssignmentContext ctx){
		
		Node idVariableNode = visit(ctx.var());
		Node expNode = visit(ctx.exp());
		
		if (ctx.DOT() == null)
			return new AssignmentNode(idVariableNode, expNode, false, ctx);
		
		IdNode fieldNode = new IdNode(ctx.fieldName.getText(), ctx);
		ClassFieldNode objectFieldNode = new ClassFieldNode(idVariableNode, fieldNode, false, ctx);
		return new AssignmentNode(objectFieldNode, expNode, true, ctx);
	}
	
	public Node visitVar(VarContext ctx) {
		return new IdNode(ctx.ID().getText(), ctx);
	}
	
	@Override
	public Node visitLetInStms(LetInStmsContext ctx){
		
		Node res;
		
		ArrayList<Node> decs = new ArrayList<>();
		ArrayList<Node> stms = new ArrayList<>();
		
		for(StmContext stm : ctx.stms().stm())
			stms.add(visit(stm));
		
		for(DecContext dec : ctx.let().dec())
			decs.add(visit(dec));
			
		
		res = new BlockLetInStmsNode(decs,stms);
		
		return res;
		
	}
	
	public Node visitPrintStm(PrintStmContext ctx) {
		
		ArrayList<Node> exps = new ArrayList<>();
		for (ExpContext exp: ctx.exp())
			exps.add(visit(exp));
		
		return new PrintNode(exps, ctx);
	}
	
	public Node visitMethodStm(MethodStmContext ctx) {
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		Node objectNode = visit(ctx.object);
		
		if (ctx.LPAR() == null) {
			IdNode fieldNode = new IdNode(ctx.memberName.getText(), ctx);
			return new ClassFieldNode(objectNode, fieldNode, false, ctx);
		}
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		// TODO: check FunExpNode usage with new MethodDecNode
		FunExpNode methodNode = new FunExpNode(ctx.memberName.getText(), args, false, ctx); // TODO: check passed context for error line numbers
		return new ClassMethodNode(objectNode, methodNode);
		
	}
	
	
	@Override
	public Node visitIfStm(IfStmContext ctx){
		
		ArrayList<Node> thenStms = new ArrayList<>();
		
		for (StmContext stm: ctx.thenBranch.stm())
			thenStms.add(visit(stm));

		if (ctx.elseBranch == null)
			return new IfNode(visit(ctx.cond), thenStms, ctx);
		else {
			ArrayList<Node> elseStms = new ArrayList<>();
			for (StmContext stm: ctx.elseBranch.stm())
				elseStms.add(visit(stm));
			
			return new IfNode(visit(ctx.cond), thenStms, elseStms, ctx);
		}
	}
	
	@Override
	public Node visitVarasm(VarasmContext ctx){
		
		Node typeNode = visit(ctx.vardec().type());
		
		//visit the exp
		Node expNode = visit(ctx.exp());
		
		//build the varNode
		return new VarNode(ctx.vardec().ID().getText(), typeNode, ctx, expNode);
	}
	
	@Override
	public Node visitType(TypeContext ctx){

		if (ctx.getText().equals("int"))
			return new IntType();
		else if (ctx.getText().equals("bool"))
			return new BoolType();
		else if (ctx.getText().equals("void"))
			return new VoidType();
		else
			return new ClassType(ctx.getText(), ctx);
	}
	
	@Override
	public Node visitClassDecBlock(FOOLParser.ClassDecBlockContext ctx) {
		return visit(ctx.classdec());
	}
	
	public Node visitClassdec(FOOLParser.ClassdecContext ctx) {
		String id=ctx.className.getText();
		ArrayList<Node> pars = new ArrayList<Node>();
		ArrayList<Node> methods = new ArrayList<Node>();
		String exp = (ctx.superName!=null) ? ctx.superName.getText() : null;
		
		for (VarasmContext par : ctx.varasm()) {
			VarNode node = (VarNode)visit(par);
			node.setInsideClass(ctx.className.getText());
			pars.add(node);
		}
		
		for(FundecContext dec : ctx.fundec()) {
			FunDecNode funNode = (FunDecNode)visit(dec);
			// TODO: Improve MethodDecNode creation
			
			MethodDecNode methodNode = new MethodDecNode(funNode, ctx.className.getText());
			methods.add(methodNode);
		}
		
		return new BlockClassDecNode(id,exp,pars,methods, ctx);
		
	}
	
	public Node visitVardec(FOOLParser.VardecContext ctx) {
		return new VarNode(ctx.ID().getText(), visit(ctx.type()), ctx);
	}
	
	public Node visitFunStm(FunStmContext ctx) {
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		res = new FunExpNode(ctx.ID().getText(), args, false, ctx);
		
		return res;
	}
}