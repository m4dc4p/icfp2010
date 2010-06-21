import string
import urllib2
import httplib, mimetypes
#import urlparts

Host="nfa.imn.htwk-leipzig.de"
Sel="/icfpcont/#hotspot"

def post_multipart(host, selector, fields, files):
    """
    Post fields and files to an http host as multipart/form-data.
    fields is a sequence of (name, value) elements for regular form fields.
    files is a sequence of (name, filename, value) elements for data to be uploaded as files
    Return the server's response page.
    """
    content_type, body = encode_multipart_formdata(fields, files)
    h = httplib.HTTP(host)
    h.putrequest('POST', selector)
    h.putheader('content-type', content_type)
    h.putheader('content-length', str(len(body)))
    h.endheaders()
    h.send(body)
    errcode, errmsg, headers = h.getreply()
    return h.file.read()

def encode_multipart_formdata(fields, files):
    """
    fields is a sequence of (name, value) elements for regular form fields.
    files is a sequence of (name, filename, value) elements for data to be uploaded as files
    Return (content_type, body) ready for httplib.HTTP instance
    """
    BOUNDARY = '----------ThIs_Is_tHe_bouNdaRY_$'
    CRLF = '\r\n'
    L = []
    for (key, value) in fields:
        L.append('--' + BOUNDARY)
        L.append('Content-Disposition: form-data; name="%s"' % key)
        L.append('')
        L.append(value)
    for (key, filename, value) in files:
        L.append('--' + BOUNDARY)
        L.append('Content-Disposition: form-data; name="%s"; filename="%s"' % (key, filename))
        L.append('Content-Type: %s' % get_content_type(filename))
        L.append('')
        L.append(value)
    L.append('--' + BOUNDARY + '--')
    L.append('')
    body = CRLF.join(L)
    content_type = 'multipart/form-data; boundary=%s' % BOUNDARY
    return content_type, body

def get_content_type(filename):
    return mimetypes.guess_type(filename)[0] or 'application/octet-stream'


def getpage (g1):
    res = post_multipart(Host, Sel, (("G0", "122000010"), ("G1", g1)), ())
    pres = string.split(res, "<pre")
    return pres

def int2fuel (i):
    if i == 0:
        return "0"
    if i == 1:
        return "1"
    if i == 2:
        return "220"
    if i == 3:
        return "2210"
    if i == 4:
        return "2211"
    if i == 5:
        return "2212"

def list2fuel (l):
    #print "called with ", l
    res = int2fuel(len(l))
    for x in l:
        if isinstance(x, int):
            res += int2fuel(x)
        else:
            if isinstance(x, list) or isinstance(x, tuple):
                res += list2fuel(x)
            else:
                print 'unknown object!', x
    return res

import os

key = "11021210112101221"
tanks = 1
ing = 2

for l in [[[1,0], [0,1]]]: #, [2, 2, 2, 2], [[2, 2], [2, 2]], [4, 3, 4, 3], [[2], [2], [2], [2]]): #, [[2, [2, 2]], [[2, 2], [4, [3, [3, [3, [3, [3, [3, [3, [3, [3, 3]]]]]]]]]]]], [[[[[1]]]], [[[[[1]]]]]], [0, 0], [[1], [1]], [[2, 2], [3, 4]]):
    mystr = int2fuel(tanks) + int2fuel(ing) + list2fuel(l)
    print mystr
    myg1 = os.popen("echo " + key + mystr + " | make circuit")
    realg1 = myg1.read()
    pres = getpage(realg1)
    if len(pres) == 3:
        print mystr, " ".join(string.split(pres[1], "</pre")[0].lstrip().splitlines()), " ".join(string.split(pres[2], "</pre")[0].lstrip().splitlines())
    else:
        print mystr
        print pres
