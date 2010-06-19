'''
explanation of the gate count to circuit computation:

example circuit ('b' denotes back edges 'f' denotes forward edges, [n]
denotes gate n, {...} is a block of gates, with length underneath):

  (X,2) b {[N]} b {[N-k-1] f ... f [N-1]} b ... b {[3]f [4]f [5]} b {[0]f [1]f [2]} f (X,N)
            1               k                       3               3

corresponding input to circuit-concise.py:

NL    # N = sum(counts)

# block 0
1L1R  # general case, not at end: connect to next in block
2L2R
X NR  # special case: Lout -> X, Rout -> dummy (N+1)st gate

# block 1
4L4R  # next block is current count (our index + 1 = sum(blocks[:block]) + 1
5L5R
0L0R  # general case, at end: connect to first index in previous block (sum[blocks[:block-1]])

.
.
.

# block len(blocks)-1
(N-k)L(N-k)R
.
.
(N-1)L(N-1)R
(N-k-2)L(N-k-2)R

# block len(blocks), the "dummy" block
(N-k-1)L(N-k-1)R # not a special case, just append a [1] to the block counts
'''
import sys

def gate_counts2circuit_spec(counts):
    N = sum(counts)
    print "%iL" % N
    counts.append(1)
    for (n,c) in enumerate(counts):
        s  = sum(counts[:n])
        s_ = sum(counts[:n-1])
        for i in xrange(s+1,s+c):
            print "%iL%iR" % (i,i)
        if n == 0:
            print "X%iR" % N
        else:
            print "%iL%iR" % (s_,s_)

outs = [(0, 0),
        (0, 2),
        (1, 2),
        (2, 1),
        (1, 1)]
period = 5
(L,R) = (0,1)

def stream2gate_counts(trits) : # [trit] -> [nat]
    '''returns list of gate counts, [N0,N1,N2,...]'''
    print >> sys.stderr, trits
    counts = []
    for t in trits:
        for c in xrange(1,period):
            # interesting: we don't care what the R wire outputs!
            if outs[(c + sum(counts)) % period][L] == t:
                counts.append(c)
                break
        else:
            raise Exception("bug")
    print >> sys.stderr, counts
    return counts
    
def main(trits):
    gate_counts2circuit_spec(stream2gate_counts(trits))

if __name__ == '__main__':
    # expects a string of trits, easy to adjust if you want something else
    trits = map(int,raw_input())
    main(trits)
