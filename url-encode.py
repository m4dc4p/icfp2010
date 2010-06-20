#!/bin/python
import urllib, sys

inputstring = sys.stdin.read()
print urllib.urlencode({
  "G0" : sys.argv[1],
  "G1" : inputstring})

#inputstring)
