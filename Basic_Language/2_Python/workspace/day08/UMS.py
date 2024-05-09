#UMS.py

#DTO,VO
class User:
    def __init__(self,userid,userpw,username,userage):
        self.userid = userid
        self.userpw = userpw
        self.username = username
        self.userage = userage

class Manager:

    def join(self,user):
        file = open("User.txt","a")
        file.write(user.userid+"\t"+user.userpw+"\t"+user.username+"\t"+user.userage+"\n")
        file.close()
        
    def login(self,userid,userpw):
        file = open("User.txt","r")
        lines = file.readlines()
        for line in lines:
            #"문자열1".rstrip("문자열2"):문자열1의 오른쪽에 문자열2가 있다면 없애주기
            line = line.rstrip("\n")
            datas = line.split("\t")
            if datas[0] == userid:
                if datas[1] == userpw:
                    print(datas[2]+"님 어서오세요~")
                    print("나이 :",datas[3])

manager = Manager()
while True:
    print("1. 회원가입\n2. 로그인\n3. 나가기")
    choice = input()

    if choice == '1':
        userid = input("아이디 : ")
        userpw = input("비밀번호 : ")
        username = input("이름 : ")
        userage = input("나이 : ")
        newUser = User(userid,userpw,username,userage)
        manager.join(newUser)
        print(username+"님 가입을 환영합니다!")

    elif choice == '2':
        userid = input("아이디 : ")
        userpw = input("비밀번호 : ")
        manager.login(userid,userpw)

    elif choice == '3':
        print("안녕히가세요")
        break












