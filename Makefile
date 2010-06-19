# compile a circuit: send a string of trits on stdin, and a circuit
# implementing it will appear on stdout.  there will also be debug
# messages, but to stderr, so redirection works.  e.g., to compile a
# circuit that prints 17 0's:
#
#   echo 0000000000000000 | make circuit > 00000000000000000.cir
circuit:
	@ python gate_compiler.py | python circuit-concise.py
