#ifndef CONTROLLER_H
#define CONTROLLER_H

#include <iostream>
#include <conio.h>
#include "Getkey.h"
#include "Consola.h"
#include "Game.h"
#include <Windows.h>
using namespace std;


#define FREE_L push_map[stage][user_y][user_x-1] == 0		//왼쪽에 빈공간이 있을때
#define FREE_R push_map[stage][user_y][user_x+1] == 0		//오른쪽에 빈공간이 있을때
#define FREE_U push_map[stage][user_y-1][user_x] == 0		//위쪽에 빈공간이 있을때
#define FREE_D push_map[stage][user_y+1][user_x] == 0		//아래쪽에 빈공간이 있을때

#define BLOCK_L push_map[stage][user_y][user_x-1] == 1		//벽이 왼쪽에 있을때
#define BLOCK_R push_map[stage][user_y][user_x+1] == 1		//벽이 오른쪽에 있을때
#define BLOCK_U push_map[stage][user_y-1][user_x] == 1		//벽이 위에 있을때
#define BLOCK_D push_map[stage][user_y+1][user_x] == 1		//벽이 아래 있을때
#define BBLOCK_L push_map[stage][user_y][user_x-2] == 1		//그 왼쪽에 벽이 있을때
#define BBLOCK_R push_map[stage][user_y][user_x+2] == 1		//그 오른쪽에 벽이 있을때
#define BBLOCK_U push_map[stage][user_y-2][user_x] == 1		//그 위에 벽이 있을때
#define BBLOCK_D push_map[stage][user_y+2][user_x] == 1		//그 아래에 벽이 있을때
#define BALL_L push_map[stage][user_y][user_x-1] == 3		//왼쪽에 공이있을때
#define BALL_R push_map[stage][user_y][user_x+1] == 3		//오른쪽에 공이있을때
#define BALL_U push_map[stage][user_y-1][user_x] == 3		//위에 공이있을때
#define BALL_D push_map[stage][user_y+1][user_x] == 3		//아래에 공이있을때


class Controller  : public View
{
private:
	typedef int( *ThreeArray )[15][15];	//ThreeArray라는 자료형을 선언해준다.
	ThreeArray push_map;	//삼차원 배열의 변수명을 push_map(이)라고 한다.
	Model m;	//Model클래스의 변수를 m(이)라고 한다.
	View v;		//View클래스의 변수를 v(이)라고 한다.

	
	
public:
	Controller(void){
	push_map = m.getMap();	// Model 클래스의 있는 배열을 return 해주는 함수를 push_map변수에 저장하는 생성자를 만든다.
	};
	~Controller(void){};

	
	static int getkey()		//키를 입력 받는 함수
	{
		int retval = (_getch() & 0x00ff);
		return ( retval == 0xe0 ) ? ( ( retval << 8 ) | _getch()) : retval;
	}

	int keyLEFT()	// 게임중 왼쪽키를 입력했을 때 맵의 변화를 출력해주는 함수
	{
		/**********************************************조건 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(중요) 내가 집에 없는경우를 첫번째 큰 조건으로 갖는다.
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
				if(push_map[stage][user_y][user_x-2] == 4)		//		공 왼쪽이 집일때
				{
					push_map[stage][user_y][user_x-2] = 5;
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 3)//		내 왼쪽이 공이고 그 왼쪽도 공일때
					return 0;

				else if(push_map[stage][user_y][user_x-2] == 0)		//		공 왼쪽이 빈공간일때
				{
					temp = push_map[stage][user_y][user_x-2];
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y][user_x-1] == 4)//		내 왼쪽이 비어있는 집일때
			{
				push_map[stage][user_y][user_x-1] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 5)//		왼쪽에 공이 들어있는 집일때
			{
				if(push_map[stage][user_y][user_x-2] == 1 || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)//	공이 들어있는 집 왼쪽이 벽, 공, 공이들어있는 집일때
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 0)//		공이 들어있는 집 왼쪽이 빈공간일때
				{
					push_map[stage][user_y][user_x-2] = 3;
					push_map[stage][user_y][user_x-1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y][user_x-2] == 4)//		공이 들어있는 집 왼쪽이 비어있는 집일때
				{
					temp = push_map[stage][user_y][user_x-2];
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************조건 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		********(중요)내가 지금 집에 있을때를 두번째 큰 조건으로 갖는다.*******
		{

			if(push_map[stage][user_y][user_x-1] == 1)//벽
				return 0;
			else if(push_map[stage][user_y][user_x-1] == 0)//빈공간
			{

				push_map[stage][user_y][user_x-1] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 3)//공
			{
				if(BBLOCK_L || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)//		왼쪽이 공이고 그 왼쪽이 벽 || 왼쪽이 공이고 그 왼쪽이 공 || 왼쪽이 공이고 그 왼쪽이 공이 들어있는 집일때 
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 0)//		내 왼쪽이 공이고 그 왼쪽이 빈공간일때 
				{
					push_map[stage][user_y][user_x-2] = push_map[stage][user_y][user_x-1];
					push_map[stage][user_y][user_x-1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 4)//		내 왼쪽이 공이고 그 왼쪽이 비어있는 집일때
				{
					push_map[stage][user_y][user_x-2] = 5;
					push_map[stage][user_y][user_x-1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x-1] == 4)//집
			{
				push_map[stage][user_y][user_x-1] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x-1] == 5)//왼쪽이 공이 들어있는 집일때
			{
				if(push_map[stage][user_y][user_x-2] == 0)//그 왼쪽이 빈공간 
				{
					push_map[stage][user_y][user_x-2] = 3;
					push_map[stage][user_y][user_x-1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x-2] == 1 || push_map[stage][user_y][user_x-2] == 3 || push_map[stage][user_y][user_x-2] == 5)// 그 왼쪽이 벽, 공, 공이들어있는집
					return 0;
				else if(push_map[stage][user_y][user_x-2] == 4)// 그 왼쪽이 비어있는 집
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
	int keyRIGHT()  // 게임중 오른쪽키를 입력했을 때 맵의 변화를 출력해주는 함수
	{
		/**********************************************조건 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(중요) 내가 집에 없는경우를 첫번째 큰 조건으로 갖는다.
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
				if(push_map[stage][user_y][user_x+2] == 4)		//		공 왼쪽이 집일때
				{
					push_map[stage][user_y][user_x+2] = 5; // user_x + x ,  x = +2 , y = 0; <- RIGHT   user_x + x ,  x = -2, y = 0 LEFT,
					//user_x + x ,  x = 0 , y = +2; <- DOWN   user_x + x , user_y + y.......  x = 0, y = -2 UP,
						push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 3)//		내 왼쪽이 공이고 그 왼쪽도 공일때
					return 0;

				else if(push_map[stage][user_y][user_x+2] == 0)		//		공 왼쪽이 빈공간일때
				{
					temp = push_map[stage][user_y][user_x+2];
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x+1] == 4)//		내 오른쪽이 비어있는 집일때
			{
				push_map[stage][user_y][user_x+1] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 5)//		오른쪽에 공이 들어있는 집일때
			{
				if(push_map[stage][user_y][user_x+2] == 1 || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)//	공이 들어있는 집 왼쪽이 벽, 공, 공이들어있는 집일때
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 0)//		공이 들어있는 집 왼쪽이 빈공간일때
				{
					push_map[stage][user_y][user_x+2] = 3;
					push_map[stage][user_y][user_x+1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y][user_x+2] == 4)//		공이 들어있는 집 왼쪽이 비어있는 집일때
				{
					temp = push_map[stage][user_y][user_x+2];
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}
		}
		/**********************************************조건 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		내가 지금 집에 있을때
		{

			if(push_map[stage][user_y][user_x+1] == 0)//빈공간
			{

				push_map[stage][user_y][user_x+1] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 1)//벽
				return 0;
			else if(push_map[stage][user_y][user_x+1] == 3)//공
			{
				if(BBLOCK_R || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)//		왼쪽이 공이고 그 왼쪽이 벽 || 왼쪽이 공이고 그 왼쪽이 공 || 왼쪽이 공이고 그 왼쪽이 공이 들어있는 집일때 
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 0)//		내 왼쪽이 집이고 그 왼쪽이 공일때
				{
					push_map[stage][user_y][user_x+2] = push_map[stage][user_y][user_x+1];
					push_map[stage][user_y][user_x+1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 4)//		내 왼쪽이 공이고 그 왼쪽이 비어있는 집일때
				{
					push_map[stage][user_y][user_x+2] = 5;
					push_map[stage][user_y][user_x+1] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y][user_x+1] == 4)//집
			{
				push_map[stage][user_y][user_x+1] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y][user_x+1] == 5)//왼쪽이 공이 들어있는 집일때
			{
				if(push_map[stage][user_y][user_x+2] == 0)//그 왼쪽이 빈공간 
				{
					push_map[stage][user_y][user_x+2] = 3;
					push_map[stage][user_y][user_x+1] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y][user_x+2] == 1 || push_map[stage][user_y][user_x+2] == 3 || push_map[stage][user_y][user_x+2] == 5)// 그 왼쪽이 벽, 공, 공이들어있는집
					return 0;
				else if(push_map[stage][user_y][user_x+2] == 4)// 그 왼쪽이 비어있는 집
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
	int keyUP()  // 게임중 위쪽키를 입력했을 때 맵의 변화를 출력해주는 함수
	{
		/**********************************************조건 1**************************************************/
		if(push_map[stage][user_y][user_x] == 2)//		(중요) 내가 집에 없는경우를 첫번째 큰 조건으로 갖는다.
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
				if(push_map[stage][user_y-2][user_x] == 4)		//		공 왼쪽이 집일때
				{
					push_map[stage][user_y-2][user_x] = 5;
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 3)//		내 왼쪽이 공이고 그 왼쪽도 공일때
					return 0;

				else if(push_map[stage][user_y-2][user_x] == 0)		//		공 왼쪽이 빈공간일때
				{
					temp = push_map[stage][user_y-2][user_x];
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y-1][user_x] == 4)//		내 왼쪽이 비어있는 집일때
			{
				push_map[stage][user_y-1][user_x] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 5)//		왼쪽에 공이 들어있는 집일때
			{
				if(push_map[stage][user_y-2][user_x] == 1 || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)//	공이 들어있는 집 왼쪽이 벽, 공, 공이들어있는 집일때
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 0)//		공이 들어있는 집 왼쪽이 빈공간일때
				{
					push_map[stage][user_y-2][user_x] = 3;
					push_map[stage][user_y-1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y-2][user_x] == 4)//		공이 들어있는 집 왼쪽이 비어있는 집일때
				{
					temp = push_map[stage][user_y-2][user_x];
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************조건 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		내가 지금 집에 있을때
		{

			if(push_map[stage][user_y-1][user_x] == 0)//빈공간
			{

				push_map[stage][user_y-1][user_x] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 1)//벽
				return 0;
			else if(push_map[stage][user_y-1][user_x] == 3)//공
			{
				if(BBLOCK_U || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)//		왼쪽이 공이고 그 왼쪽이 벽 || 왼쪽이 공이고 그 왼쪽이 공 || 왼쪽이 공이고 그 왼쪽이 공이 들어있는 집일때 
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 0)//		내 왼쪽이 집이고 그 왼쪽이 공일때
				{
					push_map[stage][user_y-2][user_x] = push_map[stage][user_y-1][user_x];
					push_map[stage][user_y-1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 4)//		내 왼쪽이 공이고 그 왼쪽이 비어있는 집일때
				{
					push_map[stage][user_y-2][user_x] = 5;
					push_map[stage][user_y-1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y-1][user_x] == 4)//집
			{
				push_map[stage][user_y-1][user_x] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y-1][user_x] == 5)//왼쪽이 공이 들어있는 집일때
			{
				if(push_map[stage][user_y-2][user_x] == 0)//그 왼쪽이 빈공간 
				{
					push_map[stage][user_y-2][user_x] = 3;
					push_map[stage][user_y-1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y-2][user_x] == 1 || push_map[stage][user_y-2][user_x] == 3 || push_map[stage][user_y-2][user_x] == 5)// 그 왼쪽이 벽, 공, 공이들어있는집
					return 0;
				else if(push_map[stage][user_y-2][user_x] == 4)// 그 왼쪽이 비어있는 집
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
	int keyDOWN()  // 게임중 아래쪽키를 입력했을 때 맵의 변화를 출력해주는 함수
	{
		/**********************************************조건 1**************************************************/

		if(push_map[stage][user_y][user_x] == 2)//		(중요) 내가 집에 없는경우를 첫번째 큰 조건으로 갖는다.
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
				if(push_map[stage][user_y+2][user_x] == 4)		//		공 왼쪽이 집일때
				{
					push_map[stage][user_y+2][user_x] = 5;
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 3)//		내 왼쪽이 공이고 그 왼쪽도 공일때
					return 0;

				else if(push_map[stage][user_y+2][user_x] == 0)		//		공 왼쪽이 빈공간일때
				{
					temp = push_map[stage][user_y+2][user_x];
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = temp;
					m.count++;
				}
			}

			else if(push_map[stage][user_y+1][user_x] == 4)//		내 왼쪽이 비어있는 집일때
			{
				push_map[stage][user_y+1][user_x] = 6;  
				push_map[stage][user_y][user_x] = 0;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 5)//		왼쪽에 공이 들어있는 집일때
			{
				if(push_map[stage][user_y+2][user_x] == 1 || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)//	공이 들어있는 집 왼쪽이 벽, 공, 공이들어있는 집일때
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 0)//		공이 들어있는 집 왼쪽이 빈공간일때
				{
					push_map[stage][user_y+2][user_x] = 3;
					push_map[stage][user_y+1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;

				}
				else if(push_map[stage][user_y+2][user_x] == 4)//		공이 들어있는 집 왼쪽이 비어있는 집일때
				{
					temp = push_map[stage][user_y+2][user_x];
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = 6;
					push_map[stage][user_y][user_x] = 0;
					m.count++;
				}
			}

		}
		/**********************************************조건 2**************************************************/
		else if(push_map[stage][user_y][user_x] == 6)//		내가 지금 집에 있을때
		{
			if(push_map[stage][user_y+1][user_x] == 1)//벽
				return 0;
			else if(push_map[stage][user_y+1][user_x] == 0)//빈공간
			{

				push_map[stage][user_y+1][user_x] = 2;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 3)//공
			{
				if(BBLOCK_D || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)//		왼쪽이 공이고 그 왼쪽이 벽 || 왼쪽이 공이고 그 왼쪽이 공 || 왼쪽이 공이고 그 왼쪽이 공이 들어있는 집일때 
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 0)//		내 왼쪽이 집이고 그 왼쪽이 공일때
				{
					push_map[stage][user_y+2][user_x] = push_map[stage][user_y+1][user_x];
					push_map[stage][user_y+1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 4)//		내 왼쪽이 공이고 그 왼쪽이 비어있는 집일때
				{
					push_map[stage][user_y+2][user_x] = 5;
					push_map[stage][user_y+1][user_x] = 2;
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
			}
			else if(push_map[stage][user_y+1][user_x] == 4)//집
			{
				push_map[stage][user_y+1][user_x] = 6;
				push_map[stage][user_y][user_x] = 4;
				m.count++;
			}
			else if(push_map[stage][user_y+1][user_x] == 5)//왼쪽이 공이 들어있는 집일때
			{
				if(push_map[stage][user_y+2][user_x] == 0)//그 왼쪽이 빈공간 
				{
					push_map[stage][user_y+2][user_x] = 3;
					push_map[stage][user_y+1][user_x] = push_map[stage][user_y][user_x];
					push_map[stage][user_y][user_x] = 4;
					m.count++;
				}
				else if(push_map[stage][user_y+2][user_x] == 1 || push_map[stage][user_y+2][user_x] == 3 || push_map[stage][user_y+2][user_x] == 5)// 그 왼쪽이 벽, 공, 공이들어있는집
					return 0;
				else if(push_map[stage][user_y+2][user_x] == 4)// 그 왼쪽이 비어있는 집
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
	void goodKey()	//시뮬레이션이 실행되는 함수
	{
		mapClean();
		Beep(500,150);	//주파수를  500, 길이를 150이라 한다.
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
		gotoxy(8,20);cout << "이런식으로 게임 하시면 됩니다 " <<endl;
		gotoxy(8,22);cout << "메뉴로 돌아가시려면 ESC키를 입력하시오 " ;
		if(getkey())	//만일 ESC키를 입력했을 때
		{
			Game :: firstViews();	//초기 메뉴화면 출력
		}
	}
	void selectMap()	//원하는 맵을 선택했을 때 출력되는 함수
	{
		if ( Game :: first_map%3 == 0 )	//만일 두번째 맵을 선택하였을 때
		{
			mapClean();	//맵 클린
			stage=1;	//두번째 맵을 선택하였을 경우 값을 1로 설정
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();	//맵을 출력전에 초기화 해준다.
			v.mapView();	//맵 출력
		}
		else if ( Game :: first_map%3 == 1 )	//만일 세번째 맵을 선택하였을 때
		{
			mapClean();//맵 클린
			stage=2;	//세번째 맵을 선택하였을 경우 값을 2로 설정
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();//맵을 출력전에 초기화 해준다.
			v.mapView();//맵 출력
		}
		else if ( Game :: first_map%3 == 2 )	//만일 첫번째 맵을 선택하였을 때
		{
			mapClean();//맵 클린
			stage=0;	//첫번째 맵을 선택하였을 경우 값을 0로 설정
			Game :: start_menu=0;
			Game :: start_map=0;
			Game :: rePush();//맵을 출력전에 초기화 해준다.
			v.mapView();//맵 출력
		}
	}
	void stageFind()	//원하는 stage로 가는 함수.
	{
		mapClean();
		Game :: first_map=1001;	//맵선택 화면에서 키를 받을때 원하는 상태를 출력해주기 위해 초기화
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
				cout << icon[push_map[0][i][j]];	//	맵 출력
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

				cout << icon[push_map[1][i][j]];	//	맵 출력
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

				cout << icon[push_map[2][i][j]];	//	맵 출력
			}
			cout<<endl;
			gotoxy(60,5+i);
		}
		gotoxy(64,15);
		cout << " < STAGE 3 > ";
		textcolor(WHITE);
		gotoxy(8,20);
		cout<< "방향키 (       <-     ) 와 (        ->        ) 로 맵을 선택하십시오 ";
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
	void userRank()	//각 단계별로 최고기록을 출력하는 함수
	{
		Game :: first_menu=1001;	//메뉴 초기화
		system("mode con cols=75 lines=25"); //mode 창의 길이를 75, 높이를 25로 한다.
		//m.whiteColor();	//배경색을 하얀색으로 지정
		//textcolor(BLACK);	//글자색을 검정색으로 지정
		gotoxy(14,5); cout << "1단계 최고기록 : " <<m.user_rank1;	//1단계 최고기록 출력
		gotoxy(14,8); cout << "2단계 최고기록 : " <<m.user_rank2;	//2단계 최고기록 출력
		gotoxy(14,11); cout << "3단계 최고기록 : " <<m.user_rank3;	//3단계 최고기록 출력
		if(getkey())	//만일 키를 입력/
		{
			MenuRelease();	//맵 CLEAN
			Game :: firstViews();	//메뉴출력 함수 호출	
		}
	}	

	int selectMenu()	//원하는 메뉴에 ENTER 키를 눌렀을 때 출력해주는 함수
	{
		if( Game :: first_menu%5 == 1)	// 만일 게임시작 메뉴를 선택했을 경우
		{
			MenuRelease(); //맵 CLEAN
			v.mapView(); //맵 출력
			Game :: start_menu=0;	//게임을 시작하는 맵을 호출하기 위하여 menu변수의 값을 0으로 설정
			return 0;
		}

		else if( Game :: first_menu%5 == 2 )	//만일 맵 선택 메뉴를 선택했을 경우
		{
			MenuRelease();	//맵 CLEAN
			stageFind();	//맵을 검색하는 함수 호출
		}

		else if( Game :: first_menu%5 == 3 )	// 만일 도움말 메뉴를 선택했을 경우
		{
			MenuRelease();	//맵 CLEAN
			HelpRender();	//도움말 함수 호출
		}


		else if( Game :: first_menu%5 == 4 )	// 만일 랭킹 메뉴를 선택했을 경우
		{
			MenuRelease();	//맵 CLEAN
			userRank();	//랭킹 함수 호출
		}

		else if( Game :: first_menu%5 == 0 )	// 만일 종료 메뉴를 선택했을 경우
		{
			MenuRelease();	//맵 CLEAN					
			Game :: start_menu=100;	//종료메뉴에 ENTER키를 눌렀을 때 종료해주기위해 menu의 변수값을 100로 설정
			m.rankingWirte();	//랭킹을 ranking.txt에 입력하는 함수 호출
		}
	}
	
	void firstMenu()	//메뉴선택 함수
	{
		switch(getkey())
		{		 
		case UPKEY : Game:: upMenu(); break;
		case DOWNKEY : Game :: downMenu(); break;
		case ENTERKEY : selectMenu(); break;	
		}
	}
	
	void HelpRender()	//도움말을 출력해주는 함수
{
	Game :: first_menu = 1001;	//메뉴 초기화
	m.blackColor(); //배경 색을 검정으로 지정
	textcolor(WHITE);	//글자색을 흰색으로 지정
	
	//system("mode con cols=110 lines=18");	//mode 창의 길이를 110, 높이를 18로 지정
	Beep(260,150);gotoxy(4,3);cout << "푸쉬푸쉬는 사용자가 공을 밀어서 ";
	Beep(270,150); cout << "특정 위치에 이동시켜야 되는 게임입니다.";
	Beep(285,150);gotoxy(4,5);cout << "☆";
	Beep(300,150);gotoxy(6,5);cout << "는 플레이어입니다. 플레이어는 ";
	Beep(320,150);gotoxy(36,5);cout << "◎";
	Beep(335,150);gotoxy(38,5);cout << "공을 밀어서 ";
	Beep(350,150);gotoxy(52,5);cout << "○";
	Beep(365,150);gotoxy(54,5);cout << "에 집어넣으면 됩니다.";
	Beep(380,150);gotoxy(4,7);cout << "이동방법 : 방향키";
	Beep(405,150);gotoxy(4,8);cout << "이전 단계 : BackSpace ";
	Beep(420,150);gotoxy(4,9);cout << "다음 단계 : W ";
	Beep(435,150);gotoxy(4,10);cout << "STAGE 선택 : P";
	Beep(450,150);gotoxy(4,11);cout << "새로시작 : R";
	Beep(465,150);gotoxy(4,12);cout << "게임 종료 : Q";
	Beep(480,150);gotoxy(4,14);cout << "메뉴 화면으로 나가시려면 ESC를 클릭하세요.";
	Beep(480,150);gotoxy(4,16);cout << "시뮬레이션을 보시려면 ENTER키를 입력하시오. ";
	switch(getkey())
	{
	case ESCKEY : Game :: firstViews(); break;
	case ENTERKEY : goodKey(); break;
	}
}
	void MenuRelease()			//맵 CLEAN함수
	{

		int i;
		for(i=1; i<50; i++)
		{
			gotoxy(1,i);
			printf("                                                                                                                  ");
		}

	}

	int gameStart()	//게임을 시작하는 함수
	{
		m.rankingRead();	//랭킹을 출력하는 함수 호출
		_setcursortype(_NOCURSOR);	//커서 제거
		system("title ■ P U S H   P U S H ---->BY KIM MOON SU■");	//mode 텍스트바에 타이틀 출력
		Game :: firstViews();	//메뉴 출력 함수 호출
		
		while(1)	// 무한 반복
		{
			if( Game :: start_menu == 1)	{ firstMenu(); }	//menu변수의 값이 0이 아니고 100이 아닐때 원하는 메뉴에 ENTER 키를 눌렀을 때 출력해주는 함수 호출
			else if( Game :: start_menu == 100)	{return 0;}	//종료메뉴에 ENTER키를 눌렀을 때 종료해주기위해 menu의 변수값이 100인지를 확인하고 종료한다.
			else if( m.stage == 3  )	//만일 stage가 끝났거나 메뉴에서 종료메뉴를 선택했을 때
			{
				Game :: first_menu=1001;	//menu변수값을 1로 지정하여 다시 메뉴가 출력되었을 때 초기값으로 되돌리게 해준다
				MenuRelease();	//맵 CLEAN
				Game :: firstViews();	//메뉴함수 호출 
			}
		
			else if( Game :: start_menu == 0)	//그밖에 menu변수의 값이 0임을 확인하여 게임을 시작한다.
			{
				
				v.sideMap();	//side 메뉴함수 호출
				m.userSearch();	//사용자의 위치를 알려주는 함수 호출
				switch(getkey())	//만일 키를 입력받았을 때
				{
				case LEFTKEY: keyLEFT(); break;	//왼쪽키를 입력받으면 keyLEFT함수 호출
				case RIGHTKEY: keyRIGHT(); break;	//오른쪽키를 입력받으면 keyRIGHT함수 호출
				case DOWNKEY: keyDOWN(); break;	//위쪽키를 입력받으면 keyUP함수 호출
				case UPKEY:	keyUP(); break;	//아래쪽키를 입력받으면 keyDOWN함수 호출
				case PREVKEY: return 0;	//Q키를 입력받으면 프로그램 종료
				case NEXTKEY : v.nextMap(); break;	//W키를 입력받으면 다음 맵을 출력
				case BACKKEY : v.prevMap(); break;	//backspace키를 입력받으면 이전 맵을 출력
				case SEARCHKEY : stageFind(); break;	//P키를 입력받으면 원하는 맵을 선택하는 함수 호출
				case RESETKEY : Game :: rePush(); break;	//R키를 입력받으면 맵 초기화
				case ESCKEY : 	Game :: firstViews(); break;	//ESC키를 입력받으면 메뉴화면 함수 호출	
				}
				if( Game :: start_menu == 0)	// 게임 중 메뉴로 돌아갔을 때 맵을 출력 안되게 하려고 start_menu변수를 0으로 지정했다. 그래서 start_menu변수가 0일 때 맵을 출력
					v.mapView();	//계속 맵을 출력해준다.

			}
		}
	}

	
};

#endif