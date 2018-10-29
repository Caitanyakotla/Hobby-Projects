import sys
import os

filename=open("ping.txt",'r')
a=[]
final=[]
for lines in filename:
	line=lines.strip().split()
	if len(line)==8:
		source_ip=line[3]
		icmp=line[4].split('=')
		icmp_no=int(icmp[1])
		timen=line[6].split('=')
		time=float(timen[1])
		c=(icmp_no,time)
		a.extend([c])
		final=dict(a)
#print final

for (n,p) in final.items():
	if p>60:
		icmp_no=n
		time=p
		print " ",icmp_no,time
