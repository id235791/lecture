#삽입정렬.py
arData = [8,10,3,9,7,6,5,1,4,2]
size = len(arData)

for i in range(1,size):
    #중간으로 삽입해서 배치해야 할 값을 임시저장
    temp = arData[i]
    #만약 아래의 else문에 들어가지 못했다면 현재 임시저장한 값이
    #최소값이라는 뜻, 가장 앞에 배치해야 하므로 -1로 초기설정
    idx = -1
    for j in range(i-1,-1,-1):
        #임시 저장했던 값이 더 작다
        if arData[j] > temp:
            #현재 비교하고 있는 j번방 보다는 앞에다가 삽입해야 한다는 뜻
            #따라서 j번방은 뒤에 덮어씌우기를 진행하며 뒤로 밀어준다.
            arData[j+1] = arData[j]
        #임시 저장했던 값이 더 크다
        else:
            #자신보다 작은 값들은 앞에 정렬되어 있을 것이므로
            #현재 찾은 위치를 idx에 기억해놓고 for문을 탈출해서
            #그 idx 바로 뒤쪽에 삽입해 주면 정렬이 일어난다.
            idx = j
            break
    arData[idx+1] = temp
