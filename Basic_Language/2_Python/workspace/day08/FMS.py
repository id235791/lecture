#FMS.py
#1. 내용 추가
#   추가할 내용 :

#2. 내용 수정
#   수정할 내용 :
#   새로운 내용 :

#3. 내용 삭제
#   삭제할 내용 :

#4. 목록 보기

#5. 나가기
FILE = "lang.txt"
def showList():
    file = open(FILE,"r")
    lines = file.readlines()
    print("☆★☆★☆★파일내용☆★☆★☆★")
    for line in lines:
        print(line,end="")
    print("☆★☆★☆★☆★☆★☆★☆★☆★")

while True:
    print("1. 내용 추가\n2. 내용 수정\n3. 내용 삭제\n4. 목록 보기\n5. 나가기")
    choice = input()
    if choice == '1':
        #내용추가
        showList()
        newData = input("추가할 내용 : ")
        file = open(FILE,"a")
        file.write(newData+"\n")
        file.close()
        print(newData+"이(가) 추가되었습니다")
    elif choice == '2':
        #내용수정
        showList()
        flag = False
        oldData = input("기존의 내용 : ")
        
        newData = input("새로운 내용 : ")
        file = open(FILE,"r")
        lines = file.readlines()
        for i in range(len(lines)):
            lines[i] = lines[i].rstrip("\n")
        result = ""
        for line in lines:
            if line == oldData:
                flag = True
                result += newData
            else:
                result += line
            result += "\n"
        if flag:
            file = open(FILE,"w")
            file.write(result)
            file.close()
            print(oldData+"이(가) "+newData+"(으)로 수정되었습니다.")
        else:
            print("수정할 내용이 없습니다.")
        
    elif choice == '3':
        #내용삭제
        #삭제할 내용 입력받기 > 라인들 읽어오기 > 각 라인 끝의 \n 없애기
        #> 각 라인들 꺼내오며 입력받은것과 비교 > 같지 않다면 빈문자열에 연결
        #> 연결된 그 문자열 파일에 덮어씌우기

        #flag 기법 : 어떤 구역에 들어갔는지를 체크하는 기법
        #어떤 알고리즘(핵심적 부분)이 수행되었는지를 확인할 때 사용
        showList()
        flag = False
        delData = input("삭제할 내용 : ")
        file = open(FILE,"r")
        lines = file.readlines()
        for i in range(len(lines)):
            lines[i] = lines[i].rstrip("\n")
        result = ""
        for line in lines:
            if line == delData:
                flag = True
            else:
                result += line+"\n"
        if flag:
            file = open(FILE,"w")
            file.write(result)
            file.close()
            print(delData+"이(가) 삭제되었습니다.")
        else:
            print("입력하신 내용은 없는 데이터입니다.")

        
    elif choice == '4':
        #목록보기
        showList()
        
    elif choice == '5':
        print("안녕히가세요")
        break














    
