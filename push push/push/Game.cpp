#include <iostream>
#include "Model.h"
#include "View.h"
#include "Game.h"
#include "Controller.h"
#include "Consola.h"
#include <conio.h>

using namespace std;

int Game :: first_menu=1001;	//first_menu변수의 값에 따라 사용자가 보는 메뉴 화면이 바뀐다.
int Game :: start_menu=1;		//start_menu가 1일때 메뉴화면이 계속 출력되고 0으로 바뀌는 순간 게임이 시작된다.
int Game :: first_map=1001;		//first_map변수의 값에 따라 사용자가 보는 맵 메뉴 화면이 바뀐다.
int Game :: start_map=1;		//start_map가 1일때 메뉴화면이 계속 출력되고 0으로 바뀌는 순간 게임이 시작된다.

void Game :: rightMap()	//맵선택에서 오른쪽키를 눌렀을 때 바뀐 상태를 출력하는 함수
{
	first_map++;
	
	if( first_map %3 == 1)	//만일 세번째 맵을 선택하였을 때
	{
		textcolor(WHITE);
		gotoxy(3,5);
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
		gotoxy(30,5);
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
		textcolor(YELLOW);
		gotoxy(60,5);
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
	}
	else if( first_map % 3 == 0)	//만일 두번째 맵을 선택하였을 때
	{
		textcolor(WHITE);
		gotoxy(3,5);
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
		textcolor(YELLOW);
		gotoxy(30,5);
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
		textcolor(WHITE);
		gotoxy(60,5);
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
	}
	else if( first_map % 3 == 2 )	//만일 첫번째 맵을 선택하였을 때
	{
		textcolor(YELLOW);
		gotoxy(3,5);
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
	}
}
void Game :: leftMap()	//맵선택에서 왼쪽키를 눌렀을 때 바뀐 상태를 출력하는 함수
{
	
	first_map--;
	
	if( first_map %3 == 1)	//만일 세번째 맵을 선택하였을 때
	{
		textcolor(WHITE);
		gotoxy(3,5);
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
		gotoxy(30,5);
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
		textcolor(YELLOW);
		gotoxy(60,5);
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
	}
	else if( first_map % 3 == 0)	//만일 두번째 맵을 선택하였을 때
	{
		textcolor(WHITE);
		gotoxy(3,5);
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
		textcolor(YELLOW);
		gotoxy(30,5);
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
		textcolor(WHITE);
		gotoxy(60,5);
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
	}
	else if( first_map % 3 == 2 )	//만일 첫번째 맵을 선택하였을 때
	{
		textcolor(YELLOW);
		gotoxy(3,5);
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
	}
}
void Game :: upMenu()	// 메뉴에서 방향키(위)를 입력받았을 때 출력되는 함수
{
	first_menu--;
	if( first_menu%5 == 2 )	// menu변수 값이 5로 나누었을 때 나머지가 1이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";
		textcolor(YELLOW);  
		gotoxy(36,19);cout << "2 : ▶  맵  선택  ◀        ";
		textcolor(WHITE);
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";

	}
	else if( first_menu%5 == 3 )	// menu변수 값이 5로 나누었을 때 나머지가 2이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		textcolor(YELLOW);
		gotoxy(36,21);cout << "3 : ▶  도 움 말  ◀        ";
		textcolor(WHITE);
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";

	}
	else if( first_menu%5 == 4 )	// menu변수 값이 5로 나누었을 때 나머지가 3이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		textcolor(YELLOW);
		gotoxy(36,23);cout << "4 : ▶  랭    킹  ◀        ";
		textcolor(WHITE);
		gotoxy(36,25);cout << "5 :     종    료            ";

	}
	else if( first_menu%5 == 0 )	// menu변수 값이 5로 나누었을 때 나머지가 4이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		textcolor(YELLOW);
		gotoxy(36,25);cout << "5 : ▶  종    료  ◀        ";

	}
	else if( first_menu%5 == 1 )	// menu변수 값이 5로 나누었을 때 나머지가 0이면
	{
		textcolor(YELLOW);
		gotoxy(36,17);cout << "1 : ▶  게임시작  ◀        ";
		textcolor(WHITE);
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";

	}
}

void Game :: downMenu()	// 메뉴에서 방향키(아래)를 입력받았을 때 출력되는 함수
{
	Game :: first_menu++;
	if(	Game :: first_menu%5 == 2 )	// menu변수 값이 5로 나누었을 때 나머지가 1이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";
		textcolor(YELLOW);  
		gotoxy(36,19);cout << "2 : ▶  맵  선택  ◀        ";
		textcolor(WHITE);
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";
	}
	else if( Game :: first_menu%5 == 3 )	// menu변수 값이 5로 나누었을 때 나머지가 2이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		textcolor(YELLOW);
		gotoxy(36,21);cout << "3 : ▶  도 움 말  ◀        ";
		textcolor(WHITE);
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";
	}
	else if( Game :: first_menu%5 == 4 )	// menu변수 값이 5로 나누었을 때 나머지가 3이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		textcolor(YELLOW);
		gotoxy(36,23);cout << "4 : ▶  랭    킹  ◀        ";
		textcolor(WHITE);
		gotoxy(36,25);cout << "5 :     종    료            ";
	}
	else if( Game :: first_menu%5 == 0 )	// menu변수 값이 5로 나누었을 때 나머지가 4이면
	{
		textcolor(WHITE);
		gotoxy(36,17);cout << "1 :     게임시작            ";  
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		textcolor(YELLOW);
		gotoxy(36,25);cout << "5 : ▶  종    료  ◀        ";
	}
	else if( Game :: first_menu%5 == 1 )	// menu변수 값이 5로 나누었을 때 나머지가 0이면
	{
		textcolor(YELLOW);
		gotoxy(36,17);cout << "1 : ▶  게임시작  ◀        ";
		textcolor(WHITE);
		gotoxy(36,19);cout << "2 :     맵  선택            ";
		gotoxy(36,21);cout << "3 :     도 움 말            ";
		gotoxy(36,23);cout << "4 :     랭    킹            ";
		gotoxy(36,25);cout << "5 :     종    료            ";
	}
}

void Game :: firstViews()	//초기 메뉴 화면 출력하는 함수 
{
	textcolor(WHITE);
	mapClean();
	first_menu=1001;	//menu변수의 값에 따라 사용자가 보는 메뉴 화면이 바뀐다.
	start_menu=1;		//start_menu가 1일때 메뉴화면이 계속 출력되고 0으로 바뀌는 순간 게임이 시작된다.
	start_map=0;		//start_map을 초기화 시켜줘서 다시 메뉴화면이 정상적으로 작동하게 한다.
	system("mode con cols=101 lines=30"); //mode 창의 길이 101, 높이를 30로 한다.
	stage=0;
	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{
			push_map[stage][i][j]= repush_map[stage][i][j];	//혹시나 게임중 메뉴로 돌아왔다 다시 실행했을 시 맵 초기화.
		}
	}
	count=0;	//메뉴로 돌아왔을시 움직인 횟수를 초기화
	
	gotoxy(14,5);cout<<" ■■■  ■  ■    ■■■  ■  ■  ■■■  ■  ■    ■■■  ■  ■\n";
	gotoxy(14,6);cout<<" ■  ■  ■  ■    ■      ■  ■  ■  ■  ■  ■    ■      ■  ■\n"; 
	gotoxy(14,7);cout<<" ■■■  ■  ■    ■      ■  ■  ■■■  ■  ■    ■      ■  ■\n"; 
	gotoxy(14,8);cout<<" ■      ■  ■    ■■■  ■■■  ■      ■  ■    ■■■  ■■■\n"; 
	gotoxy(14,9);cout<<" ■      ■  ■        ■  ■  ■  ■      ■  ■        ■  ■  ■\n";
	gotoxy(14,10);cout<<" ■      ■  ■        ■  ■  ■  ■      ■  ■        ■  ■  ■\n"; 
	gotoxy(14,11);cout<<" ■      ■■■    ■■■  ■  ■  ■      ■■■    ■■■  ■  ■\n"; 
	textcolor(YELLOW);
	gotoxy(36,17);cout << "1 : ▶  게임시작  ◀        ";
	textcolor(WHITE);
	gotoxy(36,19);cout << "2 :     맵  선택            ";
	gotoxy(36,21);cout << "3 :     도 움 말            ";
	gotoxy(36,23);cout << "4 :     랭    킹            ";
	gotoxy(36,25);cout << "5 :     종    료            ";
}

void Game :: rePush()				//stage 초기화 해주는 함수.
{
	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{
			push_map[stage][i][j]= repush_map[stage][i][j];	//초기맵을 repush_map변수에 저장해놓았다가 다시 원래 맵에 덮어 쓰는 과정
		}

	}

}

