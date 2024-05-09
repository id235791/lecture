#퀵정렬.py
arData = [8,10,3,9,7,6,5,1,4,2]
size = len(arData)

def partition(ar,left,right):
    pivot = ar[right]
    idx = left-1
    for i in range(left,right):
        if ar[i] <= pivot:
            idx += 1
            ar[i],ar[idx] = ar[idx],ar[i]
            print("SWAP :",ar)
    idx += 1
    ar[idx],ar[right] = ar[right],ar[idx]
    print("SWAP :",ar)
    return idx


def quickSort(ar,left,right):
    if left<right:
        q = partition(ar,left,right)
        quickSort(ar,left,q-1)
        quickSort(ar,q+1,right)

print("정렬 전 :",arData)
quickSort(arData,0,size-1)
print("정렬 후 :",arData)
