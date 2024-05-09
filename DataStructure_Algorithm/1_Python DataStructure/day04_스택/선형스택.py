#선형스택.py
MAX = 5

class Stack:
    def __init__(self):
        self.stackList = list()
        for i in range(MAX):
            self.stackList.append(None)
        self.top = 0
    
    def push(self,data):
        #꽉차있는지 검사
        #top 위치에 데이터 추가
        #top 증가
        if self.isFull():
            print("Stack Is Full!")
        else:
            self.stackList[self.top] = data
            self.top += 1
        
    def pop(self):
        #비어있는지 검사
        #top 감소
        #top 위치를 None으로 비우며 return
        if self.isEmpty():
            print("Stack Is Empty!")
        else:
            self.top -= 1
            temp = self.stackList[self.top]
            self.stackList[self.top] = None
            print(temp)

    #10 > 3
    #메소드 이름에 is, has가 있다면 보통 리턴은 Bool타입(True,False)
    def isEmpty(self):
        #3+5 10!=3
        return self.top == 0
    
    def isFull(self):
        return self.top == MAX

s = Stack()
s.push('A')
s.push('B')
s.push('C')
s.pop()#C
s.pop()#B
s.push('D')
s.push('E')
s.push('F')
s.push('G')
s.push('H')#Stack Is Full!
s.pop()
s.pop()
s.pop()
s.pop()
s.pop()
s.pop()#Stack Is Empty!

















