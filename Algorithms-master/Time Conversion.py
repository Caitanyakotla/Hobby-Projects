"""
Given a time in -hour AM/PM format, convert it to military (-hour) time.

Note: Midnight is on a -hour clock, and on a -hour clock. Noon is on a -hour clock, and on a -hour clock.

Input Format

A single string containing a time in -hour clock format (i.e.: or ), where and .

Output Format

Convert and print the given time in -hour format, where .

Sample Input

07:05:45PM

Sample Output

19:05:45

"""
import sys
time = raw_input().split(':')

if 'PM' in time[2]:
	if time[0]=='12': print time[0]+':'+time[1]+':'+time[2][0:2]
	else: print str(int(time[0])+12)+':'+time[1]+':'+time[2][0:2]
elif 'AM' in time[2]:
	if time[0]=='12': print '00:'+time[1]+':'+time[2][0:2]
	else:print time[0]+':'+time[1]+':'+time[2][0:2]



