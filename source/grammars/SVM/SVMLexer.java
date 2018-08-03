// Generated from SVM.g4 by ANTLR 4.7.1

package grammars.SVM;
import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SVMLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUSH=1, POP=2, ADD=3, SUB=4, MULT=5, DIV=6, STOREW=7, LOADW=8, BRANCH=9, 
		BRANCHEQ=10, BRANCHLESSEQ=11, JS=12, LOADRA=13, STORERA=14, LOADRV=15, 
		STORERV=16, LOADFP=17, STOREFP=18, COPYFP=19, LOADHP=20, STOREHP=21, PRINT=22, 
		HALT=23, NEW=24, CPHEAD=25, JSMETH=26, COL=27, LABEL=28, NUMBER=29, WHITESP=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "NEW", 
		"CPHEAD", "JSMETH", "COL", "LABEL", "NUMBER", "WHITESP"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'push'", "'pop'", "'add'", "'sub'", "'mult'", "'div'", "'sw'", 
		"'lw'", "'b'", "'beq'", "'bleq'", "'js'", "'lra'", "'sra'", "'lrv'", "'srv'", 
		"'lfp'", "'sfp'", "'cfp'", "'lhp'", "'shp'", "'print'", "'halt'", "'new'", 
		"'cp'", "'jsmeth'", "':'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PUSH", "POP", "ADD", "SUB", "MULT", "DIV", "STOREW", "LOADW", "BRANCH", 
		"BRANCHEQ", "BRANCHLESSEQ", "JS", "LOADRA", "STORERA", "LOADRV", "STORERV", 
		"LOADFP", "STOREFP", "COPYFP", "LOADHP", "STOREHP", "PRINT", "HALT", "NEW", 
		"CPHEAD", "JSMETH", "COL", "LABEL", "NUMBER", "WHITESP"
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


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2 \u00c7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\35\3\35\7\35\u00af\n\35\f\35\16\35\u00b2\13\35\3\36\3\36\5\36\u00b6"+
		"\n\36\3\36\3\36\7\36\u00ba\n\36\f\36\16\36\u00bd\13\36\5\36\u00bf\n\36"+
		"\3\37\6\37\u00c2\n\37\r\37\16\37\u00c3\3\37\3\37\2\2 \3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\5\4\2C\\c"+
		"|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\2\u00cb\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2"+
		"\5D\3\2\2\2\7H\3\2\2\2\tL\3\2\2\2\13P\3\2\2\2\rU\3\2\2\2\17Y\3\2\2\2\21"+
		"\\\3\2\2\2\23_\3\2\2\2\25a\3\2\2\2\27e\3\2\2\2\31j\3\2\2\2\33m\3\2\2\2"+
		"\35q\3\2\2\2\37u\3\2\2\2!y\3\2\2\2#}\3\2\2\2%\u0081\3\2\2\2\'\u0085\3"+
		"\2\2\2)\u0089\3\2\2\2+\u008d\3\2\2\2-\u0091\3\2\2\2/\u0097\3\2\2\2\61"+
		"\u009c\3\2\2\2\63\u00a0\3\2\2\2\65\u00a3\3\2\2\2\67\u00aa\3\2\2\29\u00ac"+
		"\3\2\2\2;\u00be\3\2\2\2=\u00c1\3\2\2\2?@\7r\2\2@A\7w\2\2AB\7u\2\2BC\7"+
		"j\2\2C\4\3\2\2\2DE\7r\2\2EF\7q\2\2FG\7r\2\2G\6\3\2\2\2HI\7c\2\2IJ\7f\2"+
		"\2JK\7f\2\2K\b\3\2\2\2LM\7u\2\2MN\7w\2\2NO\7d\2\2O\n\3\2\2\2PQ\7o\2\2"+
		"QR\7w\2\2RS\7n\2\2ST\7v\2\2T\f\3\2\2\2UV\7f\2\2VW\7k\2\2WX\7x\2\2X\16"+
		"\3\2\2\2YZ\7u\2\2Z[\7y\2\2[\20\3\2\2\2\\]\7n\2\2]^\7y\2\2^\22\3\2\2\2"+
		"_`\7d\2\2`\24\3\2\2\2ab\7d\2\2bc\7g\2\2cd\7s\2\2d\26\3\2\2\2ef\7d\2\2"+
		"fg\7n\2\2gh\7g\2\2hi\7s\2\2i\30\3\2\2\2jk\7l\2\2kl\7u\2\2l\32\3\2\2\2"+
		"mn\7n\2\2no\7t\2\2op\7c\2\2p\34\3\2\2\2qr\7u\2\2rs\7t\2\2st\7c\2\2t\36"+
		"\3\2\2\2uv\7n\2\2vw\7t\2\2wx\7x\2\2x \3\2\2\2yz\7u\2\2z{\7t\2\2{|\7x\2"+
		"\2|\"\3\2\2\2}~\7n\2\2~\177\7h\2\2\177\u0080\7r\2\2\u0080$\3\2\2\2\u0081"+
		"\u0082\7u\2\2\u0082\u0083\7h\2\2\u0083\u0084\7r\2\2\u0084&\3\2\2\2\u0085"+
		"\u0086\7e\2\2\u0086\u0087\7h\2\2\u0087\u0088\7r\2\2\u0088(\3\2\2\2\u0089"+
		"\u008a\7n\2\2\u008a\u008b\7j\2\2\u008b\u008c\7r\2\2\u008c*\3\2\2\2\u008d"+
		"\u008e\7u\2\2\u008e\u008f\7j\2\2\u008f\u0090\7r\2\2\u0090,\3\2\2\2\u0091"+
		"\u0092\7r\2\2\u0092\u0093\7t\2\2\u0093\u0094\7k\2\2\u0094\u0095\7p\2\2"+
		"\u0095\u0096\7v\2\2\u0096.\3\2\2\2\u0097\u0098\7j\2\2\u0098\u0099\7c\2"+
		"\2\u0099\u009a\7n\2\2\u009a\u009b\7v\2\2\u009b\60\3\2\2\2\u009c\u009d"+
		"\7p\2\2\u009d\u009e\7g\2\2\u009e\u009f\7y\2\2\u009f\62\3\2\2\2\u00a0\u00a1"+
		"\7e\2\2\u00a1\u00a2\7r\2\2\u00a2\64\3\2\2\2\u00a3\u00a4\7l\2\2\u00a4\u00a5"+
		"\7u\2\2\u00a5\u00a6\7o\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7v\2\2\u00a8"+
		"\u00a9\7j\2\2\u00a9\66\3\2\2\2\u00aa\u00ab\7<\2\2\u00ab8\3\2\2\2\u00ac"+
		"\u00b0\t\2\2\2\u00ad\u00af\t\3\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2"+
		"\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1:\3\2\2\2\u00b2\u00b0"+
		"\3\2\2\2\u00b3\u00bf\7\62\2\2\u00b4\u00b6\7/\2\2\u00b5\u00b4\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00bb\4\63;\2\u00b8\u00ba\4\62"+
		";\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be\u00b3\3\2"+
		"\2\2\u00be\u00b5\3\2\2\2\u00bf<\3\2\2\2\u00c0\u00c2\t\4\2\2\u00c1\u00c0"+
		"\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c6\b\37\2\2\u00c6>\3\2\2\2\b\2\u00b0\u00b5\u00bb"+
		"\u00be\u00c3\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}