#School.py
class Student:
    def __init__(self,stuname,stuclass,stunum):
        self.stuname=stuname
        self.stuclass=stuclass
        self.stunum=stunum
        self.stuscore=list()

#학생 객체들이 담길 리스트
stuList=list()
while True:
    print("1. 점수 기입하기\n2. 점수 조회하기\n3. 나가기")
    choice = int(input())
    if choice==1:
        #점수 기입하기
        stuname = input("이름 : ")
        stuclass = int(input("반 : "))
        stunum = int(input("번호 : "))
        #입력받은 값들로 Student 객체 하나 생성하기
        newstudent = Student(stuname,stuclass,stunum)
        #전체 학생 목록에 그 객체 추가
        stuList.append(newstudent)
        kor = int(input("국어점수 : "))
        eng = int(input("영어점수 : "))
        mat = int(input("수학점수 : "))

        #위에서 입력받은 세개의 점수들은 방금 만들어낸 학생객체의
        #정보들이므로 학생객체 내부의 stuscore 리스트에 append 해준다.
        newstudent.stuscore.append(kor)
        newstudent.stuscore.append(eng)
        newstudent.stuscore.append(mat)
        print(newstudent.stuname+" 학생 점수 기입 완료!")
        
    elif choice==2:
        #점수 조회하기(반, 번호 입력받아서 일치하는 학생의 점수 출력해주기)
        stuclass=int(input("반 : "))
        stunum = int(input("번호 : "))
        '''
        for i in range(len(stuList)):
            if stuList[i].stuclass==stuclass:
                if stuList[i].stunum==stunum:
                    for j in range(len(stuList[i].stuscore)):
                        print(stuList[i].stuscore[j])
        '''
        for student in stuList:
            if student.stuclass == stuclass:
                if student.stunum == stunum:
                    for score in student.stuscore:
                        print(score)
        
    elif choice==3:
        #나가기
        print("안녕히가세요")
        break
    else:
        print("잘못 입력했습니다.")










        
