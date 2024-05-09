//목표 : 포인터의 사용 예 #2. 함수 인자로 배열을 넘기고자 할때

#include <stdio.h>

#define SIZE 5

//배열을 함수 인자로 받을 때 : 배열의 주소와 배열의 사이즈 둘다 필요함
//함수입장에서 배열의 크기를 알 수 있는 방법이 없다, 포인터는 4byte일 뿐

void print_array(int* arr, int count)//(배열의 주소와 배열의 사이즈)	
{									
	int i=0;

	//출력
	//printf("%d \n", sizeof(arr));

	for (i=0; i<count; i++) {
		//arr[i] = *(arr+i)
		printf("%d  %d \n", arr[i], *(arr+i));
	}
}

void print_array2(int p_arr[]) //* arr = p_arr[] 와 동일한 포인터임.
{
	int i=0;
	for (i=0; i<SIZE; i++) {
		printf("%p  %d \n", p_arr, *(p_arr++));
	}
}

void add_100_array(int* arr)
{
	int i=0;
	//변경
	for (i=0; i<SIZE; i++) {
		//arr[i] = *(arr+i)
		//arr[i] += 100;
		*(arr+i) += 100;
	}
}
int main()
{
	int arr[SIZE]={10,20,30,40,50}; //5개
	int arr2[]={1,2,3,4,5,6,7,8,9}; //9개
	printf("%d %d \n", sizeof(arr), sizeof(arr2));
	print_array(arr, SIZE); //배열이름=주소 OX98464~ 
	//quiz 1) 배열의 각 원소에 100씩 더하기
	print_array2(arr);
	add_100_array(arr);
	print_array(arr,SIZE);
	//quiz 2) arr2 출력
	print_array(arr2, sizeof(arr2)/sizeof(int)); // (전체 bytes / 원소 한개당 byte 수) = (36/4) = 9
}