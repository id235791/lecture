//Zoo.c
//동물 세마리의 정보 입력받아서
//동물들의 나이 총합과 평균 구하기
//이름, 성별, 나이
#include<stdio.h>
#include<stdlib.h>
typedef struct Animal {
	char name[100];
	char gender[100];
	int age;
}Animal;
void main() {
	/*
	Animal arAnimal[3] = { 0, };
	int tot = 0;
	int len = sizeof(arAnimal) / sizeof(Animal);
	for (int i = 0; i < len; i++) {
		printf("%d번째 동물의 이름 : ", i + 1);
		scanf_s("%s", arAnimal[i].name,sizeof(arAnimal[i].name));
		printf("%d번째 동물의 성별 : ", i + 1);
		scanf_s("%s", arAnimal[i].gender, sizeof(arAnimal[i].gender));
		printf("%d번째 동물의 나이 : ", i + 1);
		scanf_s("%d", &arAnimal[i].age);
		tot += arAnimal[i].age;
	}
	printf("나이 총합 : %d\n", tot);
	printf("나이 평균 : %.2f\n", tot / (double)len);
	*/
	Animal animal = { .age = 10,.name = "해피",.gender = "암컷" };
	Animal* p = &animal;

	//*(참조연산자)가 .(하위연산자)보다 우선순위가 낮기 때문에
	//괄호로 먼저 연산해서 주소로 찾아간 후 필드를 찾아야 한다.
	printf("%d\n", (*p).age);
	printf("%s\n", (*p).name);
	//-> : (*p).
	printf("%s\n",p->gender);

	//구조체배열 동적 할당
	//Animal* ar = (Animal*)calloc(3, sizeof(Animal));
}