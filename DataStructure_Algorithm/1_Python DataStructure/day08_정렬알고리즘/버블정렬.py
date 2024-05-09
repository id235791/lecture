#버블정렬.py
arData = [8,10,3,9,7,6,5,1,4,2]
size = len(arData)

for i in range(size-1):
    for j in range(size-1-i):
        #0~8
        #0~7
        #...
        #0~0
        if arData[j] > arData[j+1]:
            arData[j],arData[j+1] = arData[j+1],arData[j]

print(arData)
