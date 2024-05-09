#FunctionTask.py
#1~10까지 출력하는 함수
def print1To10():
    for i in range(1,11):
        print(i)
#1~10까지의 합 구해주는 함수
def sum1To10():
    tot = 0
    for i in range(1,11):
        tot+=i
    return tot
#1~n까지의 합 구해주는 함수
def sum1ToN(n):
    tot = 0
    for i in range(1,n+1):
        tot+=i
    return tot
#두 정수의 나눗셈결과를 구해주는 함수
def div(num1,num2):
    return num1/num2
#어떤 문자열을 n번만큼 출력하는 함수
def printMsg(msg,n):
    for i in range(n):
        print(msg)
 
#n~m까지의 합 구해주는 함수
def sumNtoM(n,m):
    tot = 0
    for i in range(n,m+1):
        tot += i
    return tot




















