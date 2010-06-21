# input is a file where each gate is on a single line
# only the outputs of each gate are specified, in L-to-R order
# (as seen in examples subdir)

# first line is the world output, so only one output specified
# output to the world can be specified by X

# author: hannes

import re
import fileinput

REG="(\d+)(['R'|'L'])|'X'"
REGEXP=re.compile(REG)

NODES={ }

class Gate():
    def __init__(self, outLpos='', outLdir='', outRpos='', outRdir=''):
        self.outLpos = outLpos
        self.outLdir = outLdir
        self.outRpos = outRpos
        self.outRdir = outRdir
        self.inLpos = ''
        self.inRpos = ''
        self.inLdir = ''
        self.inRdir = ''

    def setinputL (self, position, direction):
        if self.inLpos != '':
            print "tried to call inputL a second time"
        self.inLpos = position
        self.inLdir = direction

    def setinputR (self, position, direction):
        if self.inRpos != '':
            print "tried to call inputR a second time"
        self.inRpos = position
        self.inRdir = direction

i = -1
for line in fileinput.input():
    matches = re.split(REGEXP, line)
    fm = []
    for m in matches:
        if m != '' and m != '\n' and m != '\r':
            fm.append(m)
    #print fm
    if i == -1:
        #world output
        NODES[i] = Gate(outLpos=int(fm[0]), outLdir=fm[1])
    else:
        if len(fm) == 4:
            NODES[i] = Gate(outLpos=int(fm[0]), outLdir=fm[1], outRpos=int(fm[2]), outRdir=fm[3])
        else:
            if len(fm) == 3:
                #print fm
                if fm[0] == 'X':
                    NODES[i] = Gate(outLdir=fm[0], outRpos=int(fm[1]), outRdir=fm[2])
                else:
                    #if fm[2] == 'X':
                    NODES[i] = Gate(outLpos=int(fm[0]), outLdir=fm[1], outRdir='X')
            else:
                print "couldn't parse the line ", fm
    #print "added", i
    i += 1

for no in NODES.keys():
    n = NODES[no]
    if no == -1:
        if n.outLdir == 'L':
            NODES[n.outLpos].setinputL('X', '')
        else:
            if n.outLdir == 'R':
                NODES[n.outLpos].setinputR('X', '')
    else:
        if n.outLdir == 'L':
            NODES[n.outLpos].setinputL(no, 'L')
        else:
            if n.outLdir == 'R':
                NODES[n.outLpos].setinputR(no, 'L')
            else:
                if n.outLdir == 'X':
                    NODES[-1].setinputL(no, 'L')
        if n.outRdir == 'L':
            NODES[n.outRpos].setinputL(no, 'R')
        else:
            if n.outRdir == 'R':
                NODES[n.outRpos].setinputR(no, 'R')
            else:
                if n.outRdir == 'X':
                    NODES[-1].setinputL(no, 'R')

print "%s%s:" % (NODES[-1].outLpos, NODES[-1].outLdir)
for no in NODES.keys():
    if no != -1:
        n = NODES[no]
        s = ","
        #print "no", no, "len", len(NODES)
        if no + 2 == len(NODES):
            s = ":"
        print "%s%s%s%s%s%s%s%s%s%s" % (n.inLpos, n.inLdir, n.inRpos, n.inRdir, "0#", n.outLpos, n.outLdir, n.outRpos, n.outRdir, s)
print "%s%s" % (NODES[-1].inLpos, NODES[-1].inLdir)
