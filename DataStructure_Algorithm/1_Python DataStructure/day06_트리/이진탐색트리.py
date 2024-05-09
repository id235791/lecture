#이진탐색트리.py
class Node:
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None

class BST:
    def __init__(self):
        self.root = None

    def insertNode(self,data,node):
        if node is None:
            #같이 넘어온 노드가 없다면
            #새로 노드를 만들어서 node 변수에 넣어주고
            node = Node(data)
            if self.root is None:
                self.root = node

        else:
            #같이 넘어온 노드가 있다면
            if node.data > data:
                #추가하려는 데이터가 넘어온 노드의 데이터보다 작다면
                #넘어온 노드의 left에다 insertNode를 재귀호출 한다.
                #재귀호출시 추가하려는 데이터와 왼쪽노드를 같이 넘겨준다.
                #왼쪽 노드가 없다면 None이 넘어갔을 것이고 재귀호출시
                #위의 if문으로 들어가므로 새로운 노드가 생성되게 된다.
                node.left = self.insertNode(data,node.left)
            else:
                #위와 비슷하게 진행
                node.right = self.insertNode(data,node.right)
        #if문에 들어갔다면 새로 생성된 노드가 리턴되고
        #else문에 들어갔다면 같이 넘어왔던 노드를 다시 그대로 리턴한다.
        return node

    def searchNode(self,data,node):
        if node is None:
            return None
        if node.data == data:
            return node
        elif node.data > data:
            return self.searchNode(data,node.left)
        elif node.data < data:
            return self.searchNode(data,node.right)

    def deleteNode(self,data,node):
        if node is None:
            return None
        #넘겨진 데이터가 같이 넘어온 노드의 데이터보다 작은 경우
        #그 노드의 왼쪽에 존재할 것이다.
        if node.data > data:
            #왼쪽노드와 삭제할 데이터를 같이 넘겨주면서 delete 재귀호출
            #넘겨준 노드가 삭제할 노드가 아닌 경우에는 가장 마지막에 return을
            #통해 넘겨주었던 노드를 다시 그대로 돌려받는다.(연결이 유지된다)
            node.left = self.deleteNode(data,node.left)
        elif node.data < data:
            node.right = self.deleteNode(data,node.right)
        #삭제할 노드를 찾은경우
        else:
            #삭제할 노드의 오른쪽 노드가 있다는 뜻(오른쪽 서브트리가 있다는 뜻)
            if node.right is not None:
                #오른쪽 서브트리에서 최소 노드 찾기
                temp = self.findMinNode(node.right)
                #삭제할 노드의 데이터를 최소노드의 데이터로 바꿔주기
                node.data = temp.data
                #기존에 존재하던 그 최소노드는 삭제
                node.right = self.deleteNode(temp.data,node.right)
            #왼쪽 서브트리가 존재한다는 뜻
            elif node.left is not None:
                #왼쪽 서브트리에서 최대 노드 찾기
                temp = self.findMaxNode(node.left)
                node.data = temp.data
                #기존에 존재하던 그 최대노드는 삭제
                node.left = self.deleteNode(temp.data,node.left)
            #자식노드가 없는 경우
            else:
                return None
        #매개변수로 넘어온 node가 삭제할 노드가 아닌 경우에는 받은것 그대로 반환
        #삭제할 노드였다면 데이터를 바꿔주고 그 노드를 반환
        return node

    def findMinNode(self,node):
        curr = node
        while curr.left is not None:
            curr = curr.left
        return curr

    def findMaxNode(self,node):
        curr = node
        while curr.right is not None:
            curr = curr.right
        return curr




t = BST()
for data in [21,10,7,19,3,80,61,17,52,100]:
    t.insertNode(data,t.root)

temp = t.searchNode(80,t.root)
if temp is not None:
    print(temp.data)

t.deleteNode(80,t.root)
temp = t.searchNode(80,t.root)
if temp is not None:
    print(temp.data)
else:
    print("None")









