#NumTest.py
while True:
    try:
        num1 = int(input("첫번째 정수 : "))
        num2 = int(input("두번째 정수 : "))
        
        print("결과 :",num1//num2)

    except ValueError:
        print("정수만 입력하세요 제발좀")

    except ZeroDivisionError:
        print("0으로는 나눌 수 없습니다")

    except Exception as e:
        print("알 수 없는 오류 발생 / 개발자에게 알려주세요")
        #print(type(e))
