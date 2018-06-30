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
		PLUS=11, MINUS=12, TIMES=13, DIV=14, TRUE=15, FALSE=16, LPAR=17, RPAR=18, 
		CLPAR=19, CRPAR=20, IF=21, THEN=22, ELSE=23, PRINT=24, LET=25, IN=26, 
		VAR=27, FUN=28, INT=29, BOOL=30, VOID=31, CLASS=32, EXTENDS=33, NULL=34, 
		NEW=35, DOT=36, INTEGER=37, ID=38, WS=39, LINECOMENTS=40, BLOCKCOMENTS=41, 
		ERR=42;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SEMIC", "COLON", "COMMA", "EQ", "LEQ", "GEQ", "OR", "AND", "NOT", "ASM", 
		"PLUS", "MINUS", "TIMES", "DIV", "TRUE", "FALSE", "LPAR", "RPAR", "CLPAR", 
		"CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", "VAR", "FUN", "INT", 
		"BOOL", "VOID", "CLASS", "EXTENDS", "NULL", "NEW", "DOT", "DIGIT", "INTEGER", 
		"CHAR", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "','", "'=='", "'<='", "'>='", "'||'", "'&&'", "'not'", 
		"'='", "'+'", "'-'", "'*'", "'/'", "'true'", "'false'", "'('", "')'", 
		"'{'", "'}'", "'if'", "'then'", "'else'", "'print'", "'let'", "'in'", 
		"'var'", "'fun'", "'int'", "'bool'", "'void'", "'class'", "'extends'", 
		null, "'new'", "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMIC", "COLON", "COMMA", "EQ", "LEQ", "GEQ", "OR", "AND", "NOT", 
		"ASM", "PLUS", "MINUS", "TIMES", "DIV", "TRUE", "FALSE", "LPAR", "RPAR", 
		"CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", "IN", "VAR", "FUN", 
		"INT", "BOOL", "VOID", "CLASS", "EXTENDS", "NULL", "NEW", "DOT", "INTEGER", 
		"ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u0117\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!"+
		"\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00d8"+
		"\n#\3$\3$\3$\3$\3%\3%\3&\3&\3\'\6\'\u00e3\n\'\r\'\16\'\u00e4\3(\3(\3)"+
		"\3)\3)\7)\u00ec\n)\f)\16)\u00ef\13)\3*\3*\3*\3*\3+\3+\3+\3+\7+\u00f9\n"+
		"+\f+\16+\u00fc\13+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\7,\u0109\n,\f,\16"+
		",\u010c\13,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\2\2.\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\'"+
		"O\2Q(S)U*W+Y,\3\2\b\4\2C\\c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\4\2,,\61"+
		"\61\3\2,,\3\2\61\61\2\u011d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2M\3\2\2\2\2Q\3\2\2\2\2S\3\2"+
		"\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3\2\2\2\5]\3\2\2\2\7_\3\2\2\2"+
		"\ta\3\2\2\2\13d\3\2\2\2\rg\3\2\2\2\17j\3\2\2\2\21m\3\2\2\2\23p\3\2\2\2"+
		"\25t\3\2\2\2\27v\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2\2\37~\3\2\2"+
		"\2!\u0083\3\2\2\2#\u0089\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u008f"+
		"\3\2\2\2+\u0091\3\2\2\2-\u0094\3\2\2\2/\u0099\3\2\2\2\61\u009e\3\2\2\2"+
		"\63\u00a4\3\2\2\2\65\u00a8\3\2\2\2\67\u00ab\3\2\2\29\u00af\3\2\2\2;\u00b3"+
		"\3\2\2\2=\u00b7\3\2\2\2?\u00bc\3\2\2\2A\u00c1\3\2\2\2C\u00c7\3\2\2\2E"+
		"\u00d7\3\2\2\2G\u00d9\3\2\2\2I\u00dd\3\2\2\2K\u00df\3\2\2\2M\u00e2\3\2"+
		"\2\2O\u00e6\3\2\2\2Q\u00e8\3\2\2\2S\u00f0\3\2\2\2U\u00f4\3\2\2\2W\u00ff"+
		"\3\2\2\2Y\u0112\3\2\2\2[\\\7=\2\2\\\4\3\2\2\2]^\7<\2\2^\6\3\2\2\2_`\7"+
		".\2\2`\b\3\2\2\2ab\7?\2\2bc\7?\2\2c\n\3\2\2\2de\7>\2\2ef\7?\2\2f\f\3\2"+
		"\2\2gh\7@\2\2hi\7?\2\2i\16\3\2\2\2jk\7~\2\2kl\7~\2\2l\20\3\2\2\2mn\7("+
		"\2\2no\7(\2\2o\22\3\2\2\2pq\7p\2\2qr\7q\2\2rs\7v\2\2s\24\3\2\2\2tu\7?"+
		"\2\2u\26\3\2\2\2vw\7-\2\2w\30\3\2\2\2xy\7/\2\2y\32\3\2\2\2z{\7,\2\2{\34"+
		"\3\2\2\2|}\7\61\2\2}\36\3\2\2\2~\177\7v\2\2\177\u0080\7t\2\2\u0080\u0081"+
		"\7w\2\2\u0081\u0082\7g\2\2\u0082 \3\2\2\2\u0083\u0084\7h\2\2\u0084\u0085"+
		"\7c\2\2\u0085\u0086\7n\2\2\u0086\u0087\7u\2\2\u0087\u0088\7g\2\2\u0088"+
		"\"\3\2\2\2\u0089\u008a\7*\2\2\u008a$\3\2\2\2\u008b\u008c\7+\2\2\u008c"+
		"&\3\2\2\2\u008d\u008e\7}\2\2\u008e(\3\2\2\2\u008f\u0090\7\177\2\2\u0090"+
		"*\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7h\2\2\u0093,\3\2\2\2\u0094\u0095"+
		"\7v\2\2\u0095\u0096\7j\2\2\u0096\u0097\7g\2\2\u0097\u0098\7p\2\2\u0098"+
		".\3\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7n\2\2\u009b\u009c\7u\2\2\u009c"+
		"\u009d\7g\2\2\u009d\60\3\2\2\2\u009e\u009f\7r\2\2\u009f\u00a0\7t\2\2\u00a0"+
		"\u00a1\7k\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7v\2\2\u00a3\62\3\2\2\2\u00a4"+
		"\u00a5\7n\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7v\2\2\u00a7\64\3\2\2\2\u00a8"+
		"\u00a9\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\66\3\2\2\2\u00ab\u00ac\7x\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad\u00ae\7t\2\2\u00ae8\3\2\2\2\u00af\u00b0\7h\2\2\u00b0"+
		"\u00b1\7w\2\2\u00b1\u00b2\7p\2\2\u00b2:\3\2\2\2\u00b3\u00b4\7k\2\2\u00b4"+
		"\u00b5\7p\2\2\u00b5\u00b6\7v\2\2\u00b6<\3\2\2\2\u00b7\u00b8\7d\2\2\u00b8"+
		"\u00b9\7q\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7n\2\2\u00bb>\3\2\2\2\u00bc"+
		"\u00bd\7x\2\2\u00bd\u00be\7q\2\2\u00be\u00bf\7k\2\2\u00bf\u00c0\7f\2\2"+
		"\u00c0@\3\2\2\2\u00c1\u00c2\7e\2\2\u00c2\u00c3\7n\2\2\u00c3\u00c4\7c\2"+
		"\2\u00c4\u00c5\7u\2\2\u00c5\u00c6\7u\2\2\u00c6B\3\2\2\2\u00c7\u00c8\7"+
		"g\2\2\u00c8\u00c9\7z\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc"+
		"\7p\2\2\u00cc\u00cd\7f\2\2\u00cd\u00ce\7u\2\2\u00ceD\3\2\2\2\u00cf\u00d0"+
		"\7p\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2\7n\2\2\u00d2\u00d8\7n\2\2\u00d3"+
		"\u00d4\7P\2\2\u00d4\u00d5\7W\2\2\u00d5\u00d6\7N\2\2\u00d6\u00d8\7N\2\2"+
		"\u00d7\u00cf\3\2\2\2\u00d7\u00d3\3\2\2\2\u00d8F\3\2\2\2\u00d9\u00da\7"+
		"p\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7y\2\2\u00dcH\3\2\2\2\u00dd\u00de"+
		"\7\60\2\2\u00deJ\3\2\2\2\u00df\u00e0\4\62;\2\u00e0L\3\2\2\2\u00e1\u00e3"+
		"\5K&\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5N\3\2\2\2\u00e6\u00e7\t\2\2\2\u00e7P\3\2\2\2\u00e8"+
		"\u00ed\5O(\2\u00e9\u00ec\5O(\2\u00ea\u00ec\5K&\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00eeR\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f1\t\3\2\2\u00f1\u00f2"+
		"\3\2\2\2\u00f2\u00f3\b*\2\2\u00f3T\3\2\2\2\u00f4\u00f5\7\61\2\2\u00f5"+
		"\u00f6\7\61\2\2\u00f6\u00fa\3\2\2\2\u00f7\u00f9\n\4\2\2\u00f8\u00f7\3"+
		"\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fd\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\b+\2\2\u00feV\3\2\2\2\u00ff"+
		"\u0100\7\61\2\2\u0100\u0101\7,\2\2\u0101\u010a\3\2\2\2\u0102\u0109\n\5"+
		"\2\2\u0103\u0104\7\61\2\2\u0104\u0109\n\6\2\2\u0105\u0106\7,\2\2\u0106"+
		"\u0109\n\7\2\2\u0107\u0109\5W,\2\u0108\u0102\3\2\2\2\u0108\u0103\3\2\2"+
		"\2\u0108\u0105\3\2\2\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u010e\7,\2\2\u010e\u010f\7\61\2\2\u010f\u0110\3\2\2\2\u0110\u0111\b,"+
		"\2\2\u0111X\3\2\2\2\u0112\u0113\13\2\2\2\u0113\u0114\b-\3\2\u0114\u0115"+
		"\3\2\2\2\u0115\u0116\b-\4\2\u0116Z\3\2\2\2\n\2\u00d7\u00e4\u00eb\u00ed"+
		"\u00fa\u0108\u010a\5\b\2\2\3-\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}