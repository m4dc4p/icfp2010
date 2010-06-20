'''
prints (n,gate^n(0,0)) to see what the period of the gate is, when
composed with itself.

The period is 5:

(n, (L out, R out))

(0, (0, 0)) ,
(1, (0, 2)) ,
(2, (1, 2)) ,
(3, (2, 1)) ,
(4, (1, 1)) ,
(5, (0, 0)) ,
'''
def g(l,r): return ((l-r)%3,(l*r + 2)%3)

def period():
    in_ = (0,0)
    for n in xrange(10):
        print (n,in_),','
        in_ = g(*in_)

if __name__ == '__main__':
    period()
