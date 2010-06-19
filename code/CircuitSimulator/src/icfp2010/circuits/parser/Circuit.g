grammar Circuit;

options{
	language=Java;
	output=AST;
	ASTLabelType=CommonTree;
}

tokens {
	CIRCUIT;
	GATES;
	GATE;
	WORLD_POSITION;
	POSITION;
}

@header {
package icfp2010.circuits.parser;
}
@lexer::header{
package icfp2010.circuits.parser;
}

circuit	:	world_input gates world_output -> ^(CIRCUIT world_input gates world_output);
world_input
	:	position COLON -> position;

gates	:	gate gates_tail* -> ^(GATES gate gates_tail*);
gates_tail
	:	GATE_DELIM gate -> gate;
gate	:	position position IO_DELIM position position -> ^(GATE position position position position);
position:	'X' -> ^(WORLD_POSITION)
	|	DIGIT SIDE -> ^(POSITION DIGIT SIDE);

world_output
	:	COLON position -> position;

IO_DELIM
	:	'0#';
COLON	:	':';
GATE_DELIM
	:	',';
DIGIT	:	('0'..'9')+;
SIDE	:	'L'
	|	'R';

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;