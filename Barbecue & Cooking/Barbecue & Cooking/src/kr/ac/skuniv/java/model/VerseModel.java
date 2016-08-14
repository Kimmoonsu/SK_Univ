package kr.ac.skuniv.java.model;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import kr.ac.skuniv.java.model.Model.MENU;

public class VerseModel extends JPanel {
	/******Map�� �̹��� ũ�⸦ ������ �ִ� data*********/
	public final static int WIDTH = 100; // ���� �������� ����
	public final static int HEIGHT = 100; // ���� �������� ����
	/****************************************/
	
	
	/***********************��ġ �ȱ� Game Mode Data*********************/
	
	public int finish_count = 0; // ������ ������ �� thread�۵� Ƚ���� ���� ����
	
	/*****��ġ �ȱ� ���� ������ �˸��� State*******/
	public boolean p1_takeGameStart = false;
	public boolean p2_takeGameStart = false;
	/************************************/
	
	/*********ȹ���� �̹���**************/
	private int takeImg[] = new int[10];
	public void setTakeImg(int takeImg[]) { this.takeImg = takeImg; }
	public int[] getTakeImg() { return takeImg; }
	private int take_p1Img[] = new int[8];
	public void setTake_P1Img(int take_p1Img[]) { this.take_p1Img = take_p1Img; }
	public int[] getTake_P1Img() { return take_p1Img; }
	
	private int v_takeImg[] = new int[10];
	public void setV_TakeImg(int v_takeImg[]) { this.v_takeImg = v_takeImg; }
	public int[] getV_TakeImg() { return v_takeImg; }
	/*****************************/
	
	/*********��ġ�ȱ� stage data*********/
	public int p1_takeStage = 0;
	public int p2_takeStage = 0;
	/*********************************/
	
	/******��ġ�� �̹����� ���� �� ����ϴ� data*****/
	private int selectImg[] = new int[10];
	public void setSelectImg(int selectImg[]) { this.takeImg = takeImg; }
	public int[] getSelectImg() { return selectImg; }
	private int v_selectImg[] = new int[10];
	public void setV_SelectImg(int v_selectImg[]) { this.v_takeImg = v_takeImg; }
	public int[] getV_SelectImg() { return v_selectImg; }
	/*************************************/
	
	/*****************************************************************************/
	
	
	/************************Original VerseGame Mode Data*********************************/
	
	/*****��ź�� ���õǾ����� Check ���ִ� State********/
	public int count = 0; // ���� �ð����� ��ź�� �����ϰ� �ϴ� data
	private boolean bombCheck = false;
	public void setBombCheck(boolean bombCheck) { this.bombCheck = bombCheck; }
	public boolean getBombCheck() { return bombCheck; }
	/***************************************/
	
	
	/*********1p 2p �̸�Ƽ��*************/
	public int p1_count = 0;
	public int p2_count = 0;
	/*****************************************/
	 
	
	/*****************���� �׸��� ã���ִ� �޼ҵ�********************/
	public int findCol[]  = new int[8];
	public int v_findCol[] = new int[8];
	public int findRow[]  = new int[8];
	public int v_findRow[]  = new int[8];
	// [0,.....] -> ��,��,��,��, [5,....] - > �ϼ�, �ϵ�, ����, ����
	public int sameButtonCheck[] = {1,1,1,1,1,1,1,1};
	public int v_sameButtonCheck[] = {1,1,1,1,1,1,1,1};
	/*****************************************/
	
	/********************��ư Ŭ���� üũ�ϴ� �޼ҵ�*******************/
	private boolean startCheck = true; // �ʱ�ȭ�鿡�� ��ư�� Ŭ�� �� �� ������ ������ �ֱ� ���� state �ʱⰪ�� true�� ����
	public void setStartCheck(boolean startCheck) {this.startCheck = startCheck;} // �ٲ� ���ӽ��� state���� ����
	public boolean getStartCheck() {return startCheck;} // ���ӽ��� state�� ��ȯ
	
	private boolean threadCheck = false;
	public void setthreadCheck(boolean threadCheck) {this.threadCheck = threadCheck;} // �ٲ� ���ӽ��� state���� ����
	public boolean getthreadCheck() {return threadCheck;} // ���ӽ��� state�� ��ȯ
	
	private boolean gameModeCheck = true; // �ʱ�ȭ�鿡�� ��ư�� Ŭ�� �� �� ������ ������ �ֱ� ���� state �ʱⰪ�� true�� ����
	public void setgameModeCheck(boolean gameModeCheck) {this.gameModeCheck = gameModeCheck;} // �ٲ� ���ӽ��� state���� ����
	public boolean getgameModeCheck() {return gameModeCheck;} // ���ӽ��� state�� ��ȯ
	/*********************************************************/
	
	/******************* mouse **********************/
	private int keyState = 1; // 0 == Press, 1 == Release
	public void setKeyState(int keyState) {	this.keyState = keyState;}
	public int getkeyState() { return keyState; }
	
	private int enterState = 1;
	public void setEnterState(int enterState) {	this.enterState = enterState;}
	public int getEnterState() { return enterState; }
	
	private int v_keyState = 1; // 0 == Press, 1 == Release
	public void setV_KeyState(int v_keyState) {	this.v_keyState = v_keyState;}
	public int getV_KeyState() { return v_keyState; }
	
	private int v_enterState = 1;
	public void setV_EnterState(int v_enterState) {	this.v_enterState = v_enterState;}
	public int getV_EnterState() { return v_enterState; }
	/************************************************/
	
	/*****************player1  Ŭ���� image�� ��ǥ, ���, �ε��� ���� ����**************/
	private int firstImage, secondImage; 	//�̹����� �ε��� ��
	public void setFirstImage(int firstImage) { this.firstImage = firstImage; }
	public void setSecondImage(int secondImage) { this.secondImage = secondImage; }
	
	public int getFirstImage() { return firstImage; }
	public int getSecondImage() { return secondImage; }
	
	private int choiceRow, choiceCol;
	private int firstRow, firstCol, secondRow, secondCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	public void setFirstRow(int firstRow) {	this.firstRow = firstRow; }
	public void setFirstCol(int firstCol) {	this.firstCol = firstCol; }
	public void setSecondRow(int secondRow) { this.secondRow = secondRow; }
	public void setSecondCol(int secondCol) { this.secondCol = secondCol; }
	public void setChoiceRow(int choiceRow) { this.choiceRow = choiceRow; }
	public void setChoiceCol(int choiceCol) { this.choiceCol = choiceCol; }
	
	public int getFirstRow() { return firstRow; }
	public int getFirstCol() { return firstCol;	}
	public int getSecondRow() { return secondRow; }
	public int getSecondCol() {	return secondCol; }
	public int getChoiceRow() { return choiceRow; }
	public int getChoiceCol() {	return choiceCol; }
	/***********************************************/

	/*****************player2 Ŭ���� image�� ��ǥ, ���, �ε��� ���� ����**************/
	private int v_firstImage, v_secondImage; 	//�̹����� �ε��� ��
	public void setV_FirstImage(int v_firstImage) { this.v_firstImage = v_firstImage; }
	public void setV_SecondImage(int v_secondImage) { this.v_secondImage = v_secondImage; }
	
	public int getV_FirstImage() { return v_firstImage; }
	public int getV_SecondImage() { return v_secondImage; }
	
	private int v_choiceRow, v_choiceCol;
	private int v_firstRow, v_firstCol, v_secondRow, v_secondCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	public void setV_FirstRow(int v_firstRow) {	this.v_firstRow = v_firstRow; }
	public void setV_FirstCol(int v_firstCol) {	this.v_firstCol = v_firstCol; }
	public void setV_SecondRow(int v_secondRow) { this.v_secondRow = v_secondRow; }
	public void setV_SecondCol(int v_secondCol) { this.v_secondCol = v_secondCol; }
	public void setV_ChoiceRow(int v_choiceRow) { this.v_choiceRow = v_choiceRow; }
	public void setV_ChoiceCol(int v_choiceCol) { this.v_choiceCol = v_choiceCol; }
	
	public int getV_FirstRow() { return v_firstRow; }
	public int getV_FirstCol() { return v_firstCol;	}
	public int getV_SecondRow() { return v_secondRow; }
	public int getV_SecondCol() { return v_secondCol; }
	public int getV_ChoiceRow() { return v_choiceRow; }
	public int getV_ChoiceCol() { return v_choiceCol; }
	/***********************************************/
	
	/********************player1 Map **************************/
	private int map[][][] = 
		{ 
			{
				{ 1, 2, 2, 3, 3}, 
				{ 1, 1, 1, 2, 3}, 
				{ 3, 1, 2, 1, 2},
				{ 3, 1, 2, 3, 3},
				{ 3, 1, 1, 3, 3}
			},
			
			{
				{ 2, 2, 2, 2, 2}, 
				{ 2, 2, 2, 2, 2}, 
				{ 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2}
			},
			
			{
				{ 3, 3, 3, 3, 3}, 
				{ 3, 3, 3, 3, 3}, 
				{ 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3}
			},
		};// ���� 3���� �迭�� ����
	/****************Map**********************/
	public void setMap(int stage, int row, int col, int value) { this.map[stage][row][col] = value; }
	public int getMap(int stage, int row, int col) { return map[stage][row][col]; }
	/***********************************************/
	
	/******************** player2 Map **************************/
	private int verseMap[][][] = 
		{ 
			{
				{ 1, 2, 2, 3, 3}, 
				{ 1, 1, 1, 2, 3}, 
				{ 3, 1, 2, 1, 2},
				{ 3, 1, 2, 3, 3},
				{ 3, 1, 1, 3, 3}
			},
			
			{
				{ 2, 2, 2, 2, 2}, 
				{ 2, 2, 2, 2, 2}, 
				{ 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2}
			},
			
			{
				{ 3, 3, 3, 3, 3}, 
				{ 3, 3, 3, 3, 3}, 
				{ 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3}
			},
		};// ���� 3���� �迭�� ����
	/****************Map**********************/
	public void setVerseMap(int verseStage, int row, int col, int verseValue) { this.verseMap[verseStage][row][col] = verseValue; }
	public int getVerseMap(int verseStage, int row, int col) { return verseMap[verseStage][row][col]; }
	/***********************************************/
	
	/**************************************************************************/
	
	
	/*************map Size Check �� ��ȯ***********************/
	private int stage = 0;
	public int getStage() { return stage; }
	public void setStage(int stage) { this.stage = stage; }
	
	private int verseStage = 0;
	public int getVerseStage() { return verseStage; }
	public void setVerseStage(int verseStage) { this.verseStage = verseStage; }
	
	private int mapRowSize = map[0].length; // map�� ���� size�� check
	private int mapColSize = map[0][0].length;// map�� ���� size�� check
	public int getMapRowSize() {return mapRowSize;}
	public int getMapColSize() {return mapColSize;}
	
	private int v_mapRowSize = verseMap[0].length; // map�� ���� size�� check
	private int v_mapColSize = verseMap[0][0].length;// map�� ���� size�� check
	public int getV_MapRowSize() {return v_mapRowSize;}
	public int getV_MapColSize() {return v_mapColSize;}
	/*****************************************************/
	

	/******************** Image ****************************/
	
	/***Map�� ��µǴ� image data****/
	Toolkit toolkit = getToolkit();
	private String buttonArray[] = {"image/delete.png", "image/button1.png", "image/button2.png","image/button3.png",
									"image/������.png","image/Item1.png","image/Item2.png","image/Item3.png", 
									"image/dish.png","image/Item_1.png","image/Item_2.png","image/Item_3.png" //���ÿ� ��� ������ �̹���
									}; //������
	
	private Image buttonImg[] = new Image[buttonArray.length]; // ���ڿ� �迭�� ����� button���� Image��ü�� ����
	public void setButtonImg() // �̹����� �����ϴ� �޼ҵ�
	{
		for (int i = 0; i < buttonArray.length; i++) {
			buttonImg[i] = toolkit.getImage(buttonArray[i]);
		}
	}

	public Image[] getButtonImg() {	return buttonImg; } // �̹��� ��ü�� ������ �̹����� ��ȯ
	public String[] getButtonArray() { return buttonArray; } // ���ڿ� �迭�� ����� �̹����� ��ȯ

	/****************************/

	/*******��ġ�� ���� �׸� ���� data****************/
	private String takeArray[] = {"image/empty.png", "image/Item1.png", "image/Item2.png", "image/Item3.png"}; 
	private Image takeImage[] = new Image[takeArray.length]; 

	public void setTakeImage() // �̹����� �����ϴ� �޼ҵ�
	{
		for (int i = 0; i < takeArray.length; i++) {
			takeImage[i] = toolkit.getImage(takeArray[i]);
		}
	}
	
	
	public Image[] getTakeImage() { return takeImage;}

	public String[] getTakeArray() { return takeArray; } // ���ڿ� �迭�� ����� �̹����� ��ȯ
	/******************************************/
	
	/****************************************************************************************/	
	
	
	/***************Menu State******************/

	public enum MENU { GAMESTART, VERSEGAME, RANKING, EXIT, MENU};
	public MENU menuState = MENU.MENU;
	/********************************************/
}
