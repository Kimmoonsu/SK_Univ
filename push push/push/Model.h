#ifndef MODEL_H
#define MODEL_H

#include <iostream>

using namespace std;

#define max1 15
#define max2 15
#define max3 100
class Model
{

public:
	typedef int( *ThreeArray )[15][15];//ThreeArray라는 자료형을 선언해준다.
	ThreeArray getMap() { return push_map; }	//ThreeArray라는 자료형의 함수이름을 getMap()이라 하자
	static int push_map[3][max1][max2];	//맵의 정보를 입력받는 변수
	static int repush_map[3][max1][max2];	//각 stage의 단계마다 초기 맵을 저장하는 변수
	static int user_x, user_y;	//사용자 위치의 x,y좌표
	static int fullroom;	//공이 들어가있는 방
	static int temp;	//배열값의 위치를 변경할때 임시 저장해두는 변수
	static void mapClean();	//맵 CLEAN 함수
	static int gettkey();	//키를 입력받는 변수
	static void blackColor();	//배경색을 검정으로 지정해주는 함수
	static void whiteColor();	//배경색을 흰색으로 지정해주는 함수
	static int count;	//움직인 횟수를 저장하는 변수
	static int stage;	//맵 단계를 저장하는 변수
	static void FullBox(int x1, int y1, int x2, int y2, int color);	//박스의 색과 사이즈와 위치를 저장하는 함수.
	static int clear_condition[3];	//공이 들어가야할 방의 개수를 저장하고 있는 변수
	static char icon[max1][max2];	//배열의 숫자에 따라 맵아이콘을 출력해주는 변수
	static void userSearch();	//사용자의 위치를 탐색해주는 함수
	static int fullroomSearch();	//공이 들어가있는 방의 개수를 알려주는 함수.
	static void pushClear();	//단계마다 성공시 다음 단계의 맵 배열로 넘어가주는 함수.
	static void rankingWirte();	//랭킹을 입력하는 함수
	static void rankingRead();	//랭킹을 출력하는 함수
	static int user_count1[max3];	//1단계 기록보유
	static int user_count2[max3];	//2단계 기록보유
	static int user_count3[max3];	//3단계 기록보유
	static int user_rank1;	//1단계 최고기록
	static int user_rank2;	//2단계 최고기록
	static int user_rank3;	//3단계 최고기록
	static int rank_i;	//user_cout1 배열 변수의 인덱스 값
	static int rank_j;	//user_cout2 배열 변수의 인덱스 값
	static int rank_k;	//user_cout3 배열 변수의 인덱스 값
	Model(void){};
	~Model(void){};
};

#endif