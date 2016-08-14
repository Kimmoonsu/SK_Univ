package kr.ac.skuniv.java.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.VerseModel;
import kr.ac.skuniv.java.view.GameBoard;
import kr.ac.skuniv.java.view.VerseBoard;

public class VerseController extends KeyAdapter implements KeyListener	{

	Model model = new Model();
	GameBoard gameBoard;
	VerseBoard verseBoard;
	VerseModel v_model;
	
	/***********멤버 변수******************/
	private int enterState = 1, v_enterState = 1;
	private int keyState = 1; 	// 첫번째 이미지와 두번째 이미지 클릭을 구분하기 위한 state
									//2= 꼬치 계속 그리기
									//3= 꼬치 안그리고 위에있는 아이템만 그려
	private int compareState = 1;	// 이미지 비교를 위한 state
	private int v_keyState = 1; 	// 첫번째 이미지와 두번째 이미지 클릭을 구분하기 위한 state
	private int v_compareState = 1;	// 이미지 비교를 위한 state
	private int firstRow, firstCol, secondRow, secondCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int firstImage, secondImage;	// 클릭한 이미지의 인덱스 값을 저장하는 변수
	private int v_firstRow, v_firstCol, v_secondRow, v_secondCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int v_firstImage, v_secondImage;	// 클릭한 이미지의 인덱스 값을 저장하는 변수
	private int mapRowSize, v_mapRowSize;	// map의 행의 size를 가지고 있는 변수
	private int mapColSize, v_mapColSize;// map의 행의 size를 가지고 있는 변수
	private int changeFirstRow;	// 첫번째 선택한 곳의 row값을 방향에 따라 변화시키는 변수
	private int changeFirstCol; // 첫번째 선택한 곳의 col값을 방향에 따라 변화시키는 변수
	private int v_changeFirstRow;	// 첫번째 선택한 곳의 row값을 방향에 따라 변화시키는 변수
	private int v_changeFirstCol; // 첫번째 선택한 곳의 col값을 방향에 따라 변화시키는 변수
	private boolean check = true;	// 같은 그림이 아무것도 없을 때 다시 첫번째 클릭으로 만들어 주는 변수
	private boolean v_check = true;	// 같은 그림이 아무것도 없을 때 다시 첫번째 클릭으로 만들어 주는 변수
	private int stage = 0, verseStage = 0;
	private int clearCheck1 = 1;	// map button의 image index를 check해주는 변수
	private int clearCheck2 = 0;	// 맵 전체 button이 제거되었는지 check해주는 변수
	private int v_clearCheck1 = 1;	// map button의 image index를 check해주는 변수
	private int v_clearCheck2 = 0;	// 맵 전체 button이 제거되었는지 check해주는 변수
	private int choiceRow, choiceCol, v_choiceRow , v_choiceCol;
	private int obtainItem[] = {0,0,0,0,0};	// button을 획득하면 배열의 값을 '1'로 바꾸고 더이상 획득하지 않게 된다.  
	private int countItem[] = {0,0,0,0,0};	// map에 남아있는 각 button의 개수(button이 다 delect되면 0으로 값이 바뀐다.)
	private int v_obtainItem[] = {0,0,0,0,0};	// button을 획득하면 배열의 값을 '1'로 바꾸고 더이상 획득하지 않게 된다.  
	private int v_countItem[] = {0,0,0,0,0};	// map에 남아있는 각 button의 개수(button이 다 delect되면 0으로 값이 바뀐다.)
	private int takeImg[] = new int[8];
	private int v_takeImg[] = new int[10];
	private int index = 0;
	private int v_index = 0;
	/***********************************************************************/
	
	/****** Stack클래스 객체 생성*********/
	Stack p1_pre = new Stack();
	Stack p2_pre = new Stack();
	/***************************/
	
	/*****생성자********/
	public VerseController(VerseBoard verseBoard, VerseModel v_model)
	{
		this.verseBoard = verseBoard;
		this.v_model = v_model;
	}
	/****************/
	
	/***************마우스 클릭 이벤트**********************/
	
	/******************keyEvent*********************/
	public void keyPressed(KeyEvent e) {
		
		enterState = v_model.getEnterState();
		v_enterState = v_model.getV_EnterState();
		choiceRow = v_model.getChoiceRow();
		choiceCol = v_model.getChoiceCol();
		v_choiceRow = v_model.getV_ChoiceRow();
		v_choiceCol = v_model.getV_ChoiceCol();
		firstRow = v_model.getFirstRow();
		firstCol = v_model.getFirstCol();
		v_firstRow = v_model.getV_FirstRow();
		v_firstCol = v_model.getV_FirstCol();
		mapRowSize = v_model.getMapRowSize();
		mapColSize = v_model.getMapColSize();
		v_mapRowSize = v_model.getV_MapRowSize();
		v_mapColSize = v_model.getV_MapColSize();
		stage = v_model.getStage();
		verseStage = v_model.getVerseStage();
		enterState = v_model.getEnterState();
		check = true; // 클릭할 때 마다 true로 바꾸어 준다.
		v_check = true;
		int keyCode = (int) e.getKeyCode();
		/***********Map Clear 되었을 경우****************/
		
		if (v_model.p1_takeGameStart == true) //꼬치 꽂을떄
		{
			
			switch (keyCode) {
			
			case KeyEvent.VK_UP: {
				if(choiceCol < 0)
					return;
				
				else if(choiceCol < 170)
				{
					v_model.setChoiceRow(0);
					v_model.setChoiceCol(0);
				}
				else
					v_model.setChoiceCol((choiceCol - 50));
				v_model.setEnterState(3);
				v_model.setV_EnterState(3);
				choiceCol = v_model.getChoiceCol();
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_DOWN: {
				if(choiceCol == 0)
				{
					v_model.setChoiceCol(180);
					v_model.setChoiceRow(300);
				}
				else if (choiceCol > 400)
					return;
				else
					v_model.setChoiceCol((choiceCol + 50));
				v_model.setEnterState(3);
				v_model.setV_EnterState(3);
				choiceCol = v_model.getChoiceCol();
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_LEFT: {
				v_model.setChoiceRow((choiceRow - 50));
				choiceRow = v_model.getChoiceRow();
				v_model.setEnterState(3);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_RIGHT: {
				v_model.setChoiceRow((choiceRow + 50));
				choiceRow = v_model.getChoiceRow();
				v_model.setEnterState(3);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_ENTER: {
				v_model.p1_count = 1;
				v_model.p2_count = 0;
				if(v_model.getkeyState()==2)
					v_model.setEnterState(2);
				v_model.setV_EnterState(3);
				v_model.setKeyState(2);
				
				if(v_model.p1_takeStage==3)
				{
					VerseFinishThread finishThread = new VerseFinishThread(v_model, verseBoard);
					finishThread.start();
				}
			}
			break;
			}
			c_keyReturn();
		} 
		/*************************************************/
		else if(v_model.p1_takeGameStart == false) 
		{
			switch (keyCode) {
			case KeyEvent.VK_UP: {
				v_model.setChoiceCol((choiceCol - 100));
				choiceCol = v_model.getChoiceCol();
				v_model.setEnterState(1);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_DOWN: {
				v_model.setChoiceCol((choiceCol + 100));
				choiceCol = v_model.getChoiceCol();
				v_model.setEnterState(1);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_LEFT: {
				v_model.setChoiceRow((choiceRow - 100));
				choiceRow = v_model.getChoiceRow();
				v_model.setEnterState(1);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_RIGHT: {
				v_model.setChoiceRow((choiceRow + 100));
				choiceRow = v_model.getChoiceRow();
				v_model.setEnterState(1);
				v_model.setV_EnterState(3);
				v_model.p1_count = 1;
				v_model.p2_count = 0;
			}
				break;
			case KeyEvent.VK_SHIFT: {
				p1_pop();
				v_model.setEnterState(1);
				v_model.setV_EnterState(3);
				break;
			}
			case KeyEvent.VK_ENTER: {
				v_model.p1_count = 1;
				v_model.setV_EnterState(3);
				if (keyState == 1) {
					p1_push();
					firstEnter();		
				} 
				else if (keyState == 0) {
					secondEnter();
				}
				
				if (compareState == 0) {
					compareImage();
					resetSameButtonState();
					clearMap();
					if (clearCheck2 == (mapRowSize * mapColSize)) // 맵에 모든 button이 제거되었을 경우
					{
						stage++; // stage증가(다음단계)
						v_model.setStage(stage); // stage값을 model에 알려준다.
						v_model.setChoiceRow(0);
						v_model.setChoiceCol(0);
						v_model.p1_takeGameStart = true;
						verseBoard.repaint();
					}
				}
				
			}
				break;
			}
			keyReturn();
		}
		/**********************************************/
		 
		/***********2player Key*********************/
		
		if (v_model.p2_takeGameStart == true)  
		{
			switch (keyCode) {
			
			case KeyEvent.VK_W: {
				if(v_choiceCol < 0)
					return;
				else if(v_choiceCol < 145)
				{
					v_model.setV_ChoiceRow(0);
					v_model.setV_ChoiceCol(0);
					
				}
				else
					v_model.setV_ChoiceCol((v_choiceCol - 50));
				v_choiceCol = v_model.getV_ChoiceCol();
				v_model.setV_EnterState(3);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_S: {
				if(v_choiceCol == 0)
				{
					v_model.setV_ChoiceCol(175);
					v_model.setV_ChoiceRow(300);
				}
				else if (v_choiceCol > 400)
					return;
				else
					v_model.setV_ChoiceCol((v_choiceCol + 50));
				v_choiceCol = v_model.getV_ChoiceCol();
				v_model.setV_EnterState(3);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_A: {
				v_model.setV_ChoiceRow((v_choiceRow - 50));
				v_choiceRow = v_model.getV_ChoiceRow();
				v_model.setV_EnterState(3);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_D: {
				v_model.setV_ChoiceRow((v_choiceRow + 50));
				v_choiceRow = v_model.getV_ChoiceRow();
				v_model.setV_EnterState(3);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_SPACE: {
				v_model.p1_count = 0;
				v_model.p2_count = 1;
				if(v_model.getV_KeyState()==2)
					v_model.setV_EnterState(2);
				v_model.setV_KeyState(2);
				v_model.setEnterState(3);
				if(v_model.p2_takeStage==3)
				{
					VerseFinishThread finishThread = new VerseFinishThread(v_model, verseBoard);
					finishThread.start();
				}
			}
				break;
			}
			V_c_keyReturn();
		}

		/**************************************************************/
		if (v_model.p2_takeGameStart == false) 
		{
			switch (keyCode) 
			{
			case KeyEvent.VK_W: {
				v_model.setV_ChoiceCol((v_choiceCol - 100));
				v_choiceCol = v_model.getV_ChoiceCol();
				v_model.setV_EnterState(1);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_S: {
				v_model.setV_ChoiceCol((v_choiceCol + 100));
				v_choiceCol = v_model.getV_ChoiceCol();
				v_model.setV_EnterState(1);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_A: {
				v_model.setV_ChoiceRow((v_choiceRow - 100));
				v_choiceRow = v_model.getV_ChoiceRow();
				v_model.setV_EnterState(1);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_D: {
				v_model.setV_ChoiceRow((v_choiceRow + 100));
				v_choiceRow = v_model.getV_ChoiceRow();
				v_model.setV_EnterState(1);
				v_model.setEnterState(3);
				v_model.p1_count = 0;
				v_model.p2_count = 1;
			}
				break;
			case KeyEvent.VK_R: {
				p2_pop();
				v_model.setEnterState(3);
				v_model.setV_EnterState(1);
				break;
			}
			case KeyEvent.VK_SPACE: {
				v_model.p1_count = 0;
				v_model.p2_count = 1;
				v_model.setEnterState(3);
				if (v_keyState == 1) 
				{
					p2_push();
					V_FirstEnter();
				} 
				else if (v_keyState == 0) {
					V_SecondEnter();
				}
				if (v_compareState == 0) {
					V_CompareImage();
					V_ResetSameButtonState();
					V_ClearMap();
					if (v_clearCheck2 == (v_mapRowSize * v_mapColSize)) // 맵에 모든 button이 제거되었을 경우
					{
						v_model.p2_takeGameStart = true;
						v_model.setV_ChoiceRow(0);
						v_model.setV_ChoiceCol(0);
						verseBoard.repaint();
					}
				}
			}
				break;
			}
			V_KeyReturn();
		}
		
		
		verseBoard.repaint();
	}

	/**********************************************************/
	
	/**************클릭 할 때마다 맵을 push 시켜주는 Method************/
	public void p1_push()	 
	{
		for(int x=0; x < mapRowSize; x++)
			for(int y=0; y < mapColSize; y++)
			 p1_pre.push(v_model.getMap(stage, x, y)); 
	}
	/*************************************************/
	
	/**************한 Step 전으로 돌아가주는 Method************/
	public void p1_pop()
	{
		for(int x=0; x < mapRowSize; x++){
			for(int y = 0; y < mapColSize; y++)
			{
				int pop = (Integer) p1_pre.pop();
				v_model.setMap(stage, mapRowSize-(x+1), mapColSize-(y+1), pop);
			}
		}
	}
	/*************************************************/
	
	/**************클릭 할 때마다 맵을 push 시켜주는 Method************/
	public void p2_push()	 
	{
		for(int x=0; x < mapRowSize; x++)
			for(int y=0; y < mapColSize; y++)
			 p2_pre.push(v_model.getVerseMap(verseStage, x, y)); 
	}
	/*************************************************/
	
	/**************한 Step 전으로 돌아가주는 Method************/
	public void p2_pop()
	{
		for(int x=0; x < mapRowSize; x++){
			for(int y = 0; y < mapColSize; y++)
			{
				int pop = (Integer) p2_pre.pop();
				v_model.setVerseMap(verseStage, mapRowSize-(x+1), mapColSize-(y+1), pop);
			}
		}
	}
	/*************************************************/
	
	/*************1Player Control Method*****************/
	
	/*****기본 play에서 map범위 초과시 키 제한하는 Method*******/
	public void keyReturn()	//키 제한
	{
		if(choiceRow > (mapRowSize-1)*100)
        {
        	v_model.setChoiceRow(0);
        }
        if (choiceCol > (mapColSize-1)*100)
        {
        	v_model.setChoiceCol(0);
        }
        if(choiceRow < 0 )
        {
        	v_model.setChoiceRow((mapRowSize-1)*100);	
        }
        if(choiceCol < 0 )
        {
        	v_model.setChoiceCol((mapColSize-1)*100);	
        }
        
	}
	/*******************************************/
	
	/*****꼬치 꽂기 play에서 map범위 초과시 키 제한하는 Method*******/
	public void c_keyReturn()
	{
		if(choiceRow >= (mapRowSize-1)*100)
        {
        	v_model.setChoiceRow(0);
        }
		
        if(choiceRow < 0 )
        {
        	v_model.setChoiceRow(350);	
        }
        
	}
	/**************************************************/
	
	/**********첫번째 Enter를 눌렀을 경우 State를 알려주는 Method*******/
	public void firstEnter()	// 첫번째 Enter를 눌렀을 경우
	{
		firstRow = (choiceCol / 100);
		firstCol = (choiceRow / 100);
		v_model.setFirstCol(firstCol);
		v_model.setFirstRow(firstRow);
		v_model.setFirstImage(v_model.getMap(stage, firstRow, firstCol));
		firstImage = v_model.getFirstImage();
		
		/*****만일 폭탄을 선택했을 경우 Thread가 작동되게 state 값을 바꾸어준다******/
		if(firstImage == 4)
		{
			v_model.setBombCheck(true);
			BombThread thread = new BombThread(v_model, verseBoard);
			thread.start();
		}
		/*******************************************************/
		
		changeFirstRow = firstRow;
		changeFirstCol = firstCol;
		E_W_S_N_Check();
		diagonalCheck();
		for (int i = 0; i < 8; i++) // 같은 그림이 있으면 check 를 false로 바꾸어 준다.
		{
			if (v_model.sameButtonCheck[i] == 0) {
				check = false;
			}	
		}
		v_model.setKeyState(0);
		keyState = v_model.getkeyState();
	    keyState = (check == false) ? 0 : 1; // 같은 그림이 없으면 다시 첫번 째클릭이라고 keyState = 0으로 해준다.
	    compareState = 1; // 첫번째 이미지를 클릭 완료했다는 것을 알려주는 state
	    v_model.setEnterState(0);
	}
	/*******************************************************/
	
	/**********첫번째 Enter를 눌렀을 경우 State를 알려주는 Method*******/
	public void secondEnter()	// 두번째 Enter를 클릭했을 경우
	{
		secondRow = (choiceCol / 100);
		secondCol = (choiceRow / 100);
		v_model.setSecondRow(secondRow);	// 선택한 이미지의 열을 반환
		v_model.setSecondCol(secondCol);	// 선택한 이미지의 행을 반환
		v_model.setKeyState(1);
		v_model.setEnterState(1);
		keyState = v_model.getkeyState();
		compareState = 0;

	}
	/********************************************************/
	
	/**********첫번 째 선택과 두번 째 선택을 비교해주는 Method**********/
	public void compareImage()	// 선택한 이미지를 비교하는 메소드
	{
		
		/*************첫번째 클릭값의 인덱스와, 두번 째 클릭값의 인덱스를 firstImage와 secondImage변수에 저장************/
		v_model.setFirstImage(v_model.getMap(stage, firstRow, firstCol)); 
		v_model.setSecondImage(v_model.getMap(stage, secondRow, secondCol));
		firstImage = v_model.getFirstImage();
		secondImage = v_model.getSecondImage();
		/***************************************************************************/
	
		if (firstImage == secondImage) {
			if (firstRow == secondRow && firstCol == secondCol)// 첫번째 클릭한 곳과 두번째 클릭한 곳이 같을 때 return(예외처리)
				return;

			/*************** East button delete *************/
			if (v_model.sameButtonCheck[0] == 0 && firstRow == secondRow
					&& firstCol < secondCol && secondCol <= v_model.findCol[0]) 
			{
				for (int i = firstCol; i <= secondCol; i++) 
				{
					firstImage = 0;
					v_model.setMap(stage, firstRow, i, firstImage);
				}
			}
			/**********************************************/

			/*************** West button delete *************/
			if (v_model.sameButtonCheck[1] == 0 && firstRow == secondRow
					&& firstCol > secondCol && secondCol >= v_model.findCol[1]) 
			{
//				takeImg[index] = firstImage;
//				v_model.setTakeImg(takeImg);
//				index++;
				for (int i = firstCol; i >= secondCol; i--) {
					firstImage = 0;
					v_model.setMap(stage, firstRow, i, firstImage);
				}
			}
			/**********************************************/

			/*************** South button delete *************/
			if (v_model.sameButtonCheck[2] == 0 && firstCol == secondCol
					&& firstRow < secondRow && secondRow <= v_model.findRow[2]) 
			{
				for (int i = firstRow; i <= secondRow; i++) {
					firstImage = 0;
					v_model.setMap(stage, i, firstCol, firstImage);
				}
			}
			/**********************************************/

			/*************** North button delete *************/
			if (v_model.sameButtonCheck[3] == 0 && firstCol == secondCol
					&& firstRow > secondRow && secondRow >= v_model.findRow[3]) 
			{
				for (int i = firstRow; i >= secondRow; i--) {
					firstImage = 0;
					v_model.setMap(stage, i, firstCol, firstImage);
				}
			}
			/**********************************************/

			/*************** NorthWest button delete *************/
			for (int k = 0; k <= firstRow; k++) {
				if (v_model.sameButtonCheck[4] == 0
						&& (firstRow - k) == secondRow
						&& (firstCol - k) == secondCol && secondRow >= v_model.findRow[4]) 
				{
					int j = 0;
					for (int i = firstRow; i >= secondRow; i--) {

						firstImage = 0;
						v_model.setMap(stage, i, (firstCol - j), firstImage);
						j++;
					}

				}
			}
			/**********************************************/

			/*************** NorthEast button delete *************/
			for (int k = 0; k <= firstRow; k++) {

				if (v_model.sameButtonCheck[5] == 0
						&& (firstRow - k) == secondRow
						&& (firstCol + k) == secondCol && secondRow >= v_model.findRow[5] ) 
				{
					int j = 0;
					for (int i = firstRow; i >= secondRow; i--) {
						firstImage = 0;
						v_model.setMap(stage, i, firstCol + j, firstImage);
						j++;
					}
				}
			}
			/**********************************************/

			/*************** SouthEast button delete *************/
			for (int k = 0; k <= secondRow; k++) {
				if (v_model.sameButtonCheck[6] == 0
						&& (firstRow + k) == secondRow
						&& (firstCol + k) == secondCol && secondRow <= v_model.findRow[6]) 
				{
					int j = 0;
					for (int i = firstRow; i <= secondRow; i++) {
						firstImage = 0;
						v_model.setMap(stage, i, firstCol + j, firstImage);
						j++;
					}

				}
			}
			/**********************************************/

			/*************** SouthWest button delete *************/
			for (int k = 0; k <= secondRow; k++) {
				if (v_model.sameButtonCheck[7] == 0
						&& (firstRow + k) == secondRow
						&& (firstCol - k) == secondCol && secondRow <= v_model.findRow[7]) {
					int j = 0;
					for (int i = firstRow; i <= secondRow; i++) {
						firstImage = 0;
						v_model.setMap(stage, i, (firstCol - j), firstImage);
						j++;
					}
				}
			}
			/**********************************************/
		}
		
		/*******폭탄으로 바꿔주기*************/
		if(v_model.count == 0 && v_model.getMap(stage, 1, 1) == 0)
		{
			v_model.setMap(stage, 1, 1, 4);
		}
		/**************************************/
		
	}
	/*****************************************************/
	
	/**********Map에 이미지가 다 삭제되었는 지 Check해주는 Method*******/
	public void clearMap()	// map Clear Check Method
	{
		for(int i = 0; i < mapRowSize; i++)
		{
			for(int j=0; j<mapColSize; j++)
			{
				if(v_model.getMap(stage, i, j)==1)
					countItem[1]+=1;
				else if(v_model.getMap(stage, i, j)==2)
					countItem[2]+=1;
				else if(v_model.getMap(stage, i, j)==3)
					countItem[3]+=1;
						
			}
		}
		
		if( countItem[1]==0 && obtainItem[1]==0){
			takeImg[index] = 1;
			v_model.setTakeImg(takeImg);
			index++;
			obtainItem[1]=1;
		
		
		}
		else if(countItem[2]==0 && obtainItem[2]==0){
			takeImg[index] = 2;
			v_model.setTakeImg(takeImg);
			index++;
			obtainItem[2]=1;
		}
		else if(countItem[3]==0 && obtainItem[3]==0){
			takeImg[index] = 3;
			v_model.setTakeImg(takeImg);
			index++;
			obtainItem[3]=1;
		}
		
		/**********게임 도중 button의 개수가 달라질 때 마다 초기화*******/
		for(int i=0;i<5;i++){
			countItem[i]=0;
		}
		/****************************************************************/
		
		clearCheck2 = 0;
		
		for(int i = 0; i < mapRowSize; i++)
		{
			for(int j=0; j<mapColSize; j++)
			{
				clearCheck1 = v_model.getMap(stage, i, j);
				if( clearCheck1 == 0)	// 버튼이 삭제되었을 경우
				{
					clearCheck2++; // 삭제된 버튼의 갯수 증가
				}
				clearCheck1 = 1;	// 버튼삭제 여부를 묻기위해 초기화
			}
		}
	}
	/*******************************************************/
	
	/*********방향 별 같은 그림이 있는지 check해주는 state를 초기화 해주는 Method******/
	public void resetSameButtonState()// 방향별 state값을 초기화 해주는 메소드
	{
		for (int i = 0; i < 8; i++) {
			v_model.sameButtonCheck[i] = 1;		
		}
	}
	/*************************************************************/
	
	/*******동서남북에 같은 그림이 있는 지 Check해주는 Method********************/
	public void E_W_S_N_Check()	//	동서남북 체크
	{
		if (firstRow != 0 )	// 북쪽 체크
		{
			for(int i=firstRow; i > 0; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{		
				i--;
				if(firstImage == v_model.getMap(stage, i, firstCol) || v_model.getMap(stage, i, firstCol) == 0 )
				{
					if(v_model.getMap(stage, i, firstCol) != 0)
					{
						v_model.findRow[3] = i;
						v_model.findCol[3] = firstCol;
						v_model.sameButtonCheck[3] = 0;
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
				if(firstImage == v_model.getMap(stage, (i), firstCol) || v_model.getMap(stage, (i), firstCol) == 0)
				{
					if(v_model.getMap(stage, (i), firstCol) != 0)
					{
						v_model.findRow[2] = i;
						v_model.findCol[2] = firstCol;
						v_model.sameButtonCheck[2] = 0;
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
				if ( firstImage == v_model.getMap(stage, firstRow, i) || v_model.getMap(stage, firstRow, i) == 0)
				{
					if( v_model.getMap(stage, firstRow, i) != 0 )
					{
						v_model.findRow[1] = firstRow;
						v_model.findCol[1] = i;
						v_model.sameButtonCheck[1] = 0;
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
				if(firstImage == v_model.getMap(stage, firstRow,i) || v_model.getMap(stage, firstRow,i) == 0 ) 
				{
					if( v_model.getMap(stage, firstRow,i) != 0)
					{
						v_model.findRow[0] = firstRow;
						v_model.findCol[0] = i;
						v_model.sameButtonCheck[0] = 0;
					}
					
				}
				else 
				{
					i = mapColSize;	// 다르면 더이상 비교 안해
				}	
			}
		}
	}
	/**************************************************************/
	
	/*******대각선에 같은 그림이 있는 지 Check해주는 Method********************/
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
					if ( firstImage == v_model.getMap(stage, changeFirstRow, i) || v_model.getMap(stage, changeFirstRow, i) == 0 )
					{
						if( v_model.getMap(stage, changeFirstRow, i) != 0)
						{
							v_model.findRow[4] = changeFirstRow;
							v_model.findCol[4] = i;	
							v_model.sameButtonCheck[4] = 0;
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
					if( firstImage == v_model.getMap(stage, i, changeFirstCol) || v_model.getMap(stage, i, changeFirstCol) == 0 )
					{
						if(v_model.getMap(stage, i, changeFirstCol) != 0)
						{
							v_model.findRow[4] = i;
							v_model.findCol[4] = changeFirstCol;
							v_model.sameButtonCheck[4] = 0;	
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
					
					if ( firstImage == v_model.getMap(stage, i, changeFirstCol) || v_model.getMap(stage, i, changeFirstCol) == 0 )
					{
						if(v_model.getMap(stage, i, changeFirstCol) != 0 )
						{
							v_model.findRow[6] = i;
							v_model.findCol[6] = changeFirstCol;
							v_model.sameButtonCheck[6] = 0;
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
					if( firstImage == v_model.getMap(stage, changeFirstRow, i) || v_model.getMap(stage, changeFirstRow, i) == 0 )
					{
						if( v_model.getMap(stage, changeFirstRow, i) != 0 )
						{
							v_model.findRow[6] = changeFirstRow;
							v_model.findCol[6] = i;
							v_model.sameButtonCheck[6] = 0;
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
				if (firstImage == v_model.getMap(stage, i, changeFirstCol) || v_model.getMap(stage, i, changeFirstCol) == 0 ) 
				{
					if(v_model.getMap(stage, i, changeFirstCol) != 0 )
					{
						
						v_model.findRow[5] = i;
						v_model.findCol[5] = changeFirstCol;
						v_model.sameButtonCheck[5] = 0;
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
				if (firstImage == v_model.getMap(stage, changeFirstRow, i) || v_model.getMap(stage, changeFirstRow, i) == 0) 
				{
					if (v_model.getMap(stage, changeFirstRow, i) != 0 )
					{
						v_model.findRow[7] = changeFirstRow;
						v_model.findCol[7] = i;	
						v_model.sameButtonCheck[7] = 0;
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
	/*******************************************************/
	
	/**************************************************************************/
	
	
	/*************2Player Control Method*****************/
	
	/*****기본 play에서 map범위 초과시 키 제한하는 Method*******/
	public void V_KeyReturn()	//키 제한
	{
		if(v_choiceRow > 400)
        {
        	v_model.setV_ChoiceRow(0);
        }
        if (v_choiceCol > 400)
        {
        	v_model.setV_ChoiceCol(0);
        }
        if(v_choiceRow < 0 )
        {
        	v_model.setV_ChoiceRow(400);	
        }
        if(v_choiceCol < 0 )
        {
        	v_model.setV_ChoiceCol(400);	
        }
        
	}
	/*******************************************************/

	/*****꼬치 꽂기 play에서 map범위 초과시 키 제한하는 Method*******/
	public void V_c_keyReturn()
	{
		if(v_choiceRow > 350)
        {
        	v_model.setV_ChoiceRow(0);
        }
		
        if(v_choiceRow < 0 )
        {
        	v_model.setV_ChoiceRow(350);	
        }
        
	}
	/*******************************************************/
	
	/**********첫번째 Enter를 눌렀을 경우 State를 알려주는 Method*******/
	public void V_FirstEnter()	// 첫번째 Enter를 눌렀을 경우
	{
		v_firstRow = v_choiceCol / 100;
		v_firstCol = v_choiceRow / 100;
		v_model.setV_FirstCol(v_firstCol);
		v_model.setV_FirstRow(v_firstRow);
		v_model.setV_FirstImage(v_model.getVerseMap(verseStage, v_firstRow,	v_firstCol));
		v_firstImage = v_model.getV_FirstImage();
		v_changeFirstRow = v_firstRow;
		v_changeFirstCol = v_firstCol;
		V_E_W_S_N_Check();
		V_DiagonalCheck();
		for (int i = 0; i < 8; i++) // 같은 그림이 있으면 check 를 false로 바꾸어 준다.
		{
			if (v_model.v_sameButtonCheck[i] == 0) {
				v_check = false;
			}	
		}
		v_model.setV_KeyState(0);
		v_keyState = v_model.getV_KeyState();
		v_keyState = (v_check == false) ? 0 : 1; // 같은 그림이 없으면 다시 첫번 째클릭이라고 keyState = 0으로 해준다.
		v_compareState = 1; // 첫번째 이미지를 클릭 완료했다는 것을 알려주는 state
	    v_model.setV_EnterState(0);
	}
	/*******************************************************/
	
	/**********두번째 Enter를 눌렀을 경우 State를 알려주는 Method*******/
	public void V_SecondEnter()	// 두번째 Enter를 클릭했을 경우
	{
		v_secondRow = (v_choiceCol / 100);
		v_secondCol = (v_choiceRow / 100);
		v_model.setV_SecondRow(v_secondRow);	// 선택한 이미지의 열을 반환
		v_model.setV_SecondCol(v_secondCol);	// 선택한 이미지의 행을 반환
		v_model.setV_KeyState(1);
		v_model.setV_EnterState(1);
		v_keyState = v_model.getV_KeyState();
		v_compareState = 0;
	}
	/*******************************************************/
	
	/**********첫번 째 선택과 두번 째 선택을 비교해주는 Method**********/
	public void V_CompareImage()	// 선택한 이미지를 비교하는 메소드
	{
		
		/*************첫번째 클릭값의 인덱스와, 두번 째 클릭값의 인덱스를 firstImage와 secondImage변수에 저장************/
		v_model.setV_FirstImage(v_model.getVerseMap(verseStage, v_firstRow, v_firstCol)); 
		v_model.setV_SecondImage(v_model.getVerseMap(verseStage, v_secondRow, v_secondCol));
		v_firstImage = v_model.getV_FirstImage();
		v_secondImage = v_model.getV_SecondImage();
		/***************************************************************************/
	
		if (v_firstImage == v_secondImage) {
			if (v_firstRow == v_secondRow && v_firstCol == v_secondCol)// 첫번째 클릭한 곳과 두번째 클릭한 곳이 같을 때 return(예외처리)
				return;

			/*************** East button delete *************/
			if (v_model.v_sameButtonCheck[0] == 0 && v_firstRow == v_secondRow
					&& v_firstCol < v_secondCol && v_secondCol <= v_model.v_findCol[0]) {
				
				for (int i = v_firstCol; i <= v_secondCol; i++) {
					v_firstImage = 0;
					v_model.setVerseMap(verseStage, v_firstRow, i, v_firstImage);
				}
			}
			/**********************************************/

			/*************** West button delete *************/
			if (v_model.v_sameButtonCheck[1] == 0 && v_firstRow == v_secondRow
					&& v_firstCol > v_secondCol && v_secondCol >= v_model.v_findCol[1]) {
				
				for (int i = v_firstCol; i >= v_secondCol; i--) {
					v_firstImage = 0;
					v_model.setVerseMap(verseStage, v_firstRow, i, v_firstImage);
				}
			}
			/**********************************************/

			/*************** South button delete *************/
			if (v_model.v_sameButtonCheck[2] == 0 && v_firstCol == v_secondCol
					&& v_firstRow < v_secondRow && v_secondRow <= v_model.v_findRow[2]) {
				
				for (int i = v_firstRow; i <= v_secondRow; i++) {
					v_firstImage = 0;
					v_model.setVerseMap(verseStage, i, v_firstCol, v_firstImage);
				}
			}
			/**********************************************/

			/*************** North button delete *************/
			if (v_model.v_sameButtonCheck[3] == 0 && v_firstCol == v_secondCol
					&& v_firstRow > v_secondRow && v_secondRow >= v_model.v_findRow[3]) {
				
				for (int i = v_firstRow; i >= v_secondRow; i--) {
					v_firstImage = 0;
					v_model.setVerseMap(verseStage, i, v_firstCol, v_firstImage);
				}
			}
			/**********************************************/

			/*************** NorthWest button delete *************/
			for (int k = 0; k <= v_firstRow; k++) {
				if (v_model.v_sameButtonCheck[4] == 0
						&& (v_firstRow - k) == v_secondRow
						&& (v_firstCol - k) == v_secondCol && v_secondRow >= v_model.v_findRow[4]) {
					
					int j = 0;
					for (int i = v_firstRow; i >= v_secondRow; i--) {
						
						v_firstImage = 0;
						v_model.setVerseMap(verseStage, i, (v_firstCol - j), v_firstImage);
						j++;
					}

				}
			}
			/**********************************************/

			/*************** NorthEast button delete *************/
			for (int k = 0; k <= v_firstRow; k++) {

				if (v_model.v_sameButtonCheck[5] == 0
						&& (v_firstRow - k) == v_secondRow
						&& (v_firstCol + k) == v_secondCol && v_secondRow >= v_model.v_findRow[5] ) {
					
					int j = 0;
					for (int i = v_firstRow; i >= v_secondRow; i--) {
						v_firstImage = 0;
						v_model.setVerseMap(verseStage, i, v_firstCol + j, v_firstImage);
						j++;
					}
				}
			}
			/**********************************************/

			/*************** SouthEast button delete *************/
			for (int k = 0; k <= v_secondRow; k++) {
				if (v_model.v_sameButtonCheck[6] == 0
						&& (v_firstRow + k) == v_secondRow
						&& (v_firstCol + k) == v_secondCol && v_secondRow <= v_model.v_findRow[6]) {
					
					int j = 0;
					for (int i = v_firstRow; i <= v_secondRow; i++) {
						v_firstImage = 0;
						v_model.setVerseMap(verseStage, i, v_firstCol + j, v_firstImage);
						j++;
					}

				}
			}
			/**********************************************/

			/*************** SouthWest button delete *************/
			for (int k = 0; k <= v_secondRow; k++) {
				if (v_model.v_sameButtonCheck[7] == 0
						&& (v_firstRow + k) == v_secondRow
						&& (v_firstCol - k) == v_secondCol && v_secondRow <= v_model.v_findRow[7]) {
					
					int j = 0;
					for (int i = v_firstRow; i <= v_secondRow; i++) {
						v_firstImage = 0;
						v_model.setVerseMap(verseStage, i, (v_firstCol - j), v_firstImage);
						j++;
					}
				}
			}
			/**********************************************/
		}
	
	}
	/*******************************************************/
	
	/**********Map에 이미지가 다 삭제되었는 지 Check해주는 Method*******/
	public void V_ClearMap()	// map Clear Check Method
	{
		for(int i = 0; i < v_mapRowSize; i++)
		{
			for(int j = 0; j < v_mapColSize; j++)
			{
				if(v_model.getVerseMap(verseStage, i, j)==1)
					v_countItem[1]+=1;
				else if(v_model.getVerseMap(verseStage, i, j)==2)
					v_countItem[2]+=1;
				else if(v_model.getVerseMap(verseStage, i, j)==3)
					v_countItem[3]+=1;
						
			}
		}
		
		if( v_countItem[1]==0 && v_obtainItem[1]==0){
			v_takeImg[v_index] = 1;
			v_model.setV_TakeImg(v_takeImg);
			v_index++;
			v_obtainItem[1]=1;
		
		
		}
		else if(v_countItem[2]==0 && v_obtainItem[2]==0){
			v_takeImg[v_index] = 2;
			v_model.setV_TakeImg(v_takeImg);
			v_index++;
			v_obtainItem[2]=1;
		}
		else if(v_countItem[3]==0 && v_obtainItem[3]==0){
			v_takeImg[v_index] = 3;
			v_model.setV_TakeImg(v_takeImg);
			v_index++;
			v_obtainItem[3]=1;
		}
		
		/**********게임 도중 button의 개수가 달라질 때 마다 초기화*******/
		for(int i=0;i<5;i++){
			v_countItem[i]=0;
		}
		/****************************************************************/
		
		v_clearCheck2 = 0;
		for(int i = 0; i < v_mapRowSize; i++)
		{
			for(int j=0; j < v_mapColSize; j++)
			{
				v_clearCheck1 = v_model.getVerseMap(verseStage, i, j);
				if( v_clearCheck1 == 0)	// 버튼이 삭제되었을 경우
				{
					v_clearCheck2++; // 삭제된 버튼의 갯수 증가
				}
				v_clearCheck1 = 1;	// 버튼삭제 여부를 묻기위해 초기화
			}
		}
	}
	/*******************************************************/
	
	/*********방향 별 같은 그림이 있는지 check해주는 state를 초기화 해주는 Method******/
	public void V_ResetSameButtonState()// 방향별 state값을 초기화 해주는 메소드
	{
		for (int i = 0; i < 8; i++) {
			v_model.v_sameButtonCheck[i] = 1;		
		}
	}
	/*******************************************************/
	
	/*******동서남북에 같은 그림이 있는 지 Check해주는 Method********************/
	public void V_E_W_S_N_Check()	//	동서남북 체크
	{
		if (v_firstRow != 0 )	// 북쪽 체크
		{
			for(int i=v_firstRow; i > 0; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{		
				i--;
				if(v_firstImage == v_model.getVerseMap(verseStage, i, v_firstCol) || v_model.getVerseMap(verseStage, i, v_firstCol) == 0 )
				{
					if(v_model.getVerseMap(verseStage, i, v_firstCol) != 0)
					{
						v_model.v_findRow[3] = i;
						v_model.v_findCol[3] = v_firstCol;
						v_model.v_sameButtonCheck[3] = 0;
					}
				}
				else 
				{
					i = 0;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (v_firstRow != (v_mapRowSize-1))	// South Check
		{
			for(int i=v_firstRow; i < v_mapRowSize-1; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{
				i++;
				if(v_firstImage == v_model.getVerseMap(verseStage, (i), v_firstCol) || v_model.getVerseMap(verseStage, (i), v_firstCol) == 0)
				{
					if(v_model.getVerseMap(verseStage, (i), v_firstCol) != 0)
					{
						v_model.v_findRow[2] = i;
						v_model.v_findCol[2] = v_firstCol;
						v_model.v_sameButtonCheck[2] = 0;
					}
				}
				else 
				{
					i = v_mapRowSize;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (v_firstCol != 0 )	// West Check
		{
			for( int i=v_firstCol; i > 0; )	// 입력한 곳의 좌표 북쪽으로 탐색하기 위해 firstRow만큼 돌게 하였다.
			{
				i--;
				if ( v_firstImage == v_model.getVerseMap(verseStage, v_firstRow, i) || v_model.getVerseMap(verseStage, v_firstRow, i) == 0)
				{
					if( v_model.getVerseMap(verseStage, v_firstRow, i) != 0 )
					{
						v_model.v_findRow[1] = v_firstRow;
						v_model.v_findCol[1] = i;
						v_model.v_sameButtonCheck[1] = 0;
					}
				}
				else 
				{
					i = 0;	// 다르면 더이상 비교 안해
				}	
			}
		}
		if (v_firstCol != v_mapColSize-1 )	// East Check
		{
			for(int i=v_firstCol; i < v_mapColSize-1; )	// 
			{
				i++;
				if(v_firstImage == v_model.getVerseMap(verseStage, v_firstRow,i) || v_model.getVerseMap(verseStage, v_firstRow,i) == 0 ) 
				{
					if( v_model.getVerseMap(verseStage, v_firstRow,i) != 0)
					{
						v_model.v_findRow[0] = v_firstRow;
						v_model.v_findCol[0] = i;
						v_model.v_sameButtonCheck[0] = 0;
					}
					
				}
				else 
				{
					i = v_mapColSize;	// 다르면 더이상 비교 안해
				}	
			}
		}
	}
	/*******************************************************/
	
	/*******대각선에 같은 그림이 있는 지 Check해주는 Method********************/
	public void V_DiagonalCheck()	// 대각선 체크
	{
		
		/**************NorthWest Check****************/
		if ( v_firstRow != 0 && v_firstCol != 0 )
		{
			if ( v_firstRow >= v_firstCol )
			{
				for (int i = v_firstCol; i > 0; )
				{
					i--;
					v_changeFirstRow--;
					if ( v_firstImage == v_model.getVerseMap(verseStage, v_changeFirstRow, i) || v_model.getVerseMap(verseStage, v_changeFirstRow, i) == 0 )
					{
						if( v_model.getVerseMap(verseStage, v_changeFirstRow, i) != 0)
						{
							v_model.v_findRow[4] = v_changeFirstRow;
							v_model.v_findCol[4] = i;	
							v_model.v_sameButtonCheck[4] = 0;
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
				
				for ( int i = v_firstRow; i > 0; )
				{
					
					i--; 
					v_changeFirstCol--;
					if( v_firstImage == v_model.getVerseMap(verseStage, i, v_changeFirstCol) || v_model.getVerseMap(verseStage, i, v_changeFirstCol) == 0 )
					{
						if(v_model.getVerseMap(verseStage, i, v_changeFirstCol) != 0)
						{
							v_model.v_findRow[4] = i;
							v_model.v_findCol[4] = v_changeFirstCol;
							v_model.v_sameButtonCheck[4] = 0;	
						}
						
					}
					else
					{
						i = 0;
					}
						
				}
			}
			v_changeFirstRow = v_firstRow;
			v_changeFirstCol = v_firstCol;
		}
		/***************************************************/
		
		/****************SouthEast Check**************************/
		if ( v_firstRow != v_mapRowSize-1 && v_firstCol != v_mapColSize-1 )
		{
			if ( v_firstRow >= v_firstCol )
			{
			
				for (int i = v_firstRow+1; i < v_mapRowSize; )
				{
					v_changeFirstCol++;
					
					if ( v_firstImage == v_model.getVerseMap(verseStage, i, v_changeFirstCol) || v_model.getVerseMap(verseStage, i, v_changeFirstCol) == 0 )
					{
						if(v_model.getVerseMap(verseStage, i, v_changeFirstCol) != 0 )
						{
							v_model.v_findRow[6] = i;
							v_model.v_findCol[6] = v_changeFirstCol;
							v_model.v_sameButtonCheck[6] = 0;
						}
										
					}
					else
					{
						i = v_mapRowSize;
					}
					i++;
				}
			}
			else
			{
				
				for ( int i = v_firstCol+1; i < v_mapColSize;)
				{
					v_changeFirstRow++;
					if( v_firstImage == v_model.getVerseMap(verseStage, v_changeFirstRow, i) || v_model.getVerseMap(verseStage, v_changeFirstRow, i) == 0 )
					{
						if( v_model.getVerseMap(verseStage, v_changeFirstRow, i) != 0 )
						{
							v_model.v_findRow[6] = v_changeFirstRow;
							v_model.v_findCol[6] = i;
							v_model.v_sameButtonCheck[6] = 0;
						}
							
					}
					else
					{
						i = v_mapColSize;
					}
					i++;	
				}
			}
			v_changeFirstRow = v_firstRow;
			v_changeFirstCol = v_firstCol;
		}
		/***************************************************/
		
		
		/****************NorthEast Check**************************/
		if (v_firstRow != 0 && v_firstCol != v_mapColSize-1) {
			for (int i = v_firstRow-1; i > 0 || v_changeFirstCol < v_mapColSize-1;) 
			{
				v_changeFirstCol++; 
				if (v_firstImage == v_model.getVerseMap(verseStage, i, v_changeFirstCol) || v_model.getVerseMap(verseStage, i, v_changeFirstCol) == 0 ) 
				{
					if(v_model.getVerseMap(verseStage, i, v_changeFirstCol) != 0 )
					{
						v_model.v_findRow[5] = i;
						v_model.v_findCol[5] = v_changeFirstCol;
						v_model.v_sameButtonCheck[5] = 0;
					}
					
				}
				else 
				{
					i = 0;
					v_changeFirstCol = v_mapColSize;
				}
				if( i == 0 )
				{
					v_changeFirstCol = v_mapColSize;
				}
				if ( v_changeFirstCol == v_mapColSize-1 )
				{
					i = 0;
				}
				i--;
			}
			
		}
		v_changeFirstRow = v_firstRow;
		v_changeFirstCol = v_firstCol;
		/***************************************************/
		
	
		/****************SouthWest Check**************************/
		if (v_firstRow != v_mapRowSize-1 && v_firstCol != 0 ) {			
			for (int i = v_firstCol-1; i > 0 || v_changeFirstRow < v_mapRowSize-1;) 
			{
				v_changeFirstRow++; 
				if (v_firstImage == v_model.getVerseMap(verseStage, v_changeFirstRow, i) || v_model.getVerseMap(verseStage, v_changeFirstRow, i) == 0) 
				{
					if (v_model.getVerseMap(verseStage, v_changeFirstRow, i) != 0 )
					{
						v_model.v_findRow[7] = v_changeFirstRow;
						v_model.v_findCol[7] = i;	
						v_model.v_sameButtonCheck[7] = 0;
					}
				}
				else 
				{
					i = 0;
					v_changeFirstRow = v_mapRowSize;
				}
				if( i == 0 )
				{
					v_changeFirstRow = v_mapRowSize;
				}
				if ( v_changeFirstRow == v_mapRowSize-1 )
				{
					i = 0;
				}
				i--;
			}
			
		}
		v_changeFirstRow = v_firstRow;
		v_changeFirstCol = v_firstCol;
		/***************************************************/
		
	}
	/*******************************************************/
	
	/**************************************************************************/
	
	
	/*******************************************************/
	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent e) {
        }
	}

	/************************************************/

