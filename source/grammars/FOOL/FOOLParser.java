// Generated from FOOL.g4 by ANTLR 4.7.1

package grammars.FOOL;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, COLON=2, COMMA=3, EQ=4, LEQ=5, GEQ=6, OR=7, AND=8, NOT=9, ASM=10, 
		PLUS=11, MINUS=12, TIMES=13, DIV=14, BOOLVAL=15, TRUE=16, FALSE=17, LPAR=18, 
		RPAR=19, CLPAR=20, CRPAR=21, IF=22, THEN=23, ELSE=24, PRINT=25, LET=26, 
		IN=27, VAR=28, FUN=29, INT=30, BOOL=31, VOID=32, CLASS=33, EXTENDS=34, 
		NULL=35, NEW=36, DOT=37, INTEGER=38, ID=39, WS=40, LINECOMENTS=41, BLOCKCOMENTS=42, 
		ERR=43;
	public static final int
		RULE_prog = 0, RULE_block = 1, RULE_classdec = 2, RULE_let = 3, RULE_vardec = 4, 
		RULE_varasm = 5, RULE_fundec = 6, RULE_dec = 7, RULE_type = 8, RULE_exp = 9, 
		RULE_operand = 10, RULE_term = 11, RULE_factor = 12, RULE_atom = 13, RULE_value = 14, 
		RULE_stm = 15, RULE_stms = 16;
	public static final String[] ruleNames = {
		"prog", "block", "classdec", "let", "vardec", "varasm", "fundec", "dec", 
		"type", "exp", "operand", "term", "factor", "atom", "value", "stm", "stms"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "','", "'=='", "'<='", "'>='", "'||'", "'&&'", "'not'", 
		"'='", "'+'", "'-'", "'*'", "'/'", null, "'true'", "'false'", "'('", "')'", 
		"'{'", "'}'", "'if'", "'then'", "'else'", "'print'", "'let'", "'in'", 
		"'var'", "'fun'", "'int'", "'bool'", "'void'", "'class'", "'extends'", 
		null, "'new'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMIC", "COLON", "COMMA", "EQ", "LEQ", "GEQ", "OR", "AND", "NOT", 
		"ASM", "PLUS", "MINUS", "TIMES", "DIV", "BOOLVAL", "TRUE", "FALSE", "LPAR", 
		"RPAR", "CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", 
		"VAR", "FUN", "INT", "BOOL", "VOID", "CLASS", "EXTENDS", "NULL", "NEW", 
		"DOT", "INTEGER", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
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
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				block();
				setState(35);
				match(SEMIC);
				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << LET) | (1L << CLASS) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0) );
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

	public static class BlockContext extends ParserRuleContext {
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	 
		public BlockContext() { }
		public void copyFrom(BlockContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LetInExpContext extends BlockContext {
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public LetInExpContext(BlockContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitLetInExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetInStmsContext extends BlockContext {
		public LetContext let() {
			return getRuleContext(LetContext.class,0);
		}
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public LetInStmsContext(BlockContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitLetInStms(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassDecBlockContext extends BlockContext {
		public ClassdecContext classdec() {
			return getRuleContext(ClassdecContext.class,0);
		}
		public ClassDecBlockContext(BlockContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitClassDecBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleExpContext extends BlockContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public SingleExpContext(BlockContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitSingleExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new LetInExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				let();
				setState(42);
				exp();
				}
				break;
			case 2:
				_localctx = new LetInStmsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				let();
				setState(45);
				stms();
				}
				break;
			case 3:
				_localctx = new SingleExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(47);
				exp();
				}
				break;
			case 4:
				_localctx = new ClassDecBlockContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				classdec();
				}
				break;
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

	public static class ClassdecContext extends ParserRuleContext {
		public Token className;
		public Token superName;
		public TerminalNode CLASS() { return getToken(FOOLParser.CLASS, 0); }
		public TerminalNode CLPAR() { return getToken(FOOLParser.CLPAR, 0); }
		public TerminalNode CRPAR() { return getToken(FOOLParser.CRPAR, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode EXTENDS() { return getToken(FOOLParser.EXTENDS, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<FundecContext> fundec() {
			return getRuleContexts(FundecContext.class);
		}
		public FundecContext fundec(int i) {
			return getRuleContext(FundecContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<VarasmContext> varasm() {
			return getRuleContexts(VarasmContext.class);
		}
		public VarasmContext varasm(int i) {
			return getRuleContext(VarasmContext.class,i);
		}
		public ClassdecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitClassdec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassdecContext classdec() throws RecognitionException {
		ClassdecContext _localctx = new ClassdecContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classdec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(CLASS);
			setState(52);
			((ClassdecContext)_localctx).className = match(ID);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(53);
				match(EXTENDS);
				setState(54);
				((ClassdecContext)_localctx).superName = match(ID);
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAR) {
				{
				setState(57);
				match(LPAR);
				setState(61); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(58);
					varasm();
					setState(59);
					match(SEMIC);
					}
					}
					setState(63); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
				setState(65);
				match(RPAR);
				}
			}

			setState(69);
			match(CLPAR);
			setState(73); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(70);
				fundec();
				setState(71);
				match(SEMIC);
				}
				}
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(77);
			match(CRPAR);
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

	public static class LetContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public List<DecContext> dec() {
			return getRuleContexts(DecContext.class);
		}
		public DecContext dec(int i) {
			return getRuleContext(DecContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public LetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetContext let() throws RecognitionException {
		LetContext _localctx = new LetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_let);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(LET);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				dec();
				setState(81);
				match(SEMIC);
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(87);
			match(IN);
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

	public static class VardecContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public VardecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVardec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VardecContext vardec() throws RecognitionException {
		VardecContext _localctx = new VardecContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_vardec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			type();
			setState(90);
			match(ID);
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

	public static class VarasmContext extends ParserRuleContext {
		public VardecContext vardec() {
			return getRuleContext(VardecContext.class,0);
		}
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VarasmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varasm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarasm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarasmContext varasm() throws RecognitionException {
		VarasmContext _localctx = new VarasmContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varasm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			vardec();
			setState(93);
			match(ASM);
			setState(94);
			exp();
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

	public static class FundecContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<VarasmContext> varasm() {
			return getRuleContexts(VarasmContext.class);
		}
		public VarasmContext varasm(int i) {
			return getRuleContext(VarasmContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public FundecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fundec; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFundec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FundecContext fundec() throws RecognitionException {
		FundecContext _localctx = new FundecContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fundec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			type();
			setState(97);
			match(ID);
			setState(98);
			match(LPAR);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0)) {
				{
				setState(99);
				vardec();
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(100);
					match(COMMA);
					setState(101);
					vardec();
					}
					}
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(109);
			match(RPAR);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LET) {
				{
				setState(110);
				match(LET);
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(111);
					varasm();
					setState(112);
					match(SEMIC);
					}
					}
					setState(116); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
				setState(118);
				match(IN);
				}
			}

			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(122);
				exp();
				}
				break;
			case 2:
				{
				setState(123);
				stms();
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

	public static class DecContext extends ParserRuleContext {
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec; }
	 
		public DecContext() { }
		public void copyFrom(DecContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDecAssignmentContext extends DecContext {
		public VarasmContext varasm() {
			return getRuleContext(VarasmContext.class,0);
		}
		public VarDecAssignmentContext(DecContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarDecAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunDeclarationContext extends DecContext {
		public FundecContext fundec() {
			return getRuleContext(FundecContext.class,0);
		}
		public FunDeclarationContext(DecContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFunDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dec);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new VarDecAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				varasm();
				}
				break;
			case 2:
				_localctx = new FunDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				fundec();
				}
				break;
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode VOID() { return getToken(FOOLParser.VOID, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ExpContext extends ParserRuleContext {
		public OperandContext left;
		public Token operator;
		public ExpContext right;
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode EQ() { return getToken(FOOLParser.EQ, 0); }
		public TerminalNode LEQ() { return getToken(FOOLParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(FOOLParser.GEQ, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			((ExpContext)_localctx).left = operand();
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LEQ) | (1L << GEQ))) != 0)) {
				{
				setState(133);
				((ExpContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LEQ) | (1L << GEQ))) != 0)) ) {
					((ExpContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(134);
				((ExpContext)_localctx).right = exp();
				}
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

	public static class OperandContext extends ParserRuleContext {
		public TermContext left;
		public Token operator;
		public OperandContext right;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(FOOLParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINUS) {
				{
				setState(137);
				match(MINUS);
				}
			}

			setState(140);
			((OperandContext)_localctx).left = term();
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(141);
				((OperandContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
					((OperandContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(142);
				((OperandContext)_localctx).right = operand();
				}
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

	public static class TermContext extends ParserRuleContext {
		public FactorContext left;
		public Token operator;
		public TermContext right;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(FOOLParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(FOOLParser.DIV, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			((TermContext)_localctx).left = factor();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TIMES || _la==DIV) {
				{
				setState(146);
				((TermContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
					((TermContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(147);
				((TermContext)_localctx).right = term();
				}
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

	public static class FactorContext extends ParserRuleContext {
		public AtomContext left;
		public Token operator;
		public FactorContext right;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode OR() { return getToken(FOOLParser.OR, 0); }
		public TerminalNode AND() { return getToken(FOOLParser.AND, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			((FactorContext)_localctx).left = atom();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OR || _la==AND) {
				{
				setState(151);
				((FactorContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==OR || _la==AND) ) {
					((FactorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(152);
				((FactorContext)_localctx).right = factor();
				}
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

	public static class AtomContext extends ParserRuleContext {
		public ValueContext val;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(155);
				match(NOT);
				}
			}

			setState(158);
			((AtomContext)_localctx).val = value();
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

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseExpContext extends ValueContext {
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public BaseExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitBaseExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExpContext extends ValueContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public VarExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntValContext extends ValueContext {
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public IntValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIntVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullValContext extends ValueContext {
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public NullValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitNullVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MethodExpContext extends ValueContext {
		public Token object;
		public Token methodName;
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public MethodExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitMethodExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewExpContext extends ValueContext {
		public Token className;
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public NewExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitNewExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfExpContext extends ValueContext {
		public ExpContext cond;
		public ExpContext thenBranch;
		public ExpContext elseBranch;
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public IfExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIfExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolValContext extends ValueContext {
		public TerminalNode BOOLVAL() { return getToken(FOOLParser.BOOLVAL, 0); }
		public BoolValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitBoolVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunExpContext extends ValueContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public FunExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFunExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_value);
		int _la;
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new IntValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(INTEGER);
				}
				break;
			case 2:
				_localctx = new BoolValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(BOOLVAL);
				}
				break;
			case 3:
				_localctx = new NullValContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				match(NULL);
				}
				break;
			case 4:
				_localctx = new BaseExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(LPAR);
				setState(164);
				exp();
				setState(165);
				match(RPAR);
				}
				break;
			case 5:
				_localctx = new IfExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				match(IF);
				setState(168);
				match(LPAR);
				setState(169);
				((IfExpContext)_localctx).cond = exp();
				setState(170);
				match(RPAR);
				setState(171);
				match(THEN);
				setState(172);
				match(CLPAR);
				setState(173);
				((IfExpContext)_localctx).thenBranch = exp();
				setState(174);
				match(CRPAR);
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(175);
					match(ELSE);
					setState(176);
					match(CLPAR);
					setState(177);
					((IfExpContext)_localctx).elseBranch = exp();
					setState(178);
					match(CRPAR);
					}
				}

				}
				break;
			case 6:
				_localctx = new VarExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(182);
				match(ID);
				}
				break;
			case 7:
				_localctx = new FunExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(183);
				match(ID);
				{
				setState(184);
				match(LPAR);
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(185);
					exp();
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(186);
						match(COMMA);
						setState(187);
						exp();
						}
						}
						setState(192);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(195);
				match(RPAR);
				}
				}
				break;
			case 8:
				_localctx = new MethodExpContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(196);
				((MethodExpContext)_localctx).object = match(ID);
				setState(197);
				match(DOT);
				setState(198);
				((MethodExpContext)_localctx).methodName = match(ID);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(199);
					match(LPAR);
					setState(208);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(200);
						exp();
						setState(205);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(201);
							match(COMMA);
							setState(202);
							exp();
							}
							}
							setState(207);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(210);
					match(RPAR);
					}
				}

				}
				break;
			case 9:
				_localctx = new NewExpContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(213);
				match(NEW);
				setState(214);
				((NewExpContext)_localctx).className = match(ID);
				{
				setState(215);
				match(LPAR);
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(216);
					exp();
					setState(221);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(217);
						match(COMMA);
						setState(218);
						exp();
						}
						}
						setState(223);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(226);
				match(RPAR);
				}
				}
				break;
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

	public static class StmContext extends ParserRuleContext {
		public StmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stm; }
	 
		public StmContext() { }
		public void copyFrom(StmContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MethodStmContext extends StmContext {
		public Token object;
		public Token methodName;
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public MethodStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitMethodStm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintStmContext extends StmContext {
		public TerminalNode PRINT() { return getToken(FOOLParser.PRINT, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public PrintStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitPrintStm(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarStmAssignmentContext extends StmContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public VarStmAssignmentContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarStmAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmContext extends StmContext {
		public ExpContext cond;
		public StmsContext thenBranch;
		public StmsContext elseBranch;
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public List<StmsContext> stms() {
			return getRuleContexts(StmsContext.class);
		}
		public StmsContext stms(int i) {
			return getRuleContext(StmsContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public IfStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIfStm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmContext stm() throws RecognitionException {
		StmContext _localctx = new StmContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_stm);
		int _la;
		try {
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new VarStmAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				match(ID);
				setState(230);
				match(ASM);
				setState(231);
				exp();
				}
				break;
			case 2:
				_localctx = new IfStmContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				match(IF);
				setState(233);
				((IfStmContext)_localctx).cond = exp();
				setState(234);
				match(THEN);
				setState(235);
				match(CLPAR);
				setState(236);
				((IfStmContext)_localctx).thenBranch = stms();
				setState(237);
				match(CRPAR);
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(238);
					match(ELSE);
					setState(239);
					match(CLPAR);
					setState(240);
					((IfStmContext)_localctx).elseBranch = stms();
					setState(241);
					match(CRPAR);
					}
				}

				}
				break;
			case 3:
				_localctx = new MethodStmContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				((MethodStmContext)_localctx).object = match(ID);
				setState(246);
				match(DOT);
				setState(247);
				((MethodStmContext)_localctx).methodName = match(ID);
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(248);
					match(LPAR);
					setState(257);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(249);
						exp();
						setState(254);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(250);
							match(COMMA);
							setState(251);
							exp();
							}
							}
							setState(256);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(259);
					match(RPAR);
					}
				}

				}
				break;
			case 4:
				_localctx = new PrintStmContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(262);
				match(PRINT);
				setState(263);
				match(LPAR);
				setState(264);
				exp();
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(265);
					match(COMMA);
					setState(266);
					exp();
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(272);
				match(RPAR);
				}
				break;
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

	public static class StmsContext extends ParserRuleContext {
		public List<StmContext> stm() {
			return getRuleContexts(StmContext.class);
		}
		public StmContext stm(int i) {
			return getRuleContext(StmContext.class,i);
		}
		public StmsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stms; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitStms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmsContext stms() throws RecognitionException {
		StmsContext _localctx = new StmsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(276);
				stm();
				}
				}
				setState(279); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << PRINT) | (1L << ID))) != 0) );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u011c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\6\2(\n\2\r\2\16\2)\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64"+
		"\n\3\3\4\3\4\3\4\3\4\5\4:\n\4\3\4\3\4\3\4\3\4\6\4@\n\4\r\4\16\4A\3\4\3"+
		"\4\5\4F\n\4\3\4\3\4\3\4\3\4\6\4L\n\4\r\4\16\4M\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\6\5V\n\5\r\5\16\5W\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\7\bi\n\b\f\b\16\bl\13\b\5\bn\n\b\3\b\3\b\3\b\3\b\3\b\6\bu\n"+
		"\b\r\b\16\bv\3\b\3\b\5\b{\n\b\3\b\3\b\5\b\177\n\b\3\t\3\t\5\t\u0083\n"+
		"\t\3\n\3\n\3\13\3\13\3\13\5\13\u008a\n\13\3\f\5\f\u008d\n\f\3\f\3\f\3"+
		"\f\5\f\u0092\n\f\3\r\3\r\3\r\5\r\u0097\n\r\3\16\3\16\3\16\5\16\u009c\n"+
		"\16\3\17\5\17\u009f\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00b7\n\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00bf\n\20\f\20\16\20\u00c2"+
		"\13\20\5\20\u00c4\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00ce"+
		"\n\20\f\20\16\20\u00d1\13\20\5\20\u00d3\n\20\3\20\5\20\u00d6\n\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\7\20\u00de\n\20\f\20\16\20\u00e1\13\20\5\20"+
		"\u00e3\n\20\3\20\5\20\u00e6\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00f6\n\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\7\21\u00ff\n\21\f\21\16\21\u0102\13\21\5\21\u0104\n\21"+
		"\3\21\5\21\u0107\n\21\3\21\3\21\3\21\3\21\3\21\7\21\u010e\n\21\f\21\16"+
		"\21\u0111\13\21\3\21\3\21\5\21\u0115\n\21\3\22\6\22\u0118\n\22\r\22\16"+
		"\22\u0119\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\7\4"+
		"\2 \"))\3\2\6\b\3\2\r\16\3\2\17\20\3\2\t\n\2\u0138\2\'\3\2\2\2\4\63\3"+
		"\2\2\2\6\65\3\2\2\2\bQ\3\2\2\2\n[\3\2\2\2\f^\3\2\2\2\16b\3\2\2\2\20\u0082"+
		"\3\2\2\2\22\u0084\3\2\2\2\24\u0086\3\2\2\2\26\u008c\3\2\2\2\30\u0093\3"+
		"\2\2\2\32\u0098\3\2\2\2\34\u009e\3\2\2\2\36\u00e5\3\2\2\2 \u0114\3\2\2"+
		"\2\"\u0117\3\2\2\2$%\5\4\3\2%&\7\3\2\2&(\3\2\2\2\'$\3\2\2\2()\3\2\2\2"+
		")\'\3\2\2\2)*\3\2\2\2*\3\3\2\2\2+,\5\b\5\2,-\5\24\13\2-\64\3\2\2\2./\5"+
		"\b\5\2/\60\5\"\22\2\60\64\3\2\2\2\61\64\5\24\13\2\62\64\5\6\4\2\63+\3"+
		"\2\2\2\63.\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\5\3\2\2\2\65\66\7#\2"+
		"\2\669\7)\2\2\678\7$\2\28:\7)\2\29\67\3\2\2\29:\3\2\2\2:E\3\2\2\2;?\7"+
		"\24\2\2<=\5\f\7\2=>\7\3\2\2>@\3\2\2\2?<\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB"+
		"\3\2\2\2BC\3\2\2\2CD\7\25\2\2DF\3\2\2\2E;\3\2\2\2EF\3\2\2\2FG\3\2\2\2"+
		"GK\7\26\2\2HI\5\16\b\2IJ\7\3\2\2JL\3\2\2\2KH\3\2\2\2LM\3\2\2\2MK\3\2\2"+
		"\2MN\3\2\2\2NO\3\2\2\2OP\7\27\2\2P\7\3\2\2\2QU\7\34\2\2RS\5\20\t\2ST\7"+
		"\3\2\2TV\3\2\2\2UR\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7"+
		"\35\2\2Z\t\3\2\2\2[\\\5\22\n\2\\]\7)\2\2]\13\3\2\2\2^_\5\n\6\2_`\7\f\2"+
		"\2`a\5\24\13\2a\r\3\2\2\2bc\5\22\n\2cd\7)\2\2dm\7\24\2\2ej\5\n\6\2fg\7"+
		"\5\2\2gi\5\n\6\2hf\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2kn\3\2\2\2lj\3"+
		"\2\2\2me\3\2\2\2mn\3\2\2\2no\3\2\2\2oz\7\25\2\2pt\7\34\2\2qr\5\f\7\2r"+
		"s\7\3\2\2su\3\2\2\2tq\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2wx\3\2\2\2"+
		"xy\7\35\2\2y{\3\2\2\2zp\3\2\2\2z{\3\2\2\2{~\3\2\2\2|\177\5\24\13\2}\177"+
		"\5\"\22\2~|\3\2\2\2~}\3\2\2\2\177\17\3\2\2\2\u0080\u0083\5\f\7\2\u0081"+
		"\u0083\5\16\b\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\21\3\2\2"+
		"\2\u0084\u0085\t\2\2\2\u0085\23\3\2\2\2\u0086\u0089\5\26\f\2\u0087\u0088"+
		"\t\3\2\2\u0088\u008a\5\24\13\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2"+
		"\u008a\25\3\2\2\2\u008b\u008d\7\16\2\2\u008c\u008b\3\2\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0091\5\30\r\2\u008f\u0090\t\4\2\2"+
		"\u0090\u0092\5\26\f\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\27"+
		"\3\2\2\2\u0093\u0096\5\32\16\2\u0094\u0095\t\5\2\2\u0095\u0097\5\30\r"+
		"\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\31\3\2\2\2\u0098\u009b"+
		"\5\34\17\2\u0099\u009a\t\6\2\2\u009a\u009c\5\32\16\2\u009b\u0099\3\2\2"+
		"\2\u009b\u009c\3\2\2\2\u009c\33\3\2\2\2\u009d\u009f\7\13\2\2\u009e\u009d"+
		"\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\5\36\20\2"+
		"\u00a1\35\3\2\2\2\u00a2\u00e6\7(\2\2\u00a3\u00e6\7\21\2\2\u00a4\u00e6"+
		"\7%\2\2\u00a5\u00a6\7\24\2\2\u00a6\u00a7\5\24\13\2\u00a7\u00a8\7\25\2"+
		"\2\u00a8\u00e6\3\2\2\2\u00a9\u00aa\7\30\2\2\u00aa\u00ab\7\24\2\2\u00ab"+
		"\u00ac\5\24\13\2\u00ac\u00ad\7\25\2\2\u00ad\u00ae\7\31\2\2\u00ae\u00af"+
		"\7\26\2\2\u00af\u00b0\5\24\13\2\u00b0\u00b6\7\27\2\2\u00b1\u00b2\7\32"+
		"\2\2\u00b2\u00b3\7\26\2\2\u00b3\u00b4\5\24\13\2\u00b4\u00b5\7\27\2\2\u00b5"+
		"\u00b7\3\2\2\2\u00b6\u00b1\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00e6\3\2"+
		"\2\2\u00b8\u00e6\7)\2\2\u00b9\u00ba\7)\2\2\u00ba\u00c3\7\24\2\2\u00bb"+
		"\u00c0\5\24\13\2\u00bc\u00bd\7\5\2\2\u00bd\u00bf\5\24\13\2\u00be\u00bc"+
		"\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00bb\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00e6\7\25\2\2\u00c6\u00c7\7)\2\2\u00c7"+
		"\u00c8\7\'\2\2\u00c8\u00d5\7)\2\2\u00c9\u00d2\7\24\2\2\u00ca\u00cf\5\24"+
		"\13\2\u00cb\u00cc\7\5\2\2\u00cc\u00ce\5\24\13\2\u00cd\u00cb\3\2\2\2\u00ce"+
		"\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d3\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d2\u00ca\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d6\7\25\2\2\u00d5\u00c9\3\2\2\2\u00d5\u00d6\3"+
		"\2\2\2\u00d6\u00e6\3\2\2\2\u00d7\u00d8\7&\2\2\u00d8\u00d9\7)\2\2\u00d9"+
		"\u00e2\7\24\2\2\u00da\u00df\5\24\13\2\u00db\u00dc\7\5\2\2\u00dc\u00de"+
		"\5\24\13\2\u00dd\u00db\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2"+
		"\u00df\u00e0\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00da"+
		"\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\7\25\2\2"+
		"\u00e5\u00a2\3\2\2\2\u00e5\u00a3\3\2\2\2\u00e5\u00a4\3\2\2\2\u00e5\u00a5"+
		"\3\2\2\2\u00e5\u00a9\3\2\2\2\u00e5\u00b8\3\2\2\2\u00e5\u00b9\3\2\2\2\u00e5"+
		"\u00c6\3\2\2\2\u00e5\u00d7\3\2\2\2\u00e6\37\3\2\2\2\u00e7\u00e8\7)\2\2"+
		"\u00e8\u00e9\7\f\2\2\u00e9\u0115\5\24\13\2\u00ea\u00eb\7\30\2\2\u00eb"+
		"\u00ec\5\24\13\2\u00ec\u00ed\7\31\2\2\u00ed\u00ee\7\26\2\2\u00ee\u00ef"+
		"\5\"\22\2\u00ef\u00f5\7\27\2\2\u00f0\u00f1\7\32\2\2\u00f1\u00f2\7\26\2"+
		"\2\u00f2\u00f3\5\"\22\2\u00f3\u00f4\7\27\2\2\u00f4\u00f6\3\2\2\2\u00f5"+
		"\u00f0\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u0115\3\2\2\2\u00f7\u00f8\7)"+
		"\2\2\u00f8\u00f9\7\'\2\2\u00f9\u0106\7)\2\2\u00fa\u0103\7\24\2\2\u00fb"+
		"\u0100\5\24\13\2\u00fc\u00fd\7\5\2\2\u00fd\u00ff\5\24\13\2\u00fe\u00fc"+
		"\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u00fb\3\2\2\2\u0103\u0104\3\2"+
		"\2\2\u0104\u0105\3\2\2\2\u0105\u0107\7\25\2\2\u0106\u00fa\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0115\3\2\2\2\u0108\u0109\7\33\2\2\u0109\u010a\7"+
		"\24\2\2\u010a\u010f\5\24\13\2\u010b\u010c\7\5\2\2\u010c\u010e\5\24\13"+
		"\2\u010d\u010b\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110"+
		"\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\25\2\2"+
		"\u0113\u0115\3\2\2\2\u0114\u00e7\3\2\2\2\u0114\u00ea\3\2\2\2\u0114\u00f7"+
		"\3\2\2\2\u0114\u0108\3\2\2\2\u0115!\3\2\2\2\u0116\u0118\5 \21\2\u0117"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011a#\3\2\2\2%)\639AEMWjmvz~\u0082\u0089\u008c\u0091\u0096\u009b"+
		"\u009e\u00b6\u00c0\u00c3\u00cf\u00d2\u00d5\u00df\u00e2\u00e5\u00f5\u0100"+
		"\u0103\u0106\u010f\u0114\u0119";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}