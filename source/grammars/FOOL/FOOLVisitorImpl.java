package grammars.FOOL;

import ast.types.*;
import grammars.FOOL.FOOLParser.*;
import ast.*;

import java.util.ArrayList;

public class FOOLVisitorImpl extends FOOLBaseVisitor<Node> {
	
	public Node visitProg(ProgContext ctx) {
		ArrayList<Node> blocks = new ArrayList<>();
		
		for (int i=0; i < ctx.block().size(); i++)
			blocks.add(visit(ctx.block(i)));
		
		// simply return the result of the visit to the inner exp
		return new ProgNode(blocks);
	}
	
	public Node visitLetInStms(LetInStmsContext ctx){
		ArrayList<Node> decs = new ArrayList<>();
		ArrayList<Node> stms = new ArrayList<>();
		
		for(StmContext stm : ctx.stms().stm())
			stms.add(visit(stm));
		
		for(DecContext dec : ctx.let().dec())
			decs.add(visit(dec));
		
		return new BlockLetInStmsNode(decs,stms);
	}
	
	public Node visitClassDecBlock(FOOLParser.ClassDecBlockContext ctx) {
		return visit(ctx.classdec());
	}
	
	public Node visitClassdec(FOOLParser.ClassdecContext ctx) {
		String id = ctx.className.getText();
		ArrayList<Node> pars = new ArrayList<>();
		ArrayList<Node> methods = new ArrayList<>();
		String exp = (ctx.superName != null) ? ctx.superName.getText() : null;
		
		for (VarasmContext par : ctx.varasm()) {
			VarNode node = (VarNode)visit(par);
			node.setInsideClass(ctx.className.getText());
			pars.add(node);
		}
		for (FundecContext dec : ctx.fundec()) {
			FunDecNode funNode = (FunDecNode)visit(dec);
			MethodDecNode methodNode = new MethodDecNode(funNode, ctx.className.getText());
			methods.add(methodNode);
		}
		
		return new BlockClassDecNode(id, exp, pars, methods, ctx);
	}
	
	public Node visitVardec(FOOLParser.VardecContext ctx) {
		return new VarNode(ctx.ID().getText(), visit(ctx.type()), ctx);
	}
	
	public Node visitVarasm(VarasmContext ctx){
		Node typeNode = visit(ctx.vardec().type());
		Node expNode = visit(ctx.exp());
		return new VarNode(ctx.vardec().ID().getText(), typeNode, ctx, expNode);
	}
	
	public Node visitFundec(FundecContext ctx) {
		ArrayList<Node> pars = new ArrayList<>();
		ArrayList<Node> parTypes = new ArrayList<>();
		
		// add argument declarations
		for(VardecContext par : ctx.vardec()) {
			VarNode parNode = (VarNode)visit(par);
			parNode.setIsParam(true);
			pars.add(parNode);
			parTypes.add(parNode.getType());
		}

		// add body
		// create a list for the nested var declarations
		ArrayList<Node> innerDec = new ArrayList<>();

		// check whether there are actually nested decs
		if (ctx.varasm() != null){
			// if there are visit each dec and add it to the @innerDec list
			for (VarasmContext dc : ctx.varasm())
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
	
	public Node visitExp(ExpContext ctx) {
		// simple expression
		if (ctx.right == null)
			return visit(ctx.left);
		// complex expression
		else {
			if (ctx.operator.getType() == FOOLLexer.EQ)
				return new EqNode(visit(ctx.left), visit(ctx.right), ctx);
			else if (ctx.operator.getType() == FOOLLexer.LEQ)
				return new LeqNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new GeqNode(visit(ctx.left), visit(ctx.right), ctx);
		}
	}
	
	public Node visitOperand(OperandContext ctx) {
		// simple expression
		if (ctx.right == null)
			return visit(ctx.left);
		// complex expression
		else {
			if (ctx.operator.getType() == FOOLLexer.PLUS)
				return new PlusNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new SubNode(visit(ctx.left), visit(ctx.right), ctx);
		}
	}
	
	public Node visitTerm(TermContext ctx) {
		// simple expression
		if (ctx.right == null)
			return visit(ctx.left);
		// complex expression
		else {
			if (ctx.operator.getType() == FOOLLexer.TIMES)
				return new TimesNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new DivNode(visit(ctx.left), visit(ctx.right), ctx);
		}
	}
	
	public Node visitFactor(FactorContext ctx) {
		// simple expression
		if(ctx.right == null)
			return visit(ctx.left);
		// complex expression
		else {
			if (ctx.operator.getType() == FOOLLexer.AND)
				return new AndNode(visit(ctx.left), visit(ctx.right), ctx);
			else
				return new OrNode(visit(ctx.left), visit(ctx.right), ctx);
		}
	}
	
	public Node visitAtom(AtomContext ctx) {
		// expression containing both NOT and UNARY MINUS operators
		if ((ctx.NOT() != null) && (ctx.MINUS() != null))
			return new NotNode(new MinusNode(visit(ctx.val), ctx), ctx);
		// expression containing NOT operator only
		else if (ctx.NOT() != null)
			return new NotNode(visit(ctx.val), ctx);
		// expression containing UNARY MINUS operator only
		else if (ctx.MINUS() != null)
			return new MinusNode(visit(ctx.val), ctx);
		// expression without NOT or UNARY MINUS operators (other combination are not allowed by the grammar)
		else
			return visit(ctx.val);
	}
	
	public Node visitIntVal(IntValContext ctx) {
		return new IntValNode(Integer.parseInt(ctx.INTEGER().getText()), ctx);
	}
	
	public Node visitBoolVal(BoolValContext ctx){
		return new BoolValNode(Boolean.parseBoolean(ctx.BOOLVAL().getText()));
	}
	
	public Node visitNullVal(NullValContext ctx) {
		return new NullNode();
	}
	
	public Node visitBaseExp(BaseExpContext ctx) {
		return visit(ctx.exp());
	}
	
	public Node visitIfExp(FOOLParser.IfExpContext ctx){
		return new IfNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch), ctx);
	}
	
	public Node visitVarExp(VarExpContext ctx) {
		return visit(ctx.var());
	}
	
	// Function call (as expression)
	public Node visitFunExp(FunExpContext ctx) {
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		return new FunExpNode(ctx.ID().getText(), args, true, ctx);
	}
	
	// Method call or class field access (as expression)
	public Node visitMethodExp(MethodExpContext ctx) {
		
		Node objectNode = visit(ctx.object);
		
		// handling class field access
		if (ctx.LPAR() == null) {
			IdNode fieldNode = new IdNode(ctx.memberName.getText(), ctx);
			return new ClassFieldNode(objectNode, fieldNode, true, ctx);
		}
		
		ArrayList<Node> args = new ArrayList<>();
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		FunExpNode methodNode = new FunExpNode(ctx.memberName.getText(), args, true, ctx);
		return new ClassMethodNode(objectNode, methodNode);
	}
	
	public Node visitNewExp(NewExpContext ctx) {
		return new NewExpNode(ctx.className.getText(), ctx);
	}
	
	public Node visitVar(VarContext ctx) {
		return new IdNode(ctx.ID().getText(), ctx);
	}
	
	public Node visitVarStmAssignment(VarStmAssignmentContext ctx){
		Node idVariableNode = visit(ctx.var());
		Node expNode = visit(ctx.exp());
		
		// assignment involves simple variables
		if (ctx.DOT() == null)
			return new AssignmentNode(idVariableNode, expNode, false, ctx);
		
		// assignment involves class fields
		IdNode fieldNode = new IdNode(ctx.fieldName.getText(), ctx);
		ClassFieldNode objectFieldNode = new ClassFieldNode(idVariableNode, fieldNode, false, ctx);
		return new AssignmentNode(objectFieldNode, expNode, true, ctx);
	}
	
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
	
	// Method call or class field access (as statements)
	public Node visitMethodStm(MethodStmContext ctx) {
		Node objectNode = visit(ctx.object);
		
		// handling class field access
		if (ctx.LPAR() == null) {
			IdNode fieldNode = new IdNode(ctx.memberName.getText(), ctx);
			return new ClassFieldNode(objectNode, fieldNode, false, ctx);
		}
		
		// handling method call
		ArrayList<Node> args = new ArrayList<>();
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		FunExpNode methodNode = new FunExpNode(ctx.memberName.getText(), args, false, ctx);
		return new ClassMethodNode(objectNode, methodNode);
	}
	
	public Node visitPrintStm(PrintStmContext ctx) {
		ArrayList<Node> exps = new ArrayList<>();
		for (ExpContext exp: ctx.exp())
			exps.add(visit(exp));
		
		return new PrintNode(exps, ctx);
	}
	
	// Function call (as statement)
	public Node visitFunStm(FunStmContext ctx) {
		ArrayList<Node> args = new ArrayList<>();
		for (ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		return new FunExpNode(ctx.ID().getText(), args, false, ctx);
	}
}