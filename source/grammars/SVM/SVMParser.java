// Generated from SVM.g4 by ANTLR 4.7.1

package grammars.SVM;
import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, STOREW=7, LOADW=8, BRANCH=9, 
		BRANCHEQ=10, BRANCHLESSEQ=11, JS=12, LOADRA=13, STORERA=14, LOADRV=15, 
		STORERV=16, LOADFP=17, STOREFP=18, COPYFP=19, LOADHP=20, STOREHP=21, PRINT=22, 
		HALT=23, COL=24, LABEL=25, NUMBER=26, WHITESP=27, ERR=28;
	public static final int
		RULE_assembly = 0, RULE_simpleCmd = 1, RULE_composedCmd = 2;
	public static final String[] ruleNames = {
		"assembly", "simpleCmd", "composedCmd"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'sw'", 
		"'lw'", "'b'", "'beq'", "'bleq'", "'js'", "'lra'", "'sra'", "'lrv'", "'srv'", 
		"'lfp'", "'sfp'", "'cfp'", "'lhp'", "'shp'", "'print'", "'halt'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "COL", 
		"LABEL", "NUMBER", "WHITESP", "ERR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	public static int[] code = new int[ExecuteVM.CODESIZE];
	static int i = 0;
	static HashMap<String,Integer> labelAdd = new HashMap<String,Integer>();
	static HashMap<Integer,String> labelRef = new HashMap<Integer,String>();

	public SVMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AssemblyContext extends ParserRuleContext {
		public List<SimpleCmdContext> simpleCmd() {
			return getRuleContexts(SimpleCmdContext.class);
		}
		public SimpleCmdContext simpleCmd(int i) {
			return getRuleContext(SimpleCmdContext.class,i);
		}
		public AssemblyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assembly; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitAssembly(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssemblyContext assembly() throws RecognitionException {
		AssemblyContext _localctx = new AssemblyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_assembly);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PUSH) | (1L << POP) | (1L << ADD) | (1L << SUB) | (1L << MULT) | (1L << DIV) | (1L << STOREW) | (1L << LOADW) | (1L << BRANCH) | (1L << BRANCHEQ) | (1L << BRANCHLESSEQ) | (1L << JS) | (1L << LOADRA) | (1L << STORERA) | (1L << LOADRV) | (1L << STORERV) | (1L << LOADFP) | (1L << STOREFP) | (1L << COPYFP) | (1L << LOADHP) | (1L << STOREHP) | (1L << PRINT) | (1L << HALT) | (1L << LABEL))) != 0)) {
				{
				{
				setState(6);
				simpleCmd();
				}
				}
				setState(11);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleCmdContext extends ParserRuleContext {
		public ComposedCmdContext composedCmd() {
			return getRuleContext(ComposedCmdContext.class,0);
		}
		public TerminalNode POP() { return getToken(SVMParser.POP, 0); }
		public TerminalNode ADD() { return getToken(SVMParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(SVMParser.SUB, 0); }
		public TerminalNode MULT() { return getToken(SVMParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(SVMParser.DIV, 0); }
		public TerminalNode STOREW() { return getToken(SVMParser.STOREW, 0); }
		public TerminalNode LOADW() { return getToken(SVMParser.LOADW, 0); }
		public TerminalNode JS() { return getToken(SVMParser.JS, 0); }
		public TerminalNode LOADRA() { return getToken(SVMParser.LOADRA, 0); }
		public TerminalNode STORERA() { return getToken(SVMParser.STORERA, 0); }
		public TerminalNode LOADRV() { return getToken(SVMParser.LOADRV, 0); }
		public TerminalNode STORERV() { return getToken(SVMParser.STORERV, 0); }
		public TerminalNode LOADFP() { return getToken(SVMParser.LOADFP, 0); }
		public TerminalNode STOREFP() { return getToken(SVMParser.STOREFP, 0); }
		public TerminalNode COPYFP() { return getToken(SVMParser.COPYFP, 0); }
		public TerminalNode LOADHP() { return getToken(SVMParser.LOADHP, 0); }
		public TerminalNode STOREHP() { return getToken(SVMParser.STOREHP, 0); }
		public TerminalNode PRINT() { return getToken(SVMParser.PRINT, 0); }
		public TerminalNode HALT() { return getToken(SVMParser.HALT, 0); }
		public SimpleCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitSimpleCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleCmdContext simpleCmd() throws RecognitionException {
		SimpleCmdContext _localctx = new SimpleCmdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_simpleCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PUSH:
			case BRANCH:
			case BRANCHEQ:
			case BRANCHLESSEQ:
			case LABEL:
				{
				setState(12);
				composedCmd();
				}
				break;
			case POP:
				{
				setState(13);
				match(POP);
				}
				break;
			case ADD:
				{
				setState(14);
				match(ADD);
				}
				break;
			case SUB:
				{
				setState(15);
				match(SUB);
				}
				break;
			case MULT:
				{
				setState(16);
				match(MULT);
				}
				break;
			case DIV:
				{
				setState(17);
				match(DIV);
				}
				break;
			case STOREW:
				{
				setState(18);
				match(STOREW);
				}
				break;
			case LOADW:
				{
				setState(19);
				match(LOADW);
				}
				break;
			case JS:
				{
				setState(20);
				match(JS);
				}
				break;
			case LOADRA:
				{
				setState(21);
				match(LOADRA);
				}
				break;
			case STORERA:
				{
				setState(22);
				match(STORERA);
				}
				break;
			case LOADRV:
				{
				setState(23);
				match(LOADRV);
				}
				break;
			case STORERV:
				{
				setState(24);
				match(STORERV);
				}
				break;
			case LOADFP:
				{
				setState(25);
				match(LOADFP);
				}
				break;
			case STOREFP:
				{
				setState(26);
				match(STOREFP);
				}
				break;
			case COPYFP:
				{
				setState(27);
				match(COPYFP);
				}
				break;
			case LOADHP:
				{
				setState(28);
				match(LOADHP);
				}
				break;
			case STOREHP:
				{
				setState(29);
				match(STOREHP);
				}
				break;
			case PRINT:
				{
				setState(30);
				match(PRINT);
				}
				break;
			case HALT:
				{
				setState(31);
				match(HALT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComposedCmdContext extends ParserRuleContext {
		public Token num;
		public Token label;
		public TerminalNode PUSH() { return getToken(SVMParser.PUSH, 0); }
		public TerminalNode COL() { return getToken(SVMParser.COL, 0); }
		public TerminalNode BRANCH() { return getToken(SVMParser.BRANCH, 0); }
		public TerminalNode BRANCHEQ() { return getToken(SVMParser.BRANCHEQ, 0); }
		public TerminalNode BRANCHLESSEQ() { return getToken(SVMParser.BRANCHLESSEQ, 0); }
		public TerminalNode NUMBER() { return getToken(SVMParser.NUMBER, 0); }
		public TerminalNode LABEL() { return getToken(SVMParser.LABEL, 0); }
		public ComposedCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composedCmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SVMVisitor ) return ((SVMVisitor<? extends T>)visitor).visitComposedCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComposedCmdContext composedCmd() throws RecognitionException {
		ComposedCmdContext _localctx = new ComposedCmdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_composedCmd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(34);
				match(PUSH);
				setState(35);
				((ComposedCmdContext)_localctx).num = match(NUMBER);
				}
				break;
			case 2:
				{
				setState(36);
				match(PUSH);
				setState(37);
				((ComposedCmdContext)_localctx).label = match(LABEL);
				}
				break;
			case 3:
				{
				setState(38);
				((ComposedCmdContext)_localctx).label = match(LABEL);
				setState(39);
				match(COL);
				}
				break;
			case 4:
				{
				setState(40);
				match(BRANCH);
				setState(41);
				((ComposedCmdContext)_localctx).label = match(LABEL);
				}
				break;
			case 5:
				{
				setState(42);
				match(BRANCHEQ);
				setState(43);
				((ComposedCmdContext)_localctx).label = match(LABEL);
				}
				break;
			case 6:
				{
				setState(44);
				match(BRANCHLESSEQ);
				setState(45);
				((ComposedCmdContext)_localctx).label = match(LABEL);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\63\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\7\2\n\n\2\f\2\16\2\r\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3#\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\61\n\4\3\4\2\2\5\2\4\6\2"+
		"\2\2H\2\13\3\2\2\2\4\"\3\2\2\2\6\60\3\2\2\2\b\n\5\4\3\2\t\b\3\2\2\2\n"+
		"\r\3\2\2\2\13\t\3\2\2\2\13\f\3\2\2\2\f\3\3\2\2\2\r\13\3\2\2\2\16#\5\6"+
		"\4\2\17#\7\4\2\2\20#\7\5\2\2\21#\7\6\2\2\22#\7\7\2\2\23#\7\b\2\2\24#\7"+
		"\t\2\2\25#\7\n\2\2\26#\7\16\2\2\27#\7\17\2\2\30#\7\20\2\2\31#\7\21\2\2"+
		"\32#\7\22\2\2\33#\7\23\2\2\34#\7\24\2\2\35#\7\25\2\2\36#\7\26\2\2\37#"+
		"\7\27\2\2 #\7\30\2\2!#\7\31\2\2\"\16\3\2\2\2\"\17\3\2\2\2\"\20\3\2\2\2"+
		"\"\21\3\2\2\2\"\22\3\2\2\2\"\23\3\2\2\2\"\24\3\2\2\2\"\25\3\2\2\2\"\26"+
		"\3\2\2\2\"\27\3\2\2\2\"\30\3\2\2\2\"\31\3\2\2\2\"\32\3\2\2\2\"\33\3\2"+
		"\2\2\"\34\3\2\2\2\"\35\3\2\2\2\"\36\3\2\2\2\"\37\3\2\2\2\" \3\2\2\2\""+
		"!\3\2\2\2#\5\3\2\2\2$%\7\3\2\2%\61\7\34\2\2&\'\7\3\2\2\'\61\7\33\2\2("+
		")\7\33\2\2)\61\7\32\2\2*+\7\13\2\2+\61\7\33\2\2,-\7\f\2\2-\61\7\33\2\2"+
		"./\7\r\2\2/\61\7\33\2\2\60$\3\2\2\2\60&\3\2\2\2\60(\3\2\2\2\60*\3\2\2"+
		"\2\60,\3\2\2\2\60.\3\2\2\2\61\7\3\2\2\2\5\13\"\60";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}