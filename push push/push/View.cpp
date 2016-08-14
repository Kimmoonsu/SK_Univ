#include <iostream>
#include <conio.h>
#include "Consola.h"
#include "View.h"
#include "Model.h"

using namespace std;
void View :: mapView()	//���� ������ִ� �Լ�
{
	
	textcolor(WHITE);
	Model :: pushClear();	//�� CLEAR 	
	if(Model :: stage != 3)	//���� �� CLEAR���� ������ ��
	{
		gotoxy(8,10);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				cout << icon[map[stage][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(8,10+i);
		}
		Model :: fullroomSearch();	//���� ����ִ� ���� ���ڸ� ������ִ� �Լ� ȣ��
		
	}
}

void View :: originalMap()	//stage���� �ʱ� ���� �����ϴ� �Լ�
{

	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{

			repush_map[stage][i][j]=push_map[stage][i][j];
		}
		
	}
	
}
void View :: sideMap()	//�� ������� �� �����ʿ� ���� ���
{
			gotoxy(55, 1);  cout <<"��������������������";
			gotoxy(55, 2);  cout <<"��P U S H  P U S H��";
			gotoxy(55, 3);  cout <<"��������������������";
			gotoxy(55, 4);	cout <<  "       UP";
			gotoxy(55, 5);	cout <<  "       ��";
			gotoxy(55, 6);	cout <<  "LEFT ��  �� RIGHT";
			gotoxy(55, 7);	cout <<  "       ��";
			gotoxy(55, 8); cout <<  "      DOWN";
			gotoxy(55, 10); cout <<"  ESC     :     �޴�";
			gotoxy(55, 11); cout <<"   P      :  �� ����";
			gotoxy(55, 12); cout <<"   R      :     ����";
			gotoxy(55, 13); cout <<"   W      : �����ܰ�";
			gotoxy(55, 14); cout <<"   <-     : �����ܰ�";
			gotoxy(55, 15); cout <<"   Q      : ��������";
			gotoxy(55, 17); cout <<" STAGE " << stage+1 <<" / 3";
			gotoxy(55, 18); cout <<" ������ Ƚ�� : " << count;	//������ Ƚ���� ���
			gotoxy(55, 19); cout <<" ������ �� : " << fullroom <<endl;
			gotoxy(55, 21); cout << "1�ܰ� �ְ� ��� : " << user_rank1;
			gotoxy(55, 22); cout << "2�ܰ� �ְ� ��� : " << user_rank2;
			gotoxy(55, 23); cout << "3�ܰ� �ְ� ��� : " << user_rank3;
}
void View :: prevMap()	//���� �ܰ� ���� ����ϱ� ���� �Լ�
{

	
	if(stage>0)	//stage1 ���� �������� ������ �� �ƹ� ��ȭ�� ���� �ϱ� ���Ͽ�
	{
		stage--;
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				push_map[stage][i][j]= repush_map[stage][i][j];			//stage�� �°� �ʱ� ���� �����س��Ҵٰ� �ٿ��ִ� ����.
			}
		}
		mapView();	//�� ���
	}
}

void View :: nextMap()	//���� �ܰ� ���� ����ϱ� ���� �Լ�
{
	if(stage<2)		//stage3 ���� ���� ���� ������ �� �ƹ� ��ȭ�� ���� �ϱ� ���Ͽ�
	{
		stage++;	// ���� �ܰ� ���� ����ϱ� ���� stage����
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				push_map[stage][i][j]= repush_map[stage][i][j];			//stage�� �°� �ʱ� ���� �����س��Ҵٰ� �ٿ��ִ� ����.
			}
		}
		mapView();	//�� ���
	}
}
