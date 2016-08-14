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
	public final static int WIDTH = 100; // ���� �������� ����
	public final static int HEIGHT = 100; // ���� �������� ����

	Toolkit toolkit = getToolkit(); // �̹����� �׸��� ���� Toolkit Class���
	Model model; // Model Class�� ��ü �̸��� model�̶� ����
	JLabel timeLabel;
	Image[] image; // ���� �̹��� ������ ���� Image Class�� �迭�� ����
	Image main; // ��� �̹��� ������ ���� Image Class ����
	Image[] coinImg;
	Image coin1;
	Image coin2;
	Image coin3;
	Image coin4;

	/****�������� ����***/
	private int x_randomSnow[] = new int[1000]; 
	private int y_randomSnow[] = new int[1000];
	/***************/
	
	/******�������********/
	private int mapRowSize ;	// map�� ���� size�� ������ �ִ� ����
	private int mapColSize ;// map�� ���� size�� ������ �ִ� ����
	private int mouseState;
	private int stage;
	private int count=0;
	private int firstRow, firstCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	private int c_firstImg[] = new int[6];
	private int c_secondImg [] = new int[6];
	private int c_firstRow[] = new int[6];
	private int c_firstCol[] = new int[6];
	private int c_secondRow[] = new int[6];
	private int c_secondCol[] = new int[6];
	/**********************/
	
	
	/********������************/
	public GameBoard(Model model, JLabel timeLabel) {
		this.model = model;
		this.timeLabel = timeLabel;
		this.image = model.getButtonImg();
		this.coinImg = model.getCoinImg();
	}
	/**********************/
	
	/****���� ����Ͽ� �������ִ� Method****/
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
	
	
	/*****�̹����� �׷��ִ� Method*******/
	public void paint(Graphics g) {
		
		/*********Menu ȭ�� �׸���************/
		
		//Menuȭ�� �� ���
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
				
				
				/******���� �׷��ش�.******/
			for(int i = 0; i<30; i++)
				g.drawImage(toolkit.getImage("image/��.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[0]), 55, 55, this);
			for(int i = 30; i<60; i++)
				g.drawImage(toolkit.getImage("image/��Ź.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[1]), 55, 55, this);
			for(int i = 60; i<100; i++)
				g.drawImage(toolkit.getImage("image/��.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[2]), 55, 55, this);
			for(int i = 100; i<130; i++)
				g.drawImage(toolkit.getImage("image/��Ź.png"), x_randomSnow[i], y_randomSnow[i] + (10*model.snowCount[3]), 55, 55, this);
			/***********************/

			/****************/
			
			if(model.menu_state==10)
			{
				g.clearRect(0, 0, getWidth(), getHeight());
				model.setSnowCheck(false);
			}
			
		}
		/**********************************/
	
		// 'ȥ�ڸԱ�' ȭ�� �� ���
		else if(model.menuState == MENU.GAMESTART)
		{
		/**********Mouse Controller�� ���� VerseModel�� ���� ������ �ְ� �޴´�.*************/
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
		image = model.getButtonImg(); // ��ȯ�� ���� �̹����� image �迭 ��ü�� ����
		coinImg = model.getCoinImg();
		coin3 = toolkit.getImage("image/�ո�.png");
		coin4 = toolkit.getImage("image/�޸�.png");
		main = toolkit.getImage("image/main.png"); // ��� �̹����� main ��ü�� ����
		firstRow = model.getFirstRow();	// ��ȯ ���� �� ���� ����
		firstCol = model.getFirstCol(); // ��ȯ ���� �� ���� ����
		/***************************************************************************/
		
		if (stage != 3) 
		{
			g.clearRect(0, 0, getWidth(), getHeight()); // ��� �ʱ�ȭ
			g.drawImage(main, 0, 0, 500, 600, this); // 400,400 size ��� �̹�����
														// �׷��ش�
			for (int row = 0; row < mapRowSize; row++)
				for (int col = 0; col < mapColSize; col++) {
					int index = model.getMap(stage, row, col);
					g.drawImage(image[index], col * model.WIDTH, row
							* model.HEIGHT, model.WIDTH, model.HEIGHT, this);
					// ���� ���� �ε����� �´� �׸��� �׷��ش�.
				}
			g.drawImage(toolkit.getImage("image/preBtn.png"), 80,510,80,80,this);
			g.drawImage(toolkit.getImage("image/resetBtn.png"), 210,510,80,80,this);
			g.drawImage(toolkit.getImage("image/menuBtn.png"), 340,510,80,80,this);

			if (mouseState == 0) // ù��° Ŭ������ ��� state���� 0�̹Ƿ� ���� ����.
			{
				/******* ��,��,��,�� ���� �׸��ִ� �� �׸� �׷��ֱ� **********/
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

						/**************** ���� Point�� �˷��ִ� Rect ���� ********************/

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
			/**********compare�Ǿ��� �� �̹��� ���� thread �׷��ش�*******/
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
			g.clearRect(0, 0, getWidth(), getHeight()); // ��� �ʱ�ȭ
			timeLabel.setText("Score : " + model.score);
			g.drawImage(coin3, 60, 350, 100, 100, this);
			g.drawImage(coin4, 310, 350, 100, 100, this);
			g.drawImage(toolkit.getImage("image/_menuBtn.png"),300, 480, 150,100, this);
			/**********������ �������� Thread �׷��ֱ�**********/
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
		
		/********Ranking �׷��ֱ� *****************/
		// '��ŷ' ȭ�� �� ���
		else if (model.menuState == MENU.RANKING)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(toolkit.getImage("image/menuBtn.png"), 200,0, 100,100, this);
		}
		/*************************************/
	}
}
