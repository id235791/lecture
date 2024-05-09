#ReadTest.py
'''
try:
    file = open("test2.txt","r")
except FileNotFoundError:
    print("파일이 존재하지 않습니다.")
'''
file = open("test.txt","r")
lines = file.readlines()
#print(lines)
for i in range(len(lines)):
    #"문자열1".rstrip("문자열2") : 문자열1의 오른쪽에 문자열2가 있다면 없애주기
    lines[i] = lines[i].rstrip("\n")

for line in lines:
    print(line)
