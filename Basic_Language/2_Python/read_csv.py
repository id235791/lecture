import csv
#csv 파일 읽기모드로 열기
file = open('HY mall data.csv','r')
#읽기모드로 열어놓은 파일을 파이썬에서 사용하기 위해 준비시키기
reader = csv.reader(file)

print(help(reader))
tot = 0
flag = False
       
#reader가 읽은 파일에서 한 줄씩 'line' 이라는 이름으로 꺼내오기
for line in reader:
    #읽어온 line 출력
    if reader.line_num == 1:
        continue
    if "sao" in line[32]:
        tot+= float(line[17])

print(tot)

#사용이 끝난 파일 닫기
file.close()
