code/circsearch/circsearch: code/circsearch/circsearch.hs
	@make -C code/circsearch

# compile a circuit: send a string of trits on stdin, and a circuit
# implementing it will appear on stdout.  there will also be debug
# messages, but to stderr, so redirection works.  e.g., to compile a
# circuit that prints 17 0's:
#
#   echo 0000000000000000 | make circuit > 00000000000000000.cir
circuit: code/circsearch/circsearch
	@code/circsearch/circsearch `cat` | \
	  python circuit-concise.py

prefix_circuit_quick:
	@ { echo -n 11021210112101221; cat; } | python gate_compiler.py | python circuit-concise.py
# the key string iain found: 11021210112101221
prefix_circuit:
	@ { echo -n 11021210112101221; cat; } | make -s circuit

# make a bunch of "dumb" circuits with the prefix
#
# for n in `seq 1 20`; do python -c "print '0'*$n" | make prefix_circuit > prefix-$n-zeros.cir; done
