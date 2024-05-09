#FunctionTask.py
#파이썬 내장함수 사용 X
#"문자열"[n] : "문자열" 의 n번째 글자
#"" + "a" == "a"
#"a" + "b" == "ab"
#'A' : 65 / 'a' : 97 / '0' : 48

#문자열의 소문자는 대문자로, 대문자는 소문자로 바꿔주는 함수
#("HelLO123!" -> "hELlo123!")
def change(msg):
    result = ""
    for i in range(len(msg)):
        if msg[i]>='a' and msg[i]<='z':
            result += chr(ord(msg[i])-32)
        elif msg[i]>='A' and msg[i]<='Z':
            result += chr(ord(msg[i])+32)
        else:
            result += msg[i]
    return result



#문자열을 거꾸로 바꿔주는 함수("Hello" -> "olleH")
def reverse(msg):
    result = ""
    for ch in msg:
        result = ch + result
    '''
    for i in range(len(msg)-1,-1,-1):
        result += msg[i]
    '''
    return result

#정수를 한글로 바꿔주는 함수(1024 -> "일공이사")
# "공일이삼사오육칠팔구"
def changeToHangle(num):    #1024
    hangle = "공일이삼사오육칠팔구"
    data = str(num)         #'1024'
    result = ""
    for ch in data: #ch : '1' '0' '2' '4'
        result += hangle[int(ch)] #hangle[1] : "일"
    return result

#----------------------------------------------------
#문자열을 공백 기준으로 나누어서 돌려주는 함수

























