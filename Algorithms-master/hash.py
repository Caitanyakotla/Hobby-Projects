#Initialize a table of zeros. 10 is the table size.
table = [0] * 10
table = [[] for x in range(10)]
def hash_function(x): return x % 10 
#def insert(table,input,value): table[hash_function(input)] = value
def insert(table,input,value): table[hash_function(input)].append((input,value))
insert(table,41,'apple')
insert(table,93,'banana')
insert(table,1,'apnple')

print table



print (list(table))
