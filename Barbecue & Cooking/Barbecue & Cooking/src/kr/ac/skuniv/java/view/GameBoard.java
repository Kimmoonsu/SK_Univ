package kr.ac.skuniv.java.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.skuniv.java.controller.SnowThread;
import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;

public class GameBoard extends JPanel {
	public final static int WIDTH = 100; // 단추 아이콘의 넓이
	public final static int HEIGHT = 100; // 단추 아이콘의 높이

	Toolkit toolkit = getToolkit(); // 이미지를 그리기 위해 Toolkit Class사용
	Model model; // Model Class의 객체 이름을 model이라 지정
	JLabel timeLabel;
	Image[] image; // 단추 이미지 삽입을 위해 Image Class를 배열로 선언
	Image main; // 배경 이미지 삽입을 위해 Image Class 선언
	Image[] coinImg;
	Image coin1;
	Image coin2;
	Image coin3;
	Image coin4;

	/****눈내리기 변수***/
	private int x_randomSnow[] = new int[1000]; 
	private int y_randomSnow[] = new int[1000];
	/***************/
	
	/******멤버변수********/
	private int mapRowSize ;	// map의 행의 size를 가지고 있는 변수
	private int mapColSize ;// map의 행의 size를 가지고 있는 변수
	private int mouseState;
	private int stage;
	private int count=0;
	private int firstRow, firstCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int c_firstImg[] = new int[6];
	private int c_secondImg [] = new int[6];
	private int c_firstRow[] = new int[6];
	private int c_firstCol[] = new int[6];
	private int c_secondRow[] = new int[6];
	private int c_secondCol[] = new int[6];
	/**********************/
	
	
	/********생성자************/
	public GameBoard(Model model, JLabel timeLabel) {
		this.model = model;
		this.timeLabel = timeLabel;
		this.image = model.getButtonImg();
		this.coinImg = model.getCoinImg();
	}
	/**********************/
	
	/****눈을 계속하여 생성해주는 Method****/
	public void snowDraw()
	{
		if(model.snowCount[0] == 1)
		{
			for(int i = 0; i< 30; i++)
			{
					x_randomSnow[i] = model.X_RandomSnow();
					y_randomSnow[i] = model.Y_RandomSnow();
			}	
		}
		if(model.snowCount[1] ==1)
		{
			for(int i = 30; i< 60; i++)
			{
					x_randomSnow[i] = model.X_RandomSnow();
					y_randomSnow[i] = model.Y_RandomSnow();
			}
		}
		if(model.snowCount[2] ==1)
		{
			for(int i = 60; i< 100; i++)
			{
					x_randomSnow[i] = model.X_RandomSnow();
					y_randomSnow[i] = model.Y_RandomSnow();
			}
		}
		if(model.snowCount[3] == 1)
		{
			for(int i = 100; i< 130; i++)
			{
					x_randomSnow[i] = model.X_RandomSnow();
					y_randomSnow[i] = model.Y_RandomSnow();
			}
		}
	}
	/******************************/
	
	
	/*****이미지를 그려주는 Method*******/
	public void paint(Graphics g) {
		
		/*********Menu 화면 그리기************/
		
		//Menu화면 일 경우
		if(model.menuState == MENU.MENU)
		{
			snowDraw();
			g.clearRect(0, 0, getWidth(), getHeight());
			
			if(model.menu_state==0)
			{
				main = toolkit.getImage("image/Menu.jpg");
				model.menu1 = toolkit.getImage("image/MainMenu1.png");
				model.menu2 = toolkit.getImage("image/MainMenu2.png");
				model.menu3 = toolkit.getImage("image/MainMenu3.png");
				model.menu4 = toolkit.getImage("image/MainMenu4.png");
			}
			if(model.menu_state == 1)
			{
				model.menu1 = toolkit.getImage("image/MainMenu_1.png");
			}
			if(model.menu_state == 2)
			{
				model.menu2 = toolkit.getImage("image/MainMenu_2.png");
			}
			if(model.menu_state == 3)
			{
				model.menu3 = toolkit.getImage("image/MainMenu_3.png");
			}
			if(model.menu_state == 4)
			{
				model.menu4 = toolkit.getImage("image/MainMenu_4.png");
			}
			g.drawImage(main, 0, 0, 700, 1000, this);
			g.drawImage(model.menu1, 250, 300, 200, 50, this);
			g.drawImage(model.menu2, 250, 400, 200, 50, this);
			g.drawImage(model.menu3, 250, 500, 200, 50, this);
			g.drawImage(model.menu4, 250, 600, 200, 50, this);
			//if(model.menu_state==0)
				
				
				/******눈을 그려준다.******/
			for(int i = 0; i<30; i++)
				g.drawImage(toolkit.getImage("image/눈.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[0]), 55, 55, this);
			for(int i = 30; i<60; i++)
				g.drawImage(toolkit.getImage("image/부탁.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[1]), 55, 55, this);
			for(int i = 60; i<100; i++)
				g.drawImage(toolkit.getImage("image/눈.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[2]), 55, 55, this);
			for(int i = 100; i<130; i++)
				g.drawImage(toolkit.getImage("image/부탁.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[3]), 55, 55, this);
			/***********************/

			/****************/
			
			if(model.menu_state==10)
			{
				g.clearRect(0, 0, getWidth(), getHeight());
				model.setSnowCheck(false);
			}
			
		}
		/**********************************/
	
		// '혼자먹기' 화면 일 경우
		else if(model.menuState == MENU.GAMESTART)
		{
		/**********Mouse Controller을 위해 VerseModel에 변수 값들을 주고 받는다.*************/
		stage = model.getStage();
		c_firstImg = model.getC_firstImg();
		c_secondImg = model.getC_secondImg();
		c_firstRow = model.getC_firstRow();
		c_firstCol = model.getC_firstCol();
		c_secondRow = model.getC_secondRow();
		c_secondCol = model.getC_secondCol();
		mouseState = model.getMouseState();
		mapRowSize = model.getMapRowSize();
		mapColSize = model.getMapColSize();
		model.setButtonImg();
		model.setCoinImg();
		image = model.getButtonImg(); // 반환된 단추 이미지를 image 배열 객체에 저장
		coinImg = model.getCoinImg();
		coin3 = toolkit.getImage("image/앞면.png");
		coin4 = toolkit.getImage("image/뒷면.png");
		main = toolkit.getImage("image/main.png"); // 배경 이미지를 main 객체에 저장
		firstRow = model.getFirstRow();	// 반환 받은 열 값을 저장
		firstCol = model.getFirstCol(); // 반환 받은 행 값을 저장
		/***************************************************************************/
		
		if (stage != 3) 
		{
			g.clearRect(0, 0, getWidth(), getHeight()); // 배경 초기화
			g.drawImage(main, 0, 0, 500, 600, this); // 400,400 size 배경 이미지를
														// 그려준다
			for (int row = 0; row < mapRowSize; row++)
				for (int col = 0; col < mapColSize; col++) {
					int index = model.getMap(stage, row, col);
					g.drawImage(image[index], col * model.WIDTH, row
							* model.HEIGHT, model.WIDTH, model.HEIGHT, this);
					// 각각 맵의 인덱스에 맞는 그림을 그려준다.
				}
			g.drawImage(toolkit.getImage("image/preBtn.png"), 80,510,80,80,this);
			g.drawImage(toolkit.getImage("image/resetBtn.png"), 210,510,80,80,this);
			g.drawImage(toolkit.getImage("image/menuBtn.png"), 340,510,80,80,this);

			if (mouseState == 0) // 첫번째 클릭했을 경우 state값이 0이므로 조건 성립.
			{
				/******* 동,서,남,북 같은 그림있는 곳 그림 그려주기 **********/
				for (int i = 0; i < 8; i++) {
					if (model.sameButtonCheck[i] == 0) {
						g.setColor(Color.BLUE);
						g.drawRect((100 * (firstCol + 1) - 85),
								(100 * (firstRow + 1) - 85), 70, 70);
						g.setColor(Color.YELLOW);
						g.drawLine((100 * (firstCol + 1) - 50),
								(100 * (firstRow + 1) - 50),
								(100 * (model.findCol[i] + 1) - 50),
								(100 * (model.findRow[i] + 1) - 50));

						/**************** 현재 Point를 알려주는 Rect 설정 ********************/

						if (i == 0) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 80));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 20));
						}
						if (i == 1) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 80));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 20));
						}

						if (i == 2) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 80));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 80));
						}
						if (i == 3) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 20));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 20));
						}
						if (i == 4) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 20));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 50));
						}
						if (i == 5) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 20));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 50));
						}
						if (i == 6) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 80));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 80),
									(100 * (model.findRow[i] + 1) - 50));
						}
						if (i == 7) {
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 80));
							g.drawLine((100 * (model.findCol[i] + 1) - 50),
									(100 * (model.findRow[i] + 1) - 50),
									(100 * (model.findCol[i] + 1) - 20),
									(100 * (model.findRow[i] + 1) - 50));
						}
						g.drawLine((100 * (firstCol + 1) - 50),
								(100 * (firstRow + 1) - 50),
								(100 * (model.findCol[i] + 1) - 50),
								(100 * (model.findRow[i] + 1) - 50));
						/*******************************************************/
					}
				}
				/*********************************************/
			}
			/**********compare되었을 때 이미지 삭제 thread 그려준다*******/
			if(model.getCompareState() == true)
			{
				if( model.thread1 == 0 )
				{
					g.drawImage(image[c_firstImg[model.thread1]+5], c_firstCol[model.thread1]*WIDTH, (c_firstRow[model.thread1]*HEIGHT)+(model.compare_count1*30), 100,100,this);
					g.drawImage(image[c_secondImg[model.thread1]+5], c_secondCol[model.thread1]*WIDTH, (c_secondRow[model.thread1]*HEIGHT)+(model.compare_count1*30), 100,100,this);
				}
				if(model.thread2 == 1)
				{
					g.drawImage(image[c_firstImg[model.thread2]+5], c_firstCol[model.thread2]*WIDTH, (c_firstRow[model.thread2]*HEIGHT)+(model.compare_count2*30), 100,100,this);
					g.drawImage(image[c_secondImg[model.thread2]+5], c_secondCol[model.thread2]*WIDTH, (c_secondRow[model.thread2]*HEIGHT)+(model.compare_count2*30), 100,100,this);
				}
				if(model.thread3 == 2)
				{
					g.drawImage(image[c_firstImg[model.thread3]+5], c_firstCol[model.thread3]*WIDTH, (c_firstRow[model.thread3]*HEIGHT)+(model.compare_count3*30), 100,100,this);
					g.drawImage(image[c_secondImg[model.thread3]+5], c_secondCol[model.thread3]*WIDTH, (c_secondRow[model.thread3]*HEIGHT)+(model.compare_count3*30), 100,100,this);
				}
				if(model.thread4 == 3)
				{
					g.drawImage(image[c_firstImg[model.thread4]+5], c_firstCol[model.thread4]*WIDTH, (c_firstRow[model.thread4]*HEIGHT)+(model.compare_count4*30), 100,100,this);
					g.drawImage(image[c_secondImg[model.thread4]+5], c_secondCol[model.thread4]*WIDTH, (c_secondRow[model.thread4]*HEIGHT)+(model.compare_count4*30), 100,100,this);
				}
			}
			/***************************************************/
		}
		
		
		/***********coin game Map Board************/
		else if(stage == 3)
		{
			g.clearRect(0, 0, getWidth(), getHeight()); // 배경 초기화
			timeLabel.setText("Score : " + model.score);
			g.drawImage(coin3, 60, 350, 100, 100, this);
			g.drawImage(coin4, 310, 350, 100, 100, this);
			g.drawImage(toolkit.getImage("image/_menuBtn.png"),300, 480, 150,100, this);
			/**********동전이 던져지는 Thread 그려주기**********/
			if( model.coin_count == 0)
			{
			g.drawImage(coin3, 180, 180, 150, 150, this);
			}
			if ( model.coin_count >=1 && model.coin_count <=5  )
			{
				g.drawImage(coinImg[4], 150, 130-(model.coin_count*20), 200, 200, this);
			}
			if( model.coin_count > 5 && model.coin_count <= 40)
			{
				g.drawImage(coinImg[count%14], 150, 30, 200, 200, this);
				count++;
			}
			if( model.coin_count  > 40 && model.coin_count < 45 )
				g.drawImage(coinImg[4], 150, 30+((model.coin_count-41)*20), 200, 200, this);
			if( model.coin_count >= 46)
			{
				if(model.getCoin() == 0)
					g.drawImage(coin3, 160, 180, 150,150, this);
				else if(model.getCoin() == 1)
					g.drawImage(coin4, 160, 180, 150,150, this);
			}
				
			/*********************************************/
		}
		/***********************************************/
		}
		
		/********Ranking 그려주기 *****************/
		// '랭킹' 화면 일 경우
		else if (model.menuState == MENU.RANKING)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(toolkit.getImage("image/menuBtn.png"), 200,0, 100,100, this);
		}
		/*************************************/
	}
}
