#선형탐색법.py
MAX = 7
move = 2
dataList = ['' for i in range(MAX)]

def h(x):
    return x%MAX

def digitFolding(s):
    tot = 0
    for ch in s:
        tot+=ord(ch)
    return tot
'''
cnt = 0
for i in range(100):
    data = int(input('데이터 : '))
    idx = h(data)
    while True:
        if cnt == MAX+1:
            print('메모리가 가득 찼습니다.')
            break
        if dataList[idx] == '':
            dataList[idx] = data
            cnt=0
            for j in range(MAX):
                print(j,':',dataList[j])
            break
        else:
            idx=(idx+move)%MAX
            cnt+=1
'''

dataList = ['' for i in range(MAX)]
cnt = 0
for i in range(MAX):
    s = input('문자열 : ')
    idx = h(digitFolding(s))
    while True:
        if cnt==MAX+1:
            print('메모리가 가득 찼습니다')
            break
        if dataList[idx] == '':
            dataList[idx] = s
            cnt = 0
            for j in range(MAX):
                print(j,':',dataList[j])
            break
        else:
            idx = (idx+move)%MAX
            cnt+=1

















