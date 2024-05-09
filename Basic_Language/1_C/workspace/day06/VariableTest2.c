//전역변수는 다른 파일에서도 사용할 수 있다.
//extern 키워드 사용
extern int data1;
//정적변수는 다른 파일에서 사용 불가능
//extern int data2;
void f() {
	data1 += 10;
}
void f2() {
	//data2 += 20;
}