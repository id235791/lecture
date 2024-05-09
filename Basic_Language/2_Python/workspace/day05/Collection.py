#Collection.py
arTuple = (10,20,30)

print(arTuple[0])
#arTuple[1] = 200
#del arTuple[1]
#del arTuple

#튜플1 + 튜플2 : 두 튜플이 합쳐진 새로운 튜플
#arTuple = arTuple+(40,50)
arTuple += (40,50)
#값이 한개 있는 튜플은 (값) 만 쓰는 경우에는 ()를 연산자로 인식하기 때문에
#(값,) 의 방식대로 써줘야 튜플로 인식한다.
arTuple += (60,)
print(arTuple)
print(len(arTuple))

arSet = {10,20,30}
#print(arSet[1])

#set에 요소 추가하기
arSet.add(40)
arSet.add(40)
arSet.add(40)
arSet.add(40)
arSet.add(40)

print(arSet)

#set에서 요소 삭제하기
arSet.remove(30)
print(arSet)

#set에서 요소 가져오기
print(list(arSet)[1])















