#Split.py
#num1,num2 = map(int,input("정수 두개를 입력하세요(ex:10,20) : ").split(','))

#3,5 ---> 3 5
#split을 하게 되면 값이 여러개로 나오기 때문에 한번에 형태를 변환할 수 없다.
num1,num2 = input("정수 두개를 입력하세요(ex:10,20) : ").split(',')

num1 = int(num1)
num2 = int(num2)

print('결과 :',num1+num2)
