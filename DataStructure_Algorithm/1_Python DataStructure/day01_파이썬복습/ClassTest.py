#ClassTest.py
'''
myCarBrand = "Ferrari"
myCarColor = "Red"
myCarPrice = 60000
def myCarEngineStart():
    print("Ferrari 시동켜기")
def myCarEngineStop():
    print("Ferrari 시동끄기")

momCarBrand = "K7"
momCarColor = "White"

myCarEngineStart()
'''
class Car:
    brand=""
    color=""
    price=0

    def __init__(self,brand,color,price):
        self.brand = brand
        self.color = color
        self.price = price
        
    def engineStart(self):
        print(self.brand+" 시동 켜기")

    def engineStop(self):
        print(self.brand+" 시동 끄기")

#.(하위연산자) : A.b : A안의 b, A 의 b
#Car.engineStart()
mycar=Car()#생성자
mycar=Car("Ferrari","Red",60000)
momcar=Car("K7","White",7000)
#print(mycar)
    
mycar.brand = "Ferrari"
mycar.color = "Red"
mycar.price = 60000
mycar.engineStart()

















    
