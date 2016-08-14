#ifndef GAME_H
#define GAME_H

#include <iostream>
using namespace std;

class Game : public Model
{

public :
	Game(void){};
	~Game(void){};
	static int first_menu;	//menu변수의 값에 따라 사용자가 보는 메뉴 화면이 바뀐다.
	static int start_menu;  //start_menu가 1일때 메뉴화면이 계속 출력되고 0으로 바뀌는 순간 게임이 시작된다.
	static int first_map;	//map변수의 값에 따라 사용자가 보는 맵 메뉴 화면이 바뀐다.
	static int start_map;  //start_map이 1일때 메뉴화면이 계속 출력되고 0으로 바뀌는 순간 게임이 시작된다.
	static void upMenu(); // 메뉴에서 방향키(위)를 입력받았을 때 출력되는 함수
	static void downMenu(); // 메뉴에서 방향키(아래)를 입력받았을 때 출력되는 함수
	static void firstViews();	//초기 메뉴 화면 출력하는 함수
	static void leftMap(); // 메뉴에서 방향키(왼쪽)를 입력받았을 때 출력되는 함수
	static void rightMap(); // 메뉴에서 방향키(오른쪽)를 입력받았을 때 출력되는 함수
	static void rePush();	//stage 초기화 해주는 함수.
	
	
};

#endif