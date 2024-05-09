#Zoo.py
class Animal:
    def __init__(self,name,gender,age):
        self.name = name
        self.gender = gender
        self.age = age

    def eat(self):
        print(self.name+"은(는) 냠냠 먹는중")

    def sleep(self):
        print(self.name+"은(는) 쿨쿨 자는중")

    def run(self):
        print("달리기")

    
class Kangaroo(Animal):
    def __init__(self,name,gender,age,pouch):
        super().__init__(name,gender,age)
        self.pouch = pouch

    def punch(self):
        print("상대방을 한방에 기절시킨다.")

    #Overriding
    def run(self):
        print("폴짝폴짝 뛰어다닌다.")

class Penguin(Animal):
    def __init__(self,name,gender,age,wing):
        super().__init__(name,gender,age)
        self.wing = wing

    def swim(self):
        print("은근히 빠르게 수영한다.")

    #Overriding
    def run(self):
        print("뒤뚱뒤뚱 걸어간다.")

class Gerenuk(Animal):
    def __init__(self,name,gender,age,horn):
        super().__init__(name,gender,age)
        self.horn = horn

    def run(self):
        print("다각다각 뛰어다닌다.")

    def makeSomeNoise(self):
        print("끼엑끼엑~~~~~~~~")

kangaroo = Kangaroo("캥순이","암컷",10,True)
penguin = Penguin("펭수","수컷",34,True)
gerenuk = Gerenuk("눅돌이","암컷",14,False)

kangaroo.run()
penguin.run()
gerenuk.run()







        





