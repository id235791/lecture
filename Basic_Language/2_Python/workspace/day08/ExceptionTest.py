#ExceptionTest.py
try:
    print(int("10"))
    int("Helllllllllo")
    print(int("100"))
except ValueError:
    print("정수로 바꿀 수 없습니다.")

