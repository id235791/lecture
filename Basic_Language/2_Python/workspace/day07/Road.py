#Road.py
class Car:
    #파이썬 함수의 매개변수는 초기값을 설정해줄 수 있다.
    def __init__(self,brand="브랜드X",color="색상X",price=0):
        self.brand = brand
        self.color = color
        self.price = price
        #생성자는 현재 객체의 필드를 구성하는 중이기 때문에
        #내부에서 새로운 변수를 선언할 수 있다.
        self.engine = False

    def engineStart(self):
        if not self.engine:
            print(self.brand+" 시동 켜기")
            self.engine = True
        else:
            print("이미 시동이 켜져있습니다.")

    def engineStop(self):
        if self.engine:
            print(self.brand+" 시동 끄기")
            self.engine = False
        else:
            print("이미 시동이 꺼져있습니다.")

class SuperCar(Car):
    def roofOpen(self):
        print(self.brand+" 뚜껑 열기")

    def roofClose(self):
        print(self.brand+" 뚜껑 닫기")
'''
mycar = Car("Ferrari","Red",65000)
momcar = Car("K7","White",7000)
#함수 호출시 매개값을 어떤 변수에 넘기면서 호출할 지 정해줄 수 있다.
dadcar = Car(price=28000)
#print(dadcar.brand)

mycar.engineStart()#Ferrari 시동켜기
mycar.engineStart()#이미 시동이 켜져있습니다.
mycar.engineStart()#이미 시동이 켜져있습니다.
mycar.engineStart()#이미 시동이 켜져있습니다.
momcar.engineStart()#K7 시동켜기
'''
mycar = SuperCar("Ferrari","Red",65000)
mycar.engineStart()
mycar.roofOpen()






























