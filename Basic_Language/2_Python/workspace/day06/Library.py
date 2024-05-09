#Library.py
import random
import time
#import 모듈명 as 별칭 : 모듈을 사용할 때 별칭으로 사용 가능
import webbrowser as w
#.(하위연산자) : A.b - A 안의 b / A 의 b
#while True:
    #random.random() : 0.0~1.0 사이의 난수 발생
    #print(random.random())
    #random.randint(a,b) : a~b 까지의 난수 발생
    #print(random.randint(1,10))
arData = [1,2,3,4,5]
random.shuffle(arData)
#print(arData)

#time.time() : 1970년 1월 1일 0시 0분 0초 부터 지금까지 흐른 초
#print(time.time())
#time.sleep(n) : n초만큼 프로그램 진행을 잠시 멈추기
'''
for i in range(10):
    print(i)
    time.sleep(1)
'''
w.open("https://www.naver.com")
'''
while True:
    print(random.random())
    time.sleep(0.25)
'''






















