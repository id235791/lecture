#ListTask.py
'''
#빈 리스트 생성한 후 / 1부터 10까지 추가 / 하고 출력하기
#[1,2,3,4,5,6,7,8,9,10] X
arData = list()

for i in range(1,11):
    arData.append(i)

for i in range(10):
    print(arData[i])

#빈 리스트 생성 후 10부터 1까지 추가하고 출력하기
#[10,9,8,7,6,5,4,3,2,1] X
arData = list()

for i in range(10,0,-1):
    arData.append(i)
    
for i in range(10):
    print(arData[i])

#사용자에게 정수 5개 입력받아서 / 총합과 평균 구하기
arNum = list()
for i in range(5):
    num = int(input("정수 : "))
    arNum.append(num)

tot = 0
for i in range(5):
    tot += arNum[i]
print("총합 :",tot)
print("평균 :",tot/len(arNum))
    
#10,1,7,178,17,39,36,25,41,18 의 총합 구하기
#sum(숫자값들이 있는 리스트) : 총 합
print(sum([10,1,7,178,17,39,36,25,41,18]))
'''
#국어점수, 영어점수, 수학점수, 파이썬점수 입력받아서
#총점, 평균 구하기

#국어점수 :
#영어점수 :
#...

#총점 :
#평균 :
score = list()
subject = ["국어","영어","수학","파이썬","자바"]

for i in range(len(subject)):
    data = int(input(subject[i]+"점수 : "))
    score.append(data)

print("총점 :",sum(score))
print("평균 :",sum(score)/len(subject))























