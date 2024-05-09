#VariableTask.py
#------홍길동 님의 정보------
#이름 : 홍길동
#나이 : 10살
#키 : 180.24cm
#몸무게 : 79.11kg
#성적 : C

name = "홍길동"
age = 10
height = 180.24
weight = 79.11
score = "C"
print("------"+name+" 님의 정보------")
print("이름 : "+name)
#print("나이 : ",age,"살",sep="")
print("나이 : %d살"%(age))
#print("키 : ",height,"cm",sep="")
#%.2f : 소수점 밑으로 둘째자리까지
print("키 : %.2fcm"%(height))
print("몸무게 : ",weight,"kg",sep="")
print("성적 : "+score)

#서식문자열은 통째로가 완성된 문자열 값이다.
msg = "%d + %d = %d"%(1,1,1+1)
print(msg)



















