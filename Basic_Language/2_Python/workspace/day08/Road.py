#Road.py
class Car:
    def __init__(self,brand,color,price):
        self.brand = brand
        self.color = color
        self.price = price

    def engineStart(self):
        print(self.brand+" 열쇠로 시동 켜기")

    def engineStop(self):
        print(self.brand+" 열쇠로 시동 끄기")

    def show(self):
        print("저는 자동차입니다.")

class SuperCar(Car):

    def __init__(self,brand,color,price,pw):
        #super() : 상속받는 클래스의 이름(Car)
        #부모의 메소드를 그대로 실행하고자 할 때 사용
        super().__init__(brand,color,price)
        self.pw = pw
    
    def engineStart(self):
        print(self.brand+" 스마트폰으로 시동 켜기")

    def engineStop(self):
        print(self.brand+" 스마트폰으로 시동 끄기")

    def show(self):
        #super().show()
        Car.show(self)
        print("근데 나는 슈퍼카~")

mycar = SuperCar("Ferrari","Red",65000,"abcd")
momcar = Car("K7","White",7000)

momcar.engineStart()
mycar.engineStart()

mycar.show()














