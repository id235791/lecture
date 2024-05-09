#선택정렬.py
arData = [8,10,3,9,7,6,5,1,4,2]
size = len(arData)

'''
#최소값 찾는 알고리즘
min=0
for i in range(1,size):
    if arData[i]<arData[min]:
        min = i
'''
for i in range(size-1):
    min = i
    for j in range(i+1,size):
        if arData[min] > arData[j]:
            min = j
    arData[i],arData[min] = arData[min],arData[i]

print(arData)
