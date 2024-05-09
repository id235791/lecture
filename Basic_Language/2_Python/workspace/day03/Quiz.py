#Quiz.py
#다음 중 프로그래밍 언어가 아닌것은?
#1. 파이썬
#2. C언어
#3. 망둥어
#4. JAVA

print("다음 중 프로그래밍 언어가 아닌것은?\n1. 파이썬\n2. C언어\n3. 망둥어\n4. JAVA")
choice = input()
if choice == "3":
    print("정답입니다")
elif choice == "1" or choice == "2" or choice == "4":
    print("오답입니다.")
else:
    print("잘못 입력하셨습니다.")
