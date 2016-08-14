package kr.ac.skuniv.java.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.skuniv.java.controller.Controller;
import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.model.VerseModel;

public class VerseBoard extends JPanel {
	int point = 0;
	int v_point = 0;
	int p1_count = 0;
	int p2_count = 0;
	int mainView = 0;
	public final static int WIDTH = 100; // 단추 아이콘의 넓이
	public final static int HEIGHT = 100; // 단추 아이콘의 높이
	Toolkit toolkit = getToolkit(); // 이미지를 그리기 위해 Toolkit Class사용

	Model model; // Model Class의 객체 이름을 model이라 지정
	VerseModel v_model;
	JFrame frame;
	Container contentPane;
	GameBoard gameBoard;
	GameSound verseSound;
	Image[] image; // 아이템을 꼬치에 순서대로 꽂기위한 배열
	Image[] v_image;
	Image main; // 배경 이미지 삽입을 위해 Image Class 선언
	Image[] takeImage; // 배경 이미지 삽입을 위해 Image Class 선언
	Image v_main;
	
	/***********멤버 변수*************/
	private int mapRowSize, v_mapRowSize; // map의 행의 size를 가지고 있는 변수
	private int mapColSize, v_mapColSize;// map의 행의 size를 가지고 있는 변수
	private int keyState, v_keyState, enterState = 1, v_enterState = 1;
	private int stage, verseStage;
	private int firstRow, firstCol; // 클릭한 이미지의 (행,렬) 위치 (배열 인덱스)
	private int v_firstRow, v_firstCol; // 클릭한 이미지의 (행,렬)위치(배열인덱스)
	private int choiceRow, choiceCol, v_choiceRow, v_choiceCol;
	private int takeImg[] = new int[8];
	private int v_takeImg[] = new int[10];
	private int selectImg[] = new int[8];
	private int v_selectImg[] = new int[10];
	private int selectIndex = 0;
	private int v_selectIndex = 0;
	int p1_justOne = 0; // 처음 선택한 이미지를 넣어 주기 위해 사용
	int p2_justOne = 0; // 처음 선택한 이미지를 넣어 주기 위해 사용
	int count = 1;
	/******************************/
	
	/************ 꼬치 꽂기 게임 변수 ****************/
	private int p1_takeStage = 0;
	private int p2_takeStage = 0;
	private int taken[][] = new int[3][6]; // 랜덤으로 생성된 꼬치의 내용을 배열에 저장
	int bbcSameCheck = 0; // player1의 결과 꼬치와 내 꼬치의 같은 것을 비교하여 같으면 증가해주는 변수
	int v_bbcSameCheck = 0; // player1의 결과 꼬치와 내 꼬치의 같은 것을 비교하여 같으면 증가해주는 변수
	int barbecue = 0; // 결과 꼬치의 내용을 랜덤으로 딱 한번만 생성해주는 변수
	/***************************************/

	/***** 폭탄 이미지 객체 *******/
	Image bombImg1;
	Image bombImg2;
	Image bombImg3;
	/***********************/

	/***** 이모티콘 이미지 객체 *******/
	Image player1;
	Image player2;
	Image player1_1;
	Image player2_1;
	/*************************/

	/******* 생성자 *********/
	public VerseBoard(VerseModel v_model, JFrame frame, Container contentPane,
			GameBoard gameBoard, Model model, GameSound verseSound) {
		this.v_model = v_model;
		this.image = v_model.getButtonImg();
		this.takeImage = v_model.getTakeImage();
		this.frame = frame;
		this.contentPane = contentPane;
		this.gameBoard = gameBoard;
		this.model = model;
		this.verseSound = verseSound;
	}

	/******************/

	/***** 꼬치를 Random으로 생성하는 Method ******/
	public void randomTaken() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++)
				if (barbecue == 0) {
					taken[i][j] = (int) (Math.random() * 4);
					if (taken[i][j] == 0) {
						taken[i][j]++;
					}
				}
		}
		barbecue = 1; // 꼬치가 한번 정해지만 더이상 새로 정하지않는다.
	}

	/************************************/

	/***** 화면을 그려주는 Method ********/
	public void paint(Graphics g) {
		choiceRow = v_model.getChoiceRow();
		choiceCol = v_model.getChoiceCol();
		v_choiceRow = v_model.getV_ChoiceRow();
		v_choiceCol = v_model.getV_ChoiceCol();
		stage = v_model.getStage();
		verseStage = v_model.getVerseStage();
		keyState = v_model.getkeyState();
		enterState = v_model.getEnterState();
		v_keyState = v_model.getV_KeyState();
		v_enterState = v_model.getV_EnterState();
		mapRowSize = v_model.getMapRowSize();
		mapColSize = v_model.getMapColSize();
		v_mapRowSize = v_model.getV_MapRowSize();
		v_mapColSize = v_model.getV_MapColSize();
		v_model.setButtonImg();
		v_model.setTakeImage();
		image = v_model.getButtonImg(); // 반환된 단추 이미지를 image 배열 객체에 저장
		main = toolkit.getImage("image/white.png"); // 배경 이미지를 main 객체에 저장
		bombImg1 = toolkit.getImage("image/고추장.png");
		bombImg2 = toolkit.getImage("image/고추장폭탄.png");
		bombImg3 = toolkit.getImage("image/고추장2.png");
		player1 = toolkit.getImage("image/습득.png");
		player2 = toolkit.getImage("image/승리.png");
		player1_1 = toolkit.getImage("image/습득2.png");
		player2_1 = toolkit.getImage("image/승리2.png");
		firstRow = v_model.getFirstRow(); // 반환 받은 열 값을 저장
		firstCol = v_model.getFirstCol(); // 반환 받은 행 값을 저장
		v_firstRow = v_model.getV_FirstRow();
		v_firstCol = v_model.getV_FirstCol();
		takeImg = v_model.getTakeImg();
		v_takeImg = v_model.getV_TakeImg();
		selectImg = v_model.getSelectImg();
		v_selectImg = v_model.getV_SelectImg();
		if (mainView == 0) {
			mainView = 1;
		}

		/******* player1과 player2의 생성된 꼬치 내용이 같아야 하므로 미리 생성 ****/
		randomTaken();
		/******************************************************/

		/***************************** 1Player graphic ***********************************/
		if (enterState == 1) {
			g.drawImage(main, 0, 0, 600, 800, this);
			/************ 게임보드 하단에 획득한 아이템 그리기 **********************/
			for (int i = 0; i < 8; i++) {
				g.drawImage(image[takeImg[i] + 8], (10 + (i * 50)), 550, 50,
						50, this);
			}
			/*********************************************************************/
			for (int row = 0; row < mapRowSize; row++)
				for (int col = 0; col < mapColSize; col++) {
					int index = v_model.getMap(stage, row, col);
					g.drawImage(image[index], (col * v_model.WIDTH),
							(row * v_model.HEIGHT), v_model.WIDTH,
							v_model.HEIGHT, this);
					// 각각 맵의 인덱스에 맞는 그림을 그려준다.
				}

			// g.drawRect((25 + choiceRow), (25 + choiceCol), 50, 50);
			/**************** 현재 Point를 알려주는 Rect 설정 ********************/
			g.setColor(Color.YELLOW);
			g.drawRect((25 + choiceRow), (25 + choiceCol), 50, 50);
			g.drawRect((24 + choiceRow), (24 + choiceCol), 51, 51);
			g.drawRect((23 + choiceRow), (23 + choiceCol), 52, 52);
			g.drawRect((22 + choiceRow), (22 + choiceCol), 53, 53);
			g.drawRect((25 + choiceRow), (25 + choiceCol), 50, 50);
			g.drawRect((25 + choiceRow), (25 + choiceCol), 51, 51);
			g.drawRect((25 + choiceRow), (25 + choiceCol), 52, 52);
			g.drawRect((25 + choiceRow), (25 + choiceCol), 53, 53);
			/*******************************************************/
		}
		if (keyState == 0) // 첫번째 클릭했을 경우 state값이 0이므로 조건 성립.
		{
			/******* 동,서,남,북 같은 그림있는 곳 그림 그려주기 **********/
			for (int i = 0; i < 8; i++) {
				if (v_model.sameButtonCheck[i] == 0) {
					g.setColor(Color.BLUE);
					g.drawRect((100 * (firstCol + 1) - 85),
							(100 * (firstRow + 1) - 85), 70, 70);
					g.setColor(Color.YELLOW);

					/*************** 화살표를 그려주기 ****************/
					if (i == 0) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 20));
					}
					if (i == 1) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 20));
					}

					if (i == 2) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 80));
					}
					if (i == 3) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 20));
					}
					if (i == 4) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 50));
					}
					if (i == 5) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 50));
					}
					if (i == 6) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 80),
								(100 * (v_model.findRow[i] + 1) - 50));
					}
					if (i == 7) {
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.findCol[i] + 1) - 50),
								(100 * (v_model.findRow[i] + 1) - 50),
								(100 * (v_model.findCol[i] + 1) - 20),
								(100 * (v_model.findRow[i] + 1) - 50));
					}
					g.drawLine((100 * (firstCol + 1) - 50),
							(100 * (firstRow + 1) - 50),
							(100 * (v_model.findCol[i] + 1) - 50),
							(100 * (v_model.findRow[i] + 1) - 50));
				}
			}

			/*********************************************/
		}
		/*************************************************************************/

		/***************************** 2Player graphic ***********************************/

		if (v_enterState == 1) {
			g.drawImage(main, 500, 0, 700, 800, this);
			for (int i = 0; i < 8; i++) {
				g.drawImage(image[v_takeImg[i] + 8], (710 + (i * 50)), 550, 50,
						50, this);
			}
			for (int row = 0; row < v_mapRowSize; row++)
				for (int col = 0; col < v_mapColSize; col++) {
					int index = v_model.getVerseMap(verseStage, row, col);
					g.drawImage(image[index], 700 + (col * v_model.WIDTH),
							(row * v_model.HEIGHT), v_model.WIDTH,
							v_model.HEIGHT, this); // 각각 맵의 인덱스에 맞는 그림을
													// 그려준다.
				}

			/**************** 현재 Point를 알려주는 Rect 설정 ********************/
			g.setColor(Color.YELLOW);
			g.drawRect((725 + v_choiceRow), (25 + v_choiceCol), 50, 50);
			g.drawRect((724 + v_choiceRow), (24 + v_choiceCol), 51, 51);
			g.drawRect((723 + v_choiceRow), (23 + v_choiceCol), 52, 52);
			g.drawRect((722 + v_choiceRow), (22 + v_choiceCol), 53, 53);
			g.drawRect((725 + v_choiceRow), (25 + v_choiceCol), 50, 50);
			g.drawRect((725 + v_choiceRow), (25 + v_choiceCol), 51, 51);
			g.drawRect((725 + v_choiceRow), (25 + v_choiceCol), 52, 52);
			g.drawRect((725 + v_choiceRow), (25 + v_choiceCol), 53, 53);
			/*******************************************************/

		}
		if (v_keyState == 0) // 첫번째 클릭했을 경우 state값이 0이므로 조건 성립.
		{

			/******* 동,서,남,북 같은 그림있는 곳 그림 그려주기 **********/
			for (int i = 0; i < 8; i++) {
				if (v_model.v_sameButtonCheck[i] == 0) {

					// g.drawLine((100 * (v_firstCol + 8) - 50),
					// (100 * (v_firstRow + 1) - 50),
					// (100 * (v_model.v_findCol[i] + 8) - 50),
					// (100 * (v_model.v_findRow[i] + 1) - 50));
					g.setColor(Color.BLUE);
					g.drawRect((100 * (v_firstCol + 8) - 85),
							(100 * (v_firstRow + 1) - 85), 70, 70);
					g.setColor(Color.YELLOW);

					/*************** 화살표를 그려주기 ****************/
					if (i == 0) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 20));
					}
					if (i == 1) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 20));
					}

					if (i == 2) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 80));
					}
					if (i == 3) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 20));
					}
					if (i == 4) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 50));
					}
					if (i == 5) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 20));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 50));
					}
					if (i == 6) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 80),
								(100 * (v_model.v_findRow[i] + 1) - 50));
					}
					if (i == 7) {
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 80));
						g.drawLine((100 * (v_model.v_findCol[i] + 8) - 50),
								(100 * (v_model.v_findRow[i] + 1) - 50),
								(100 * (v_model.v_findCol[i] + 8) - 20),
								(100 * (v_model.v_findRow[i] + 1) - 50));
					}
					g.drawLine((100 * (v_firstCol + 8) - 50),
							(100 * (v_firstRow + 1) - 50),
							(100 * (v_model.v_findCol[i] + 8) - 50),
							(100 * (v_model.v_findRow[i] + 1) - 50));
				}
			}
		}
		/*************************************************************************/

		/*********** BombThread start시 그려주기 *************/
		if (v_model.p2_takeGameStart == false) {
			if (v_model.getBombCheck() == true) {
				v_model.setMap(stage, 1, 1, 0);
				if (v_model.count == 0) {
					g.drawImage(bombImg1, 800, 0, 300, 200, this);
				}
				for (int i = 0; i < v_model.count; i++) {
					g.drawImage(bombImg3, 800, 0, 300, 200, this);
					g.drawImage(bombImg2, 850, (140 * (i + 1)), 200, 100, this);
				}
			}
			if (v_model.count >= 3 && v_model.count < 9) {
				for (int row = 0; row < v_mapRowSize; row++)
					for (int col = 0; col < v_mapColSize; col++) {
						g.drawImage(bombImg2, 390, -250, 1100, 1100, this);
						// 각각 맵의 인덱스에 맞는 그림을 그려준다.
					}
				v_model.setBombCheck(false);
			}
		}
		/***************************************************/

		/****************************************/

		/**************************** 1P mapClear일 경우 ********************/

		if (v_model.p1_takeGameStart == true) {
			if (p1_takeStage != 3) {
				/***** 초기 이미지 다시 그려준다. *********/
				g.clearRect(0, 0, 500, 800); // 배경 초기화
				g.setColor(Color.BLACK);

				g.drawImage(toolkit.getImage("image/_stick.png"), 100, 170, 50,
						500, this);
				g.drawImage(toolkit.getImage("image/_stick.png"), 300, 170, 50,
						500, this);

				/*****************************/
				if (enterState == 2) {
					point = choiceRow / 50; // 현재 선택한 곳의 좌표
					if (selectIndex < 5) {
						selectIndex++;
					}
				}
				if (keyState == 2) {

					/******** 첫번째 클릭이 전부 0으로 되어있는 것을 방지하기 위한 예외처리 *********/

					/****** 저장 *****/
					if (p1_justOne == 0) {
						point = choiceRow / 50;
						selectImg[selectIndex] = (takeImg[point]);
						p1_justOne++;
					}
					/****************************************/
					for (int i = 0; i <= selectIndex; i++) {
						selectImg[selectIndex] = (takeImg[point]);
					}
					/************/

					/***** 비교 *****/
					for (int i = 0; i < 6; i++) {
						if (taken[p1_takeStage][i] == selectImg[i]) {
							bbcSameCheck++;
						}
					}
					/************/

					/*** 같다체크 ***/
					if (p1_takeStage == 2 && bbcSameCheck == 5)
						v_model.p1_takeStage = 3;
					if (bbcSameCheck == 6) {
						p1_takeStage++;
						for (int i = 0; i < 7; i++)
							selectImg[i] = 0;
						selectIndex = 0;
						p1_justOne = 0;
						v_model.setKeyState(3);
						v_model.setEnterState(3);
					} else if (bbcSameCheck != 6) {
						if (selectImg[5] != 0)// 나의 꼬치에 마지막 음식이 들어와있는 경우
						{
							for (int j = 0; j < 6; j++) {
								selectImg[j] = 0;
								p1_justOne = 0;
								selectIndex = 0;
								v_model.setKeyState(3);
								v_model.setEnterState(3);
							}
						}
					}
					bbcSameCheck = 0;

					/***********/
					/**** 내가 꽂고 있는 꼬치를 그려준다 *****/
					for (int i = 0; i < 6; i++) {
						if(selectImg[i] == 0 )
							g.drawImage(takeImage[0], 275, (450 - (50 * i)), 100, 100, this);
						else
							g.drawImage(image[selectImg[i] + 4], 275, (450 - (50 * i)), 100, 100, this);
					}
					/***************************/
				}

				/*************** random으로 사용자가 만들어야할 꼬치를 그린다 ****************/
				if (p1_takeStage != 3) {
					for (int i = 0; i < 6; i++) {
						g.drawImage(takeImage[taken[p1_takeStage][i]], 75,
								(450 - (50 * i)), 100, 100, this);
					}
				}
				/************************************************************/
				/***************** 꼬치를 꽂을때 게임보드 상단에 아이템을 그린다. *********************/
				for (int i = 0; i < 8; i++) {
					// if(takeImg[i] == 0)만약 빈접시라면
					// g.drawImage(image[takeImg[i]+8], (i * 50), 50, 50, 50,
					// this);
					g.drawImage(image[takeImg[i] + 8], (i * 50), 50, 50, 50,
							this);
				}
				/*******************************************************************/

				g.setColor(Color.YELLOW);
				g.drawRect((13 + choiceRow), (60 + choiceCol), 25, 25);
				g.drawRect((12 + choiceRow), (59 + choiceCol), 26, 26);
				g.drawRect((11 + choiceRow), (58 + choiceCol), 27, 27);
				g.drawRect((10 + choiceRow), (57 + choiceCol), 28, 28);
				g.drawRect((13 + choiceRow), (60 + choiceCol), 25, 25);
				g.drawRect((13 + choiceRow), (60 + choiceCol), 26, 26);
				g.drawRect((13 + choiceRow), (60 + choiceCol), 27, 27);
				g.drawRect((13 + choiceRow), (60 + choiceCol), 28, 28);
			}

			if (p1_takeStage == 3) {
				g.clearRect(0, 0, getWidth(), getHeight());
				if (v_model.finish_count == 0) {
					g.drawImage(player1, 100, 100, 300, 300, this);
					g.drawImage(toolkit.getImage("image/분노.png"), 600, 100,
							300, 300, this);
				}
				if (v_model.finish_count > 0 && v_model.finish_count < 9) {
					g.clearRect(0, 0, getWidth(), getHeight());
					if (p1_count == 1) {

						g.drawImage(player1, 100, 100, 300, 300, this);
						p1_count = 0;
					} else if (p1_count == 0) {

						g.drawImage(player1_1, 100, 100, 300, 300, this);
						p1_count = 1;
					}
					g.drawImage(toolkit.getImage("image/분노.png"), 600, 100,
							300, 300, this);
				}

				/****** 다시 메뉴로 돌아간다. *******/
				if (v_model.finish_count == 9) {
					g.clearRect(0, 0, getWidth(), getHeight());
					frame.setSize(700, 900);
					frame.setLocation(10, 10);
					model.menuState = MENU.MENU;
					contentPane.remove(this);
					contentPane.add(gameBoard);
					gameBoard.repaint();
					/***************************/

				}
				return;
			}
		}

		/********************** 2P mapClear일 경우 **********************/

		if (v_model.p2_takeGameStart == true) {
			/***** 초기 이미지를 다시 그려준다. *****/
			if (p2_takeStage != 3) {
				g.clearRect(700, 0, 500, 700); // 배경 초기화
				g.setColor(Color.BLACK);
				g.drawImage(toolkit.getImage("image/_stick.png"), 805, 170, 50,
						500, this);
				g.drawImage(toolkit.getImage("image/_stick.png"), 1005, 170,
						50, 500, this);
				for (int i = 0; i < 8; i++) {
					g.drawImage(image[v_takeImg[i] + 8], (700 + (i * 50)), 50,
							50, 50, this);
				}
				if (v_enterState == 2) {
					v_point = (v_choiceRow / 50);
					if (v_selectIndex < 5)
						v_selectIndex++;
					// v_selectImg[v_selectIndex] = v_takeImg[v_point];
				}

				if (v_keyState == 2) {
					if (p2_justOne == 0) {
						v_point = (v_choiceRow / 50);
						v_selectImg[v_selectIndex] = (v_takeImg[v_point]);
						p2_justOne++;
					}

					/*************/
					for (int i = 0; i <= v_selectIndex; i++) {
						v_selectImg[v_selectIndex] = (v_takeImg[v_point]);
					}
					/**************/

					/***** 비교 ******/
					for (int i = 0; i < 6; i++) {
						if (taken[p2_takeStage][i] == v_selectImg[i])
							v_bbcSameCheck++;
					}
					/************/
					if (p2_takeStage == 2 && v_bbcSameCheck == 5)
						v_model.p2_takeStage = 3;

					/****** 같다체크 ******/
					if (v_bbcSameCheck == 6) {
						p2_takeStage++;
						for (int i = 0; i < 7; i++)
							v_selectImg[i] = 0;
						v_selectIndex = 0;
						p2_justOne = 0;
						v_model.setV_KeyState(3);
						v_model.setV_EnterState(3);
					} else if (v_bbcSameCheck != 6) {
						if (v_selectImg[5] != 0)// 나의 꼬치에 마지막 음식이 들어와있는 경우
						{
							for (int j = 0; j < 6; j++) {
								v_selectImg[j] = 0;
								p2_justOne = 0;
								v_selectIndex = 0;
								v_model.setV_KeyState(3);
								v_model.setV_EnterState(3);
							}
						}
					}
					v_bbcSameCheck = 0;

					for (int i = 0; i < 6; i++) 
					{
						if(v_selectImg[i] == 0 )
							g.drawImage(takeImage[0], 275, (450 - (50 * i)), 100, 100, this);
						else
							g.drawImage(image[v_selectImg[i] + 4], 975,	(450 - (50 * i)), 100, 100, this);
					}
				
					/*************** random으로 사용자가 만들어야할 꼬치를 그린다 ****************/

				}
				if (p2_takeStage != 3) {
					for (int i = 0; i < 6; i++) {
						g.drawImage(takeImage[taken[p2_takeStage][i]], 775,
								(450 - (50 * i)), 100, 100, this);
					}
				}
				/************************************************************/
				g.setColor(Color.YELLOW);
				g.drawRect((713 + v_choiceRow), (60 + v_choiceCol), 25, 25);
				g.drawRect((712 + v_choiceRow), (59 + v_choiceCol), 26, 26);
				g.drawRect((711 + v_choiceRow), (58 + v_choiceCol), 27, 27);
				g.drawRect((710 + v_choiceRow), (57 + v_choiceCol), 28, 28);
				g.drawRect((713 + v_choiceRow), (60 + v_choiceCol), 25, 25);
				g.drawRect((713 + v_choiceRow), (60 + v_choiceCol), 26, 26);
				g.drawRect((713 + v_choiceRow), (60 + v_choiceCol), 27, 27);
				g.drawRect((713 + v_choiceRow), (60 + v_choiceCol), 28, 28);

			}

			/*****************/
			if (p2_takeStage == 3) {
				g.clearRect(0, 0, getWidth(), getHeight());
				if (v_model.finish_count == 0) {
					g.drawImage(toolkit.getImage("image/분노2.png"), 100, 100,
							300, 300, this);
					g.drawImage(player2, 600, 100, 300, 300, this);
				}
				if (v_model.finish_count > 0 && v_model.finish_count < 9) {
					g.clearRect(0, 0, getWidth(), getHeight());
					if (p2_count == 1) {
						g.drawImage(player2, 600, 100, 300, 300, this);
						p2_count = 0;
					} else if (p2_count == 0) {
						g.drawImage(player2_1, 600, 100, 300, 300, this);
						p2_count = 1;
					}
					g.drawImage(toolkit.getImage("image/분노2.png"), 100, 100,
							300, 300, this);
				}

				/****** 다시 메뉴로 돌아간다. *******/
				if (v_model.finish_count == 9) {
					g.clearRect(0, 0, getWidth(), getHeight());
					frame.setSize(700, 900);
					frame.setLocation(10, 10);
					model.menuState = MENU.MENU;
					contentPane.remove(this);
					contentPane.add(gameBoard);
					gameBoard.repaint();
					/***************************/

				}
			}

		}

		/********* 1player 이모티콘 움직이기 ************/
		if (p1_takeStage != 3 && p2_takeStage != 3) {
			if (v_model.p1_count == 1) {
				if (p1_count == 1) {
					g.clearRect(50, 620, 300, 130);
					g.drawImage(player1, 150, 620, 200, 130, this);
					p1_count = 0;
				} else if (p1_count == 0) {
					g.clearRect(50, 620, 300, 130);
					g.drawImage(player1_1, 150, 620, 200, 130, this);
					p1_count = 1;
				}
			} else if (v_model.p1_count == 0) {
				if (p1_count == 0) {
					g.clearRect(50, 620, 300, 130);
					g.drawImage(player1, 150, 620, 200, 130, this);
				} else if (p1_count == 1) {
					g.clearRect(50, 620, 300, 130);
					g.drawImage(player1_1, 150, 620, 200, 130, this);
				}

			}

			/*******************************************/

			/********* 2player 이모티콘 움직이기 ************/
			if (v_model.p2_count == 1) {
				if (p2_count == 1) {
					g.clearRect(800, 620, 300, 130);
					g.drawImage(player2, 830, 620, 200, 130, this);
					p2_count = 0;
				} else if (p2_count == 0) {
					g.clearRect(800, 620, 300, 130);
					g.drawImage(main, 800, 620, 200, 130, this);
					g.drawImage(player2_1, 830, 620, 200, 130, this);
					p2_count = 1;
				}
			}
			/******** space를 눌렀을 경우 ***********/
			if (v_model.p2_count == 0) {
				if (p2_count == 0) {
					g.clearRect(800, 620, 300, 130);
					g.drawImage(player2, 830, 620, 200, 130, this);
				} else if (p2_count == 1) {
					g.clearRect(800, 620, 300, 130);
					g.drawImage(main, 800, 620, 300, 130, this);
					g.drawImage(player2_1, 830, 620, 200, 130, this);
				}
			}
		}
		/********************************/
	}
	/*****************************/
}