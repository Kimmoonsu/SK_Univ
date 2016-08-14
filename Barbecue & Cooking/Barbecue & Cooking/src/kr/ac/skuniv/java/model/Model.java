package kr.ac.skuniv.java.model;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

public class Model extends JPanel {
	
	/***menu화면을 상태에 따라 다시 그려주기 위한 data****/
	public int menu_state=0;
	public Image menu1, menu2, menu3, menu4;
	/****************************************/
	
	/*******map 이미지 크기를 위한 data******/
	public final static int WIDTH = 100; // 단추 아이콘의 넓이
	public final static int HEIGHT = 100; // 단추 아이콘의 높이
	/*********************************/
	
	
	/********초기화면에 눈을 그려주기 위한 data******/
	private boolean snowCheck = true;
	public boolean getSnowCheck() { return snowCheck; }
	public void setSnowCheck(boolean snowCheck) { this.snowCheck = snowCheck; }
	public int snowCount[] = new int[100];
	
	private int x_randomSnow = 0;
	private int y_randomSnow = 0;
	
	public int getX_randomSnow() { return x_randomSnow; }
	public int getY_randomSnow() { return y_randomSnow; }
	public int X_RandomSnow() // x축에 눈의 위치를 무작위로 생성하는 메소드
	{
		Random random = new Random();
		x_randomSnow = random.nextInt(700);
		return x_randomSnow;
	}
	public int Y_RandomSnow() // y축에 눈의 위치를 무작위로 생성하는 메소드
	{
		Random random = new Random();
		y_randomSnow = random.nextInt(900);
		return y_randomSnow;
	}
	/***************************************/
	
	
	/*******방향 별 같은 그림의 인덱스를 가지고 있는 data*******/
	public int findCol[]  = new int[8];
	public int findRow[]  = new int[8];
	// [0,.....] -> 동,서,남,북, [5,....] - > 북서, 북동, 남동, 남서
	public int sameButtonCheck[] = {1,1,1,1,1,1,1,1};
	/*****************************************/
	
	/**********compare되었을 때 Thread를 작동시켜주는 Date****************/
	private boolean compareState = false;
	public boolean getCompareState() { return compareState; }
	public void setCompareState(boolean compareState) { this.compareState = compareState; }
	private boolean compareStopCheck = true;
	public boolean getCompareStopCheck() { return compareStopCheck; }
	public void setCompareStopCheck(Boolean compareStopCheck) { this.compareStopCheck = compareStopCheck; }
	
	/**thread1~4 시작 초기값**/
	public int compare_count1 = 0;	
	public int compare_count2 = 0;
	public int compare_count3 = 0;
	public int compare_count4 = 0;
	public int thread1 = 10;
	public int thread2 = 10;
	public int thread3 = 10;
	public int thread4 = 10;
	public int thread_index = 0;
	/********************/

	/*****각 Thread마다 배열에 이미지 인덱스를 저장해주는 변수****/
	private int c_firstImg[] = new int[11];
	private int c_secondImg [] = new int[11];
	private int c_firstRow[] = new int[11];
	private int c_firstCol[] = new int[11];
	private int c_secondRow[] = new int[11];
	private int c_secondCol[] = new int[11];

	public int[] getC_firstRow() { return c_firstRow; }
	public void setC_firstRow(int[] c_firstRow) { this.c_firstRow = c_firstRow; }
	public int[] getC_firstCol() { return c_firstCol; }
	public void setC_firstCol(int[] c_firstCol) { this.c_firstCol = c_firstCol; }
	public int[] getC_secondRow() { return c_secondRow; }
	public void setC_secondRow(int[] c_secondRow) {  this.c_secondRow = c_secondRow; }
	public int[] getC_secondCol() { return c_secondCol; }
	public void setC_secondCol(int[] c_secondCol) { this.c_secondCol = c_secondCol; }
	public int[] getC_firstImg() { return c_firstImg; } 
	public void setC_firstImg(int[] c_firstImg) { this.c_firstImg = c_firstImg; }
	public int[] getC_secondImg() { return c_secondImg; }
	public void setC_secondImg(int[] c_secondImg) { this.c_secondImg = c_secondImg; }
	/*********************************************/
	
	/******************************************************/
	
	
	
	
	/*********************동전 게임 변수***********************/
	private boolean coinStartCheck = false;
	public boolean getCoinStartCheck() { return coinStartCheck; }
	public void setCoinStartCheck(boolean coinStartCheck) { this.coinStartCheck = coinStartCheck; }
	
	
	private int coin = 0;	// random 동전 0-> 앞면 1-> 뒷면
	private int selectCoin = 0;	// 선택 동전
	public int coin_count = 0;
	public int getCoin() { return coin; }
	public void setCoin(int coin) { this.coin = coin; }
	public int getSelectCoin() { return selectCoin; }
	public void setSelectCoin(int selectCoin) { this.selectCoin = selectCoin; }
	/****coin의 결과값을 random으로 생성****/
	public int randCoin()
	{
		Random r = new Random();
		coin = r.nextInt(2);
		return coin; 
	}
	/*****************************/
	
	public int tryPass = 1; 	//성공 횟수
	public int score = 10000; // 게임 점수
	public void setScore(int score) { this.score = score; }
	public int getScore() {   return score; }
	public int scoreArr[] = new int[7];   
	
	/*****************************************************/
	
	
	
	/************시간 표현을 위한 변수 및 메소드**************/
	private boolean stopCheck = true; 
	public boolean getStopCheck() { return stopCheck; }
	public void setStopCheck(boolean stopCheck) { this.stopCheck = stopCheck; }
	
	private int second = 0; // 초
	private int minute = 0;	// 분
	private int hour = 0;	// 초
	
	public int getSecond() { return second; }
	public void setSecond(int second) { this.second = second; }
	public int getMinute() { return minute; }
	public void setMinute(int minute) { this.minute = minute; }
	public int getHour() { return hour; }
	public void setHour(int hour) { this.hour = hour; }
	/**************************************************************/

	

	/********************버튼 클릭을 체크하는 메소드*******************/
	private boolean startCheck = true; // 초기화면에서 버튼을 클릭 할 때 게임을 시작해 주기 위해 state 초기값을 true로 지정
	public void setStartCheck(boolean startCheck) {this.startCheck = startCheck;} // 바뀐 게임시작 state값을 저장
	public boolean getStartCheck() {return startCheck;} // 게임시작 state를 반환
	private boolean finishCheck = true; // 초기화면에서 버튼을 클릭 할 때 게임을 시작해 주기 위해 state 초기값을 true로 지정
	public void setfinishCheck(boolean finishCheck) {this.finishCheck = finishCheck;} // 바뀐 게임시작 state값을 저장
	public boolean getfinishCheck() {return finishCheck;} // 게임시작 state를 반환
	
	private boolean gameModeCheck = true; // 초기화면에서 버튼을 클릭 할 때 게임을 시작해 주기 위해 state 초기값을 true로 지정
	public void setgameModeCheck(boolean gameModeCheck) {this.gameModeCheck = gameModeCheck;} // 바뀐 게임시작 state값을 저장
	public boolean getgameModeCheck() {return gameModeCheck;} // 게임시작 state를 반환
	/*********************************************************/
	
	/******************* mouse state**********************/
	private int mouseState = 1; // 0 == Press, 1 == Release
	public void setMouseState(int mouseState) {	this.mouseState = mouseState;}
	public int getMouseState() { return mouseState; }
	/************************************************/
	
	/*****************혼자 하기 mode에서 클릭한 image의 좌표, 행렬, 인덱스 값을 저장**************/
	private int firstImage, secondImage; 	//이미지의 인덱스 값
	public void setFirstImage(int firstImage) { this.firstImage = firstImage; }
	public void setSecondImage(int secondImage) { this.secondImage = secondImage; }
	
	private int firstX,firstY, secondX,secondY; 
	public void setFirstX(int firstX) { this.firstX = firstX;}
	public void setFirstY(int firstY) { this.firstY = firstY;}
	public void setSecondX(int secondX) { this.secondX = secondX;}
	public void setSecondY(int secondY) { this.secondY = secondY;}
    public int getFirstX() { return firstX;}
    public int getFirstY() { return firstY;}
    public int getSecondX() { return secondX;}
    public int getSecondY() { return secondY;}

	public int getFirstImage() { return firstImage; }
	public int getSecondImage() { return secondImage; }
	
	private int firstRow, firstCol, secondRow, secondCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	public void setFirstRow(int firstRow) {	this.firstRow = firstRow; }
	public void setFirstCol(int firstCol) {	this.firstCol = firstCol; }
	public void setSecondRow(int secondRow) { this.secondRow = secondRow; }
	public void setSecondCol(int secondCol) { this.secondCol = secondCol; }

	public int getFirstRow() { return firstRow; }
	public int getFirstCol() { return firstCol;	}
	public int getSecondRow() { return secondRow; }
	public int getSecondCol() {	return secondCol; }

	/***********************************************/
	
	/******************** Map **************************/
	private int map[][][] = 
		{ 
			{
				{ 1, 2, 1, 3, 4}, 
				{ 4, 2, 1, 3, 4}, 
				{ 3, 4, 1, 2, 4},
				{ 3, 2, 5, 3, 5},
				{ 5, 5, 5, 4, 1}
			},
			
			{
				{ 1, 2, 3, 4, 3}, 
				{ 5, 5, 5, 3, 4}, 
				{ 5, 4, 1, 2, 4},
				{ 5, 5, 2, 3, 4},
				{ 5, 5, 2, 1, 1}
			},
			
			{
				{ 1, 2, 2, 4, 5}, 
				{ 1, 3, 2, 3, 1}, 
				{ 4, 3, 5, 4, 5},
				{ 4, 2, 1, 4, 5},
				{ 5, 4, 4, 3, 5}
			
			},
			
		};// 맵을 3차원 배열로 선언
	/****************Map**********************/
	public void setMap(int stage, int row, int col, int value) { this.map[stage][row][col] = value; }
	public int getMap(int stage, int row, int col) { return map[stage][row][col]; }
	/***********************************************/
	
	/******************** save Map **************************/
	private int saveMap[][][] = 
		{ 
			{
				{ 1, 2, 1, 3, 4}, 
				{ 4, 2, 1, 3, 4}, 
				{ 3, 4, 1, 2, 4},
				{ 3, 2, 5, 3, 5},
				{ 5, 5, 5, 4, 1}
			},
			
			{
				{ 1, 2, 3, 4, 3}, 
				{ 5, 5, 5, 3, 4}, 
				{ 5, 4, 1, 2, 4},
				{ 5, 5, 2, 3, 4},
				{ 5, 5, 2, 1, 1}
			},
			
			{
				{ 1, 2, 2, 4, 5}, 
				{ 1, 3, 2, 3, 1}, 
				{ 4, 3, 5, 4, 5},
				{ 4, 2, 1, 4, 5},
				{ 5, 4, 4, 3, 5}
			
			},
		};// 맵을 3차원 배열로 선언
	/****************Map**********************/
	public void setSaveMap(int verseStage, int verseRow, int verseCol, int verseValue) { this.saveMap[verseStage][verseRow][verseCol] = verseValue; }
	public int getSaveMap(int verseStage, int verseRow, int verseCol) { return saveMap[verseStage][verseRow][verseCol]; }
	/***********************************************/
	
	/**************************************************************************/
	
	
	/*************map Size Check 후 반환***********************/
	private int stage = 0;
	public int getStage() { return stage; }
	public void setStage(int stage) { this.stage = stage; }

	private int mapRowSize = map[0].length; // map의 행의 size를 check
	private int mapColSize = map[0][0].length;// map의 열의 size를 check
	public int getMapRowSize() {return mapRowSize;}
	public int getMapColSize() {return mapColSize;}
	/*****************************************************/
	
	/***********ranking data***************/
	private boolean newRankCheck = false;
	public boolean getNewRankCheck() { return newRankCheck; }
	public void setNewRankCheck(boolean newRankCheck) { this.newRankCheck = newRankCheck; }
	public String rankTable[] = new String[7]; // rank값을 저장하는 array
	
	/******rankTable 값 초기화*******/
	public void setRankTable()
	{
		for(int i=0; i<rankTable.length;  i++)
	         rankTable[i] = "";
	}
	/***************************/
	public int rankIndex = 0; 
	/*****************************************/
	

	/******************** Image ****************************/
	Toolkit toolkit = getToolkit();
	private String menuArray[] = { "image/MainMenu1.png", "image/MainMenu2.png","image/MainMenu3.png","image/MainMenu4.png"};
	private Image menuImg[] = new Image[4];
	public void setMenuImg()
	{
		for (int i = 0; i < menuArray.length; i++)
		{
			menuImg[i] = toolkit.getImage(menuArray[i]);
		}
	}
	public Image[] getMenuImg()	{ return menuImg; }
	private String buttonArray[] = {"image/delete.png", "image/button1.png", "image/button2.png",
			"image/button3.png", "image/button4.png", "image/button5.png" ,"image/Item1.png","image/Item2.png","image/Item3.png", "image/Item4.png", "image/Item5.png"}; // 각각의 button을 문자열 배열에 저장
	private Image buttonImg[] = new Image[11]; // 문자열 배열에 저장된 button들을 Image객체에 저장
	public void setButtonImg() // 이미지를 저장하는 메소드
	{
		for (int i = 0; i < buttonArray.length; i++) {
			buttonImg[i] = toolkit.getImage(buttonArray[i]);
		}
	}

	public Image[] getButtonImg() {	return buttonImg; } // 이미지 객체에 저장한 이미지를 반환
	public String[] getButtonArray() { return buttonArray; } // 문자열 배열에 저장된 이미지를 반환

	private String coinArray[] = {"image/coin2.png","image/coin3.png","image/coin4.png","image/coin5.png",
								"image/coin6.png","image/coin7.png","image/coin8.png","image/coin9.png",
								"image/coin10.png","image/coin11.png","image/coin12.png","image/coin13.png",
								"image/coin14.png","image/coin15.png",};
	private Image coinImg[] = new Image[14];
	public void setCoinImg()
	{
		for( int i = 0; i < coinImg.length; i++)
		{
			coinImg[i] = toolkit.getImage(coinArray[i]);
		}
	}
	public Image[] getCoinImg() { return coinImg; }
	/*******************************************************/

	/***************Menu State******************/
	public enum MENU { GAMESTART, VERSEGAME, RANKING, EXIT, MENU};
	public MENU menuState = MENU.MENU;
	/******************************************/
}
