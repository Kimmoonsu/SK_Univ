#ifndef GAME_H
#define GAME_H

#include <iostream>
using namespace std;

class Game : public Model
{

public :
	Game(void){};
	~Game(void){};
	static int first_menu;	//menu������ ���� ���� ����ڰ� ���� �޴� ȭ���� �ٲ��.
	static int start_menu;  //start_menu�� 1�϶� �޴�ȭ���� ��� ��µǰ� 0���� �ٲ�� ���� ������ ���۵ȴ�.
	static int first_map;	//map������ ���� ���� ����ڰ� ���� �� �޴� ȭ���� �ٲ��.
	static int start_map;  //start_map�� 1�϶� �޴�ȭ���� ��� ��µǰ� 0���� �ٲ�� ���� ������ ���۵ȴ�.
	static void upMenu(); // �޴����� ����Ű(��)�� �Է¹޾��� �� ��µǴ� �Լ�
	static void downMenu(); // �޴����� ����Ű(�Ʒ�)�� �Է¹޾��� �� ��µǴ� �Լ�
	static void firstViews();	//�ʱ� �޴� ȭ�� ����ϴ� �Լ�
	static void leftMap(); // �޴����� ����Ű(����)�� �Է¹޾��� �� ��µǴ� �Լ�
	static void rightMap(); // �޴����� ����Ű(������)�� �Է¹޾��� �� ��µǴ� �Լ�
	static void rePush();	//stage �ʱ�ȭ ���ִ� �Լ�.
	
	
};

#endif