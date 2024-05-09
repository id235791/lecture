#Zoo.py
#동물 클래스 구현
#변수 - 성별, 이름, 종, 나이
#메소드 - 밥먹기, 잠자기, 움직이기, 울기

#동물 세마리 만들어서 울게 만들기
import time as t
class Animal:
    def __init__(self,name,age,gender,sort):
        self.name = name
        self.age = age
        self.gender = gender
        self.sort = sort

    def eat(self):
        print(self.name+"이(가) 냠냠 먹는중...")

    def sleep(self):
        print(self.name+"이(가) 쿨쿨 자는중...")

class Lizard(Animal):
    def move(self):
        print(self.name+"이(가) 뽈뽈뽈 기어다니는중...")

    def makeSomeNoise(self):
        print(self.name+" 화르륵 화르륵 까야야아악")

class Whale(Animal):
    def move(self):
        print(self.name+"이(가) 입을 벌리고 죄다 부시며 헤엄치는중...")

    def makeSomeNoise(self):
        print(self.name+" 위잉위잉")

class FloralColossus(Animal):
    def move(self):
        print(self.name+"이(가) 폴짝폴짝 뛰어다니는중...")

    def makeSomeNoise(self):
        print("아이 엠 그루우웉트")
'''
lizard = Animal("니코",10,"암컷","도마뱀")
whale = Animal("모비딕",170,"수컷","흰머리 향유고래")
groot = Animal("그루트",2,"수컷","플로라 콜로서스")

lizard.makeSomeNoise()
whale.makeSomeNoise()
groot.makeSomeNoise()
'''
a1 = Lizard("브루니",20,"암컷","도마뱀")
a2 = Whale("모비딕",170,"수컷","흰머리 향유고래")
a3 = FloralColossus("그루트",2,"수컷","플로라 콜로서스")

while True:
    a1.makeSomeNoise()
    a2.makeSomeNoise()
    a3.makeSomeNoise()
    t.sleep(0.4)















