// Generated from SVM.g4 by ANTLR 4.7.1

package grammars.SVM;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SVMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SVMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SVMParser#assembly}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssembly(SVMParser.AssemblyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#simpleCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleCmd(SVMParser.SimpleCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link SVMParser#composedCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComposedCmd(SVMParser.ComposedCmdContext ctx);
}