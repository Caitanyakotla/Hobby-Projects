print ("Hello Friend")
print("This is GPA calculator")
print("please enter your grades")

points={'A+':4.0, 'A':4.0, 'A-':3.67, 'B+':3.33, 'B':3.0, 'B-':2.67,'C+':2.33, 'C':2.0, 'C':1.67, 'D+':1.33, 'D':1.0, 'F':0.0}

course=0
t_points=0

done= False

while not done:
	grade= raw_input()
	if grade=="":
		done=True
	elif grade not in points:
		
		print("unknown grade{0} beeing ignored".format(grade))
	else:
		course+=1
		t_points+=points[grade]

if course>0:
	print ("your final GPA is {0:.3} ".format(t_points/course))
