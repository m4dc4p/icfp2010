import re
import fileinput

REG="(\d+)([R|L])"
REGEXP=re.compile(REG)

NODES={ }

class Gate():
    def __init__(self, outLpos=-1, outLdir='X', outRpos=-1, outRdir='X'):
        self.outLpos = int(outLpos)
        self.outLdir = outLdir
        self.outRpos = int(outRpos)
        self.outRdir = outRdir
        self.inLpos = -1
        self.inRpos = -1
        self.inLdir = 'X'
        self.inRdir = 'X'

    def setinputL (self, position, direction):
        if self.inLpos != -1:
            print "tried to call inputL a second time"
        self.inLpos = position
        self.inLdir = direction

    def setinputR (self, position, direction):
        if self.inRpos != -1:
            print "tried to call inputR a second time"
        self.inRpos = position
        self.inRdir = direction

i = 0
for line in fileinput.input():
    matches = re.split(REGEXP, line)
    fm = []
    for m in matches:
        if m != '' and m != '\n' and m != '\r':
            fm.append(m)
    if len(fm) == 4:
        NODES[i] = Gate(outLpos=fm[0], outLdir=fm[1], outRpos=fm[2], outRdir=fm[3])
    else:
        print "couldn't parse the line ", fm
    i += 1

for no in NODES.keys():
    n = NODES[no]
    if n.outLdir == 'L':
        NODES[n.outLpos].setinputL(no, 'L')
    else:
        NODES[n.outLpos].setinputR(no, 'L')
    if n.outRdir == 'L':
        NODES[n.outRpos].setinputL(no, 'R')
    else:
        NODES[n.outRpos].setinputR(no, 'R')

for no in NODES.keys():
    n = NODES[no]
    print "%s%s%s%s%s%s%s%s%s" % (n.inLpos, n.inLdir, n.inRpos, n.inRdir, "0#", n.outLpos, n.outLdir, n.outRpos, n.outRdir)
