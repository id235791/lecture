#BloodType.py

#혈액형별 성격 테스트 만들기
#혈액형 하나 입력받아서 결과 보여주기
#"문자열".lower() : 문자열 내부에 대문자들 전부 소문자로 바꾸기
btype = input("당신의 혈액형을 입력하세요~ : ").lower()
if btype == 'a':
    print("다정다감한 성격이며 상대를 잘 배려하는 편이다.")
elif btype == 'b':
    print("유머와 장난끼가 많아서 인기가 높다.")
elif btype == 'o':
    print("모든일에 적극적이며 리더쉽이 있다.")
elif btype == 'ab':
    print("논리적이며 계산적이다.")
else:
    print("당신은 사람이 아닙니다.")
