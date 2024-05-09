#DictTest.py
dict1 = {"fly":"날다","walk":"걷다","run":"달리다"}

#간단하게 dict 구조 파악하기
print(dict1)

#dict의 길이 : 한 쌍을 한개로 센다.
print(len(dict1))

#dict에 요소 추가하기
dict1["sleep"] = "자다"

#dict의 요소 수정하기
dict1["run"] = "뛰다"

#dict에서 요소 가져오기
print(dict1["run"])

#dict의 요소 삭제하기
del dict1["sleep"]

#print(dict1.keys())
keys = dict1.keys()
print(list(keys)[0])

values = list(dict1.values())
print(values)

items = list(dict1.items())
print(items[1])
print("Key값 :",items[1][0])
print("Value값 :",items[1][1])

for i in range(len(items)):
    print("Key값 :",items[i][0])
    print("Value값 :",items[i][1])



























