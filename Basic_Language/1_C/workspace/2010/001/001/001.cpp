//��ǥ : �������� ��� �� #2. �Լ� ���ڷ� �迭�� �ѱ���� �Ҷ�

#include <stdio.h>

#define SIZE 5

//�迭�� �Լ� ���ڷ� ���� �� : �迭�� �ּҿ� �迭�� ������ �Ѵ� �ʿ���
//�Լ����忡�� �迭�� ũ�⸦ �� �� �ִ� ����� ����, �����ʹ� 4byte�� ��

void print_array(int* arr, int count)//(�迭�� �ּҿ� �迭�� ������)	
{									
	int i=0;

	//���
	//printf("%d \n", sizeof(arr));

	for (i=0; i<count; i++) {
		//arr[i] = *(arr+i)
		printf("%d  %d \n", arr[i], *(arr+i));
	}
}

void print_array2(int p_arr[]) //* arr = p_arr[] �� ������ ��������.
{
	int i=0;
	for (i=0; i<SIZE; i++) {
		printf("%p  %d \n", p_arr, *(p_arr++));
	}
}

void add_100_array(int* arr)
{
	int i=0;
	//����
	for (i=0; i<SIZE; i++) {
		//arr[i] = *(arr+i)
		//arr[i] += 100;
		*(arr+i) += 100;
	}
}
int main()
{
	int arr[SIZE]={10,20,30,40,50}; //5��
	int arr2[]={1,2,3,4,5,6,7,8,9}; //9��
	printf("%d %d \n", sizeof(arr), sizeof(arr2));
	print_array(arr, SIZE); //�迭�̸�=�ּ� OX98464~ 
	//quiz 1) �迭�� �� ���ҿ� 100�� ���ϱ�
	print_array2(arr);
	add_100_array(arr);
	print_array(arr,SIZE);
	//quiz 2) arr2 ���
	print_array(arr2, sizeof(arr2)/sizeof(int)); // (��ü bytes / ���� �Ѱ��� byte ��) = (36/4) = 9
}