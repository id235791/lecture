#VariableTest.py
x = 100000
arData = [10,20,30]
def f1():
    print(x)

def f2():
    x = 10

def f3():
    global x
    x = 10

def f4():
    arData[1] = 20000
    arData.append(40)
#f1()
#f2()
#f3()
#print(x)
f4()
print(arData)



