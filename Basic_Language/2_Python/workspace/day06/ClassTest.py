#ClassTest.py
'''
mycar_brand = "Ferrari"
mycar_color = "Red"
mycar_price = 65000

def mycar_engineStart():
'''
class Car:
    brand = ''
    color = ''
    price = 0

    def engineStart(self):
        print(self.brand + " 시동 켜기")

    def engineStop(self):
        print(self.brand + " 시동 끄기")

mycar = Car()
mycar.brand = "Ferrari"
mycar.color = "Red"
mycar.price = 65000

momcar = Car()
momcar.brand = "K7"
momcar.color = "White"
momcar.price = 7000

print(mycar.brand)
mycar.engineStart()
momcar.engineStart()

print(Car.brand)




















