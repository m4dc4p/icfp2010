// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g 2010-06-19 15:48:18

package icfp2010.circuits.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CircuitLexer extends Lexer {
    public static final int COLON=9;
    public static final int GATE_DELIM=10;
    public static final int WS=14;
    public static final int T__15=15;
    public static final int SIDE=13;
    public static final int DIGIT=12;
    public static final int CIRCUIT=4;
    public static final int GATES=5;
    public static final int EOF=-1;
    public static final int IO_DELIM=11;
    public static final int WORLD_POSITION=7;
    public static final int GATE=6;
    public static final int POSITION=8;

    // delegates
    // delegators

    public CircuitLexer() {;} 
    public CircuitLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CircuitLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g"; }

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:11:7: ( 'X' )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:11:9: 'X'
            {
            match('X'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "IO_DELIM"
    public final void mIO_DELIM() throws RecognitionException {
        try {
            int _type = IO_DELIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:39:2: ( '0#' )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:39:4: '0#'
            {
            match("0#"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IO_DELIM"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:40:7: ( ':' )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:40:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "GATE_DELIM"
    public final void mGATE_DELIM() throws RecognitionException {
        try {
            int _type = GATE_DELIM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:42:2: ( ',' )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:42:4: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GATE_DELIM"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:43:7: ( ( '0' .. '9' )+ )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:43:9: ( '0' .. '9' )+
            {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:43:9: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:43:10: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "SIDE"
    public final void mSIDE() throws RecognitionException {
        try {
            int _type = SIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:44:6: ( 'L' | 'R' )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='R' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIDE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:47:5: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:47:9: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:8: ( T__15 | IO_DELIM | COLON | GATE_DELIM | DIGIT | SIDE | WS )
        int alt2=7;
        switch ( input.LA(1) ) {
        case 'X':
            {
            alt2=1;
            }
            break;
        case '0':
            {
            int LA2_2 = input.LA(2);

            if ( (LA2_2=='#') ) {
                alt2=2;
            }
            else {
                alt2=5;}
            }
            break;
        case ':':
            {
            alt2=3;
            }
            break;
        case ',':
            {
            alt2=4;
            }
            break;
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt2=5;
            }
            break;
        case 'L':
        case 'R':
            {
            alt2=6;
            }
            break;
        case '\t':
        case '\n':
        case '\r':
        case ' ':
            {
            alt2=7;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 2, 0, input);

            throw nvae;
        }

        switch (alt2) {
            case 1 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:10: T__15
                {
                mT__15(); 

                }
                break;
            case 2 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:16: IO_DELIM
                {
                mIO_DELIM(); 

                }
                break;
            case 3 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:25: COLON
                {
                mCOLON(); 

                }
                break;
            case 4 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:31: GATE_DELIM
                {
                mGATE_DELIM(); 

                }
                break;
            case 5 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:42: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 6 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:48: SIDE
                {
                mSIDE(); 

                }
                break;
            case 7 :
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:1:53: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}