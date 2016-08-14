#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <iostream>
#include <conio.h>
#include "Getkey.h"
#include "Consola.h"
#include "Game.h"
#include <Windows.h>
using namespace std;


#define FREE_L push_map[stage][user_y][user_x-1] == 0		//���ʿ� ������� ������
#define FREE_R push_map[stage][user_y][user_x+1] == 0		//�����ʿ� ������� ������
#define FREE_U push_map[stage][user_y-1][user_x] == 0		//���ʿ� ������� ������
#define FREE_D push_map[stage][user_y+1][user_x] == 0		//�Ʒ��ʿ� ������� ������

#define BLOCK_L push_map[stage][user_y][user_x-1] == 1		//���� ���ʿ� ������
#define BLOCK_R push_map[stage][user_y][user_x+1] == 1		//���� �����ʿ� ������
#define BLOCK_U push_map[stage][user_y-1][user_x] == 1		//���� ���� ������
#define BLOCK_D push_map[stage][user_y+1][user_x] == 1		//���� �Ʒ� ������
#define BBLOCK_L push_map[stage][user_y][user_x-2] == 1		//�� ���ʿ� ���� ������
#define BBLOCK_R push_map[stage][user_y][user_x+2] == 1		//�� �����ʿ� ���� ������
#define BBLOCK_U push_map[stage][user_y-2][user_x] == 1		//�� ���� ���� ������
#define BBLOCK_D push_map[stage][user_y+2][user_x] == 1		//�� �Ʒ��� ���� ������
#define BALL_L push_map[stage][user_y][user_x-1] == 3		//���ʿ� ����������
#define BALL_R push_map[stage][user_y][user_x+1] == 3		//�����ʿ� ����������
#define BALL_U push_map[stage][user_y-1][user_x] == 3		//���� ����������
#define BALL_D push_map[stage][user_y+1][user_x] == 3		//�Ʒ��� ����������


class Controller  : public View
{
private:
	typedef int( *ThreeArray )[15][15];	//ThreeArray��� �ڷ����� �������ش�.
	ThreeArray push_map;	//������ �迭�� �������� push_map(��)��� �Ѵ�.
	Model m;	//ModelŬ������ ������ m(��)��� �Ѵ�.
	View v;		//ViewŬ������ ������ v(��)��� �Ѵ�.

	
	
public:
	Controller(void){
	push_map = m.getMap();	// Model Ŭ������ �ִ� �迭�� return ���ִ� �Լ��� push_map������ �����ϴ� �����ڸ� �����.
	};
	~Controller(void){};

	
	static int getkey()		//Ű�� �Է� �޴� �Լ�
	{
		int retval = (_getch() & 0x00ff);
		return ( retval == 0xe0 ) ? ( ( retval << 8 ) | _getch()) : retval;
	}

	int keyLEFT()	// ������ ����Ű�� �Է����� �� ���� ��ȭ�� ������ִ� �Լ�
	{
		/**********************************************���� 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(�߿�) ���� ���� ���°�츦 ù��° ū �������� ���´�.
		{
			if(FREE_L)
			{
				temp = push_map[stage][user_y][user_x];
				push_map[stage][user_y][user_x] = push_map[stage][user_y][user_x-1];
				push_map[stage][user_y][user_x-1] = temp;
				m.count++;
			}
			else if(BLOCK_L) 
				return 0;

			else if(BALL_L)
			{
				if(BBLOCK_L) 
					return 0;
				if(push_map[stage][user_y][user_x-2] == 4)		//		�� ������ ���϶�
				{
					push_map[stage][user_y][user_x-2] = 5;
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 3)//		�� ������ ���̰� �� ���ʵ� ���϶�
					return 0;

				else if(push_map[stage][user_y][user_x-2] == 0)		//		�� ������ ������϶�
				{
					temp = push_map[stage][user_y][user_x-2];
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y][user_x-1] == 4)//		�� ������ ����ִ� ���϶�
			{
				push_map[stage][user_y][user_x-1] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 5)//		���ʿ� ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y][user_x-2] == 1 || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)//	���� ����ִ� �� ������ ��, ��, ���̵���ִ� ���϶�
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 0)//		���� ����ִ� �� ������ ������϶�
				{
					push_map[stage][user_y][user_x-2] = 3;
					push_map[stage][user_y][user_x-1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y][user_x-2] == 4)//		���� ����ִ� �� ������ ����ִ� ���϶�
				{
					temp = push_map[stage][user_y][user_x-2];
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************���� 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		********(�߿�)���� ���� ���� �������� �ι�° ū �������� ���´�.*******
		{

			if(push_map[stage][user_y][user_x-1] == 1)//��
				return 0;
			else if(push_map[stage][user_y][user_x-1] == 0)//�����
			{

				push_map[stage][user_y][user_x-1] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 3)//��
			{
				if(BBLOCK_L || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)//		������ ���̰� �� ������ �� || ������ ���̰� �� ������ �� || ������ ���̰� �� ������ ���� ����ִ� ���϶� 
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 0)//		�� ������ ���̰� �� ������ ������϶� 
				{
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 4)//		�� ������ ���̰� �� ������ ����ִ� ���϶�
				{
					push_map[stage][user_y][user_x-2] = 5;
					push_map[stage][user_y][user_x-1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x-1] == 4)//��
			{
				push_map[stage][user_y][user_x-1] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 5)//������ ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y][user_x-2] == 0)//�� ������ ����� 
				{
					push_map[stage][user_y][user_x-2] = 3;
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 1 || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)// �� ������ ��, ��, ���̵���ִ���
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 4)// �� ������ ����ִ� ��
				{
					push_map[stage][user_y][user_x-2] = 5;
					push_map[stage][user_y][user_x-1] = 6;
					push_map[stage][user_y][user_x] = 4;	
					m.count++;
				}
			}

		}
		user_x--;
		
		
		return 0;
	}
	int keyRIGHT()  // ������ ������Ű�� �Է����� �� ���� ��ȭ�� ������ִ� �Լ�
	{
		/**********************************************���� 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(�߿�) ���� ���� ���°�츦 ù��° ū �������� ���´�.
		{
			if(FREE_R)
			{
				temp = push_map[stage][user_y][user_x];
				push_map[stage][user_y][user_x] = push_map[stage][user_y][user_x+1];
				push_map[stage][user_y][user_x+1] = temp;
				m.count++;
			}
			else if(BLOCK_R) 
				return 0;
			else if(BALL_R)
			{
				if(BBLOCK_R) 
					return 0;
				if(push_map[stage][user_y][user_x+2] == 4)		//		�� ������ ���϶�
				{
					push_map[stage][user_y][user_x+2] = 5; // user_x + x ,  x = +2 , y = 0; <- RIGHT   user_x + x ,  x = -2, y = 0 LEFT,
					//user_x + x ,  x = 0 , y = +2; <- DOWN   user_x + x , user_y + y.......  x = 0, y = -2 UP,
						push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 3)//		�� ������ ���̰� �� ���ʵ� ���϶�
					return 0;

				else if(push_map[stage][user_y][user_x+2] == 0)		//		�� ������ ������϶�
				{
					temp = push_map[stage][user_y][user_x+2];
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x+1] == 4)//		�� �������� ����ִ� ���϶�
			{
				push_map[stage][user_y][user_x+1] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 5)//		�����ʿ� ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y][user_x+2] == 1 || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)//	���� ����ִ� �� ������ ��, ��, ���̵���ִ� ���϶�
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 0)//		���� ����ִ� �� ������ ������϶�
				{
					push_map[stage][user_y][user_x+2] = 3;
					push_map[stage][user_y][user_x+1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y][user_x+2] == 4)//		���� ����ִ� �� ������ ����ִ� ���϶�
				{
					temp = push_map[stage][user_y][user_x+2];
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}
		}
		/**********************************************���� 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		���� ���� ���� ������
		{

			if(push_map[stage][user_y][user_x+1] == 0)//�����
			{

				push_map[stage][user_y][user_x+1] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 1)//��
				return 0;
			else if(push_map[stage][user_y][user_x+1] == 3)//��
			{
				if(BBLOCK_R || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)//		������ ���̰� �� ������ �� || ������ ���̰� �� ������ �� || ������ ���̰� �� ������ ���� ����ִ� ���϶� 
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 0)//		�� ������ ���̰� �� ������ ���϶�
				{
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 4)//		�� ������ ���̰� �� ������ ����ִ� ���϶�
				{
					push_map[stage][user_y][user_x+2] = 5;
					push_map[stage][user_y][user_x+1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x+1] == 4)//��
			{
				push_map[stage][user_y][user_x+1] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 5)//������ ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y][user_x+2] == 0)//�� ������ ����� 
				{
					push_map[stage][user_y][user_x+2] = 3;
					push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 1 || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)// �� ������ ��, ��, ���̵���ִ���
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 4)// �� ������ ����ִ� ��
				{
					push_map[stage][user_y][user_x+2] = 5;
					push_map[stage][user_y][user_x+1] = 6;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}

		}

		user_x++;
		
		return 0;
	}
	int keyUP()  // ������ ����Ű�� �Է����� �� ���� ��ȭ�� ������ִ� �Լ�
	{
		/**********************************************���� 1**************************************************/
		if(push_map[stage][user_y][user_x] == 2)//		(�߿�) ���� ���� ���°�츦 ù��° ū �������� ���´�.
		{
			if(FREE_U)
			{
				temp = push_map[stage][user_y][user_x];
				push_map[stage][user_y][user_x] = push_map[stage][user_y-1][user_x];
				push_map[stage][user_y-1][user_x] = temp;
				m.count++;
			}
			else if(BLOCK_U) 
				return 0;
			else if(BALL_U)
			{
				if(BBLOCK_U) 
					return 0;
				if(push_map[stage][user_y-2][user_x] == 4)		//		�� ������ ���϶�
				{
					push_map[stage][user_y-2][user_x] = 5;
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 3)//		�� ������ ���̰� �� ���ʵ� ���϶�
					return 0;

				else if(push_map[stage][user_y-2][user_x] == 0)		//		�� ������ ������϶�
				{
					temp = push_map[stage][user_y-2][user_x];
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y-1][user_x] == 4)//		�� ������ ����ִ� ���϶�
			{
				push_map[stage][user_y-1][user_x] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 5)//		���ʿ� ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y-2][user_x] == 1 || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)//	���� ����ִ� �� ������ ��, ��, ���̵���ִ� ���϶�
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 0)//		���� ����ִ� �� ������ ������϶�
				{
					push_map[stage][user_y-2][user_x] = 3;
					push_map[stage][user_y-1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y-2][user_x] == 4)//		���� ����ִ� �� ������ ����ִ� ���϶�
				{
					temp = push_map[stage][user_y-2][user_x];
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************���� 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		���� ���� ���� ������
		{

			if(push_map[stage][user_y-1][user_x] == 0)//�����
			{

				push_map[stage][user_y-1][user_x] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 1)//��
				return 0;
			else if(push_map[stage][user_y-1][user_x] == 3)//��
			{
				if(BBLOCK_U || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)//		������ ���̰� �� ������ �� || ������ ���̰� �� ������ �� || ������ ���̰� �� ������ ���� ����ִ� ���϶� 
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 0)//		�� ������ ���̰� �� ������ ���϶�
				{
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 4)//		�� ������ ���̰� �� ������ ����ִ� ���϶�
				{
					push_map[stage][user_y-2][user_x] = 5;
					push_map[stage][user_y-1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y-1][user_x] == 4)//��
			{
				push_map[stage][user_y-1][user_x] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 5)//������ ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y-2][user_x] == 0)//�� ������ ����� 
				{
					push_map[stage][user_y-2][user_x] = 3;
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 1 || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)// �� ������ ��, ��, ���̵���ִ���
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 4)// �� ������ ����ִ� ��
				{
					push_map[stage][user_y-2][user_x] = 5;
					push_map[stage][user_y-1][user_x] = 6;
					push_map[stage][user_y][user_x] = 4;	
					m.count++;
				}
			}

		}
		user_y--;
	
		return 0;
	}
	int keyDOWN()  // ������ �Ʒ���Ű�� �Է����� �� ���� ��ȭ�� ������ִ� �Լ�
	{
		/**********************************************���� 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(�߿�) ���� ���� ���°�츦 ù��° ū �������� ���´�.
		{
			if(FREE_D)
			{	
				temp = push_map[stage][user_y][user_x];
				push_map[stage][user_y][user_x] = push_map[stage][user_y+1][user_x];
				push_map[stage][user_y+1][user_x] = temp;
				m.count++;
			}
			if(BLOCK_D) 
				return 0;
			else if(BALL_D)
			{
				if(BBLOCK_D) 
					return 0;
				if(push_map[stage][user_y+2][user_x] == 4)		//		�� ������ ���϶�
				{
					push_map[stage][user_y+2][user_x] = 5;
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 3)//		�� ������ ���̰� �� ���ʵ� ���϶�
					return 0;

				else if(push_map[stage][user_y+2][user_x] == 0)		//		�� ������ ������϶�
				{
					temp = push_map[stage][user_y+2][user_x];
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y+1][user_x] == 4)//		�� ������ ����ִ� ���϶�
			{
				push_map[stage][user_y+1][user_x] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 5)//		���ʿ� ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y+2][user_x] == 1 || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)//	���� ����ִ� �� ������ ��, ��, ���̵���ִ� ���϶�
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 0)//		���� ����ִ� �� ������ ������϶�
				{
					push_map[stage][user_y+2][user_x] = 3;
					push_map[stage][user_y+1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y+2][user_x] == 4)//		���� ����ִ� �� ������ ����ִ� ���϶�
				{
					temp = push_map[stage][user_y+2][user_x];
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************���� 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		���� ���� ���� ������
		{
			if(push_map[stage][user_y+1][user_x] == 1)//��
				return 0;
			else if(push_map[stage][user_y+1][user_x] == 0)//�����
			{

				push_map[stage][user_y+1][user_x] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 3)//��
			{
				if(BBLOCK_D || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)//		������ ���̰� �� ������ �� || ������ ���̰� �� ������ �� || ������ ���̰� �� ������ ���� ����ִ� ���϶� 
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 0)//		�� ������ ���̰� �� ������ ���϶�
				{
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 4)//		�� ������ ���̰� �� ������ ����ִ� ���϶�
				{
					push_map[stage][user_y+2][user_x] = 5;
					push_map[stage][user_y+1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y+1][user_x] == 4)//��
			{
				push_map[stage][user_y+1][user_x] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 5)//������ ���� ����ִ� ���϶�
			{
				if(push_map[stage][user_y+2][user_x] == 0)//�� ������ ����� 
				{
					push_map[stage][user_y+2][user_x] = 3;
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 1 || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)// �� ������ ��, ��, ���̵���ִ���
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 4)// �� ������ ����ִ� ��
				{
					push_map[stage][user_y+2][user_x] = 5;
					push_map[stage][user_y+1][user_x] = 6;
					push_map[stage][user_y][user_x] = 4;	
					m.count++;
				}
			}

		}
		user_y++;
		
		return 0;
	}
	void goodKey()	//�ùķ��̼��� ����Ǵ� �Լ�
	{
		mapClean();
		Beep(500,150);	//���ļ���  500, ���̸� 150�̶� �Ѵ�.
		v.mapView();Beep(500,150);
		Model :: push_map[0][7][4]=0;	
		Model :: push_map[0][7][5]=2;	
		v.mapView();Beep(500,150);
		Model :: push_map[0][7][5]=0;	
		Model :: push_map[0][7][6]=2;	
		v.mapView();Beep(350,150);
		Model :: push_map[0][7][6]=0;	
		Model :: push_map[0][6][6]=2;	
		v.mapView();Beep(350,150);
		Model :: push_map[0][6][6]=0;	
		Model :: push_map[0][5][6]=2;	
		v.mapView();Beep(350,150);
		Model :: push_map[0][5][6]=0;	
		Model :: push_map[0][4][6]=2;	
		v.mapView();Beep(350,150);
		Model :: push_map[0][4][6]=0;	
		Model :: push_map[0][4][5]=2;	
		v.mapView();Beep(350,150);
		Model :: push_map[0][4][5]=0;	
		Model :: push_map[0][5][5]=2;	
		Model :: push_map[0][6][5]=5;
		v.mapView();
		gotoxy(8,20);cout << "�̷������� ���� �Ͻø� �˴ϴ� " <<endl;
		gotoxy(8,22);cout << "�޴��� ���ư��÷��� ESCŰ�� �Է��Ͻÿ� " ;
		if(getkey())	//���� ESCŰ�� �Է����� ��
		{
			Game :: firstViews();	//�ʱ� �޴�ȭ�� ���
		}
	}
	void selectMap()	//���ϴ� ���� �������� �� ��µǴ� �Լ�
	{
		if ( Game :: first_map%3 == 0 )	//���� �ι�° ���� �����Ͽ��� ��
		{
			mapClean();	//�� Ŭ��
			stage=1;	//�ι�° ���� �����Ͽ��� ��� ���� 1�� ����
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();	//���� ������� �ʱ�ȭ ���ش�.
			v.mapView();	//�� ���
		}
		else if ( Game :: first_map%3 == 1 )	//���� ����° ���� �����Ͽ��� ��
		{
			mapClean();//�� Ŭ��
			stage=2;	//����° ���� �����Ͽ��� ��� ���� 2�� ����
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();//���� ������� �ʱ�ȭ ���ش�.
			v.mapView();//�� ���
		}
		else if ( Game :: first_map%3 == 2 )	//���� ù��° ���� �����Ͽ��� ��
		{
			mapClean();//�� Ŭ��
			stage=0;	//ù��° ���� �����Ͽ��� ��� ���� 0�� ����
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();//���� ������� �ʱ�ȭ ���ش�.
			v.mapView();//�� ���
		}
	}
	void stageFind()	//���ϴ� stage�� ���� �Լ�.
	{
		mapClean();
		Game :: first_map=1001;	//�ʼ��� ȭ�鿡�� Ű�� ������ ���ϴ� ���¸� ������ֱ� ���� �ʱ�ȭ
		Game :: start_menu=1;
		Game :: start_map=1;
		textcolor(YELLOW);
		gotoxy(3,5);
		m.stage=0;
		Game :: rePush();
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
		m.stage=1;
	   
		Game :: rePush();
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
		m.stage=2;
	  
		Game :: rePush();
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
		textcolor(WHITE);
		gotoxy(8,20);
		cout<< "����Ű (       <-     ) �� (        ->        ) �� ���� �����Ͻʽÿ� ";
		m.stage=0;
		while(Game :: start_map!=0)
		{
			switch(getkey())
			{
			case RIGHTKEY : Game :: rightMap(); break;
			case LEFTKEY : Game :: leftMap(); break;
			case ENTERKEY : selectMap(); break;
			case ESCKEY : Game :: firstViews(); break;
			}
		}
	} 
	void userRank()	//�� �ܰ躰�� �ְ����� ����ϴ� �Լ�
	{
		Game :: first_menu=1001;	//�޴� �ʱ�ȭ
		system("mode con cols=75 lines=25"); //mode â�� ���̸� 75, ���̸� 25�� �Ѵ�.
		//m.whiteColor();	//������ �Ͼ������ ����
		//textcolor(BLACK);	//���ڻ��� ���������� ����
		gotoxy(14,5); cout << "1�ܰ� �ְ��� : " <<m.user_rank1;	//1�ܰ� �ְ��� ���
		gotoxy(14,8); cout << "2�ܰ� �ְ��� : " <<m.user_rank2;	//2�ܰ� �ְ��� ���
		gotoxy(14,11); cout << "3�ܰ� �ְ��� : " <<m.user_rank3;	//3�ܰ� �ְ��� ���
		if(getkey())	//���� Ű�� �Է�/
		{
			MenuRelease();	//�� CLEAN
			Game :: firstViews();	//�޴���� �Լ� ȣ��	
		}
	}	

	int selectMenu()	//���ϴ� �޴��� ENTER Ű�� ������ �� ������ִ� �Լ�
	{
		if( Game :: first_menu%5 == 1)	// ���� ���ӽ��� �޴��� �������� ���
		{
			MenuRelease(); //�� CLEAN
			v.mapView(); //�� ���
			Game :: start_menu=0;	//������ �����ϴ� ���� ȣ���ϱ� ���Ͽ� menu������ ���� 0���� ����
			return 0;
		}

		else if( Game :: first_menu%5 == 2 )	//���� �� ���� �޴��� �������� ���
		{
			MenuRelease();	//�� CLEAN
			stageFind();	//���� �˻��ϴ� �Լ� ȣ��
		}

		else if( Game :: first_menu%5 == 3 )	// ���� ���� �޴��� �������� ���
		{
			MenuRelease();	//�� CLEAN
			HelpRender();	//���� �Լ� ȣ��
		}


		else if( Game :: first_menu%5 == 4 )	// ���� ��ŷ �޴��� �������� ���
		{
			MenuRelease();	//�� CLEAN
			userRank();	//��ŷ �Լ� ȣ��
		}

		else if( Game :: first_menu%5 == 0 )	// ���� ���� �޴��� �������� ���
		{
			MenuRelease();	//�� CLEAN					
			Game :: start_menu=100;	//����޴��� ENTERŰ�� ������ �� �������ֱ����� menu�� �������� 100�� ����
			m.rankingWirte();	//��ŷ�� ranking.txt�� �Է��ϴ� �Լ� ȣ��
		}
	}
	
	void firstMenu()	//�޴����� �Լ�
	{
		switch(getkey())
		{		 
		case UPKEY : Game:: upMenu(); break;
		case DOWNKEY : Game :: downMenu(); break;
		case ENTERKEY : selectMenu(); break;	
		}
	}
	
	void HelpRender()	//������ ������ִ� �Լ�
{
	Game :: first_menu = 1001;	//�޴� �ʱ�ȭ
	m.blackColor(); //��� ���� �������� ����
	textcolor(WHITE);	//���ڻ��� ������� ����
	
	//system("mode con cols=110 lines=18");	//mode â�� ���̸� 110, ���̸� 18�� ����
	Beep(260,150);gotoxy(4,3);cout << "Ǫ��Ǫ���� ����ڰ� ���� �о ";
	Beep(270,150); cout << "Ư�� ��ġ�� �̵����Ѿ� �Ǵ� �����Դϴ�.";
	Beep(285,150);gotoxy(4,5);cout << "��";
	Beep(300,150);gotoxy(6,5);cout << "�� �÷��̾��Դϴ�. �÷��̾�� ";
	Beep(320,150);gotoxy(36,5);cout << "��";
	Beep(335,150);gotoxy(38,5);cout << "���� �о ";
	Beep(350,150);gotoxy(52,5);cout << "��";
	Beep(365,150);gotoxy(54,5);cout << "�� ��������� �˴ϴ�.";
	Beep(380,150);gotoxy(4,7);cout << "�̵���� : ����Ű";
	Beep(405,150);gotoxy(4,8);cout << "���� �ܰ� : BackSpace ";
	Beep(420,150);gotoxy(4,9);cout << "���� �ܰ� : W ";
	Beep(435,150);gotoxy(4,10);cout << "STAGE ���� : P";
	Beep(450,150);gotoxy(4,11);cout << "���ν��� : R";
	Beep(465,150);gotoxy(4,12);cout << "���� ���� : Q";
	Beep(480,150);gotoxy(4,14);cout << "�޴� ȭ������ �����÷��� ESC�� Ŭ���ϼ���.";
	Beep(480,150);gotoxy(4,16);cout << "�ùķ��̼��� ���÷��� ENTERŰ�� �Է��Ͻÿ�. ";
	switch(getkey())
	{
	case ESCKEY : Game :: firstViews(); break;
	case ENTERKEY : goodKey(); break;
	}
}
	void MenuRelease()			//�� CLEAN�Լ�
	{

		int i;
		for(i=1; i<50; i++)
		{
			gotoxy(1,i);
			printf("                                                                                                                  ");
		}

	}

	int gameStart()	//������ �����ϴ� �Լ�
	{
		m.rankingRead();	//��ŷ�� ����ϴ� �Լ� ȣ��
		_setcursortype(_NOCURSOR);	//Ŀ�� ����
		system("title �� P U S H   P U S H ---->BY KIM MOON SU��");	//mode �ؽ�Ʈ�ٿ� Ÿ��Ʋ ���
		Game :: firstViews();	//�޴� ��� �Լ� ȣ��
		
		while(1)	// ���� �ݺ�
		{
			if( Game :: start_menu == 1)	{ firstMenu(); }	//menu������ ���� 0�� �ƴϰ� 100�� �ƴҶ� ���ϴ� �޴��� ENTER Ű�� ������ �� ������ִ� �Լ� ȣ��
			else if( Game :: start_menu == 100)	{return 0;}	//����޴��� ENTERŰ�� ������ �� �������ֱ����� menu�� �������� 100������ Ȯ���ϰ� �����Ѵ�.
			else if( m.stage == 3  )	//���� stage�� �����ų� �޴����� ����޴��� �������� ��
			{
				Game :: first_menu=1001;	//menu�������� 1�� �����Ͽ� �ٽ� �޴��� ��µǾ��� �� �ʱⰪ���� �ǵ����� ���ش�
				MenuRelease();	//�� CLEAN
				Game :: firstViews();	//�޴��Լ� ȣ�� 
			}
		
			else if( Game :: start_menu == 0)	//�׹ۿ� menu������ ���� 0���� Ȯ���Ͽ� ������ �����Ѵ�.
			{
				
				v.sideMap();	//side �޴��Լ� ȣ��
				m.userSearch();	//������� ��ġ�� �˷��ִ� �Լ� ȣ��
				switch(getkey())	//���� Ű�� �Է¹޾��� ��
				{
				case LEFTKEY: keyLEFT(); break;	//����Ű�� �Է¹����� keyLEFT�Լ� ȣ��
				case RIGHTKEY: keyRIGHT(); break;	//������Ű�� �Է¹����� keyRIGHT�Լ� ȣ��
				case DOWNKEY: keyDOWN(); break;	//����Ű�� �Է¹����� keyUP�Լ� ȣ��
				case UPKEY:	keyUP(); break;	//�Ʒ���Ű�� �Է¹����� keyDOWN�Լ� ȣ��
				case PREVKEY: return 0;	//QŰ�� �Է¹����� ���α׷� ����
				case NEXTKEY : v.nextMap(); break;	//WŰ�� �Է¹����� ���� ���� ���
				case BACKKEY : v.prevMap(); break;	//backspaceŰ�� �Է¹����� ���� ���� ���
				case SEARCHKEY : stageFind(); break;	//PŰ�� �Է¹����� ���ϴ� ���� �����ϴ� �Լ� ȣ��
				case RESETKEY : Game :: rePush(); break;	//RŰ�� �Է¹����� �� �ʱ�ȭ
				case ESCKEY : 	Game :: firstViews(); break;	//ESCŰ�� �Է¹����� �޴�ȭ�� �Լ� ȣ��	
				}
				if( Game :: start_menu == 0)	// ���� �� �޴��� ���ư��� �� ���� ��� �ȵǰ� �Ϸ��� start_menu������ 0���� �����ߴ�. �׷��� start_menu������ 0�� �� ���� ���
					v.mapView();	//��� ���� ������ش�.

			}
		}
	}

	
};

#endif