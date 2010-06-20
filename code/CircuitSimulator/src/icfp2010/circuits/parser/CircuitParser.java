// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g 2010-06-19 15:48:18

package icfp2010.circuits.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class CircuitParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CIRCUIT", "GATES", "GATE", "WORLD_POSITION", "POSITION", "COLON", "GATE_DELIM", "IO_DELIM", "DIGIT", "SIDE", "WS", "'X'"
    };
    public static final int COLON=9;
    public static final int GATE_DELIM=10;
    public static final int WS=14;
    public static final int SIDE=13;
    public static final int T__15=15;
    public static final int DIGIT=12;
    public static final int CIRCUIT=4;
    public static final int EOF=-1;
    public static final int GATES=5;
    public static final int IO_DELIM=11;
    public static final int WORLD_POSITION=7;
    public static final int POSITION=8;
    public static final int GATE=6;

    // delegates
    // delegators


        public CircuitParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CircuitParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CircuitParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g"; }


    public static class circuit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "circuit"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:24:1: circuit : world_input gates world_output -> ^( CIRCUIT world_input gates world_output ) ;
    public final CircuitParser.circuit_return circuit() throws RecognitionException {
        CircuitParser.circuit_return retval = new CircuitParser.circuit_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CircuitParser.world_input_return world_input1 = null;

        CircuitParser.gates_return gates2 = null;

        CircuitParser.world_output_return world_output3 = null;


        RewriteRuleSubtreeStream stream_world_output=new RewriteRuleSubtreeStream(adaptor,"rule world_output");
        RewriteRuleSubtreeStream stream_world_input=new RewriteRuleSubtreeStream(adaptor,"rule world_input");
        RewriteRuleSubtreeStream stream_gates=new RewriteRuleSubtreeStream(adaptor,"rule gates");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:24:9: ( world_input gates world_output -> ^( CIRCUIT world_input gates world_output ) )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:24:11: world_input gates world_output
            {
            pushFollow(FOLLOW_world_input_in_circuit71);
            world_input1=world_input();

            state._fsp--;

            stream_world_input.add(world_input1.getTree());
            pushFollow(FOLLOW_gates_in_circuit73);
            gates2=gates();

            state._fsp--;

            stream_gates.add(gates2.getTree());
            pushFollow(FOLLOW_world_output_in_circuit75);
            world_output3=world_output();

            state._fsp--;

            stream_world_output.add(world_output3.getTree());


            // AST REWRITE
            // elements: world_output, world_input, gates
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 24:42: -> ^( CIRCUIT world_input gates world_output )
            {
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:24:45: ^( CIRCUIT world_input gates world_output )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CIRCUIT, "CIRCUIT"), root_1);

                adaptor.addChild(root_1, stream_world_input.nextTree());
                adaptor.addChild(root_1, stream_gates.nextTree());
                adaptor.addChild(root_1, stream_world_output.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "circuit"

    public static class world_input_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "world_input"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:25:1: world_input : position COLON -> position ;
    public final CircuitParser.world_input_return world_input() throws RecognitionException {
        CircuitParser.world_input_return retval = new CircuitParser.world_input_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON5=null;
        CircuitParser.position_return position4 = null;


        CommonTree COLON5_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_position=new RewriteRuleSubtreeStream(adaptor,"rule position");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:26:2: ( position COLON -> position )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:26:4: position COLON
            {
            pushFollow(FOLLOW_position_in_world_input95);
            position4=position();

            state._fsp--;

            stream_position.add(position4.getTree());
            COLON5=(Token)match(input,COLON,FOLLOW_COLON_in_world_input97);  
            stream_COLON.add(COLON5);



            // AST REWRITE
            // elements: position
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 26:19: -> position
            {
                adaptor.addChild(root_0, stream_position.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "world_input"

    public static class gates_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gates"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:1: gates : gate ( gates_tail )* -> ^( GATES gate ( gates_tail )* ) ;
    public final CircuitParser.gates_return gates() throws RecognitionException {
        CircuitParser.gates_return retval = new CircuitParser.gates_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CircuitParser.gate_return gate6 = null;

        CircuitParser.gates_tail_return gates_tail7 = null;


        RewriteRuleSubtreeStream stream_gates_tail=new RewriteRuleSubtreeStream(adaptor,"rule gates_tail");
        RewriteRuleSubtreeStream stream_gate=new RewriteRuleSubtreeStream(adaptor,"rule gate");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:7: ( gate ( gates_tail )* -> ^( GATES gate ( gates_tail )* ) )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:9: gate ( gates_tail )*
            {
            pushFollow(FOLLOW_gate_in_gates109);
            gate6=gate();

            state._fsp--;

            stream_gate.add(gate6.getTree());
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:14: ( gates_tail )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==GATE_DELIM) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:14: gates_tail
            	    {
            	    pushFollow(FOLLOW_gates_tail_in_gates111);
            	    gates_tail7=gates_tail();

            	    state._fsp--;

            	    stream_gates_tail.add(gates_tail7.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);



            // AST REWRITE
            // elements: gate, gates_tail
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 28:26: -> ^( GATES gate ( gates_tail )* )
            {
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:29: ^( GATES gate ( gates_tail )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GATES, "GATES"), root_1);

                adaptor.addChild(root_1, stream_gate.nextTree());
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:28:42: ( gates_tail )*
                while ( stream_gates_tail.hasNext() ) {
                    adaptor.addChild(root_1, stream_gates_tail.nextTree());

                }
                stream_gates_tail.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gates"

    public static class gates_tail_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gates_tail"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:29:1: gates_tail : GATE_DELIM gate -> gate ;
    public final CircuitParser.gates_tail_return gates_tail() throws RecognitionException {
        CircuitParser.gates_tail_return retval = new CircuitParser.gates_tail_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token GATE_DELIM8=null;
        CircuitParser.gate_return gate9 = null;


        CommonTree GATE_DELIM8_tree=null;
        RewriteRuleTokenStream stream_GATE_DELIM=new RewriteRuleTokenStream(adaptor,"token GATE_DELIM");
        RewriteRuleSubtreeStream stream_gate=new RewriteRuleSubtreeStream(adaptor,"rule gate");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:30:2: ( GATE_DELIM gate -> gate )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:30:4: GATE_DELIM gate
            {
            GATE_DELIM8=(Token)match(input,GATE_DELIM,FOLLOW_GATE_DELIM_in_gates_tail131);  
            stream_GATE_DELIM.add(GATE_DELIM8);

            pushFollow(FOLLOW_gate_in_gates_tail133);
            gate9=gate();

            state._fsp--;

            stream_gate.add(gate9.getTree());


            // AST REWRITE
            // elements: gate
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 30:20: -> gate
            {
                adaptor.addChild(root_0, stream_gate.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gates_tail"

    public static class gate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gate"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:31:1: gate : position position IO_DELIM position position -> ^( GATE position position position position ) ;
    public final CircuitParser.gate_return gate() throws RecognitionException {
        CircuitParser.gate_return retval = new CircuitParser.gate_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IO_DELIM12=null;
        CircuitParser.position_return position10 = null;

        CircuitParser.position_return position11 = null;

        CircuitParser.position_return position13 = null;

        CircuitParser.position_return position14 = null;


        CommonTree IO_DELIM12_tree=null;
        RewriteRuleTokenStream stream_IO_DELIM=new RewriteRuleTokenStream(adaptor,"token IO_DELIM");
        RewriteRuleSubtreeStream stream_position=new RewriteRuleSubtreeStream(adaptor,"rule position");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:31:6: ( position position IO_DELIM position position -> ^( GATE position position position position ) )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:31:8: position position IO_DELIM position position
            {
            pushFollow(FOLLOW_position_in_gate144);
            position10=position();

            state._fsp--;

            stream_position.add(position10.getTree());
            pushFollow(FOLLOW_position_in_gate146);
            position11=position();

            state._fsp--;

            stream_position.add(position11.getTree());
            IO_DELIM12=(Token)match(input,IO_DELIM,FOLLOW_IO_DELIM_in_gate148);  
            stream_IO_DELIM.add(IO_DELIM12);

            pushFollow(FOLLOW_position_in_gate150);
            position13=position();

            state._fsp--;

            stream_position.add(position13.getTree());
            pushFollow(FOLLOW_position_in_gate152);
            position14=position();

            state._fsp--;

            stream_position.add(position14.getTree());


            // AST REWRITE
            // elements: position, position, position, position
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 31:53: -> ^( GATE position position position position )
            {
                // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:31:56: ^( GATE position position position position )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(GATE, "GATE"), root_1);

                adaptor.addChild(root_1, stream_position.nextTree());
                adaptor.addChild(root_1, stream_position.nextTree());
                adaptor.addChild(root_1, stream_position.nextTree());
                adaptor.addChild(root_1, stream_position.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "gate"

    public static class position_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "position"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:32:1: position : ( 'X' -> ^( WORLD_POSITION ) | DIGIT SIDE -> ^( POSITION DIGIT SIDE ) );
    public final CircuitParser.position_return position() throws RecognitionException {
        CircuitParser.position_return retval = new CircuitParser.position_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal15=null;
        Token DIGIT16=null;
        Token SIDE17=null;

        CommonTree char_literal15_tree=null;
        CommonTree DIGIT16_tree=null;
        CommonTree SIDE17_tree=null;
        RewriteRuleTokenStream stream_SIDE=new RewriteRuleTokenStream(adaptor,"token SIDE");
        RewriteRuleTokenStream stream_DIGIT=new RewriteRuleTokenStream(adaptor,"token DIGIT");
        RewriteRuleTokenStream stream_15=new RewriteRuleTokenStream(adaptor,"token 15");

        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:32:9: ( 'X' -> ^( WORLD_POSITION ) | DIGIT SIDE -> ^( POSITION DIGIT SIDE ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==15) ) {
                alt2=1;
            }
            else if ( (LA2_0==DIGIT) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:32:11: 'X'
                    {
                    char_literal15=(Token)match(input,15,FOLLOW_15_in_position172);  
                    stream_15.add(char_literal15);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 32:15: -> ^( WORLD_POSITION )
                    {
                        // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:32:18: ^( WORLD_POSITION )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(WORLD_POSITION, "WORLD_POSITION"), root_1);

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;
                case 2 :
                    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:33:4: DIGIT SIDE
                    {
                    DIGIT16=(Token)match(input,DIGIT,FOLLOW_DIGIT_in_position183);  
                    stream_DIGIT.add(DIGIT16);

                    SIDE17=(Token)match(input,SIDE,FOLLOW_SIDE_in_position185);  
                    stream_SIDE.add(SIDE17);



                    // AST REWRITE
                    // elements: SIDE, DIGIT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 33:15: -> ^( POSITION DIGIT SIDE )
                    {
                        // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:33:18: ^( POSITION DIGIT SIDE )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(POSITION, "POSITION"), root_1);

                        adaptor.addChild(root_1, stream_DIGIT.nextNode());
                        adaptor.addChild(root_1, stream_SIDE.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "position"

    public static class world_output_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "world_output"
    // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:35:1: world_output : COLON position -> position ;
    public final CircuitParser.world_output_return world_output() throws RecognitionException {
        CircuitParser.world_output_return retval = new CircuitParser.world_output_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COLON18=null;
        CircuitParser.position_return position19 = null;


        CommonTree COLON18_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleSubtreeStream stream_position=new RewriteRuleSubtreeStream(adaptor,"rule position");
        try {
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:36:2: ( COLON position -> position )
            // /Users/iainmcgin/Dropbox/icfp/icfp2010/code/Circuit.g:36:4: COLON position
            {
            COLON18=(Token)match(input,COLON,FOLLOW_COLON_in_world_output204);  
            stream_COLON.add(COLON18);

            pushFollow(FOLLOW_position_in_world_output206);
            position19=position();

            state._fsp--;

            stream_position.add(position19.getTree());


            // AST REWRITE
            // elements: position
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 36:19: -> position
            {
                adaptor.addChild(root_0, stream_position.nextTree());

            }

            retval.tree = root_0;
            }

            retval.stop = input.LT(-1);

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "world_output"

    // Delegated rules


 

    public static final BitSet FOLLOW_world_input_in_circuit71 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_gates_in_circuit73 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_world_output_in_circuit75 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_position_in_world_input95 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_COLON_in_world_input97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gate_in_gates109 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_gates_tail_in_gates111 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_GATE_DELIM_in_gates_tail131 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_gate_in_gates_tail133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_position_in_gate144 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_position_in_gate146 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_IO_DELIM_in_gate148 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_position_in_gate150 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_position_in_gate152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_position172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIGIT_in_position183 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_SIDE_in_position185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COLON_in_world_output204 = new BitSet(new long[]{0x0000000000009000L});
    public static final BitSet FOLLOW_position_in_world_output206 = new BitSet(new long[]{0x0000000000000002L});

}