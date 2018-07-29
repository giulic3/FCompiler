package grammars.SVM;

import grammars.SVM.SVMParser.*;

public class SVMVisitorImpl extends SVMBaseVisitor<Void> {
	
	public Void visitAssembly(AssemblyContext ctx) {
		
		SVMParser.resetSVM();
		
		for (int i = 0; i < ctx.simpleCmd().size(); i++)
			visit(ctx.simpleCmd(i));
		
		for (Integer refAdd: SVMParser.labelRef.keySet()) {
			String key = SVMParser.labelRef.get(refAdd);
			SVMParser.code[refAdd] = SVMParser.labelAdd.get(key);
		}
		
		return null;
	}
	
	public Void visitSimpleCmd(SimpleCmdContext ctx) {
		
		if (ctx.composedCmd() != null)
			visit(ctx.composedCmd());
		else if (ctx.POP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.POP;
		else if (ctx.ADD() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.ADD;
		else if (ctx.SUB() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.SUB;
		else if (ctx.MULT() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.MULT;
		else if (ctx.DIV() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.DIV;
		else if (ctx.STOREW() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.STOREW;
		else if (ctx.LOADW() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.LOADW;
		else if (ctx.JS() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.JS;
		else if (ctx.LOADRA() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.LOADRA;
		else if (ctx.STORERA() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.STORERA;
		else if (ctx.LOADRV() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.LOADRV;
		else if (ctx.STORERV() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.STORERV;
		else if (ctx.LOADFP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.LOADFP;
		else if (ctx.STOREFP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.STOREFP;
		else if (ctx.COPYFP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.COPYFP;
		else if (ctx.LOADHP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.LOADHP;
		else if (ctx.STOREHP() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.STOREHP;
		else if (ctx.PRINT() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.PRINT;
		else if (ctx.NEW() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.NEW;
        else if (ctx.LABEL() != null) {
			SVMParser.labelRef.put(SVMParser.i, ctx.LABEL().getText());
			SVMParser.code[SVMParser.i++] = SVMParser.LABEL; // non so se ci vuole il ++
		}
		else if (ctx.CPHEAD() != null)
			SVMParser.code[SVMParser.i++] = SVMParser.CPHEAD;
        else if (ctx.JSMETH() != null)
        	SVMParser.code[SVMParser.i++] = SVMParser.JSMETH;
		else
			SVMParser.code[SVMParser.i++] = SVMParser.HALT;
		
		return null;
	}
	
	public Void visitComposedCmd(ComposedCmdContext ctx) {
		
		if (ctx.num != null) {
			SVMParser.code[SVMParser.i++] = SVMParser.PUSH;
			SVMParser.code[SVMParser.i++] = Integer.parseInt(ctx.num.getText());
		}
		else {
			String label = ctx.label.getText();
			
			if (ctx.PUSH() != null) {
				SVMParser.code[SVMParser.i++] = SVMParser.PUSH;
				SVMParser.labelRef.put(SVMParser.i++, label);
			}
			else if (ctx.BRANCH() != null) {
				SVMParser.code[SVMParser.i++] = SVMParser.BRANCH;
				SVMParser.labelRef.put(SVMParser.i++, label);
			}
			else if (ctx.BRANCHEQ() != null) {
				SVMParser.code[SVMParser.i++] = SVMParser.BRANCHEQ;
				SVMParser.labelRef.put(SVMParser.i++, label);
			}
			else if (ctx.BRANCHLESSEQ() != null) {
				SVMParser.code[SVMParser.i++] = SVMParser.BRANCHLESSEQ;
				SVMParser.labelRef.put(SVMParser.i++, label);
			}
			else
				SVMParser.labelAdd.put(label, SVMParser.i);
		}
		
		return null;
	}
}
