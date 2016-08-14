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
	typedef int( *ThreeArray )[15][15];//ThreeArray��� �ڷ����� �������ش�.
	ThreeArray getMap() { return push_map; }	//ThreeArray��� �ڷ����� �Լ��̸��� getMap()�̶� ����
	static int push_map[3][max1][max2];	//���� ������ �Է¹޴� ����
	static int repush_map[3][max1][max2];	//�� stage�� �ܰ踶�� �ʱ� ���� �����ϴ� ����
	static int user_x, user_y;	//����� ��ġ�� x,y��ǥ
	static int fullroom;	//���� ���ִ� ��
	static int temp;	//�迭���� ��ġ�� �����Ҷ� �ӽ� �����صδ� ����
	static void mapClean();	//�� CLEAN �Լ�
	static int gettkey();	//Ű�� �Է¹޴� ����
	static void blackColor();	//������ �������� �������ִ� �Լ�
	static void whiteColor();	//������ ������� �������ִ� �Լ�
	static int count;	//������ Ƚ���� �����ϴ� ����
	static int stage;	//�� �ܰ踦 �����ϴ� ����
	static void FullBox(int x1, int y1, int x2, int y2, int color);	//�ڽ��� ���� ������� ��ġ�� �����ϴ� �Լ�.
	static int clear_condition[3];	//���� ������ ���� ������ �����ϰ� �ִ� ����
	static char icon[max1][max2];	//�迭�� ���ڿ� ���� �ʾ������� ������ִ� ����
	static void userSearch();	//������� ��ġ�� Ž�����ִ� �Լ�
	static int fullroomSearch();	//���� ���ִ� ���� ������ �˷��ִ� �Լ�.
	static void pushClear();	//�ܰ踶�� ������ ���� �ܰ��� �� �迭�� �Ѿ�ִ� �Լ�.
	static void rankingWirte();	//��ŷ�� �Է��ϴ� �Լ�
	static void rankingRead();	//��ŷ�� ����ϴ� �Լ�
	static int user_count1[max3];	//1�ܰ� ��Ϻ���
	static int user_count2[max3];	//2�ܰ� ��Ϻ���
	static int user_count3[max3];	//3�ܰ� ��Ϻ���
	static int user_rank1;	//1�ܰ� �ְ���
	static int user_rank2;	//2�ܰ� �ְ���
	static int user_rank3;	//3�ܰ� �ְ���
	static int rank_i;	//user_cout1 �迭 ������ �ε��� ��
	static int rank_j;	//user_cout2 �迭 ������ �ε��� ��
	static int rank_k;	//user_cout3 �迭 ������ �ε��� ��
	Model(void){};
	~Model(void){};
};

#endif