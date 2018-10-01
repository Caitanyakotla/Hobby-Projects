import re
n=int(raw_input())
ar=[]
for i in range(0,n):
    s=raw_input()
    t=re.search(r"^[a-zA-Z][\w-]*@[a-zA-Z0-9]+\.[a-zA-Z]{1,3}$",s)
    if t:
        ar.append(s)
ar.sort()        
print ar