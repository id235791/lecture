#ForTask.py

#for문을 횟수로만 반복하게 고정
#for i in range(횟수)

#1~10 까지 출력하기
for i in range(10):
    print(i+1,end=" ")
print()
#10~1 까지 출력하기
'''
for i in range(10,0,-1):
    print(i,end=" ")
'''
for i in range(10):
    print(10-i,end=" ")
print()
#100 98 96 94 .... 42 출력하기
'''
for i in range(100,40,-2):
    print(i,end=" ")
'''
for i in range(30):
    print(100-2*i,end=" ")
print()
#41부터 -5씩 변하며 12번 반복 출력하기
for i in range(41,-19,-5):
    print(i,end=" ")
print()
#정수 하나 입력받아서 1부터 그 수까지 출력하기
num = int(input("정수 : "))
for i in range(1,num+1):
    print(i,end=" ")
print()
#1부터 10까지의 합 출력하기
tot = 0
for i in range(1,11):
    tot += i
print(tot)

#n,m 입력받아서 n부터 m까지의 합 출력하기
n = int(input("n : "))
m = int(input("m : "))
tot = 0
for i in range(n,m+1):
    tot += i
print(tot)

#1~100 까지 중 짝수만 출력하기
for i in range(1,101):
    if i%2 == 0:
        print(i,end=" ")
print()

#1~100 까지 중 3과 5의 공배수 출력하기
#3과 5의 공배수가 아닐 조건 : not (i%3==0 and i%5==0)
#                               i%3!=0 or i%5!=0
for i in range(1,101):
    if i%3==0 and i%5==0:
        print(i,end=" ")
print()
#아스키 코드 : 문자들 마다 대응되는 숫자값을 가지고 있다.
#'A' : 65 / 'a' : 97 / '0' : 48
#chr(n) : 해당하는 아스키코드의 문자로 변환
#ord('문자') : 해당하는 아스키코드로 변환
#ord('0') : 48  int('0') : 0

#A~F 출력하기(A:65 F:70)
for i in range(65,71):
    print(chr(i),end=" ")
print()

#AbCdEf....z 출력하기
#A(65+0)      a(97)
#C(65+2)      b(97+1)
#E(65+4)      d(97+3)
for i in range(26):
    '''
    if i%2==0:
        print(chr(65+i),end="")
    else:
        print(chr(97+i),end="")
    '''
    print(chr(65+i if i%2 == 0 else 97+i),end="")
print()

























