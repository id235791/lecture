#이중연결리스트.py
class Node:
    def __init__(self,data):
        self.data = data
        self.next = None
        self.prev = None

class DoubleLinkedList:
    def __init__(self):
        self.head = Node('head')
        self.tail = Node('tail')
        self.count = 0
        self.head.next = self.tail
        self.tail.prev = self.head

    def findNode(self,idx):
        curr = None
        if idx<self.count//2:
            curr = self.head
            for i in range(idx+1):
                curr = curr.next
        else:
            curr = self.tail
            for i in range(self.count-idx):
                curr = curr.prev
        return curr

    #추가
    def append(self,data):
        newNode = Node(data)

        newNode.next = self.tail
        newNode.prev = self.tail.prev

        self.tail.prev.next = newNode
        self.tail.prev = newNode

        self.count += 1
    #삽입
    def insert(self,idx,data):
        newNode = Node(data)
        if idx<self.count//2:
            curr = self.findNode(idx-1)

            newNode.prev = curr
            newNode.next = curr.next

            curr.next = newNode
            newNode.next.prev = newNode
        else:
            curr = self.findNode(idx)

            newNode.next = curr
            newNode.prev = curr.prev

            curr.prev = newNode
            newNode.prev.next = newNode
        self.count+=1
    
    #수정
    def update(self,idx,data):
        self.findNode(idx).data = data
        
    #삭제
    def delete(self,idx):
        if idx<self.count//2:
            #앞의노드 찾아서 다다음 노드로 접근
            curr = self.findNode(idx-1)
            curr.next = curr.next.next
            curr.next.prev = curr
        else:
            #뒤의노드 찾아서 전전 노드로 접근
            curr = self.findNode(idx+1)
            curr.prev = curr.prev.prev
            curr.prev.next = curr
        self.count -= 1
        
    #조회
    def get(self,idx):
        #return self.findNode(idx).data
        curr = None
        if idx<self.count//2:
            curr = self.head
            for i in range(idx+1):
                curr = curr.next
        else:
            curr = self.tail
            for i in range(self.count-idx):
                curr = curr.prev
        return curr.data
    #목록
    def show(self,order=True):
        #order == True
        if order:
            self.ascShow()
        else:
            self.descShow()
        
    def ascShow(self):
        curr = self.head
        for i in range(self.count+1):
            print(curr.data,end="☞")
            curr = curr.next
        print(curr.data)
        
    def descShow(self):
        curr = self.tail
        for i in range(self.count+1):
            print(curr.data,end="☞")
            curr = curr.prev
        print(curr.data)



















