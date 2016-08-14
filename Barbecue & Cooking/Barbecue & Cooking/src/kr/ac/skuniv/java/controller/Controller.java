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
	Stack pre = new Stack();	// �� step ������ ���ư��� Stack  
	Stack reset = new Stack();	// ���� �ʱ�ȭ ���ִ� Stack
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
	private int mouseState = 1; 	// ù��° �̹����� �ι�° �̹��� Ŭ���� �����ϱ� ���� state
	private int compareState = 1;	// �̹��� �񱳸� ���� state
	private int v_mouseState = 1; 	// ù��° �̹����� �ι�° �̹��� Ŭ���� �����ϱ� ���� state
	private int firstRow, firstCol, secondRow, secondCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	private int firstImage, secondImage;	// Ŭ���� �̹����� �ε��� ���� �����ϴ� ����
	private int v_firstRow, v_firstCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	private int mapRowSize;	// map�� ���� size�� ������ �ִ� ����
	private int mapColSize;// map�� ���� size�� ������ �ִ� ����
	private int changeFirstRow;	// ù��° ������ ���� row���� ���⿡ ���� ��ȭ��Ű�� ����
	private int changeFirstCol; // ù��° ������ ���� col���� ���⿡ ���� ��ȭ��Ű�� ����
	private boolean check = true;	// ���� �׸��� �ƹ��͵� ���� �� �ٽ� ù��° Ŭ������ ����� �ִ� ����
	private int stage = 0;	// �� 
	private int clearCheck1 = 1;	// map button�� image index�� check���ִ� ����
	private int clearCheck2 = 0;	// �� ��ü button�� ���ŵǾ����� check���ִ� ����
	private int firstClick = 0;	//�ʱ� ���� push��Ű�� ���� state
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
	
	/***************���콺 Ŭ�� �̺�Ʈ**********************/
	
	
	/****Click�� Drag�� �����ϱ� ���� Method (click���� �� ��ǥ�� üũ)********/
	void releaseCheck(MouseEvent arg0) // ������ ���� ��ǥ�� üũ
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
	
	/*******gameBoard�� repaint���ִ� Method******/
	void showPressImage()	//����ȭ���� ��� �׷��ִ� �޼ҵ� 
	{
		gameBoard.repaint();	// ����ȭ���� �ٽ� �׷��ش�.
	}
	/******************************************/
	
	/********ù��° Ŭ�� �̹����� �ε��� ���� �˷��ִ� Method*********/
	public void findFirstIndex(MouseEvent arg0)	// ù��° �̹��� Ŭ��  �ε��� ��
	{
		for (int x = 0; x < mapRowSize; x++) {
			for (int y = 0; y < mapColSize; y++) {
				if ((arg0.getX() > x * model.WIDTH && arg0.getY() > y * model.HEIGHT)
						&& (arg0.getX() < (x + 1) * model.WIDTH && arg0.getY() < (y + 1) * model.HEIGHT)) 
				{
					model.setFirstX(arg0.getY());
					model.setFirstY(arg0.getX());
					model.setFirstRow(y);	// ������ �̹����� ���� ��ȯ
					model.setFirstCol(x);	// ������ �̹����� ���� ��ȯ
					firstRow = model.getFirstRow();	// ��ȯ ���� �� ���� ����
					firstCol = model.getFirstCol(); // ��ȯ ���� �� ���� ����
					/****ù��° �̹�����  ��,��,�ε��� ����*******/
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
		for(int i=0; i<8; i++)	// ���� �׸��� ������ check �� false�� �ٲپ� �ش�.
		{
			if(model.sameButtonCheck[i] == 0)
			{
				check  = false;
			}
		}
		mouseState = (check == false) ? 0 : 1; // ���� �׸��� ������ �ٽ� ù�� ° Ŭ���̶�� mouseState = 0���� ���ش�.
		compareState = 1;	// ù��° �̹����� Ŭ�� �Ϸ��ߴٴ� ���� �˷��ִ� state
		model.setMouseState(mouseState);
	}
	/***************************************************/
	
	/********ù��° Ŭ�� �̹����� �ε��� ���� �˷��ִ� Method*********/
	public void findSecondIndex(MouseEvent arg0) // �ι�° �̹��� Ŭ��  �ε��� ��
	{
		for (int x = 0; x < mapRowSize; x++) {
			for (int y = 0; y < mapColSize; y++) {
				if ((arg0.getX() > x * model.WIDTH && arg0.getY() > y * model.HEIGHT)
						&& (arg0.getX() < (x + 1) * model.WIDTH && arg0.getY() < (y + 1) * model.HEIGHT)) 
				{
					model.setSecondRow(y);	// ������ �̹����� ���� ��ȯ
					model.setSecondCol(x);	// ������ �̹����� ���� ��ȯ
					secondRow = model.getSecondRow(); // ��ȯ ���� �� ���� ����
					secondCol = model.getSecondCol(); // ��ȯ ���� �� ���� ����
					/****�ι�° �̹����� ��, ��, �ε��� �� ����*******/
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
		mouseState = 1; // �ι��� �̹����� Ŭ�� �Ϸ� �ߴٰ� �˷��ִ� state set
		model.setMouseState(mouseState);
		compareState = 0; // �ι��� �̹����� �Ϸ��Ͽ� �񱳰��� �ϴٴ� ���� �˷��ִ� state set
		v_mouseState = 1;
	}
	/****************************************************/

	/********ù��° Ŭ���� �ι�° Ŭ���� ���� �ִ� Method*********/
	public void compareImage()	// ������ �̹����� ���ϴ� �޼ҵ�
	{
		
		/*************ù��° Ŭ������ �ε�����, �ι� ° Ŭ������ �ε����� firstImage�� secondImage������ ����************/
		model.setFirstImage(model.getMap(stage, firstRow, firstCol)); 
		model.setSecondImage(model.getMap(stage, secondRow, secondCol));
		firstImage = model.getFirstImage();
		secondImage = model.getSecondImage();
		/***************************************************************************/
	
		if (firstImage == secondImage) {
			if (firstRow == secondRow && firstCol == secondCol)// ù��° Ŭ���� ���� �ι�° Ŭ���� ���� ���� �� return(����ó��)
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
			
			/*********compare������********/
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
	
	/***********�����Ǿ��� �� Thread�߻� Method*****************/
	public void compareThread()
	{
		/********���� �Ǿ��� �� Thread ����*******************/
		if(thread_index==0)
		{
			CompareThread compareThread = new CompareThread(model, gameBoard);
			compareThread.start(); //thread ����
		}
		else if(thread_index == 1)
		{
			CompareThread compareThread1 = new CompareThread(model, gameBoard);
			compareThread1.start(); //thread ����
		}
		else if(thread_index == 2)
		{
			CompareThread compareThread2 = new CompareThread(model, gameBoard);
			compareThread2.start(); //thread ����
		}
		else if(thread_index == 3)
		{
			CompareThread compareThread3 = new CompareThread(model, gameBoard);
			compareThread3.start(); //thread ����
		}
		thread_index++;
		model.setCompareState(true); //state ���� true�� �ٲپ� �ְ� board���� �׷��ش�.
		/**********multiThread �ݺ��� ���� �ʱ�ȭ********/
		if(thread_index == 4)
		{
			thread_index=0;
		}
		/**********************************************/
	}
	/*************************************************/
	
	/******* Map�� ��� �̹����� �����Ǿ����� Check���ִ� Method ******/
	public void clearMap()	// map Clear Check Method
	{
		clearCheck2 = 0;
		for(int i = 0; i < mapRowSize; i++)
		{
			for(int j=0; j<mapColSize; j++)
			{
				clearCheck1 = model.getMap(stage, i, j);
				if( clearCheck1 == 0)	// ��ư�� �����Ǿ��� ���
				{
					clearCheck2++; // ������ ��ư�� ���� ����
				}
				clearCheck1 = 1;	// ��ư���� ���θ� �������� �ʱ�ȭ
			}
		}
	}
	/******************************************************/
	
	/********���� �� state���� �ʱ�ȭ ���ִ� Method********/
	public void resetSameButtonState()// ���⺰ state���� �ʱ�ȭ ���ִ� �޼ҵ�
	{
		for (int i = 0; i < 8; i++) {
			model.sameButtonCheck[i] = 1;		
		}
	}
	/*********************************************/
	
	/********�������Ͽ� ���� �׸��� �ִ��� Check���ִ� Method********/
	public void E_W_S_N_Check()	//	�������� üũ
	{
		if (firstRow != 0 )	// ���� üũ
		{
			for(int i=firstRow; i > 0; )	// �Է��� ���� ��ǥ �������� Ž���ϱ� ���� firstRow��ŭ ���� �Ͽ���.
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
					i = 0;	// �ٸ��� ���̻� �� ����
				}	
			}
		}
		if (firstRow != (mapRowSize-1))	// South Check
		{
			for(int i=firstRow; i < mapRowSize-1; )	// �Է��� ���� ��ǥ �������� Ž���ϱ� ���� firstRow��ŭ ���� �Ͽ���.
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
					i = mapRowSize;	// �ٸ��� ���̻� �� ����
				}	
			}
		}
		if (firstCol != 0 )	// West Check
		{
			for( int i=firstCol; i > 0; )	// �Է��� ���� ��ǥ �������� Ž���ϱ� ���� firstRow��ŭ ���� �Ͽ���.
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
					i = 0;	// �ٸ��� ���̻� �� ����
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
					i = mapColSize;	// �ٸ��� ���̻� �� ����
				}	
			}
		}
	}
	/***************************************************/
	
	/********�밢���� ���� �׸��� �ִ��� Check���ִ� Method********/
	public void diagonalCheck()	// �밢�� üũ
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
	
	/**************Ŭ�� �� ������ ���� push �����ִ� Method************/
	public void push()	 
	{
		for(int x=0; x < mapRowSize; x++)
			for(int y=0; y < mapColSize; y++)
			 pre.push(model.getMap(stage, x, y)); 
	}
	/*************************************************/
	
	/**************�� Step ������ ���ư��ִ� Method************/
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
	
	/*****************�ʱ� ���� Push���ִ� Method********************/
	public void resetPush()
	{
		for (int x = 0; x < mapRowSize; x++)
			for (int y = 0; y < mapColSize; y++)
				reset.push(model.getMap(stage, x, y));
	}
	/********************************************************/
	
	/*****************���� Reset���ִ� Method********************/
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
		/*****pre �̹��� Ŭ���� ���***/
		if( (arg0.getX() > 80 && arg0.getX() < 160) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			pop();
			gameModeCheck=false;
		}
		/*****reset �̹��� Ŭ���� ���***/
		else if( (arg0.getX() > 210 && arg0.getX() < 290) && (arg0.getY() > 510 && arg0.getY() < 590) )
		{
			if(firstClick==1)	//push�Ǿ��ִ� ����
			{
				resetPop();
				firstClick=0;
			}
			gameModeCheck = false;
		}
		/*****�޴�ȭ�� �̹��� Ŭ���� ���***/
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
			/************������ �������� Thread ����**************/
			CoinThread coinThread = new CoinThread(model,gameBoard,frame,timeLabel,contentPane , rankPanel, ioRanking);
			coinThread.start();
			/**********************************************/
		}
		else if ( (arg0.getX() > 310 && arg0.getX() < 410) && (arg0.getY() > 350 && arg0.getY() < 450 ))
		{
			model.setSelectCoin(1);
			model.coin_count = 1;
			model.randCoin();
			/************������ �������� Thread ����**************/
			CoinThread coinThread = new CoinThread(model,gameBoard,frame,timeLabel,contentPane, rankPanel, ioRanking);
			coinThread.start();
			/**********************************************/
		}
		else if ((arg0.getX() > 300 && arg0.getX() < 450)
				&& (arg0.getY() > 480 && arg0.getY() < 580)) {
	 		model.setNewRankCheck(true); 	// ���ο� ������ ������.
			String name = JOptionPane.showInputDialog("�̸����Է��Ͽ��ּ���.");
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
	
	/*********ranking����϶� index�� �˷��ִ� Method********/
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
	
	/********Mouse�� Press ���� �� state�� �������ִ� Event********/
	public void mousePressed(MouseEvent arg0) 
	{
		if(model.menuState == MENU.GAMESTART)
		{
		/********** �⺻ ���� ����************/
		if(model.getCoinStartCheck() == false)
		{
			buttonCheck1(arg0);
			if (gameModeCheck == true) 
			{
				stage = model.getStage();
				check = true; // Ŭ���� �� ���� true�� �ٲپ� �ش�.
				mapRowSize = model.getMapRowSize();
				mapColSize = model.getMapColSize();
				if (v_mouseState == 1 && mouseState == 1) { ;push();findFirstIndex(arg0); } // ù��° Ŭ�� �̹��� �ε��� ��ȯ
				if (v_mouseState == 0)	{ findSecondIndex(arg0); }
				showPressImage(); // ���콺�� Ŭ���� �� ���� ����ȭ���� �׷��ش�.
			}
			
		}
		}
		/**********************************/
	}
	/****************************************************/
	
	/********Mouse�� Release ���� �� state�� �������ִ� Event********/
	public void mouseReleased(MouseEvent e) {
		if (model.menuState == MENU.MENU)
		{
			for(int i=0; i<4; i++){
				if((e.getX() > 250 && e.getY() >300+(i*100)
						&& (e.getX() < 450) && e.getY() < 350+(i*100)))
				{
					if(i==0)	// ù��° �޴��� ���
					{
						/*********�ʱ�ȭ***********/
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
						gameSound.stopSound();//ȿ������ �����.
						model.score = 10000; // score �ʱ�ȭ
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
					else if (i==1)	// �ι�° �޴��� ���
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
					else if (i==2)	// ����° �޴��� ���
					{
						model.menuState = MENU.RANKING;
						contentPane.remove(timeLabel);
						contentPane.remove(gameBoard);
						frame.setLocation(300,300);
						frame.setSize(500,500);
						/****���α׷� �������ڸ��� ranking�� ������ ���� �������� state***/
						if(model.getNewRankCheck()==true)
							ioRanking.output();
						/***********************************************/
						contentPane.add(rankPanel, BorderLayout.CENTER);
						contentPane.add(gameBoard, BorderLayout.SOUTH);
						gameBoard.setPreferredSize(new Dimension(100,100));
						gameBoard.repaint();
					}
					else if (i==3)	// �׹�° �޴��� ���
						System.exit(0);
					
				}		
			}
		}
		else if(model.menuState == MENU.GAMESTART)
		{
		/**************************Coin Game ����***************************/
		
		/********�ʱ� ���� push �ϱ� ���� ����*****/
		if(firstClick == 0)	//�ʱ�� ����
		{ 
			resetPush(); // �ʱ���� Stack�� ����
			firstClick=1;	//push�� ���̻�push ���ϰڴ�.}
		}
		/**************************************/
		if ( model.getCoinStartCheck() == true )	// coin���� ����
		{
			/*********��ǥ��***********/
			coinIndex(e);
			/***********************/
		}
		/**********************************/
		
		/********�⺻ ���� ����***********/
		if(model.getCoinStartCheck()==false)
		{
			buttonCheck2(e);
			
			if (gameModeCheck == true) {
				releaseCheck(e); // released��ǥ �˻�
				if (firstRow == v_firstRow && firstCol == v_firstCol) // click�� ���� ����
				{
					if (check == false) {
						mouseState = 1; // ù��° Ŭ���� ����
						v_mouseState = 0; // �ι�° Ŭ���� ���̶�� �˷���.
					} else
						v_mouseState = 1; // ���� �׸��� ������ �ٽ� ù��° Ŭ���̶�� �˷���
				}
				
				if (mouseState == 0) {
					findSecondIndex(e);
				}
				if (compareState == 0) {
					compareImage();
					
					resetSameButtonState();
					clearMap();
					if (clearCheck2 == (mapRowSize * mapColSize)) // �ʿ� ��� button�� ���ŵǾ��� ���
					{
						stage++; // stage����(�����ܰ�)
						model.setStage(stage); // stage���� model�� �˷��ش�.
						showPressImage();
						/*********Map Clear���� ��� Score�� ��ȯ �� ���� ���� ����*************/
						if(stage == 3)
						{
							model.score -= ((model.getSecond()-1) * 10 ) + (model.getMinute() * 50 );
							model.setStopCheck(false); //Timer�� �����.
							model.setCoinStartCheck(true);	// coinGame������ �˷��ش�.
						}
						/************************************************************/
					}
				}
			}
		}
		/***********************************************/
		showPressImage(); // ���콺�� Ŭ���� �� ���� ����ȭ���� �׷��ش�.
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

	/****Mouse�� ������ �� ���� state�� �ٲپ� �ִ� Event******/
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
   			{// ù��° �޴��� ���
   				model.menu_state=1;
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   			}
   			else if((arg0.getX() > 250 && arg0.getY() >300+(100)
   	   				&& (arg0.getX() < 450) && arg0.getY() < 350+(100)))
   			{	// �ι�° �޴��� ���
   				model.menu_state=2;
   				
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   				
   			}
   			else if((arg0.getX() > 250 && arg0.getY() >300+(2*100)
   	   				&& (arg0.getX() < 450) && arg0.getY() < 350+(2*100)))
   			{// ����° �޴��� ���
   				model.menu_state=3;
   				if(movedcheck == 0)
   				{
   					new GameSound("black_turn.wav");
   					movedcheck=1;
   				}
   			}// �׹�° �޴��� ���
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