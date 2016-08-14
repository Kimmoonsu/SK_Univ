#include <iostream>
#include "Model.h"
#include "View.h"
#include "Game.h"
#include "Controller.h"
#include "Consola.h"
#include <conio.h>

using namespace std;

int Game :: first_menu=1001;	//first_menu������ ���� ���� ����ڰ� ���� �޴� ȭ���� �ٲ��.
int Game :: start_menu=1;		//start_menu�� 1�϶� �޴�ȭ���� ��� ��µǰ� 0���� �ٲ�� ���� ������ ���۵ȴ�.
int Game :: first_map=1001;		//first_map������ ���� ���� ����ڰ� ���� �� �޴� ȭ���� �ٲ��.
int Game :: start_map=1;		//start_map�� 1�϶� �޴�ȭ���� ��� ��µǰ� 0���� �ٲ�� ���� ������ ���۵ȴ�.

void Game :: rightMap()	//�ʼ��ÿ��� ������Ű�� ������ �� �ٲ� ���¸� ����ϴ� �Լ�
{
	first_map++;
	
	if( first_map %3 == 1)	//���� ����° ���� �����Ͽ��� ��
	{
		textcolor(WHITE);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		textcolor(YELLOW);
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
	else if( first_map % 3 == 0)	//���� �ι�° ���� �����Ͽ��� ��
	{
		textcolor(WHITE);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		textcolor(YELLOW);
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		textcolor(WHITE);
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
	else if( first_map % 3 == 2 )	//���� ù��° ���� �����Ͽ��� ��
	{
		textcolor(YELLOW);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		textcolor(WHITE);
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
}
void Game :: leftMap()	//�ʼ��ÿ��� ����Ű�� ������ �� �ٲ� ���¸� ����ϴ� �Լ�
{
	
	first_map--;
	
	if( first_map %3 == 1)	//���� ����° ���� �����Ͽ��� ��
	{
		textcolor(WHITE);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		textcolor(YELLOW);
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
	else if( first_map % 3 == 0)	//���� �ι�° ���� �����Ͽ��� ��
	{
		textcolor(WHITE);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		textcolor(YELLOW);
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		textcolor(WHITE);
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
	else if( first_map % 3 == 2 )	//���� ù��° ���� �����Ͽ��� ��
	{
		textcolor(YELLOW);
		gotoxy(3,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[0][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(3,5+i);
		}
		gotoxy(7,15);
		cout << " < STAGE 1 > ";
		textcolor(WHITE);
		gotoxy(30,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[1][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(30,5+i);
		}
		gotoxy(34,15);
		cout << " < STAGE 2 > ";
		gotoxy(60,5);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{

				cout << icon[push_map[2][i][j]];	//	�� ���
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
	}
}
void Game :: upMenu()	// �޴����� ����Ű(��)�� �Է¹޾��� �� ��µǴ� �Լ�
{
	first_menu--;
	if( first_menu%5 == 2 )	// menu���� ���� 5�� �������� �� �������� 1�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";
		textcolor(YELLOW);  
		gotoxy(36,19);cout << "2 : ��  ��  ����  ��        ";
		textcolor(WHITE);
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";

	}
	else if( first_menu%5 == 3 )	// menu���� ���� 5�� �������� �� �������� 2�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		textcolor(YELLOW);
		gotoxy(36,21);cout << "3 : ��  �� �� ��  ��        ";
		textcolor(WHITE);
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";

	}
	else if( first_menu%5 == 4 )	// menu���� ���� 5�� �������� �� �������� 3�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		textcolor(YELLOW);
		gotoxy(36,23);cout << "4 : ��  ��    ŷ  ��        ";
		textcolor(WHITE);
		gotoxy(36,25);cout << "5 :     ��    ��            ";

	}
	else if( first_menu%5 == 0 )	// menu���� ���� 5�� �������� �� �������� 4�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		textcolor(YELLOW);
		gotoxy(36,25);cout << "5 : ��  ��    ��  ��        ";

	}
	else if( first_menu%5 == 1 )	// menu���� ���� 5�� �������� �� �������� 0�̸�
	{
		textcolor(YELLOW);
		gotoxy(36,17);cout << "1 : ��  ���ӽ���  ��        ";
		textcolor(WHITE);
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";

	}
}

void Game :: downMenu()	// �޴����� ����Ű(�Ʒ�)�� �Է¹޾��� �� ��µǴ� �Լ�
{
	Game :: first_menu++;
	if(	Game :: first_menu%5 == 2 )	// menu���� ���� 5�� �������� �� �������� 1�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";
		textcolor(YELLOW);  
		gotoxy(36,19);cout << "2 : ��  ��  ����  ��        ";
		textcolor(WHITE);
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";
	}
	else if( Game :: first_menu%5 == 3 )	// menu���� ���� 5�� �������� �� �������� 2�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		textcolor(YELLOW);
		gotoxy(36,21);cout << "3 : ��  �� �� ��  ��        ";
		textcolor(WHITE);
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";
	}
	else if( Game :: first_menu%5 == 4 )	// menu���� ���� 5�� �������� �� �������� 3�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		textcolor(YELLOW);
		gotoxy(36,23);cout << "4 : ��  ��    ŷ  ��        ";
		textcolor(WHITE);
		gotoxy(36,25);cout << "5 :     ��    ��            ";
	}
	else if( Game :: first_menu%5 == 0 )	// menu���� ���� 5�� �������� �� �������� 4�̸�
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     ���ӽ���            ";  
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		textcolor(YELLOW);
		gotoxy(36,25);cout << "5 : ��  ��    ��  ��        ";
	}
	else if( Game :: first_menu%5 == 1 )	// menu���� ���� 5�� �������� �� �������� 0�̸�
	{
		textcolor(YELLOW);
		gotoxy(36,17);cout << "1 : ��  ���ӽ���  ��        ";
		textcolor(WHITE);
		gotoxy(36,19);cout << "2 :     ��  ����            ";
		gotoxy(36,21);cout << "3 :     �� �� ��            ";
		gotoxy(36,23);cout << "4 :     ��    ŷ            ";
		gotoxy(36,25);cout << "5 :     ��    ��            ";
	}
}

void Game :: firstViews()	//�ʱ� �޴� ȭ�� ����ϴ� �Լ� 
{
	textcolor(WHITE);
	mapClean();
	first_menu=1001;	//menu������ ���� ���� ����ڰ� ���� �޴� ȭ���� �ٲ��.
	start_menu=1;		//start_menu�� 1�϶� �޴�ȭ���� ��� ��µǰ� 0���� �ٲ�� ���� ������ ���۵ȴ�.
	start_map=0;		//start_map�� �ʱ�ȭ �����༭ �ٽ� �޴�ȭ���� ���������� �۵��ϰ� �Ѵ�.
	system("mode con cols=101 lines=30"); //mode â�� ���� 101, ���̸� 30�� �Ѵ�.
	stage=0;
	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{
			push_map[stage][i][j]= repush_map[stage][i][j];	//Ȥ�ó� ������ �޴��� ���ƿԴ� �ٽ� �������� �� �� �ʱ�ȭ.
		}
	}
	count=0;	//�޴��� ���ƿ����� ������ Ƚ���� �ʱ�ȭ
	
	gotoxy(14,5);cout<<" ����  ��  ��    ����  ��  ��  ����  ��  ��    ����  ��  ��\n";
	gotoxy(14,6);cout<<" ��  ��  ��  ��    ��      ��  ��  ��  ��  ��  ��    ��      ��  ��\n"; 
	gotoxy(14,7);cout<<" ����  ��  ��    ��      ��  ��  ����  ��  ��    ��      ��  ��\n"; 
	gotoxy(14,8);cout<<" ��      ��  ��    ����  ����  ��      ��  ��    ����  ����\n"; 
	gotoxy(14,9);cout<<" ��      ��  ��        ��  ��  ��  ��      ��  ��        ��  ��  ��\n";
	gotoxy(14,10);cout<<" ��      ��  ��        ��  ��  ��  ��      ��  ��        ��  ��  ��\n"; 
	gotoxy(14,11);cout<<" ��      ����    ����  ��  ��  ��      ����    ����  ��  ��\n"; 
	textcolor(YELLOW);
	gotoxy(36,17);cout << "1 : ��  ���ӽ���  ��        ";
	textcolor(WHITE);
	gotoxy(36,19);cout << "2 :     ��  ����            ";
	gotoxy(36,21);cout << "3 :     �� �� ��            ";
	gotoxy(36,23);cout << "4 :     ��    ŷ            ";
	gotoxy(36,25);cout << "5 :     ��    ��            ";
}

void Game :: rePush()				//stage �ʱ�ȭ ���ִ� �Լ�.
{
	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{
			push_map[stage][i][j]= repush_map[stage][i][j];	//�ʱ���� repush_map������ �����س��Ҵٰ� �ٽ� ���� �ʿ� ���� ���� ����
		}

	}

}

