#School.py

class Student:
    def __init__(self,stunum,stuname,stumajor):
        self.stunum=stunum
        self.stuname=stuname
        self.stumajor=stumajor
        self.stuscore=dict()
        
    def __repr__(self):
        return self.stuname+"("+self.stunum+") - "+self.stumajor

#1. 학생 정보 기입
    #학번 : 19001
    #이름 : 홍길동
    #전공 : 컴퓨터공학과
    #홍길동님 정보 기입 완료!

#2. 학생 점수 기입
    #학번 :

    #과목 :
    #점수 : 
#3. 학생 점수 조회
    #학번 :

    #C언어 : A-
    #JAVA : B+
    #Python : A0

#4. 전체 학생 목록
    #홍길동(19001) - 컴퓨터공학과
    #이순신(21003) - 해양조선학과
    
#5. 나가기
studentList = []
while True:
    print("1. 학생 정보 기입\n2. 학생 점수 기입\n3. 학생 점수 조회\n4. 전체 학생 목록\n5. 나가기")
    choice = int(input())
    if choice==1:
        stunum = input('학번 : ')
        stuname = input('이름 : ')
        stumajor = input('전공 : ')
        newstudent = Student(stunum,stuname,stumajor)
        studentList.append(newstudent)
        #정렬
        for i in range(len(studentList)):
            for j in range(len(studentList)-1):
                if studentList[j].stunum > studentList[j+1].stunum:
                    studentList[j],studentList[j+1] = studentList[j+1],studentList[j]
        print(stuname+'님 정보 기입 완료')
        
    elif choice==2:
        stunum = input('학번 : ')
        for student in studentList:
            if student.stunum == stunum:
                while True:
                    print('점수 기입란입니다. 나가시려면 x를 입력해주세요.')
                    subject = input('과목 : ')
                    if subject == 'x':
                        break
                    score = input('점수 : ')
                    student.stuscore[subject]=score
                    
    elif choice==3:
        pass
    elif choice==4:
        t = ''
        for student in studentList:
            if student.stunum[:2] != t:
                t = student.stunum[:2]
                print('============'+t+'학번============')
            print(student)
    elif choice==5:
        print("안녕히가세요")
        break














    







