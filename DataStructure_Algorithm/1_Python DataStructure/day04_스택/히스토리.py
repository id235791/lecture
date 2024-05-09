#히스토리.py
#현재 페이지 : 빈 페이지
#1. 페이지 이동
    #이동할 페이지 : www.naver.com
#2. 뒤로가기
    #페이지 이동을 안했으면 불가능
    #페이지 이동을 했었다면 가장 최근에 들렸던 페이지로 이동
#3. 앞으로가기
    #뒤로가기를 안했으면 불가능
    #뒤로가기를 했었다면 다시 앞으로 이동
#4. 나가기
from 연결스택 import *
page = "빈 페이지"
history = LinkedStack()
back = LinkedStack()
while True:
    if history.isEmpty():
        page = "빈 페이지"
    print("현재 페이지 : "+page)
    print("1. 페이지 이동\n2. 뒤로가기\n3. 앞으로가기\n4. 나가기")
    choice = input()

    if choice == '1':
        #페이지이동
        page = input("이동할 페이지 : ")
        history.push(page)
        back = LinkedStack()
        
    elif choice == '2':
        #뒤로가기
        if history.isEmpty():
            print("이동할 페이지가 없습니다.")
        else:
            temp = history.pop()
            back.push(temp)
        
    elif choice == '3':
        #앞으로가기
        if back.isEmpty():
            print("이동할  페이지가 없습니다.")
        else:
            temp = back.pop()
            history.push(temp)
                    
    elif choice == '4':
        print("안녕히가세요")
        break

    page = history.top.data







    
