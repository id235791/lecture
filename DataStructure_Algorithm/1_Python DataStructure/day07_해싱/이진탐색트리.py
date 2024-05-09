#이진탐색트리.py
class Node:
    def __init__(self,data):
        self.data = data
        self.left = None
        self.right = None

    def __repr__(self):
        return str(self.data)
    
class BST:
    def __init__(self):
        self.root = None
        self.count = 1
        self.queue=[Node('none')]

    def insertNode(self,data,node):
        
        if node is None:
            #같이 넘어온 노드가 없다면
            #새로 노드를 만들어서 node 변수에 넣어주고
            node = Node(data)
            self.queue.append(node)
            self.count+=1
            if self.root is None:
                self.root = node
        else:
            #같이 넘어온 노드가 있다면
            if node.data>data:
                #추가하려는 데이터가 넘어온 노드의 데이터보다 작다면
                #넘어온 노드의 left에다가 insertNode를 재귀호출 해서 넣어준다
                #재귀호출시 추가하려는 데이터와 왼쪽노드를 같이 넘겨준다.
                #왼쪽노드가 없다면 None이 넘어갔을 것이고 재귀호출시 위의 if문으로
                #들어가므로 새로운 노드가 만들어지게 된다.
                node.left=self.insertNode(data,node.left)

            else:
                #추가하려는 데이터가 넘어온 노드의 데이터보다 크다면
                #넘어온 노드의 right에다 재귀호출을 진행하여
                #위의 과정과 비슷하게 진행한다
                node.right=self.insertNode(data,node.right)
        #if문에 들어갔다면 새로 생성된 노드가 리턴되고
        #else문에 들어갔다면 같이 넘어온 노드를 다시 그대로 리턴한다
        return node

    def searchNode(self,data,node):
        if node is None:
            return None

        if node.data == data:
            return node

        elif node.data > data:
            return self.searchNode(data,node.left)

        else:
            return self.searchNode(data,node.right)

    def deleteNode(self,data,node):
        if node is None:
            return None

        #넘겨진 데이터가 같이 넘어온 노드의 데이터보다 작은경우에는
        #왼쪽에 존재할 것이다.
        if node.data>data:
            #왼쪽노드와 데이터를 같이 넘겨주면서 delete 재귀호출
            #넘겨준 노드가 삭제할 노드가 아닌 경우에는 가장 마지막에
            #return node를 통해서 그대로 다시 돌려받는다.
            node.left = self.deleteNode(data,node.left)

        #넘겨진 데이터가 넘어온 노드의 데이터보다 큰 경우에는 오른쪽에 존재
        elif node.data<data:
            #오른쪽 노드와 데이터를 같이 넘겨주며 재귀호출
            node.right = self.deleteNode(data,node.right)

        #삭제할 노드를 찾은 경우
        else:
            #삭제할 노드의 오른쪽 노드가 있다는 뜻은 오른쪽에 서브트리가
            #존재한다는 뜻이다.
            if node.right is not None:
                #오른쪽 서브트리에서 최소 노드 찾기
                temp = self.findMinNode(node.right)
                #삭제할 노드의 데이터를 최소노드의 데이터로 바꾸어주기
                node.data = temp.data
                #기존에 존재하던 그 최소노드는 삭제
                node.right=self.deleteNode(temp.data,node.right)
            #왼쪽노드 존재 : 왼쪽에 서브트리 존재
            elif node.left is not None:
                #왼쪽 서브트리에서 최대 노드 찾기
                temp = self.findMaxNode(node.left)
                node.data = temp.data
                #기존에 존재하던 그 최대노드 삭제
                node.left = self.deleteNode(temp.data,node.left)
            #자식노드가 없는 경우
            else:
                return None
        return node

    def findMinNode(self,node):
        curr = node
        while curr.left is not None:
            curr=curr.left
        return curr

    def findMaxNode(self,node):
        curr = node
        while curr.right is not None:
            curr = curr.right
        return curr

    def preOrder(self,node):
        if node is not None:
            print(node.data,end=' ')
            self.preOrder(node.left)
            self.preOrder(node.right)
            
    def inOrder(self,node):
        if node is not None:
            self.inOrder(node.left)
            print(node.data,end=' ')
            self.inOrder(node.right)

    def postOrder(self,node):
        if node is not None:
            self.postOrder(node.left)
            self.postOrder(node.right)
            print(node.data,end=' ')

t = BST()
for data in [21,7,38,61,15,17,42,81,100,1]:
    t.insertNode(data,t.root)

print(t.searchNode(61,t.root).data)

t.preOrder(t.root)
print()
t.inOrder(t.root)
print()
t.postOrder(t.root)
print()















    











