package kr.ac.skuniv.java.controller;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.model.VerseModel;
import kr.ac.skuniv.java.view.GameBoard;
import kr.ac.skuniv.java.view.VerseBoard;
import kr.ac.skuniv.java.view.View;

public class Controller implements MouseListener, MouseMotionListener {
	Stack pre = new Stack();	// 한 step 전으로 돌아가는 Stack  
	Stack reset = new Stack();	// 맵을 초기화 해주는 Stack
	Model model;
	GameBoard gameBoard;
	GameSound gameSound;
	Image main;
	View view;
	JFrame frame;
	JLabel timeLabel;
	Container contentPane;
	JLabel clearLabel;
	JPanel rankPanel;
	IORanking ioRanking;
	JLabel rankLabel1;
	JLabel rankLabel2;
	JLabel rankLabel3;
	JLabel rankLabel4;
	JLabel rankLabel5;
	JLabel rankLabel6;
	JLabel rankLabel7;
	int rank=0;
	public int soundcheck = 1;
	int mainsound = 0;
	int movedcheck = 0;
	private boolean gameModeCheck = true;
	private int mouseState = 1; 	// 첫번째 이미지와 두번째 이미지 클릭을 구분하기 위한 state
	private int compareState = 1;	// 이미지 비교를 위한 state
	private int v_mouseState = 1; 	// 첫번째 이미지와 두번째 이미지 클릭을 구분하기 위한 state
	private int firstRow, firstCol, secondRow, secondCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int firstImage, secondImage;	// 클릭한 이미지의 인덱스 값을 저장하는 변수
	private int v_firstRow, v_firstCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int mapRowSize;	// map의 행의 size를 가지고 있는 변수
	private int mapColSize;// map의 행의 size를 가지고 있는 변수
	private int changeFirstRow;	// 첫번째 선택한 곳의 row값을 방향에 따라 변화시키는 변수
	private int changeFirstCol; // 첫번째 선택한 곳의 col값을 방향에 따라 변화시키는 변수
	private boolean check = true;	// 같은 그림이 아무것도 없을 때 다시 첫번째 클릭으로 만들어 주는 변수
	private int stage = 0;	// 맵 
	private int clearCheck1 = 1;	// map button의 image index를 check해주는 변수
	private int clearCheck2 = 0;	// 맵 전체 button이 제거되었는지 check해주는 변수
	private int firstClick = 0;	//초기 맵을 push시키기 위한 state
	private int c_firstImg[] = new int[6];
	private int c_secondImg [] = new int[6];
	private int c_firstRow[] = new int[6];
	private int c_firstCol[] = new int[6];
	private int c_secondRow[] = new int[6];
	private int c_secondCol[] = new int[6];
	private int thread_index = 0;
	public Controller(Model model, GameBoard gameBoard, JFrame frame, JLabel timeLabel, Container contentPane, JLabel clearLabel, JPanel rankPanel, IORanking ioRanking, JLabel rankLabel1, JLabel rankLabel2, JLabel rankLabel3, JLabel rankLabel4, JLabel rankLabel5, JLabel rankLabel6, JLabel rankLabel7, GameSound gameSound)
	{
		this.model = model;
		this.gameBoard = gameBoard;
		this.frame = frame;
		this.timeLabel = timeLabel;
		this.contentPane = contentPane;
		this.clearLabel = clearLabel;
		this.rankPanel =rankPanel;
		this.ioRanking = ioRanking;
		this.rankLabel1 = rankLabel1;
		this.rankLabel2 = rankLabel2;
		this.rankLabel3 = rankLabel3;
		this.rankLabel4 = rankLabel4;
		this.rankLabel5 = rankLabel5;
		this.rankLabel6 = rankLabel6;
		this.rankLabel7 = rankLabel7;
		this.gameSound = gameSound;
	}
	public Controller(Model model, GameBoard gameBoard)
	{
		this.model = model;
		this.gameBoard = gameBoard;
	}
	
	/***************마우스 클릭 이벤트**********************/
	
	
	/****Click과 Drag를 구분하기 위한 Method (click했을 때 좌표를 체크)********/
	void releaseCheck(MouseEvent arg0) // 선택한 곳의 좌표를 체크
	{
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				if ((arg0.getX() > x * model.WIDTH && arg0.getY() > y * model.HEIGHT)
						&& (arg0.getX() < (x + 1) * model.WIDTH && arg0.getY() < (y + 1) * model.HEIGHT)) 
				{
					v_firstRow = y;
					v_firstCol = x;
				} 
			}
		}
	}
	/*****************************************/
	
	/*******gameBoard를 repaint해주는 Method******/
	void showPressImage()	//게임화면을 계속 그려주는 메소드 
	{
		gameBoard.repaint();	// 게임화면을 다시 그려준다.
	}
	/******************************************/
	
	/********첫번째 클릭 이미지의 인덱스 값을 알려주는 Method*********/
	public void findFirstIndex(MouseEvent arg0)	// 첫번째 이미지 클릭  인덱스 값
	{
		for (int x = 0; x < mapRowSize; x++) {
			for (int y = 0; y < mapColSize; y++) {
				if ((arg0.getX() > x * model.WIDTH && arg0.getY() > y * model.HEIGHT)
						&& (arg0.getX() < (x + 1) * model.WIDTH && arg0.getY() < (y + 1) * model.HEIGHT)) 
				{
					model.setFirstX(arg0.getY());
					model.setFirstY(arg0.getX());
					model.setFirstRow(y);	// 선택한 이미지의 열을 반환
					model.setFirstCol(x);	// 선택한 이미지의 행을 반환
					firstRow = model.getFirstRow();	// 반환 받은 열 값을 저장
					firstCol = model.getFirstCol(); // 반환 받은 행 값을 저장
					/****첫번째 이미지의  행,열,인덱스 저장*******/
					c_firstImg[thread_index] = model.getMap(stage, firstRow, firstCol);
					model.setC_firstImg(c_firstImg);
	
					c_firstRow[thread_index] = firstRow;
					model.setC_firstRow(c_firstRow);
					c_firstCol[thread_index] = firstCol;
					model.setC_firstCol(c_firstCol);
					model.thread_index = thread_index;
					/************************************************/
				} 
			}
		}
		model.setFirstImage(model.getMap(stage, firstRow, firstCol)); 
		firstImage = model.getFirstImage();
		changeFirstRow = firstRow;
		changeFirstCol = firstCol;
		E_W_S_N_Check();
		diagonalCheck();
		for(int i=0; i<8; i++)	// 같은 그림이 있으면 check 를 false로 바꾸어 준다.
		{
			if(model.sameButtonCheck[i] == 0)
			{
				check  = false;
			}
		}
		mouseState = (check == false) ? 0 : 1; // 같은 그림이 없으면 다시 첫번 째 클릭이라고 mouseState = 0으로 해준다.
		compareState = 1;	// 첫번째 이미지를 클릭 완료했다는 것을 알려주는 state
		model.setMouseState(mouseState);
	}
	/***************************************************/
	
	/********첫번째 클릭 이미지의 인덱스 값을 알려주는 Method*********/
	public void findSecondIndex(MouseEvent arg0) // 두번째 이미지 클릭  인덱스 값
	{
		for (int x = 0; x < mapRowSize; x++) {
			for (int y = 0; y < mapColSize; y++) {
				if ((arg0.getX() > x * model.WIDTH && arg0.getY() > y * model.HEIGHT)
						&& (arg0.getX() < (x + 1) * model.WIDTH && arg0.getY() < (y + 1) * model.HEIGHT)) 
				{
					model.setSecondRow(y);	// 선택한 이미지의 열을 반환
					model.setSecondCol(x);	// 선택한 이미지의 행을 반환
					secondRow = model.getSecondRow(); // 반환 받은 열 값을 저장
					secondCol = model.getSecondCol(); // 반환 받은 행 값을 저장
					/****두번째 이미지의 행, 열, 인덱스 값 저장*******/
					c_secondImg[thread_index] = model.getMap(stage, secondRow, secondCol);
					model.setC_secondImg(c_secondImg);
					
					c_secondRow[thread_index] = secondRow;
					model.setC_secondRow(c_secondRow);
					c_secondCol[thread_index] = secondCol;
					model.setC_secondCol(c_secondCol);
					
					model.thread_index = thread_index;
					/***************************************/
				} 
			}
		}
		mouseState = 1; // 두번쨰 이미지를 클릭 완료 했다고 알려주는 state set
		model.setMouseState(mouseState);
		compareState = 0; // 두번쨰 이미지를 완료하여 비교가능 하다는 것을 알려주는 state set
		v_mouseState = 1;
	}
	/****************************************************/

	/********첫번째 클릭과 두번째 클릭을 비교해 주는 Method*********/
	public void compareImage()	// 선택한 이미지를 비교하는 메소드
	{
		
		/*************첫번째 클릭값의 인덱스와, 두번 째 클릭값의 인덱스를 firstImage와 secondImage변수에 저장************/
		model.setFirstImage(model.getMap(stage, firstRow, firstCol)); 
		model.setSecondImage(model.getMap(stage, secondRow, secondCol));
		firstImage = model.getFirstImage();
		secondImage = model.getSecondImage();
		/***************************************************************************/
	
		if (firstImage == secondImage) {
			if (firstRow == secondRow && firstCol == secondCol)// 첫번째 클릭한 곳과 두번째 클릭한 곳이 같을 때 return(예외처리)
			{
				mouseState = 1;
				v_mouseState=1;
				return;
			}
			
			/*************** East button delete *************/
			if (model.sameButtonCheck[0] == 0 && firstRow == secondRow
					&& firstCol < secondCol && secondCol <= model.findCol[0]) {
				for (int i = firstCol; i <= secondCol; i++) {
					firstImage = 0;
					model.setMap(stage, firstRow, i, firstImage);
				}
			}
			/**********************************************/

			/*************** West button delete *************/
			if (model.sameButtonCheck[1] == 0 && firstRow == secondRow
					&& firstCol > secondCol && secondCol >= model.findCol[1]) {
				for (int i = firstCol; i >= secondCol; i--) {
					firstImage = 0;
					model.setMap(stage, firstRow, i, firstImage);
				}
			}
			/**********************************************/

			/*************** South button delete *************/
			if (model.sameButtonCheck[2] == 0 && firstCol == secondCol
					&& firstRow < secondRow && secondRow <= model.findRow[2]) {
				for (int i = firstRow; i <= secondRow; i++) {
					firstImage = 0;
					model.setMap(stage, i, firstCol, firstImage);
				}
				
			}
			/**********************************************/

			/*************** North button delete *************/
			if (model.sameButtonCheck[3] == 0 && firstCol == secondCol
					&& firstRow > secondRow && secondRow >= model.findRow[3]) {
				for (int i = firstRow; i >= secondRow; i--) {
					firstImage = 0;
					model.setMap(stage, i, firstCol, firstImage);
				}
				
			}
			/**********************************************/
			
			/*************** NorthWest button delete *************/
			for (int k = 0; k <= firstRow; k++) {
				if (model.sameButtonCheck[4] == 0
						&& (firstRow - k) == secondRow
						&& (firstCol - k) == secondCol && secondRow >= model.findRow[4]) {
					
					int j = 0;
					for (int i = firstRow; i >= secondRow; i--) {

						firstImage = 0;
						model.setMap(stage, i, (firstCol - j), firstImage);
						j++;
					}

				}
				
			}
			/**********************************************/

			/*************** NorthEast button delete *************/
			for (int k = 0; k <= firstRow; k++) {

				if (model.sameButtonCheck[5] == 0
						&& (firstRow - k) == secondRow
						&& (firstCol + k) == secondCol && secondRow >= model.findRow[5] ) {

					int j = 0;
					for (int i = firstRow; i >= secondRow; i--) {
						firstImage = 0;
						model.setMap(stage, i, firstCol + j, firstImage);
						j++;
					}
				}
			}
			/**********************************************/

			/*************** SouthEast button delete *************/
			for (int k = 0; k <= secondRow; k++) {
				if (model.sameButtonCheck[6] == 0
						&& (firstRow + k) == secondRow
						&& (firstCol + k) == secondCol && secondRow <= model.findRow[6]) {

					int j = 0;
					for (int i = firstRow; i <= secondRow; i++) {
						firstImage = 0;
						model.setMap(stage, i, firstCol + j, firstImage);
						j++;
					}

				}
			}
			/**********************************************/

			/*************** SouthWest button delete *************/
			for (int k = 0; k <= secondRow; k++) {
				if (model.sameButtonCheck[7] == 0
						&& (firstRow + k) == secondRow
						&& (firstCol - k) == secondCol && secondRow <= model.findRow[7]) {

					int j = 0;
					for (int i = firstRow; i <= secondRow; i++) {
						firstImage = 0;
						model.setMap(stage, i, (firstCol - j), firstImage);
						j++;
					}
				}
			}
			/**********************************************/
			
			/*********compare성공시********/
			if(firstImage == 0)
			{
				compareThread();
				new GameSound("white_turn.wav");
			}
			/***************************/
			
			/*********************************************/
			
		}
	
	}
	/***********************************************/
	
	/***********삭제되었을 때 Thread발생 Method*****************/
	public void compareThread()
	{
		/********삭제 되었을 때 Thread 실행*******************/
		if(thread_index==0)
		{
			CompareThread compareThread = new CompareThread(model, gameBoard);
			compareThread.start(); //thread 실행
		}
		else if(thread_index == 1)
		{
			CompareThread compareThread1 = new CompareThread(model, gameBoard);
			compareThread1.start(); //thread 실행
		}
		else if(thread_index == 2)
		{
			CompareThread compareThread2 = new CompareThread(model, gameBoard);
			compareThread2.start(); //thread 실행
		}
		else if(thread_index == 3)
		{
			CompareThread compareThread3 = new CompareThread(model, gameBoard);
			compareThread3.start(); //thread 실행
		}
		thread_index++;
		model.setCompareState(true); //state 값을 true로 바꾸어 주고 board에서 그려준다.
		/**********multiThread 반복을 위해 초기화********/
		if(thread_index == 4)
		{
			thread_index=0;
		}
		/**********************************************/
	}
	/*************************************************/
	
	/******* Map에 모든 이미지가 삭제되었는지 Check해주는 Method ******/
	public void clearMap()	// map Clear Check Method
	{
		clearCheck2 = 0;
		for(int i = 0; i < mapRowSize; i++)
		{
			for(int j=0; j<mapColSize; j++)
			{
				clearCheck1 = model.getMap(stage, i, j);
				if( clearCheck1 == 0)	// 버튼이 삭제되었을 경우
				{
					clearCheck2++; // 삭제된 버튼의 갯수 증가
				}
				clearCheck1 = 1;	// 버튼삭제 여부를 묻기위해 초기화
			}
		}
	}
	/******************************************************/
	
	/********방향 별 state값을 초기화 해주는 Method********/
	public void resetSameButtonState()// 방향별 state값을 초기화 해주는 메소드
	{
		for (int i = 0; i < 8; i++) {
			model.sameButtonCheck[i] = 1;		
		}
	}
	/*********************************************/
	
	/********동서남북에 같은 그림이 있는지 Check해주는 Method********/
	public void E_W_S_N_Check()	//	동서남북 체크
	{
		if (firstRow != 0 )	// 북쪽 체크
		{
			for(int i=firstRow; i > 0; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{		
				i--;
				if(firstImage == model.getMap(stage, i, firstCol) || model.getMap(stage, i, firstCol) == 0 )
				{
					if(model.getMap(stage, i, firstCol) != 0)
					{
						model.findRow[3] = i;
						model.findCol[3] = firstCol;
						model.sameButtonCheck[3] = 0;
					}
				}
				else 
				{
					i = 0;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (firstRow != (mapRowSize-1))	// South Check
		{
			for(int i=firstRow; i < mapRowSize-1; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{
				i++;
				if(firstImage == model.getMap(stage, (i), firstCol) || model.getMap(stage, (i), firstCol) == 0)
				{
					if(model.getMap(stage, (i), firstCol) != 0)
					{
						model.findRow[2] = i;
						model.findCol[2] = firstCol;
						model.sameButtonCheck[2] = 0;
					}
				}
				else 
				{
					i = mapRowSize;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (firstCol != 0 )	// West Check
		{
			for( int i=firstCol; i > 0; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{
				i--;
				if ( firstImage == model.getMap(stage, firstRow, i) || model.getMap(stage, firstRow, i) == 0)
				{
					if( model.getMap(stage, firstRow, i) != 0 )
					{
						model.findRow[1] = firstRow;
						model.findCol[1] = i;
						model.sameButtonCheck[1] = 0;
					}
					//model.sameButtonCheck[1] = 0;
				}
				else 
				{
					i = 0;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (firstCol != mapColSize-1 )	// East Check
		{
			for(int i=firstCol; i < mapColSize-1; )	// 
			{
				i++;
				if(firstImage == model.getMap(stage, firstRow,i) || model.getMap(stage, firstRow,i) == 0 ) 
				{
					if( model.getMap(stage, firstRow,i) != 0)
					{
						model.findRow[0] = firstRow;
						model.findCol[0] = i;
						model.sameButtonCheck[0] = 0;
					}
					
				}
				else 
				{
					i = mapColSize;	// 다르면 더이상 비교 안해
				}	
			}
		}
	}
	/***************************************************/
	
	/********대각선에 같은 그림이 있는지 Check해주는 Method********/
	public void diagonalCheck()	// 대각선 체크
	{
		
		/**************NorthWest Check****************/
		if ( firstRow != 0 && firstCol != 0 )
		{
			if ( firstRow >= firstCol )
			{
				for (int i = firstCol; i > 0; )
				{
					i--;
					changeFirstRow--;
					if ( firstImage == model.getMap(stage, changeFirstRow, i) || model.getMap(stage, changeFirstRow, i) == 0 )
					{
						if( model.getMap(stage, changeFirstRow, i) != 0)
						{
							model.findRow[4] = changeFirstRow;
							model.findCol[4] = i;	
							model.sameButtonCheck[4] = 0;
						}
						
					}
					else
					{
						i = 0;
					}
				}
			}
			else
			{
				
				for ( int i = firstRow; i > 0; )
				{
					
					i--; 
					changeFirstCol--;
					if( firstImage == model.getMap(stage, i, changeFirstCol) || model.getMap(stage, i, changeFirstCol) == 0 )
					{
						if(model.getMap(stage, i, changeFirstCol) != 0)
						{
							model.findRow[4] = i;
							model.findCol[4] = changeFirstCol;
							model.sameButtonCheck[4] = 0;	
						}
						
					}
					else
					{
						i = 0;
					}
						
				}
			}
			changeFirstRow = firstRow;
			changeFirstCol = firstCol;
		}
		/***************************************************/
		
		/****************SouthEast Check**************************/
		if ( firstRow != mapRowSize-1 && firstCol != mapColSize-1 )
		{
			if ( firstRow >= firstCol )
			{
				
				for (int i = firstRow+1; i < mapRowSize; )
				{
					changeFirstCol++;
					
					if ( firstImage == model.getMap(stage, i, changeFirstCol) || model.getMap(stage, i, changeFirstCol) == 0 )
					{
						if(model.getMap(stage, i, changeFirstCol) != 0 )
						{
							model.findRow[6] = i;
							model.findCol[6] = changeFirstCol;
							model.sameButtonCheck[6] = 0;
						}
										
					}
					else
					{
						i = mapRowSize;
					}
					i++;
				}
			}
			else
			{
				
				for ( int i = firstCol+1; i < mapColSize;)
				{
					changeFirstRow++;
					if( firstImage == model.getMap(stage, changeFirstRow, i) || model.getMap(stage, changeFirstRow, i) == 0 )
					{
						if( model.getMap(stage, changeFirstRow, i) != 0 )
						{
							model.findRow[6] = changeFirstRow;
							model.findCol[6] = i;
							model.sameButtonCheck[6] = 0;
						}
							
					}
					else
					{
						i = mapColSize;
					}
					i++;	
				}
			}
			changeFirstRow = firstRow;
			changeFirstCol = firstCol;
		}
		/***************************************************/
		
		
		/****************NorthEast Check**************************/
		if (firstRow != 0 && firstCol != mapColSize-1) {
			
			for (int i = firstRow-1; i > 0 || changeFirstCol < mapColSize-1;) 
			{
				changeFirstCol++; 
				if (firstImage == model.getMap(stage, i, changeFirstCol) || model.getMap(stage, i, changeFirstCol) == 0 ) 
				{
					if(model.getMap(stage, i, changeFirstCol) != 0 )
					{
						model.findRow[5] = i;
						model.findCol[5] = changeFirstCol;
						model.sameButtonCheck[5] = 0;
					}
					
				}
				else 
				{
					i = 0;
					changeFirstCol = mapColSize;
				}
				if( i == 0 )
				{
					changeFirstCol = mapColSize;
				}
				if ( changeFirstCol == mapColSize-1 )
				{
					i = 0;
				}
				i--;
			}
			
		}
		changeFirstRow = firstRow;
		changeFirstCol = firstCol;
		/***************************************************/
		
		/****************SouthWest Check**************************/
		if (firstRow != mapRowSize-1 && firstCol != 0 ) {
			for (int i = firstCol-1; i > 0 || changeFirstRow < mapRowSize-1;) 
			{
				changeFirstRow++; 
				if (firstImage == model.getMap(stage, changeFirstRow, i) || model.getMap(stage, changeFirstRow, i) == 0) 
				{
					if (model.getMap(stage, changeFirstRow, i) != 0 )
					{
						model.findRow[7] = changeFirstRow;
						model.findCol[7] = i;	
						model.sameButtonCheck[7] = 0;
					}
					
				}
				else 
				{
					i = 0;
					changeFirstRow = mapRowSize;
				}
				if( i == 0 )
				{
					changeFirstRow = mapRowSize;
				}
				if ( changeFirstRow == mapRowSize-1 )
				{
					i = 0;
				}
				i--;
			}
			
		}
		changeFirstRow = firstRow;
		changeFirstCol = firstCol;
		/***************************************************/
		
	}
	/****************************************************/
	
	/**************클릭 할 때마다 맵을 push 시켜주는 Method************/
	public void push()	 
	{
		for(int x=0; x < mapRowSize; x++)
			for(int y=0; y < mapColSize; y++)
			 pre.push(model.getMap(stage, x, y)); 
	}
	/*************************************************/
	
	/**************한 Step 전으로 돌아가주는 Method************/
	public void pop()
	{
		for(int x=0; x < mapRowSize; x++){
			for(int y = 0; y < mapColSize; y++)
			{
				int pop = (Integer) pre.pop();
				model.setMap(stage, mapRowSize-(x+1), mapColSize-(y+1), pop);
			}
		}
	}
	/*************************************************/
	
	/*****************초기 맵을 Push해주는 Method********************/
	public void resetPush()
	{
		for (int x = 0; x < mapRowSize; x++)
			for (int y = 0; y < mapColSize; y++)
				reset.push(model.getMap(stage, x, y));
	}
	/********************************************************/
	
	/*****************맵을 Reset해주는 Method********************/
	public void resetPop()
	{
		for(int x=0; x < mapRowSize; x++){
			for(int y = 0; y < mapColSize; y++)
			{
				int pop = (Integer) reset.pop();
				model.setMap(stage, mapRowSize-(x+1), mapColSize-(y+1), pop);
			}
		}
	}
	/*************************************************/
	
	/*****************press Option Button ********************************/
	public void buttonCheck1(MouseEvent arg0)
	{
		if( (arg0.getX() > 80 && arg0.getX() < 160) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			
			gameModeCheck=false;
		}
		else if( (arg0.getX() > 210 && arg0.getX() < 290) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			
			gameModeCheck = false;
		}
		else if( (arg0.getX() > 340 && arg0.getX() < 420) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
				gameModeCheck=false;
		}
		else
			gameModeCheck = true;
		
	}
	/*************************************************/
	
	/*****************release Option Button ********************************/
	public void buttonCheck2(MouseEvent arg0)
	{
		/*****pre 이미지 클릭일 경우***/
		if( (arg0.getX() > 80 && arg0.getX() < 160) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			pop();
			gameModeCheck=false;
		}
		/*****reset 이미지 클릭일 경우***/
		else if( (arg0.getX() > 210 && arg0.getX() < 290) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			if(firstClick==1)	//push되어있는 상태
			{
				resetPop();
				firstClick=0;
			}
			gameModeCheck = false;
		}
		/*****메뉴화면 이미지 클릭일 경우***/
		else if( (arg0.getX() > 340 && arg0.getX() < 420) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			soundcheck = 0;
			
			model.menuState = MENU.MENU;
			model.setStopCheck(false);
			model.setCompareState(false);
			model.setCompareStopCheck(false);
			frame.setSize(700, 900);
			contentPane.remove(timeLabel);
			contentPane.remove(clearLabel);
			contentPane.remove(gameBoard);
			contentPane.add(gameBoard, BorderLayout.CENTER);
			model.setSecond(0);
			model.menu_state = 0;
			
			
			/***********************/
			gameModeCheck=false;
		}
		else
			gameModeCheck = true;
		
	}
	/*************************************************/
	
	/*************coinMethod******************/
	public void coinIndex(MouseEvent arg0)
	{
		if ( (arg0.getX() > 60 && arg0.getX() < 160) && (arg0.getY() > 350 && arg0.getY() < 450 ))
		{
			model.setSelectCoin(0);
			model.coin_count = 1;
			model.randCoin();
			/************동전이 던저지는 Thread 동작**************/
			CoinThread coinThread = new CoinThread(model,gameBoard,frame,timeLabel,contentPane , rankPanel, ioRanking);
			coinThread.start();
			/**********************************************/
		}
		else if ( (arg0.getX() > 310 && arg0.getX() < 410) && (arg0.getY() > 350 && arg0.getY() < 450 ))
		{
			model.setSelectCoin(1);
			model.coin_count = 1;
			model.randCoin();
			/************동전이 던저지는 Thread 동작**************/
			CoinThread coinThread = new CoinThread(model,gameBoard,frame,timeLabel,contentPane, rankPanel, ioRanking);
			coinThread.start();
			/**********************************************/
		}
		else if ((arg0.getX() > 300 && arg0.getX() < 450)
				&& (arg0.getY() > 480 && arg0.getY() < 580)) {
	 		model.setNewRankCheck(true); 	// 새로운 게임이 끝났다.
			String name = JOptionPane.showInputDialog("이름을입력하여주세요.");
			model.rankTable[model.rankIndex] = "Name : " + name + " Score : "
					+ model.score + '\n';
			ioRanking.input();
			contentPane.remove(timeLabel);
			contentPane.remove(gameBoard);
			model.menuState = MENU.MENU;
			model.setStopCheck(false);
			model.setCompareState(false);
			model.setCompareStopCheck(false);
			frame.setSize(700, 900);
			contentPane.remove(timeLabel);
			contentPane.remove(gameBoard);
			contentPane.add(gameBoard, BorderLayout.CENTER);
			model.setSecond(0);
			model.menu_state = 0;
			gameBoard.repaint();
		}
		
	}
	/******************************************/
	
	/*********ranking모드일때 index를 알려주는 Method********/
	public void rankingIndex(MouseEvent arg0)
	{
		if ( (arg0.getX() > 200 && arg0.getX() < 300) && (arg0.getY() > 0 && arg0.getY() < 100 ))
		{
			model.menuState = MENU.MENU;
			model.setStopCheck(false);
			model.setCompareState(false);
			model.setCompareStopCheck(false);
			contentPane.remove(rankPanel);
			contentPane.remove(gameBoard);
			contentPane.add(gameBoard, BorderLayout.CENTER);
			frame.setLocation(10,10);
			frame.setSize(700, 900);
			model.setSecond(0);
			model.menu_state = 0;
		}
	}
	/**************************************************/
	
	/*****************Mouse Event********************/
	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }
	
	/********Mouse를 Press 했을 때 state를 변경해주는 Event********/
	public void mousePressed(MouseEvent arg0) 
	{
		if(model.menuState == MENU.GAMESTART)
		{
		/********** 기본 게임 진행************/
		if(model.getCoinStartCheck() == false)
		{
			buttonCheck1(arg0);
			if (gameModeCheck == true) 
			{
				stage = model.getStage();
				check = true; // 클릭할 때 마다 true로 바꾸어 준다.
				mapRowSize = model.getMapRowSize();
				mapColSize = model.getMapColSize();
				if (v_mouseState == 1 && mouseState == 1) { ;push();findFirstIndex(arg0); } // 첫번째 클릭 이미지 인덱스 반환
				if (v_mouseState == 0)	{ findSecondIndex(arg0); }
				showPressImage(); // 마우스를 클릭할 때 마다 게임화면을 그려준다.
			}
			
		}
		}
		/**********************************/
	}
	/****************************************************/
	
	/********Mouse를 Release 했을 때 state를 변경해주는 Event********/
	public void mouseReleased(MouseEvent e) {
		if (model.menuState == MENU.MENU)
		{
			for(int i=0; i<4; i++){
				if((e.getX() > 250 && e.getY() >300+(i*100)
						&& (e.getX() < 450) && e.getY() < 350+(i*100)))
				{
					if(i==0)	// 첫번째 메뉴일 경우
					{
						/*********초기화***********/
						model.setCompareState(true);
						model.setCompareStopCheck(true);
						model.setStage(0);
						for(int k = 0; k < mapRowSize; k++)
						{
							for(int j =0; j < mapColSize; j++)
							{
								model.setMap(2, k, j, model.getSaveMap(2, k, j));
							}
						}
						gameSound.stopSound();//효과음을 멈춘다.
						model.score = 10000; // score 초기화
						model.setMouseState(0);	
						model.setFirstRow(0);
						model.setFirstCol(0);
						model.setSecondRow(0);
						model.setSecondCol(0);
						model.thread_index = 0;
						model.thread1 = 10;
						model.thread2 = 10;
						model.thread3 = 10;
						model.thread4 = 10;
						model.compare_count1  = 0;
						model.compare_count2  = 0;
						model.compare_count3  = 0;
						model.compare_count4  = 0;
						/***************************/
						contentPane.remove(gameBoard);
						contentPane.remove(timeLabel);
						contentPane.add(gameBoard, BorderLayout.CENTER);
						model.menuState = MENU.GAMESTART;
						model.setCoinStartCheck(false);
						frame.setSize(518,745);
						contentPane.add(timeLabel, BorderLayout.NORTH);
						model.setStopCheck(true);
						TimeThread timeThread = new TimeThread(model,timeLabel);
						timeThread.start();
					}
					else if (i==1)	// 두번째 메뉴일 경우
					{
						model.menuState = MENU.VERSEGAME;
						frame.setLocation(10,10);
						frame.setSize(1220,850);
						contentPane.remove(timeLabel);
						contentPane.remove(gameBoard);
						GameSound verseSound = new GameSound("");
						VerseModel v_model = new VerseModel();
						VerseBoard verseBoard = new VerseBoard(v_model, frame, contentPane, gameBoard, model, verseSound);
						contentPane.add(verseBoard, BorderLayout.CENTER);
						frame.addKeyListener(new VerseController(verseBoard, v_model));
						gameSound.stopSound();
					}
					else if (i==2)	// 세번째 메뉴일 경우
					{
						model.menuState = MENU.RANKING;
						contentPane.remove(timeLabel);
						contentPane.remove(gameBoard);
						frame.setLocation(300,300);
						frame.setSize(500,500);
						/****프로그램 시작하자마자 ranking을 눌렀을 때를 막기위한 state***/
						if(model.getNewRankCheck()==true)
							ioRanking.output();
						/***********************************************/
						contentPane.add(rankPanel, BorderLayout.CENTER);
						contentPane.add(gameBoard, BorderLayout.SOUTH);
						gameBoard.setPreferredSize(new Dimension(100,100));
						gameBoard.repaint();
					}
					else if (i==3)	// 네번째 메뉴일 경우
						System.exit(0);
					
				}		
			}
		}
		else if(model.menuState == MENU.GAMESTART)
		{
		/**************************Coin Game 진행***************************/
		
		/********초기 맵을 push 하기 위한 조건*****/
		if(firstClick == 0)	//초기맵 상태
		{ 
			resetPush(); // 초기맵을 Stack에 저장
			firstClick=1;	//push끝 더이상push 안하겠다.}
		}
		/**************************************/
		if ( model.getCoinStartCheck() == true )	// coin게임 진행
		{
			/*********좌표값***********/
			coinIndex(e);
			/***********************/
		}
		/**********************************/
		
		/********기본 게임 진행***********/
		if(model.getCoinStartCheck()==false)
		{
			buttonCheck2(e);
			
			if (gameModeCheck == true) {
				releaseCheck(e); // released좌표 검사
				if (firstRow == v_firstRow && firstCol == v_firstCol) // click을 위한 조건
				{
					if (check == false) {
						mouseState = 1; // 첫번째 클릭을 유지
						v_mouseState = 0; // 두번째 클릭할 것이라고 알려줌.
					} else
						v_mouseState = 1; // 같은 그림이 없으면 다시 첫번째 클릭이라고 알려줌
				}
				
				if (mouseState == 0) {
					findSecondIndex(e);
				}
				if (compareState == 0) {
					compareImage();
					
					resetSameButtonState();
					clearMap();
					if (clearCheck2 == (mapRowSize * mapColSize)) // 맵에 모든 button이 제거되었을 경우
					{
						stage++; // stage증가(다음단계)
						model.setStage(stage); // stage값을 model에 알려준다.
						showPressImage();
						/*********Map Clear했을 경우 Score값 반환 후 동전 게임 시작*************/
						if(stage == 3)
						{
							model.score -= ((model.getSecond()-1) * 10 ) + (model.getMinute() * 50 );
							model.setStopCheck(false); //Timer를 멈춘다.
							model.setCoinStartCheck(true);	// coinGame시작을 알려준다.
						}
						/************************************************************/
					}
				}
			}
		}
		/***********************************************/
		showPressImage(); // 마우스를 클릭할 때 마다 게임화면을 그려준다.
		}
		if(model.menuState == MENU.RANKING)
		{
			rankingIndex(e);
		}
	}
	/*******************************************************************/

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/****Mouse가 움직일 때 마다 state를 바꾸어 주는 Event******/
	public void mouseMoved(MouseEvent arg0) {
		
		if(model.menuState == MENU.MENU)
		{
			
//			if(soundcheck == 0)
//			{
//				new GameSound("mainmenu.wav");
//				soundcheck=1;
//			}
			
   			if((arg0.getX() > 250 && arg0.getY() >300
   	   					&& (arg0.getX() < 450) && arg0.getY() < 350))
   			{// 첫번째 메뉴일 경우
   				model.menu_state=1;
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   			}
   			else if((arg0.getX() > 250 && arg0.getY() >300+(100)
   	   				&& (arg0.getX() < 450) && arg0.getY() < 350+(100)))
   			{	// 두번째 메뉴일 경우
   				model.menu_state=2;
   				
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   				
   			}
   			else if((arg0.getX() > 250 && arg0.getY() >300+(2*100)
   	   				&& (arg0.getX() < 450) && arg0.getY() < 350+(2*100)))
   			{// 세번째 메뉴일 경우
   				model.menu_state=3;
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   			}// 네번째 메뉴일 경우
   			else if((arg0.getX() > 250 && arg0.getY() >300+(3*100)
   	   				&& (arg0.getX() < 450) && arg0.getY() < 350+(3*100)))
   			{
   				model.menu_state=4;	
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   			}
			else
   			{
   				model.menu_state=0;
   				movedcheck = 0;
   			}
   		}
   		gameBoard.repaint();
		}
	
	/*************************************************/
}