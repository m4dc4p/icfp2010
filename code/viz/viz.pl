#!/usr/bin/perl
use strict;
use warnings;

print "digraph structs {\n";
print " xo [label=\"Ext\"];\n";
print " xi [label=\"Ext\"];\n";
print " node [shape=record];\n";

my $data = do { local $/; <> };

$data =~ s/^(X|\d+[LR]):\s*// or die;
arrow("X", $1);

my $node = 0;
while ($data =~ s/^(X|\d+[LR])(X|\d+[LR])0#(X|\d+[LR])(X|\d+[LR])[,:]\s*//) {
    arrow($node."L", $3);
    arrow($node."R", $4);

    print " node$node [label=\"{{<Li>L|<Ri>R}|$node|{<Lo>L|<Ro>R}}\"];\n";

} continue {
    $node++;
}

print "}\n";

sub arrow {
    my ($from, $to) = @_;
    if ($to ne "X" && $from ne "X" && atoi($to) <= atoi($from)) {
        # Back edge
        print " ".  label($to) ."i -> ". label($from) ."o";
        print " [ arrowhead=none, arrowtail=onormal ]\n";
    } else {
        # Forward edge
        print " ". label($from) ."o -> ". label($to) ."i\n";
    }
}

sub label {
    my ($str) = @_;
    if ($str eq "X") {
        return "x";
    }
    $str =~ /^(\d+)([LR])$/ or die;
    return "node$1:$2";
}

sub atoi {
    my ($str) = @_;
    $str =~ /^(\d+)/ or die;
    return $1;
}

