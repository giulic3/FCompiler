// Generated from /home/giulia/git/FCompiler/src/parser/FOOL.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FOOLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FOOLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FOOLParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(FOOLParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letInExp}
	 * labeled alternative in {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetInExp(FOOLParser.LetInExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letInStms}
	 * labeled alternative in {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetInStms(FOOLParser.LetInStmsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code singleExp}
	 * labeled alternative in {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleExp(FOOLParser.SingleExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDecBlock}
	 * labeled alternative in {@link FOOLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecBlock(FOOLParser.ClassDecBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#classdec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdec(FOOLParser.ClassdecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(FOOLParser.LetContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#vardec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardec(FOOLParser.VardecContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#varasm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarasm(FOOLParser.VarasmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#fundec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFundec(FOOLParser.FundecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDecAssignment}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecAssignment(FOOLParser.VarDecAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funDeclaration}
	 * labeled alternative in {@link FOOLParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDeclaration(FOOLParser.FunDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FOOLParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(FOOLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(FOOLParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(FOOLParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(FOOLParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntVal(FOOLParser.IntValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolVal(FOOLParser.BoolValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullVal}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullVal(FOOLParser.NullValContext ctx);
	/**
	 * Visit a parse tree produced by the {@code baseExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExp(FOOLParser.BaseExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(FOOLParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExp(FOOLParser.VarExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExp(FOOLParser.FunExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodExp(FOOLParser.MethodExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExp}
	 * labeled alternative in {@link FOOLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExp(FOOLParser.NewExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varStmAssignment}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarStmAssignment(FOOLParser.VarStmAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStm(FOOLParser.IfStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodStm(FOOLParser.MethodStmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStm}
	 * labeled alternative in {@link FOOLParser#stm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStm(FOOLParser.PrintStmContext ctx);
	/**
	 * Visit a parse tree produced by {@link FOOLParser#stms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStms(FOOLParser.StmsContext ctx);
}