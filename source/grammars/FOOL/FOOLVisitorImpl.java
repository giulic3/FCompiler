package grammars.FOOL;

import ast.types.BoolType;
import ast.types.ClassType;
import ast.types.IntType;
import ast.types.VoidType;
import grammars.FOOL.FOOLParser.*;
import ast.*;
import org.antlr.v4.runtime.tree.TerminalNode;

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
		
		//add argument declarations
		for(VardecContext par : ctx.vardec())
			pars.add(visit(par));
			//res.addPar( new VarNode(vc.ID().getText(), visit( vc.type() ), null));

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
		
		return new FunDecNode(ctx.ID().getText(), visit(ctx.type()), innerDec, pars, body, ctx);
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
			if (ctx.operator.getType() == FOOLLexer.AND)
				return new AndNode(visit(ctx.left), visit(ctx.right));
			else
				return new OrNode(visit(ctx.left), visit(ctx.right));
		}
	}
	
	public Node visitAtom(AtomContext ctx) {
		//check whether this is a simple or binary expression
		//notice here the necessity of having named elements in the grammar
		
		if ((ctx.NOT() != null) && (ctx.MINUS() != null))
			return new NotNode(new MinusNode(visit(ctx.val)));
		else if (ctx.NOT() != null)
			return new NotNode(visit(ctx.val));
		else if (ctx.MINUS() != null)
			return new MinusNode(visit(ctx.val));
		else
			return visit(ctx.val);
	}
	
	public Node visitIntVal(IntValContext ctx) {
		// notice that this method is not actually a rule but a named production #intVal
		
		//there is no need to perform a check here, the lexer ensures this text is an int
		return new IntValNode(Integer.parseInt(ctx.INTEGER().getText()));
	}
	
	public Node visitNullVal(NullValContext ctx) {
		return new NullNode();
	}
	
	public Node visitIfExp(FOOLParser.IfExpContext ctx){
		
		if (ctx.elseBranch == null)
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch));
		else
			return new IfNode(visit(ctx.cond), visit(ctx.thenBranch), visit(ctx.elseBranch));
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
		
		/* control LPAR if is class field */
		
		//this corresponds to a function invocation
		
		//declare the result
		Node res;
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		res = new MethodNode(ctx.object.getText(), ctx.methodName.getText(), args, true, ctx);
		
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
	public Node visitVarStmAssignment(VarStmAssignmentContext ctx){

		return new AssignmentNode(visit(ctx.var()), visit(ctx.exp()));
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
			
		
		res = new BlockLetInStmsNode(decs,stms); //new BlockLetInStmsNode(decs, visit(ctx.stms()));
		
		return res;
		
	}
	
	public Node visitPrintStm(PrintStmContext ctx) {
		
		ArrayList<Node> exps = new ArrayList<>();
		for (ExpContext exp: ctx.exp())
			exps.add(visit(exp));
		
		return new PrintNode(exps);
	}
	
	public Node visitMethodStm(MethodStmContext ctx) {
		
		//get the invocation arguments
		ArrayList<Node> args = new ArrayList<>();
		
		for(ExpContext exp : ctx.exp())
			args.add(visit(exp));
		
		return new MethodNode(ctx.object.getText(), ctx.methodName.getText(), args, false, ctx);
	}
	
	
	@Override
	public Node visitIfStm(IfStmContext ctx){
		
		ArrayList<Node> thenStms = new ArrayList<>();
		
		for (StmContext stm: ctx.thenBranch.stm())
			thenStms.add(visit(stm));

		if (ctx.elseBranch == null)
			return new IfNode(visit(ctx.cond), thenStms);
		else {
			ArrayList<Node> elseStms = new ArrayList<>();
			for (StmContext stm: ctx.elseBranch.stm())
				elseStms.add(visit(stm));
			
			return new IfNode(visit(ctx.cond), thenStms, elseStms);
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
			return new ClassType(ctx.getText());

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
		
		for (VardecContext par : ctx.vardec()) {
			pars.add(visit(par));
		}
		
		for(FundecContext dec : ctx.fundec()){
			FunDecNode tmp = (FunDecNode)visit(dec);
			tmp.setInsideClass(ctx.className.getText());
			methods.add(tmp);
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