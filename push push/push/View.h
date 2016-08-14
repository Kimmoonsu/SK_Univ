#include <iostream>
using namespace std;
#include "Model.h"
#define max1 15
#define max2 15

class View : public Model
{
private:
	typedef int( *ThreeArray )[15][15];		//ThreeArray라는 자료형을 선언해준다.
	ThreeArray map;	//ThreeArray라는 자료형의 변수명을 map(이)라 한다.
	
	Model m;	//Model클래스의 변수를 m(이)라 지정
	
	

	
public:
	View(void){
		map = m.getMap();	//Model 클래스의 있는 배열을 return 해주는 함수를 map변수에 저장하는 생성자를 만든다.
	};
	~View(void){};
	static void sideMap();	//게임시작했을 때 화면 오른쪽에 설명을 출력해주는 함수
	void mapView();	//맵을 출력해주는 함수
	void originalMap();	//초기 맵을 출력해주는 함수
	void prevMap();	//이전맵을 출력하는 함수
	void nextMap();	//다음맵을 출력하는 함수
	
};