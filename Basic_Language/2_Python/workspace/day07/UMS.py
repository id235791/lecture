#UMS.py
'''
idList = list()
pwList = list()
nameList = list()
'''
#데이터들이 포장된 user객체를 추가해줄 리스트
userList = list()
#데이터들을 포장하기 위한 클래스
class User:
    def __init__(self,userid,userpw,username):
        self.userid = userid
        self.userpw = userpw
        self.username = username
        
while True:
    choice = input("1. 회원가입\n2. 로그인\n3. 나가기\n")
    if choice == '1':
        userid = input("아이디 : ")
        userpw = input("비밀번호 : ")
        username = input("이름 : ")
        '''
        idList.append(userid)
        pwList.append(userpw)
        nameList.append(username)
        '''
        #User(userid,userpw,username) : 위에서 입력받은 세개의 데이터를 포장하며
        #객체 한개 만들기
        #위에서 만들어놓은 userList에 그 객체 바로 추가
        userList.append(User(userid,userpw,username))
    elif choice == '2':
        #로그인
        userid = input("아이디 : ")
        userpw = input("비밀번호 : ")
        '''
        for i in range(len(idList)):
            if idList[i] == userid:
                if pwList[i] == userpw:
                    print(nameList[i],"님 어서오세요~")
        '''
        #가입했던 객체들을 하나씩 꺼내오면서 순회
        for user in userList:
            #user : 각 회원 객체
            if userid == user.userid:
                if userpw == user.userpw:
                    print(user.username,"님 어서오세요~")
    elif choice == '3':
        break




















