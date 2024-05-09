#IfTest.py
num = int(input("정수를 입력하세요 : "))
#분기처리
'''
if num>0:
    print("양수입니다.")

elif num<0:
    print("음수입니다.")

else:
    print("0입니다.")
'''
#일괄처리
result = "0입니다."
if num>0:
    result = "양수입니다."

elif num<0:
    result = "음수입니다."
'''
else:
    result = "0입니다."
'''
print(result)

score = int(input("점수 : "))
#F 60 D 70 C 80 B 90 A

if score<=60:
    print("F")
elif score<=70:
    print("D")
elif score<=80:
    print("C")
elif score<=90:
    print("B")
elif score<=100:
    print("A")






























