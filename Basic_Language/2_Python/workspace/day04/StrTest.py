#StrTest.py
#['H','e','l','l','o',' ','P','y','t','h','o','n','!'] 와 유사
msg = "Hello Python!"
#print(msg[1])

#a,b =       ["Hello","Python!"]
datas = msg.split(" ")
#print(datas[0])
#print(datas[1])

#print(len(msg))

#"문자열1".find("문자열2") : "문자열1"에서 처음으로 나오는 "문자열2"의 index 반환
#print(msg.find("l"))#2
#print(msg.find('Python!'))#6
#print(msg.find("J"))#-1

#"문자열1".count("문자열2") : "문자열1"에 "문자열2"가 있으면 개수를 세서 반환
#print(msg.count('l'))#2

#"문자열".upper()/lower() : 대/소문자로 바꿔서 반환
#print(msg.upper())
#print(msg.lower())

#"문자열".replace("문자열1","문자열2") : "문자열"에서 "문자열1"이 있다면
                                        #"문자열2"로 변환 후 반환
#msg2 = "Python is so easy!"
#print(msg2.replace("easy","hard"))
#print(msg2)

#파이썬 슬라이싱(문자열과 리스트에서 가능)
print(msg[:5])#0번째부터 5전까지 자르기("Hello")
print(msg[6:])#6번째부터 끝까지 자르기("Python!")




















