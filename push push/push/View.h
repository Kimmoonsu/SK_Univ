#include <iostream>
using namespace std;
#include "Model.h"
#define max1 15
#define max2 15

class View : public Model
{
private:
	typedef int( *ThreeArray )[15][15];		//ThreeArray��� �ڷ����� �������ش�.
	ThreeArray map;	//ThreeArray��� �ڷ����� �������� map(��)�� �Ѵ�.
	
	Model m;	//ModelŬ������ ������ m(��)�� ����
	
	

	
public:
	View(void){
		map = m.getMap();	//Model Ŭ������ �ִ� �迭�� return ���ִ� �Լ��� map������ �����ϴ� �����ڸ� �����.
	};
	~View(void){};
	static void sideMap();	//���ӽ������� �� ȭ�� �����ʿ� ������ ������ִ� �Լ�
	void mapView();	//���� ������ִ� �Լ�
	void originalMap();	//�ʱ� ���� ������ִ� �Լ�
	void prevMap();	//�������� ����ϴ� �Լ�
	void nextMap();	//�������� ����ϴ� �Լ�
	
};