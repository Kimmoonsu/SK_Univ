#include <iostream>
#include <conio.h>
#include "Consola.h"
#include "View.h"
#include "Model.h"

using namespace std;
void View :: mapView()	//맵을 출력해주는 함수
{
	
	textcolor(WHITE);
	Model :: pushClear();	//맵 CLEAR 	
	if(Model :: stage != 3)	//만일 맵 CLEAR하지 못했을 때
	{
		gotoxy(8,10);
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				cout << icon[map[stage][i][j]];	//	맵 출력
			}
			cout<<endl;
			gotoxy(8,10+i);
		}
		Model :: fullroomSearch();	//공이 들어있는 방의 숫자를 출력해주는 함수 호출
		
	}
}

void View :: originalMap()	//stage마다 초기 맵을 저장하는 함수
{

	for(int i=0; i < max1; i++)
	{
		for(int j=0; j < max2; j++)
		{

			repush_map[stage][i][j]=push_map[stage][i][j];
		}
		
	}
	
}
void View :: sideMap()	//맵 출력했을 때 오른쪽에 설명 출력
{
			gotoxy(55, 1);  cout <<"┏━━━━━━━━┓";
			gotoxy(55, 2);  cout <<"┃P U S H  P U S H┃";
			gotoxy(55, 3);  cout <<"┗━━━━━━━━┛";
			gotoxy(55, 4);	cout <<  "       UP";
			gotoxy(55, 5);	cout <<  "       ↑";
			gotoxy(55, 6);	cout <<  "LEFT ←  → RIGHT";
			gotoxy(55, 7);	cout <<  "       ↓";
			gotoxy(55, 8); cout <<  "      DOWN";
			gotoxy(55, 10); cout <<"  ESC     :     메뉴";
			gotoxy(55, 11); cout <<"   P      :  맵 선택";
			gotoxy(55, 12); cout <<"   R      :     리셋";
			gotoxy(55, 13); cout <<"   W      : 다음단계";
			gotoxy(55, 14); cout <<"   <-     : 이전단계";
			gotoxy(55, 15); cout <<"   Q      : 게임종료";
			gotoxy(55, 17); cout <<" STAGE " << stage+1 <<" / 3";
			gotoxy(55, 18); cout <<" 움직인 횟수 : " << count;	//움직인 횟수를 출력
			gotoxy(55, 19); cout <<" 성공한 방 : " << fullroom <<endl;
			gotoxy(55, 21); cout << "1단계 최고 기록 : " << user_rank1;
			gotoxy(55, 22); cout << "2단계 최고 기록 : " << user_rank2;
			gotoxy(55, 23); cout << "3단계 최고 기록 : " << user_rank3;
}
void View :: prevMap()	//이전 단계 맵을 출력하기 위한 함수
{

	
	if(stage>0)	//stage1 에서 이전맵을 눌렀을 때 아무 변화가 없게 하기 위하여
	{
		stage--;
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				push_map[stage][i][j]= repush_map[stage][i][j];			//stage의 맞게 초기 맵을 복사해놓았다가 붙여넣는 과정.
			}
		}
		mapView();	//맵 출력
	}
}

void View :: nextMap()	//다음 단계 맵을 출력하기 위한 함수
{
	if(stage<2)		//stage3 에서 다음 맵을 눌렀을 때 아무 변화가 없게 하기 위하여
	{
		stage++;	// 다음 단계 맵을 출력하기 위해 stage증가
		for(int i=0; i < max1; i++)
		{
			for(int j=0; j < max2; j++)
			{
				push_map[stage][i][j]= repush_map[stage][i][j];			//stage의 맞게 초기 맵을 복사해놓았다가 붙여넣는 과정.
			}
		}
		mapView();	//맵 출력
	}
}
