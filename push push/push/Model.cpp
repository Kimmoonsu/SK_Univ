
#include <iostream>
#include <fstream> //c++ 용 파일입출력 헤더
#include <conio.h>
#include "Model.h"
#include "View.h"
#include "Consola.h"

using namespace std;

int Model :: push_map[3][max1][max2] =		//맵을 나타내주는 3차원 배열
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


int Model :: clear_condition[3]={2,4,4};	//공이 들어가야할 방의 개수를 저장하고 있는 변수
int Model :: stage=0;	//맵 단계 초기화
int Model :: count=0;	//움직인 횟수 초기화
char Model :: icon[max1][max2] = {"  ", "■","☆","◎","○","●","★","▒"};	//배열의 숫자에 따른 맵아이콘
int Model :: user_x;	//사용자 y좌표
int Model :: user_y;	//사용자 x좌표
int Model :: fullroom;	//공이들어있는 방의 갯수를 입력받는 변수
int Model :: temp=0;	// 값이 없는 변수
int Model :: user_count1[max3] = {0,};	//움직인 횟수를 저장하는 변수 초기화
int Model :: user_count2[max3] = {0,};	//움직인 횟수를 저장하는 변수 초기화	
int Model :: user_count3[max3] = {0,};	//움직인 횟수를 저장하는 변수 초기화
int Model :: user_rank1;	//1단계 최고기록을 저장하는 변수의 초기값저장
int Model :: user_rank2=0;	//2단계 최고기록을 저장하는 변수의 초기값저장
int Model :: user_rank3=0;	//3단계 최고기록을 저장하는 변수의 초기값저장
int Model :: rank_i=0;	//user_cout1 배열 변수의 인덱스 값을 0으로 초기화
int Model :: rank_j=0;	//user_cout2 배열 변수의 인덱스 값을 0으로 초기화
int Model :: rank_k=0;	//user_cout3 배열 변수의 인덱스 값을 0으로 초기화
void Model :: userSearch()	//사용자 위치 탐색 함수
{
	for(int i=0; i<max1; i++)
	{
		for(int j=0; j<max2; j++)
		{
			if(push_map[stage][i][j] == 2)	//사용자의 위치를 탐색
			{
				user_x = j;	//y좌표
				user_y = i;	//x좌표
			}
		}
	}
};
int Model :: repush_map[3][max1][max2] =	//stage마다 맵의 초기값을 저장하는 변수
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
int Model :: fullroomSearch()		//공이 들어가있는 방의 개수를 알려주는 함수.
{
	fullroom=0;
	for(int i=0; i<max1; i++)
	{
		for(int j=0; j<max2; j++)
		{
			if(push_map[stage][i][j] == 5)	//공이 방안에 들어갔을 때
				fullroom++;	//fullroom의 숫자를 증가

		}
	}
	return fullroom;
};

void Model :: pushClear()		//단계마다 성공시 다음 단계의 맵 배열로 넘어가주는 함수.
{
	fullroomSearch();	//공이 들어있는 방의 갯수를 탐색하는 함수 호출

	if(stage == 0)	//1단계 일때
	{
		if( fullroom == clear_condition[0] )	//조건이 맞으면
		{
			user_count1[rank_i] = count;	//움직인 횟수를 user_count1변수의 i번째에 값을 저장
			if(user_rank1!=0 && user_rank1 > user_count1[0])	//원래 저장되어있는 최고기록이 0이 아니고 현재 사용자 기록이 최고기록보다 좋을 때
				user_rank1 = user_count1[0];	// 현재 사용자 기록을 최고기록에 저장
			else if( user_rank1 == 0 )	//원래 저장 되어 있는 랭킹이 없을 때
				user_rank1 = user_count1[0];	//현재 사용자 기록을 최고기록에 저장
			
			rank_i++;	//i값을 증가
			for(int i=0; i<max3; i++)
			{
				
				if(user_count1[i]!=0)
				{
					
					if(user_rank1 <= user_count1[i])	//만일 첫번째 값이 다른 값보다 클 경유
					{	
						user_rank1=user_rank1;	//첫번째 값을 계속 반환			
					}
					else if(user_rank1 > user_count1[i])	//만일 첫번째 값보다 다른 값이 클 경우
						user_rank1=user_count1[i];	// 큰 값을 user_rank1에 저장
				}
			}
			mapClean(); //맵 CLEAN
			system("mode con cols=40 lines=12");	//mode창의 높이를 12, 길이를 40로 지정
			gotoxy(5,5); cout << "이번 stage 움직인 횟수 :  " << count << endl;
			gotoxy(5,7); cout << "아무키나 입력하면 다음 단계 START " << endl;
			if(gettkey())	//키를 입력 받으면
			{
				stage++;//stage 증가
				count = 0;//stage CLAER했을때 count값을 초기화 해준다.
				system("mode con cols=101 lines=25");	//mode창의 길이를101, 높이를 25로 지정
				for(int i=0; i < max1; i++)
				{
					for(int j=0; j < max2; j++)
					{
						push_map[stage][i][j]= repush_map[stage][i][j];	//다음단계의 맵을 초기화 시켜준다.
					}
				}
			}
		}
		
	}
	/*******************위와 동일*****************/
	else if(stage == 1)
	{	
		if( fullroom == clear_condition[1] )
		{
			user_count2[rank_j] = count;
			user_count2[rank_j] = count;	//움직인 횟수를 user_count1변수의 i번째에 값을 저장
			if(user_rank2!=0 && user_rank2 > user_count2[0])	//원래 저장되어있는 최고기록이 0이 아니고 현재 사용자 기록이 최고기록보다 좋을 때
				user_rank2 = user_count2[0];	// 현재 사용자 기록을 최고기록에 저장
			else if( user_rank2 == 0 )	//원래 저장 되어 있는 랭킹이 없을 때
				user_rank2 = user_count2[0];	//현재 사용자 기록을 최고기록에 저장
			
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
			gotoxy(5,5); cout << "이번 stage 움직인 횟수 :  " << count << endl;
			gotoxy(5,7); cout << "아무키나 입력하면 다음 단계 START " << endl;
			if(gettkey())
			{
				stage++;
				count = 0;
				system("mode con cols=101 lines=25");	//mode창의 길이를101, 높이를 25로 지정
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
			user_count3[rank_k] = count;	//움직인 횟수를 user_count1변수의 i번째에 값을 저장
			if(user_rank3!=0 && user_rank3 > user_count3[0])	//원래 저장되어있는 최고기록이 0이 아니고 현재 사용자 기록이 최고기록보다 좋을 때
				user_rank3 = user_count3[0];	// 현재 사용자 기록을 최고기록에 저장
			else if( user_rank3 == 0 )	//원래 저장 되어 있는 랭킹이 없을 때
				user_rank3 = user_count3[0];	//현재 사용자 기록을 최고기록에 저장
			
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
			cout << "이번 stage 움직인 횟수 :  " << count << endl;
			gotoxy(4,4);
			cout << "Stage All Clear!";
			gotoxy(4,5);
			cout << "수고하셨습니다 ^^" <<endl;
			cout << "랭킹을 확인하시기 바랍니다. " ;

			gotoxy(4,8);
			cout << "제작 : Kimmoonsu (ENIAC)";

			if(gettkey()) 
			{

				stage++;
			}
		}
		
	}
	
};

void Model :: FullBox(int x1, int y1, int x2, int y2, int color)	//박스창을 출력하는 함수
{
	textbackground(color);
	for(int i=y1; i <= y2; ++i)
		for(int j=x1; j <= x2; ++j)
		{
			
			gotoxy(j, i);
			cout << " ";
		}
};

void Model :: mapClean()			//빈공간 출력
{
	int i;
	for(i=1; i<50; i++)
	{
		
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
	
};
void Model :: blackColor()	//배경색을 검정으로 지정하는 함수
{
	int i;
	for(i=1; i<50; i++)
	{
		textbackground(BLACK);
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
}
void Model :: whiteColor()	//배경색을 흰색으로 지정하는 함수
{
	int i;
	for(i=1; i<50; i++)
	{
		textbackground(WHITE);
		gotoxy(1,i);
		cout << "                                                                                                                  ";
	}
}

int Model :: gettkey()	//키를 입력받는 함수
{
	int retval = (_getch() & 0x00ff);
	return ( retval == 0xe0 ) ? ( ( retval << 8 ) | _getch()) : retval;
}


void Model :: rankingWirte()	//랭킹을 입력하는 함수
{ 
	ofstream out("ranking.txt",ios::out);	//txt파일이 없을경우 직접 생성 해준다.
	out << user_rank1 << endl;		// 1단계 최고기록을 저장
	out << user_rank2 << endl;		// 2단계 최고기록을 저장
	out << user_rank3 << endl;		// 3단계 최고기록을 저장
	out.close();   //파일을 직접만듬.다만들고 닫음 
}

void Model :: rankingRead()	//랭킹을 출력하는 함수
{
	char line[3];	//기록을 저장할 배열을 선언
	ifstream input("ranking.txt"); //입력스트림 열고   
	for(int i = 0; i < 3; i++)	// 총 stage가 3이므로 3번 반복해준다.
	{
		char cstr[10];	//문자열을 받을 배열 변수를 선언
		input.getline(cstr,10);	//문자열을 받고 한줄씩 잘라준다.
		line[i] = atoi(cstr);	//문자열 cstr변수의 값을 line배열에 저장 (atoi는 char를 int형으로 변환해준다.) 
	}
	user_rank1 = line[0];	//1단계 기록 line[0]을 1단계 최고기록에 저장
	user_rank2 = line[1];	//2단계 기록 line[1]을 2단계 최고기록에 저장
	user_rank3 = line[2];	//3단계 기록 line[2]을 3단계 최고기록에 저장
} 