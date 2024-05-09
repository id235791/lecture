#Bank.py
class Bank:
    def __init__(self,account,pw,name):
        self.account = account
        self.pw = pw
        self.name = name
        self.balance = 0

    def deposit(self,money):
        self.balance += money

    def withdraw(self,money):
        if self.balance>=money:
            self.balance -= money

    def show(self):
        print("예금주 :",self.name)
        print("계좌번호 :",self.account)
        print("잔액 :",self.balance)

#1000원 입금시 500원 입금
class Kookmin(Bank):
    def deposit(self,money):
        super().deposit(money//2)

#500원 출금시 1000원 출금
class Shinhan(Bank):
    def withdraw(self,money):
        super().withdraw(money*2)

#계좌 조회시 잔액 절반
class Woori(Bank):
    def show(self):
        self.balance=self.balance//2
        super().show()

k = Kookmin("10000","abcd","김사과")
s = Shinhan("20000","abcd","반하나")
w = Woori("30000","abcd","이체리")

arUser = [k,s,w]

while True:
    print("1. 국민은행\n2. 신한은행\n3. 우리은행\n4. 나가기")
    choice = int(input())

    if choice == 4:
        print("안녕히가세요")
        break
    else:
        user = arUser[choice-1]
        account = input("계좌번호 : ")
        pw = input("비밀번호 : ")
        flag = False
        if user.account == account:
            if user.pw == pw:
                ##
                flag = True
                print(user.name+"님 어서오세요!")
                while True:
                    print("1. 입금하기\n2. 출금하기\n3. 내 정보보기\n4. 뒤로가기")
                    choice = input()
                    if choice == '1':
                        money = int(input("입금하실 금액 : "))
                        user.deposit(money)
                    elif choice == '2':
                        money = int(input("출금하실 금액 : "))
                        user.withdraw(money)
                    elif choice == '3':
                        user.show()
                    elif choice == '4':
                        break


        if not flag:          
            print("로그인 실패! 메인메뉴로 돌아갑니다.")


















