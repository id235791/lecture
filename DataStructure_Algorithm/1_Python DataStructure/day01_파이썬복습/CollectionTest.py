#CollectionTest.py
arList = list()
arTuple = tuple()
arDict = dict()

#리스트

#값 추가하기
arList.append(10)
arList.insert(0,20)
print(arList)

#값 가져오기
print(arList[0])

#값 수정하기
arList[0]="hello"
print(arList[0])

#값 삭제하기
del arList[1]
print(arList)

#튜플

#값 추가하기
arTuple+=(10,20)
print(arTuple)

#값 가져오기
print(arTuple[0])

#del arTuple

#딕셔너리

#값 추가하기
arDict[1] = "하나"
arDict[2] = "둘"
print(arDict)

#값 가져오기
print(arDict[1])

#값 수정하기
arDict[1] = "일"
print(arDict[1])

#값 삭제하기
del arDict[2]
print(arDict)

'''
data = 10;
data2 = data;
data2+=5;

print(data)
'''
arList1 = [10,20,30,40,50]
arList2 = arList1
arList2[2] = 300
print(arList1)





















