#OperTask.py

#양수입니다. #음수입니다. #0입니다.

num = int(input("정수 : "))
print("양수입니다." if num > 0 else ("음수입니다." if num < 0 else "0입니다."))


#1. 정수 입력하기
#2. 문자열 입력하기

#1번 선택한 경우 
    #정수 입력 : 10
    #결과 : 15

#2번 선택한 경우
    #문자열 입력 : Hello
    #결과 : Hello Python

choice = int(input("1. 정수 입력하기\n2. 문자열 입력하기\n"))

result = int(input("정수 입력 : ")) if choice == 1 else input("문자열 입력 : ")
print(result+5 if choice == 1 else result+" Python")



















