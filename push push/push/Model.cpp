
#include <iostream>
#include <fstream> //c++ �� ��������� ���
#include <conio.h>
#include "Model.h"
#include "View.h"
#include "Consola.h"

using namespace std;

int Model :: push_map[3][max1][max2] =		//���� ��Ÿ���ִ� 3���� �迭
{
	
	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,1,1,1,  1,1,1,7,7},
		{7,7,1,0,4,  0,0,1,1,7},
		{7,7,1,0,3,  0,0,0,1,7},

		{7,1,1,0,0,  3,0,0,1,7},
		{7,1,0,0,0,  4,0,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7} 
	}, // stage 1
	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,1,1,  1,1,7,7,7},
		{7,7,7,1,4,  4,1,7,7,7},
		{7,7,1,1,0,  4,1,1,7,7},
		{7,7,1,0,0,  3,4,1,7,7},

		{7,1,1,0,3,  0,0,1,1,7},
		{7,1,0,0,1,  3,3,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7} 
	}, // stage 2
	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,1,1,1,  1,1,1,7,7},
		{7,7,1,4,3,  0,0,1,1,7},
		{7,7,1,4,3,  0,0,0,1,7},

		{7,1,1,0,3,  3,0,0,1,7},
		{7,1,0,0,4,  4,0,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7}
	}// stage 3
	
};


int Model :: clear_condition[3]={2,4,4};	//���� ������ ���� ������ �����ϰ� �ִ� ����
int Model :: stage=0;	//�� �ܰ� �ʱ�ȭ
int Model :: count=0;	//������ Ƚ�� �ʱ�ȭ
char Model :: icon[max1][max2] = {"  ", "��","��","��","��","��","��","��"};	//�迭�� ���ڿ� ���� �ʾ�����
int Model :: user_x;	//����� y��ǥ
int Model :: user_y;	//����� x��ǥ
int Model :: fullroom;	//���̵���ִ� ���� ������ �Է¹޴� ����
int Model :: temp=0;	// ���� ���� ����
int Model :: user_count1[max3] = {0,};	//������ Ƚ���� �����ϴ� ���� �ʱ�ȭ
int Model :: user_count2[max3] = {0,};	//������ Ƚ���� �����ϴ� ���� �ʱ�ȭ	
int Model :: user_count3[max3] = {0,};	//������ Ƚ���� �����ϴ� ���� �ʱ�ȭ
int Model :: user_rank1;	//1�ܰ� �ְ����� �����ϴ� ������ �ʱⰪ����
int Model :: user_rank2=0;	//2�ܰ� �ְ����� �����ϴ� ������ �ʱⰪ����
int Model :: user_rank3=0;	//3�ܰ� �ְ����� �����ϴ� ������ �ʱⰪ����
int Model :: rank_i=0;	//user_cout1 �迭 ������ �ε��� ���� 0���� �ʱ�ȭ
int Model :: rank_j=0;	//user_cout2 �迭 ������ �ε��� ���� 0���� �ʱ�ȭ
int Model :: rank_k=0;	//user_cout3 �迭 ������ �ε��� ���� 0���� �ʱ�ȭ
void Model :: userSearch()	//����� ��ġ Ž�� �Լ�
{
	for(int i=0; i<max1; i++)
	{
		for(int j=0; j<max2; j++)
		{
			if(push_map[stage][i][j] == 2)	//������� ��ġ�� Ž��
			{
				user_x = j;	//y��ǥ
				user_y = i;	//x��ǥ
			}
		}
	}
};
int Model :: repush_map[3][max1][max2] =	//stage���� ���� �ʱⰪ�� �����ϴ� ����
{

	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,1,1,1,  1,1,1,7,7},
		{7,7,1,0,4,  0,0,1,1,7},
		{7,7,1,0,3,  0,0,0,1,7},

		{7,1,1,0,0,  3,0,0,1,7},
		{7,1,0,0,0,  4,0,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7} 
	}, // stage 1
	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,1,1,  1,1,7,7,7},
		{7,7,7,1,4,  4,1,7,7,7},
		{7,7,1,1,0,  4,1,1,7,7},
		{7,7,1,0,0,  3,4,1,7,7},

		{7,1,1,0,3,  0,0,1,1,7},
		{7,1,0,0,1,  3,3,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7} 
	}, // stage 2
	{
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,7,7,7,  7,7,7,7,7},
		{7,7,1,1,1,  1,1,1,7,7},
		{7,7,1,4,3,  0,0,1,1,7},
		{7,7,1,4,3,  0,0,0,1,7},

		{7,1,1,0,3,  3,0,0,1,7},
		{7,1,0,0,4,  4,0,0,1,7},
		{7,1,0,0,2,  0,0,0,1,7},
		{7,1,1,1,1,  1,1,1,1,7},
		{7,7,7,7,7,  7,7,7,7,7}
	} // stage 3
};
int Model :: fullroomSearch()		//���� ���ִ� ���� ������ �˷��ִ� �Լ�.
{
	fullroom=0;
	for(int i=0; i<max1; i++)
	{
		for(int j=0; j<max2; j++)
		{
			if(push_map[stage][i][j] == 5)	//���� ��ȿ� ���� ��
				fullroom++;	//fullroom�� ���ڸ� ����

		}
	}
	return fullroom;
};

void Model :: pushClear()		//�ܰ踶�� ������ ���� �ܰ��� �� �迭�� �Ѿ�ִ� �Լ�.
{
	fullroomSearch();	//���� ����ִ� ���� ������ Ž���ϴ� �Լ� ȣ��

	if(stage == 0)	//1�ܰ� �϶�
	{
		if( fullroom == clear_condition[0] )	//������ ������
		{
			user_count1[rank_i] = count;	//������ Ƚ���� user_count1������ i��°�� ���� ����
			if(user_rank1!=0 && user_rank1 > user_count1[0])	//���� ����Ǿ��ִ� �ְ����� 0�� �ƴϰ� ���� ����� ����� �ְ��Ϻ��� ���� ��
				user_rank1 = user_count1[0];	// ���� ����� ����� �ְ��Ͽ� ����
			else if( user_rank1 == 0 )	//���� ���� �Ǿ� �ִ� ��ŷ�� ���� ��
				user_rank1 = user_count1[0];	//���� ����� ����� �ְ��Ͽ� ����
			
			rank_i++;	//i���� ����
			for(int i=0; i<max3; i++)
			{
				
				if(user_count1[i]!=0)
				{
					
					if(user_rank1 <= user_count1[i])	//���� ù��° ���� �ٸ� ������ Ŭ ����
					{	
						user_rank1=user_rank1;	//ù��° ���� ��� ��ȯ			
					}
					else if(user_rank1 > user_count1[i])	//���� ù��° ������ �ٸ� ���� Ŭ ���
						user_rank1=user_count1[i];	// ū ���� user_rank1�� ����
				}
			}
			mapClean(); //�� CLEAN
			system("mode con cols=40 lines=12");	//modeâ�� ���̸� 12, ���̸� 40�� ����
			gotoxy(5,5); cout << "�̹� stage ������ Ƚ�� :  " << count << endl;
			gotoxy(5,7); cout << "�ƹ�Ű�� �Է��ϸ� ���� �ܰ� START " << endl;
			if(gettkey())	//Ű�� �Է� ������
			{
				stage++;//stage ����
				count = 0;//stage CLAER������ count���� �ʱ�ȭ ���ش�.
				system("mode con cols=101 lines=25");	//modeâ�� ���̸�101, ���̸� 25�� ����
				for(int i=0; i < max1; i++)
				{
					for(int j=0; j < max2; j++)
					{
						push_map[stage][i][j]= repush_map[stage][i][j];	//�����ܰ��� ���� �ʱ�ȭ �����ش�.
					}
				}
			}
		}
		
	}
	/*******************���� ����*****************/
	else if(stage == 1)
	{	
		if( fullroom == clear_condition[1] )
		{
			user_count2[rank_j] = count;
			user_count2[rank_j] = count;	//������ Ƚ���� user_count1������ i��°�� ���� ����
			if(user_rank2!=0 && user_rank2 > user_count2[0])	//���� ����Ǿ��ִ� �ְ����� 0�� �ƴϰ� ���� ����� ����� �ְ��Ϻ��� ���� ��
				user_rank2 = user_count2[0];	// ���� ����� ����� �ְ��Ͽ� ����
			else if( user_rank2 == 0 )	//���� ���� �Ǿ� �ִ� ��ŷ�� ���� ��
				user_rank2 = user_count2[0];	//���� ����� ����� �ְ��Ͽ� ����
			
			rank_j++;
			for(int j=1; j<max3; j++)
			{
				
				if(user_count1[j]!=0)
				{
					if(user_rank2 <= user_count2[j])
					{
						user_rank2=user_rank2;			
					}
					else if(user_rank2 > user_count2[j])
						user_rank2=user_count2[j];
				}
			}
			mapClean();
			system("mode con cols=40 lines=12");
			gotoxy(5,5); cout << "�̹� stage ������ Ƚ�� :  " << count << endl;
			gotoxy(5,7); cout << "�ƹ�Ű�� �Է��ϸ� ���� �ܰ� START " << endl;
			if(gettkey())
			{
				stage++;
				count = 0;
				system("mode con cols=101 lines=25");	//modeâ�� ���̸�101, ���̸� 25�� ����
				for(int i=0; i < max1; i++)
				{
					for(int j=0; j < max2; j++)
					{
						push_map[stage][i][j]= repush_map[stage][i][j];
					}
				}
				
			}
		}
		
	}
	else if(stage == 2)
	{
		if( fullroom == clear_condition[2] )
		{
			mapClean();
			
			system("mode con cols=40 lines=12");
		
			user_count3[rank_k] = count;
			user_count3[rank_k] = count;	//������ Ƚ���� user_count1������ i��°�� ���� ����
			if(user_rank3!=0 && user_rank3 > user_count3[0])	//���� ����Ǿ��ִ� �ְ����� 0�� �ƴϰ� ���� ����� ����� �ְ��Ϻ��� ���� ��
				user_rank3 = user_count3[0];	// ���� ����� ����� �ְ��Ͽ� ����
			else if( user_rank3 == 0 )	//���� ���� �Ǿ� �ִ� ��ŷ�� ���� ��
				user_rank3 = user_count3[0];	//���� ����� ����� �ְ��Ͽ� ����
			
			rank_k++;
			for(int k=1; k<max3; k++)
			{
				if(user_count1[k]!=0)
				{
					if(user_rank3 <= user_count3[k])
					{
						user_rank3=user_rank3;			
					}
					else if(user_rank3 > user_count3[k])
						user_rank3=user_count3[k];
				}	
			}

			mapClean();
			gotoxy(4,3);
			cout << "�̹� stage ������ Ƚ�� :  " << count << endl;
			gotoxy(4,4);
			cout << "Stage All Clear!";
			gotoxy(4,5);
			cout << "�����ϼ̽��ϴ� ^^" <<endl;
			cout << "��ŷ�� Ȯ���Ͻñ� �ٶ��ϴ�. " ;

			gotoxy(4,8);
			cout << "���� : Kimmoonsu (ENIAC)";

			if(gettkey()) 
			{

				stage++;
			}
		}
		
	}
	
};

void Model :: FullBox(int x1, int y1, int x2, int y2, int color)	//�ڽ�â�� ����ϴ� �Լ�
{
	textbackground(color);
	for(int i=y1; i <= y2; ++i)
		for(int j=x1; j <= x2; ++j)
		{
			
			gotoxy(j, i);
			cout << " ";
		}
};

void Model :: mapClean()			//����� ���
{
	int i;
	for(i=1; i<50; i++)
	{
		
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
	
};
void Model :: blackColor()	//������ �������� �����ϴ� �Լ�
{
	int i;
	for(i=1; i<50; i++)
	{
		textbackground(BLACK);
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
}
void Model :: whiteColor()	//������ ������� �����ϴ� �Լ�
{
	int i;
	for(i=1; i<50; i++)
	{
		textbackground(WHITE);
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
}

int Model :: gettkey()	//Ű�� �Է¹޴� �Լ�
{
	int retval = (_getch() & 0x00ff);
	return ( retval == 0xe0 ) ? ( ( retval << 8 ) | _getch()) : retval;
}


void Model :: rankingWirte()	//��ŷ�� �Է��ϴ� �Լ�
{ 
	ofstream out("ranking.txt",ios::out);	//txt������ ������� ���� ���� ���ش�.
	out << user_rank1 << endl;		// 1�ܰ� �ְ����� ����
	out << user_rank2 << endl;		// 2�ܰ� �ְ����� ����
	out << user_rank3 << endl;		// 3�ܰ� �ְ����� ����
	out.close();   //������ ��������.�ٸ���� ���� 
}

void Model :: rankingRead()	//��ŷ�� ����ϴ� �Լ�
{
	char line[3];	//����� ������ �迭�� ����
	ifstream input("ranking.txt"); //�Է½�Ʈ�� ����   
	for(int i = 0; i < 3; i++)	// �� stage�� 3�̹Ƿ� 3�� �ݺ����ش�.
	{
		char cstr[10];	//���ڿ��� ���� �迭 ������ ����
		input.getline(cstr,10);	//���ڿ��� �ް� ���پ� �߶��ش�.
		line[i] = atoi(cstr);	//���ڿ� cstr������ ���� line�迭�� ���� (atoi�� char�� int������ ��ȯ���ش�.) 
	}
	user_rank1 = line[0];	//1�ܰ� ��� line[0]�� 1�ܰ� �ְ��Ͽ� ����
	user_rank2 = line[1];	//2�ܰ� ��� line[1]�� 2�ܰ� �ְ��Ͽ� ����
	user_rank3 = line[2];	//3�ܰ� ��� line[2]�� 3�ܰ� �ְ��Ͽ� ����
} 