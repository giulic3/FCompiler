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
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
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
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
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

			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAR) {
				{
				setState(57);
				match(LPAR);
				setState(58);
				vardec();
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(59);
					match(COMMA);
					setState(60);
					vardec();
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(66);
				match(RPAR);
				}
			}

			setState(70);
			match(CLPAR);
			setState(74); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				fundec();
				setState(72);
				match(SEMIC);
				}
				}
				setState(76); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(78);
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
			setState(80);
			match(LET);
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				dec();
				setState(82);
				match(SEMIC);
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
			setState(88);
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
			setState(90);
			type();
			setState(91);
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
			setState(93);
			vardec();
			setState(94);
			match(ASM);
			setState(95);
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
			setState(97);
			type();
			setState(98);
			match(ID);
			setState(99);
			match(LPAR);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0)) {
				{
				setState(100);
				vardec();
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(101);
					match(COMMA);
					setState(102);
					vardec();
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(110);
			match(RPAR);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LET) {
				{
				setState(111);
				match(LET);
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(112);
					varasm();
					setState(113);
					match(SEMIC);
					}
					}
					setState(117); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << VOID) | (1L << ID))) != 0) );
				setState(119);
				match(IN);
				}
			}

			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(123);
				exp();
				}
				break;
			case 2:
				{
				setState(124);
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
			setState(129);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new VarDecAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				varasm();
				}
				break;
			case 2:
				_localctx = new FunDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
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
			setState(131);
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
			setState(133);
			((ExpContext)_localctx).left = operand();
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LEQ) | (1L << GEQ))) != 0)) {
				{
				setState(134);
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
				setState(135);
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
			((OperandContext)_localctx).left = term();
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(139);
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
				setState(140);
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
			setState(143);
			((TermContext)_localctx).left = factor();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TIMES || _la==DIV) {
				{
				setState(144);
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
				setState(145);
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
			setState(148);
			((FactorContext)_localctx).left = atom();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OR || _la==AND) {
				{
				setState(149);
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
				setState(150);
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
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
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
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==MINUS) {
				{
				setState(153);
				_la = _input.LA(1);
				if ( !(_la==NOT || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(156);
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
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new IntValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(INTEGER);
				}
				break;
			case 2:
				_localctx = new BoolValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(BOOLVAL);
				}
				break;
			case 3:
				_localctx = new NullValContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(160);
				match(NULL);
				}
				break;
			case 4:
				_localctx = new BaseExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(161);
				match(LPAR);
				setState(162);
				exp();
				setState(163);
				match(RPAR);
				}
				break;
			case 5:
				_localctx = new IfExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				match(IF);
				setState(166);
				match(LPAR);
				setState(167);
				((IfExpContext)_localctx).cond = exp();
				setState(168);
				match(RPAR);
				setState(169);
				match(THEN);
				setState(170);
				match(CLPAR);
				setState(171);
				((IfExpContext)_localctx).thenBranch = exp();
				setState(172);
				match(CRPAR);
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(173);
					match(ELSE);
					setState(174);
					match(CLPAR);
					setState(175);
					((IfExpContext)_localctx).elseBranch = exp();
					setState(176);
					match(CRPAR);
					}
				}

				}
				break;
			case 6:
				_localctx = new VarExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(180);
				match(ID);
				}
				break;
			case 7:
				_localctx = new FunExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(181);
				match(ID);
				{
				setState(182);
				match(LPAR);
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(183);
					exp();
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(184);
						match(COMMA);
						setState(185);
						exp();
						}
						}
						setState(190);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(193);
				match(RPAR);
				}
				}
				break;
			case 8:
				_localctx = new MethodExpContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(194);
				((MethodExpContext)_localctx).object = match(ID);
				setState(195);
				match(DOT);
				setState(196);
				((MethodExpContext)_localctx).methodName = match(ID);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(197);
					match(LPAR);
					setState(206);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(198);
						exp();
						setState(203);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(199);
							match(COMMA);
							setState(200);
							exp();
							}
							}
							setState(205);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(208);
					match(RPAR);
					}
				}

				}
				break;
			case 9:
				_localctx = new NewExpContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(211);
				match(NEW);
				setState(212);
				((NewExpContext)_localctx).className = match(ID);
				{
				setState(213);
				match(LPAR);
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
					{
					setState(214);
					exp();
					setState(219);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(215);
						match(COMMA);
						setState(216);
						exp();
						}
						}
						setState(221);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(224);
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
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				_localctx = new VarStmAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(ID);
				setState(228);
				match(ASM);
				setState(229);
				exp();
				}
				break;
			case 2:
				_localctx = new IfStmContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				match(IF);
				setState(231);
				((IfStmContext)_localctx).cond = exp();
				setState(232);
				match(THEN);
				setState(233);
				match(CLPAR);
				setState(234);
				((IfStmContext)_localctx).thenBranch = stms();
				setState(235);
				match(CRPAR);
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(236);
					match(ELSE);
					setState(237);
					match(CLPAR);
					setState(238);
					((IfStmContext)_localctx).elseBranch = stms();
					setState(239);
					match(CRPAR);
					}
				}

				}
				break;
			case 3:
				_localctx = new MethodStmContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				((MethodStmContext)_localctx).object = match(ID);
				setState(244);
				match(DOT);
				setState(245);
				((MethodStmContext)_localctx).methodName = match(ID);
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(246);
					match(LPAR);
					setState(255);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << MINUS) | (1L << BOOLVAL) | (1L << LPAR) | (1L << IF) | (1L << NULL) | (1L << NEW) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(247);
						exp();
						setState(252);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(248);
							match(COMMA);
							setState(249);
							exp();
							}
							}
							setState(254);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(257);
					match(RPAR);
					}
				}

				}
				break;
			case 4:
				_localctx = new PrintStmContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(260);
				match(PRINT);
				setState(261);
				match(LPAR);
				setState(262);
				exp();
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(263);
					match(COMMA);
					setState(264);
					exp();
					}
					}
					setState(269);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(270);
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
			setState(275); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(274);
				stm();
				}
				}
				setState(277); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u011a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\6\2(\n\2\r\2\16\2)\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64"+
		"\n\3\3\4\3\4\3\4\3\4\5\4:\n\4\3\4\3\4\3\4\3\4\7\4@\n\4\f\4\16\4C\13\4"+
		"\3\4\3\4\5\4G\n\4\3\4\3\4\3\4\3\4\6\4M\n\4\r\4\16\4N\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\6\5W\n\5\r\5\16\5X\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\7\bj\n\b\f\b\16\bm\13\b\5\bo\n\b\3\b\3\b\3\b\3\b\3\b"+
		"\6\bv\n\b\r\b\16\bw\3\b\3\b\5\b|\n\b\3\b\3\b\5\b\u0080\n\b\3\t\3\t\5\t"+
		"\u0084\n\t\3\n\3\n\3\13\3\13\3\13\5\13\u008b\n\13\3\f\3\f\3\f\5\f\u0090"+
		"\n\f\3\r\3\r\3\r\5\r\u0095\n\r\3\16\3\16\3\16\5\16\u009a\n\16\3\17\5\17"+
		"\u009d\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00b5\n\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\7\20\u00bd\n\20\f\20\16\20\u00c0\13\20\5\20"+
		"\u00c2\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00cc\n\20\f"+
		"\20\16\20\u00cf\13\20\5\20\u00d1\n\20\3\20\5\20\u00d4\n\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\7\20\u00dc\n\20\f\20\16\20\u00df\13\20\5\20\u00e1\n"+
		"\20\3\20\5\20\u00e4\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u00f4\n\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\7\21\u00fd\n\21\f\21\16\21\u0100\13\21\5\21\u0102\n\21\3\21\5\21"+
		"\u0105\n\21\3\21\3\21\3\21\3\21\3\21\7\21\u010c\n\21\f\21\16\21\u010f"+
		"\13\21\3\21\3\21\5\21\u0113\n\21\3\22\6\22\u0116\n\22\r\22\16\22\u0117"+
		"\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\b\4\2 \"))\3"+
		"\2\6\b\3\2\r\16\3\2\17\20\3\2\t\n\4\2\13\13\16\16\2\u0135\2\'\3\2\2\2"+
		"\4\63\3\2\2\2\6\65\3\2\2\2\bR\3\2\2\2\n\\\3\2\2\2\f_\3\2\2\2\16c\3\2\2"+
		"\2\20\u0083\3\2\2\2\22\u0085\3\2\2\2\24\u0087\3\2\2\2\26\u008c\3\2\2\2"+
		"\30\u0091\3\2\2\2\32\u0096\3\2\2\2\34\u009c\3\2\2\2\36\u00e3\3\2\2\2 "+
		"\u0112\3\2\2\2\"\u0115\3\2\2\2$%\5\4\3\2%&\7\3\2\2&(\3\2\2\2\'$\3\2\2"+
		"\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\3\3\2\2\2+,\5\b\5\2,-\5\24\13\2-\64"+
		"\3\2\2\2./\5\b\5\2/\60\5\"\22\2\60\64\3\2\2\2\61\64\5\24\13\2\62\64\5"+
		"\6\4\2\63+\3\2\2\2\63.\3\2\2\2\63\61\3\2\2\2\63\62\3\2\2\2\64\5\3\2\2"+
		"\2\65\66\7#\2\2\669\7)\2\2\678\7$\2\28:\7)\2\29\67\3\2\2\29:\3\2\2\2:"+
		"F\3\2\2\2;<\7\24\2\2<A\5\n\6\2=>\7\5\2\2>@\5\n\6\2?=\3\2\2\2@C\3\2\2\2"+
		"A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7\25\2\2EG\3\2\2\2F;\3\2\2"+
		"\2FG\3\2\2\2GH\3\2\2\2HL\7\26\2\2IJ\5\16\b\2JK\7\3\2\2KM\3\2\2\2LI\3\2"+
		"\2\2MN\3\2\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\7\27\2\2Q\7\3\2\2\2RV\7"+
		"\34\2\2ST\5\20\t\2TU\7\3\2\2UW\3\2\2\2VS\3\2\2\2WX\3\2\2\2XV\3\2\2\2X"+
		"Y\3\2\2\2YZ\3\2\2\2Z[\7\35\2\2[\t\3\2\2\2\\]\5\22\n\2]^\7)\2\2^\13\3\2"+
		"\2\2_`\5\n\6\2`a\7\f\2\2ab\5\24\13\2b\r\3\2\2\2cd\5\22\n\2de\7)\2\2en"+
		"\7\24\2\2fk\5\n\6\2gh\7\5\2\2hj\5\n\6\2ig\3\2\2\2jm\3\2\2\2ki\3\2\2\2"+
		"kl\3\2\2\2lo\3\2\2\2mk\3\2\2\2nf\3\2\2\2no\3\2\2\2op\3\2\2\2p{\7\25\2"+
		"\2qu\7\34\2\2rs\5\f\7\2st\7\3\2\2tv\3\2\2\2ur\3\2\2\2vw\3\2\2\2wu\3\2"+
		"\2\2wx\3\2\2\2xy\3\2\2\2yz\7\35\2\2z|\3\2\2\2{q\3\2\2\2{|\3\2\2\2|\177"+
		"\3\2\2\2}\u0080\5\24\13\2~\u0080\5\"\22\2\177}\3\2\2\2\177~\3\2\2\2\u0080"+
		"\17\3\2\2\2\u0081\u0084\5\f\7\2\u0082\u0084\5\16\b\2\u0083\u0081\3\2\2"+
		"\2\u0083\u0082\3\2\2\2\u0084\21\3\2\2\2\u0085\u0086\t\2\2\2\u0086\23\3"+
		"\2\2\2\u0087\u008a\5\26\f\2\u0088\u0089\t\3\2\2\u0089\u008b\5\24\13\2"+
		"\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\25\3\2\2\2\u008c\u008f"+
		"\5\30\r\2\u008d\u008e\t\4\2\2\u008e\u0090\5\26\f\2\u008f\u008d\3\2\2\2"+
		"\u008f\u0090\3\2\2\2\u0090\27\3\2\2\2\u0091\u0094\5\32\16\2\u0092\u0093"+
		"\t\5\2\2\u0093\u0095\5\30\r\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2"+
		"\u0095\31\3\2\2\2\u0096\u0099\5\34\17\2\u0097\u0098\t\6\2\2\u0098\u009a"+
		"\5\32\16\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\33\3\2\2\2\u009b"+
		"\u009d\t\7\2\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2"+
		"\2\2\u009e\u009f\5\36\20\2\u009f\35\3\2\2\2\u00a0\u00e4\7(\2\2\u00a1\u00e4"+
		"\7\21\2\2\u00a2\u00e4\7%\2\2\u00a3\u00a4\7\24\2\2\u00a4\u00a5\5\24\13"+
		"\2\u00a5\u00a6\7\25\2\2\u00a6\u00e4\3\2\2\2\u00a7\u00a8\7\30\2\2\u00a8"+
		"\u00a9\7\24\2\2\u00a9\u00aa\5\24\13\2\u00aa\u00ab\7\25\2\2\u00ab\u00ac"+
		"\7\31\2\2\u00ac\u00ad\7\26\2\2\u00ad\u00ae\5\24\13\2\u00ae\u00b4\7\27"+
		"\2\2\u00af\u00b0\7\32\2\2\u00b0\u00b1\7\26\2\2\u00b1\u00b2\5\24\13\2\u00b2"+
		"\u00b3\7\27\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00af\3\2\2\2\u00b4\u00b5\3"+
		"\2\2\2\u00b5\u00e4\3\2\2\2\u00b6\u00e4\7)\2\2\u00b7\u00b8\7)\2\2\u00b8"+
		"\u00c1\7\24\2\2\u00b9\u00be\5\24\13\2\u00ba\u00bb\7\5\2\2\u00bb\u00bd"+
		"\5\24\13\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2"+
		"\u00be\u00bf\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00b9"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00e4\7\25\2\2"+
		"\u00c4\u00c5\7)\2\2\u00c5\u00c6\7\'\2\2\u00c6\u00d3\7)\2\2\u00c7\u00d0"+
		"\7\24\2\2\u00c8\u00cd\5\24\13\2\u00c9\u00ca\7\5\2\2\u00ca\u00cc\5\24\13"+
		"\2\u00cb\u00c9\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\7\25\2\2\u00d3\u00c7\3"+
		"\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00e4\3\2\2\2\u00d5\u00d6\7&\2\2\u00d6"+
		"\u00d7\7)\2\2\u00d7\u00e0\7\24\2\2\u00d8\u00dd\5\24\13\2\u00d9\u00da\7"+
		"\5\2\2\u00da\u00dc\5\24\13\2\u00db\u00d9\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00d8\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e4\7\25\2\2\u00e3\u00a0\3\2\2\2\u00e3\u00a1\3\2\2\2\u00e3\u00a2\3"+
		"\2\2\2\u00e3\u00a3\3\2\2\2\u00e3\u00a7\3\2\2\2\u00e3\u00b6\3\2\2\2\u00e3"+
		"\u00b7\3\2\2\2\u00e3\u00c4\3\2\2\2\u00e3\u00d5\3\2\2\2\u00e4\37\3\2\2"+
		"\2\u00e5\u00e6\7)\2\2\u00e6\u00e7\7\f\2\2\u00e7\u0113\5\24\13\2\u00e8"+
		"\u00e9\7\30\2\2\u00e9\u00ea\5\24\13\2\u00ea\u00eb\7\31\2\2\u00eb\u00ec"+
		"\7\26\2\2\u00ec\u00ed\5\"\22\2\u00ed\u00f3\7\27\2\2\u00ee\u00ef\7\32\2"+
		"\2\u00ef\u00f0\7\26\2\2\u00f0\u00f1\5\"\22\2\u00f1\u00f2\7\27\2\2\u00f2"+
		"\u00f4\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u0113\3\2"+
		"\2\2\u00f5\u00f6\7)\2\2\u00f6\u00f7\7\'\2\2\u00f7\u0104\7)\2\2\u00f8\u0101"+
		"\7\24\2\2\u00f9\u00fe\5\24\13\2\u00fa\u00fb\7\5\2\2\u00fb\u00fd\5\24\13"+
		"\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff"+
		"\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u00f9\3\2\2\2\u0101"+
		"\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105\7\25\2\2\u0104\u00f8\3"+
		"\2\2\2\u0104\u0105\3\2\2\2\u0105\u0113\3\2\2\2\u0106\u0107\7\33\2\2\u0107"+
		"\u0108\7\24\2\2\u0108\u010d\5\24\13\2\u0109\u010a\7\5\2\2\u010a\u010c"+
		"\5\24\13\2\u010b\u0109\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2"+
		"\u010d\u010e\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111"+
		"\7\25\2\2\u0111\u0113\3\2\2\2\u0112\u00e5\3\2\2\2\u0112\u00e8\3\2\2\2"+
		"\u0112\u00f5\3\2\2\2\u0112\u0106\3\2\2\2\u0113!\3\2\2\2\u0114\u0116\5"+
		" \21\2\u0115\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118#\3\2\2\2$)\639AFNXknw{\177\u0083\u008a\u008f\u0094"+
		"\u0099\u009c\u00b4\u00be\u00c1\u00cd\u00d0\u00d3\u00dd\u00e0\u00e3\u00f3"+
		"\u00fe\u0101\u0104\u010d\u0112\u0117";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}