//Zoo.c
//���� �������� ���� �Է¹޾Ƽ�
//�������� ���� ���հ� ��� ���ϱ�
//�̸�, ����, ����
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
		printf("%d��° ������ �̸� : ", i + 1);
		scanf_s("%s", arAnimal[i].name,sizeof(arAnimal[i].name));
		printf("%d��° ������ ���� : ", i + 1);
		scanf_s("%s", arAnimal[i].gender, sizeof(arAnimal[i].gender));
		printf("%d��° ������ ���� : ", i + 1);
		scanf_s("%d", &arAnimal[i].age);
		tot += arAnimal[i].age;
	}
	printf("���� ���� : %d\n", tot);
	printf("���� ��� : %.2f\n", tot / (double)len);
	*/
	Animal animal = { .age = 10,.name = "����",.gender = "����" };
	Animal* p = &animal;

	//*(����������)�� .(����������)���� �켱������ ���� ������
	//��ȣ�� ���� �����ؼ� �ּҷ� ã�ư� �� �ʵ带 ã�ƾ� �Ѵ�.
	printf("%d\n", (*p).age);
	printf("%s\n", (*p).name);
	//-> : (*p).
	printf("%s\n",p->gender);

	//����ü�迭 ���� �Ҵ�
	//Animal* ar = (Animal*)calloc(3, sizeof(Animal));
}