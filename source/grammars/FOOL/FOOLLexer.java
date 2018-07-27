// Generated from FOOL.g4 by ANTLR 4.7.1

package grammars.FOOL;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, COLON=2, COMMA=3, EQ=4, LEQ=5, GEQ=6, OR=7, AND=8, NOT=9, ASM=10, 
		PLUS=11, MINUS=12, TIMES=13, DIV=14, BOOLVAL=15, TRUE=16, FALSE=17, LPAR=18, 
		RPAR=19, CLPAR=20, CRPAR=21, IF=22, THEN=23, ELSE=24, PRINT=25, LET=26, 
		IN=27, INT=28, BOOL=29, VOID=30, CLASS=31, EXTENDS=32, NULL=33, NEW=34, 
		DOT=35, RETURN=36, INTEGER=37, ID=38, WS=39, LINECOMENTS=40, BLOCKCOMENTS=41, 
		ERR=42;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SEMIC", "COLON", "COMMA", "EQ", "LEQ", "GEQ", "OR", "AND", "NOT", "ASM", 
		"PLUS", "MINUS", "TIMES", "DIV", "BOOLVAL", "TRUE", "FALSE", "LPAR", "RPAR", 
		"CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", "INT", "BOOL", 
		"VOID", "CLASS", "EXTENDS", "NULL", "NEW", "DOT", "RETURN", "DIGIT", "INTEGER", 
		"CHAR", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "','", "'=='", "'<='", "'>='", "'||'", "'&&'", "'not'", 
		"'='", "'+'", "'-'", "'*'", "'/'", null, "'true'", "'false'", "'('", "')'", 
		"'{'", "'}'", "'if'", "'then'", "'else'", "'print'", "'let'", "'in'", 
		"'int'", "'bool'", "'void'", "'class'", "'extends'", null, "'new'", "'.'", 
		"'return'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMIC", "COLON", "COMMA", "EQ", "LEQ", "GEQ", "OR", "AND", "NOT", 
		"ASM", "PLUS", "MINUS", "TIMES", "DIV", "BOOLVAL", "TRUE", "FALSE", "LPAR", 
		"RPAR", "CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", 
		"INT", "BOOL", "VOID", "CLASS", "EXTENDS", "NULL", "NEW", "DOT", "RETURN", 
		"INTEGER", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
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


	   //there is a much better way to do this, check the ANTLR guide
	   //I will leave it like this for now just because it is quick
	   //but it doesn't work well
	   public int lexicalErrors=0;


	public FOOLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 43:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("Invalid char: "+ getText()); lexicalErrors++; 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u011a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\5\20\u0081\n\20\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\5\"\u00d4"+
		"\n\"\3#\3#\3#\3#\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3\'\6\'\u00e6\n\'\r"+
		"\'\16\'\u00e7\3(\3(\3)\3)\3)\7)\u00ef\n)\f)\16)\u00f2\13)\3*\3*\3*\3*"+
		"\3+\3+\3+\3+\7+\u00fc\n+\f+\16+\u00ff\13+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3"+
		",\3,\7,\u010c\n,\f,\16,\u010f\13,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\2\2.\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I&K\2M\'O\2Q(S)U*W+Y,\3\2\b\4\2C\\c|\5\2\13\f\17\17\"\"\4"+
		"\2\f\f\17\17\4\2,,\61\61\3\2,,\3\2\61\61\2\u0121\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2M\3\2\2"+
		"\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3\2\2\2\5"+
		"]\3\2\2\2\7_\3\2\2\2\ta\3\2\2\2\13d\3\2\2\2\rg\3\2\2\2\17j\3\2\2\2\21"+
		"m\3\2\2\2\23p\3\2\2\2\25t\3\2\2\2\27v\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2"+
		"\35|\3\2\2\2\37\u0080\3\2\2\2!\u0082\3\2\2\2#\u0087\3\2\2\2%\u008d\3\2"+
		"\2\2\'\u008f\3\2\2\2)\u0091\3\2\2\2+\u0093\3\2\2\2-\u0095\3\2\2\2/\u0098"+
		"\3\2\2\2\61\u009d\3\2\2\2\63\u00a2\3\2\2\2\65\u00a8\3\2\2\2\67\u00ac\3"+
		"\2\2\29\u00af\3\2\2\2;\u00b3\3\2\2\2=\u00b8\3\2\2\2?\u00bd\3\2\2\2A\u00c3"+
		"\3\2\2\2C\u00d3\3\2\2\2E\u00d5\3\2\2\2G\u00d9\3\2\2\2I\u00db\3\2\2\2K"+
		"\u00e2\3\2\2\2M\u00e5\3\2\2\2O\u00e9\3\2\2\2Q\u00eb\3\2\2\2S\u00f3\3\2"+
		"\2\2U\u00f7\3\2\2\2W\u0102\3\2\2\2Y\u0115\3\2\2\2[\\\7=\2\2\\\4\3\2\2"+
		"\2]^\7<\2\2^\6\3\2\2\2_`\7.\2\2`\b\3\2\2\2ab\7?\2\2bc\7?\2\2c\n\3\2\2"+
		"\2de\7>\2\2ef\7?\2\2f\f\3\2\2\2gh\7@\2\2hi\7?\2\2i\16\3\2\2\2jk\7~\2\2"+
		"kl\7~\2\2l\20\3\2\2\2mn\7(\2\2no\7(\2\2o\22\3\2\2\2pq\7p\2\2qr\7q\2\2"+
		"rs\7v\2\2s\24\3\2\2\2tu\7?\2\2u\26\3\2\2\2vw\7-\2\2w\30\3\2\2\2xy\7/\2"+
		"\2y\32\3\2\2\2z{\7,\2\2{\34\3\2\2\2|}\7\61\2\2}\36\3\2\2\2~\u0081\5!\21"+
		"\2\177\u0081\5#\22\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081 \3\2\2\2\u0082"+
		"\u0083\7v\2\2\u0083\u0084\7t\2\2\u0084\u0085\7w\2\2\u0085\u0086\7g\2\2"+
		"\u0086\"\3\2\2\2\u0087\u0088\7h\2\2\u0088\u0089\7c\2\2\u0089\u008a\7n"+
		"\2\2\u008a\u008b\7u\2\2\u008b\u008c\7g\2\2\u008c$\3\2\2\2\u008d\u008e"+
		"\7*\2\2\u008e&\3\2\2\2\u008f\u0090\7+\2\2\u0090(\3\2\2\2\u0091\u0092\7"+
		"}\2\2\u0092*\3\2\2\2\u0093\u0094\7\177\2\2\u0094,\3\2\2\2\u0095\u0096"+
		"\7k\2\2\u0096\u0097\7h\2\2\u0097.\3\2\2\2\u0098\u0099\7v\2\2\u0099\u009a"+
		"\7j\2\2\u009a\u009b\7g\2\2\u009b\u009c\7p\2\2\u009c\60\3\2\2\2\u009d\u009e"+
		"\7g\2\2\u009e\u009f\7n\2\2\u009f\u00a0\7u\2\2\u00a0\u00a1\7g\2\2\u00a1"+
		"\62\3\2\2\2\u00a2\u00a3\7r\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5\7k\2\2\u00a5"+
		"\u00a6\7p\2\2\u00a6\u00a7\7v\2\2\u00a7\64\3\2\2\2\u00a8\u00a9\7n\2\2\u00a9"+
		"\u00aa\7g\2\2\u00aa\u00ab\7v\2\2\u00ab\66\3\2\2\2\u00ac\u00ad\7k\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae8\3\2\2\2\u00af\u00b0\7k\2\2\u00b0\u00b1\7p\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2:\3\2\2\2\u00b3\u00b4\7d\2\2\u00b4\u00b5\7q\2\2\u00b5"+
		"\u00b6\7q\2\2\u00b6\u00b7\7n\2\2\u00b7<\3\2\2\2\u00b8\u00b9\7x\2\2\u00b9"+
		"\u00ba\7q\2\2\u00ba\u00bb\7k\2\2\u00bb\u00bc\7f\2\2\u00bc>\3\2\2\2\u00bd"+
		"\u00be\7e\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7u\2\2"+
		"\u00c1\u00c2\7u\2\2\u00c2@\3\2\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7z\2"+
		"\2\u00c5\u00c6\7v\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9"+
		"\7f\2\2\u00c9\u00ca\7u\2\2\u00caB\3\2\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd"+
		"\7w\2\2\u00cd\u00ce\7n\2\2\u00ce\u00d4\7n\2\2\u00cf\u00d0\7P\2\2\u00d0"+
		"\u00d1\7W\2\2\u00d1\u00d2\7N\2\2\u00d2\u00d4\7N\2\2\u00d3\u00cb\3\2\2"+
		"\2\u00d3\u00cf\3\2\2\2\u00d4D\3\2\2\2\u00d5\u00d6\7p\2\2\u00d6\u00d7\7"+
		"g\2\2\u00d7\u00d8\7y\2\2\u00d8F\3\2\2\2\u00d9\u00da\7\60\2\2\u00daH\3"+
		"\2\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de\7v\2\2\u00de"+
		"\u00df\7w\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7p\2\2\u00e1J\3\2\2\2\u00e2"+
		"\u00e3\4\62;\2\u00e3L\3\2\2\2\u00e4\u00e6\5K&\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8N\3\2\2\2"+
		"\u00e9\u00ea\t\2\2\2\u00eaP\3\2\2\2\u00eb\u00f0\5O(\2\u00ec\u00ef\5O("+
		"\2\u00ed\u00ef\5K&\2\u00ee\u00ec\3\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1R\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f4\t\3\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b*"+
		"\2\2\u00f6T\3\2\2\2\u00f7\u00f8\7\61\2\2\u00f8\u00f9\7\61\2\2\u00f9\u00fd"+
		"\3\2\2\2\u00fa\u00fc\n\4\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u00fd\3\2"+
		"\2\2\u0100\u0101\b+\2\2\u0101V\3\2\2\2\u0102\u0103\7\61\2\2\u0103\u0104"+
		"\7,\2\2\u0104\u010d\3\2\2\2\u0105\u010c\n\5\2\2\u0106\u0107\7\61\2\2\u0107"+
		"\u010c\n\6\2\2\u0108\u0109\7,\2\2\u0109\u010c\n\7\2\2\u010a\u010c\5W,"+
		"\2\u010b\u0105\3\2\2\2\u010b\u0106\3\2\2\2\u010b\u0108\3\2\2\2\u010b\u010a"+
		"\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u0110\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0111\7,\2\2\u0111\u0112\7\61"+
		"\2\2\u0112\u0113\3\2\2\2\u0113\u0114\b,\2\2\u0114X\3\2\2\2\u0115\u0116"+
		"\13\2\2\2\u0116\u0117\b-\3\2\u0117\u0118\3\2\2\2\u0118\u0119\b-\4\2\u0119"+
		"Z\3\2\2\2\13\2\u0080\u00d3\u00e7\u00ee\u00f0\u00fd\u010b\u010d\5\b\2\2"+
		"\3-\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}