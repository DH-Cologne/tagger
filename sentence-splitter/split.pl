#!/usr/bin/perl

use warnings;
use strict;

my $abbreviations = ["Hr.", "Dec.", "jr.", "Jan.", "Feb.", "Sept.", "DNS.", "Ztg.", "§.", "St.", "kaiserl.", "Se.", "Pf.", "franz.", "Nov.", "Fürstl.", "fürstl.","u.a.", "d.h.", "usw."];

my @history = ();
my $lastline = "";

sub isAbbreviation($) {
	my $history = shift;
	return 0 if (scalar @{$history}) == 0;
	my $ret = 0;
	
	# create a string of the tokens currently in the list
	my $histString = join("", (reverse @{$history}));
	#print STDERR $histString;
	# check if any of the abbreviations match at the end
	for my $ab (@$abbreviations) {
		return 1 if $histString =~ /$ab$/;
	}
	
	return $ret;
	
}

while(<>) {
	chomp;
	my $line = $_;
	my @line = split(/\t/, $line);
	
	
	my $token = $line[0];
	
	my $sentenceBoundary = 0;
	if ($lastline ne "") {
		my @lastline = split(/\t/, $lastline);
		if ($lastline[0] =~ /^[\.?!]/) {
			if ($line =~ /^[a-z]/) { # if the thing after the . is lower case
				$sentenceBoundary = 0;
			} elsif ($history[1] =~ /^[A-Z]$/) { # if last token before . was single upper case letter
				$sentenceBoundary = 0;
			} elsif ($history[1] =~ /^\d+$/) { # if last token before . was a number
				$sentenceBoundary = 0;
			} else { # if it's an abbreviation, its not a sentence boundary
				my $isAbbreviation = isAbbreviation(\@history);
				$sentenceBoundary = !$isAbbreviation;
			}
		}
		print "$lastline\n";	
	}
	
	if ($sentenceBoundary == 1) {
		print "\n";
		@history = ();
	}
	unshift(@history, $line[0]);
	$lastline = $line;
}