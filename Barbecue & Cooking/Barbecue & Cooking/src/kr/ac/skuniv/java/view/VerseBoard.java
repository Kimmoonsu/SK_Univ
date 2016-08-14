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
	public final static int WIDTH = 100; // ���� �������� ����
	public final static int HEIGHT = 100; // ���� �������� ����
	Toolkit toolkit = getToolkit(); // �̹����� �׸��� ���� Toolkit Class���

	Model model; // Model Class�� ��ü �̸��� model�̶� ����
	VerseModel v_model;
	JFrame frame;
	Container contentPane;
	GameBoard gameBoard;
	GameSound verseSound;
	Image[] image; // �������� ��ġ�� ������� �ȱ����� �迭
	Image[] v_image;
	Image main; // ��� �̹��� ������ ���� Image Class ����
	Image[] takeImage; // ��� �̹��� ������ ���� Image Class ����
	Image v_main;
	
	/***********��� ����*************/
	private int mapRowSize, v_mapRowSize; // map�� ���� size�� ������ �ִ� ����
	private int mapColSize, v_mapColSize;// map�� ���� size�� ������ �ִ� ����
	private int keyState, v_keyState, enterState = 1, v_enterState = 1;
	private int stage, verseStage;
	private int firstRow, firstCol; // Ŭ���� �̹����� (��,��) ��ġ (�迭 �ε���)
	private int v_firstRow, v_firstCol; // Ŭ���� �̹����� (��,��)��ġ(�迭�ε���)
	private int choiceRow, choiceCol, v_choiceRow, v_choiceCol;
	private int takeImg[] = new int[8];
	private int v_takeImg[] = new int[10];
	private int selectImg[] = new int[8];
	private int v_selectImg[] = new int[10];
	private int selectIndex = 0;
	private int v_selectIndex = 0;
	int p1_justOne = 0; // ó�� ������ �̹����� �־� �ֱ� ���� ���
	int p2_justOne = 0; // ó�� ������ �̹����� �־� �ֱ� ���� ���
	int count = 1;
	/******************************/
	
	/************ ��ġ �ȱ� ���� ���� ****************/
	private int p1_takeStage = 0;
	private int p2_takeStage = 0;
	private int taken[][] = new int[3][6]; // �������� ������ ��ġ�� ������ �迭�� ����
	int bbcSameCheck = 0; // player1�� ��� ��ġ�� �� ��ġ�� ���� ���� ���Ͽ� ������ �������ִ� ����
	int v_bbcSameCheck = 0; // player1�� ��� ��ġ�� �� ��ġ�� ���� ���� ���Ͽ� ������ �������ִ� ����
	int barbecue = 0; // ��� ��ġ�� ������ �������� �� �ѹ��� �������ִ� ����
	/***************************************/

	/***** ��ź �̹��� ��ü *******/
	Image bombImg1;
	Image bombImg2;
	Image bombImg3;
	/***********************/

	/***** �̸�Ƽ�� �̹��� ��ü *******/
	Image player1;
	Image player2;
	Image player1_1;
	Image player2_1;
	/*************************/

	/******* ������ *********/
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

	/***** ��ġ�� Random���� �����ϴ� Method ******/
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
		barbecue = 1; // ��ġ�� �ѹ� �������� ���̻� ���� �������ʴ´�.
	}

	/************************************/

	/***** ȭ���� �׷��ִ� Method ********/
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
		image = v_model.getButtonImg(); // ��ȯ�� ���� �̹����� image �迭 ��ü�� ����
		main = toolkit.getImage("image/white.png"); // ��� �̹����� main ��ü�� ����
		bombImg1 = toolkit.getImage("image/������.png");
		bombImg2 = toolkit.getImage("image/��������ź.png");
		bombImg3 = toolkit.getImage("image/������2.png");
		player1 = toolkit.getImage("image/����.png");
		player2 = toolkit.getImage("image/�¸�.png");
		player1_1 = toolkit.getImage("image/����2.png");
		player2_1 = toolkit.getImage("image/�¸�2.png");
		firstRow = v_model.getFirstRow(); // ��ȯ ���� �� ���� ����
		firstCol = v_model.getFirstCol(); // ��ȯ ���� �� ���� ����
		v_firstRow = v_model.getV_FirstRow();
		v_firstCol = v_model.getV_FirstCol();
		takeImg = v_model.getTakeImg();
		v_takeImg = v_model.getV_TakeImg();
		selectImg = v_model.getSelectImg();
		v_selectImg = v_model.getV_SelectImg();
		if (mainView == 0) {
			mainView = 1;
		}

		/******* player1�� player2�� ������ ��ġ ������ ���ƾ� �ϹǷ� �̸� ���� ****/
		randomTaken();
		/******************************************************/

		/***************************** 1Player graphic ***********************************/
		if (enterState == 1) {
			g.drawImage(main, 0, 0, 600, 800, this);
			/************ ���Ӻ��� �ϴܿ� ȹ���� ������ �׸��� **********************/
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
					// ���� ���� �ε����� �´� �׸��� �׷��ش�.
				}

			// g.drawRect((25 + choiceRow), (25 + choiceCol), 50, 50);
			/**************** ���� Point�� �˷��ִ� Rect ���� ********************/
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
		if (keyState == 0) // ù��° Ŭ������ ��� state���� 0�̹Ƿ� ���� ����.
		{
			/******* ��,��,��,�� ���� �׸��ִ� �� �׸� �׷��ֱ� **********/
			for (int i = 0; i < 8; i++) {
				if (v_model.sameButtonCheck[i] == 0) {
					g.setColor(Color.BLUE);
					g.drawRect((100 * (firstCol + 1) - 85),
							(100 * (firstRow + 1) - 85), 70, 70);
					g.setColor(Color.YELLOW);

					/*************** ȭ��ǥ�� �׷��ֱ� ****************/
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
							v_model.HEIGHT, this); // ���� ���� �ε����� �´� �׸���
													// �׷��ش�.
				}

			/**************** ���� Point�� �˷��ִ� Rect ���� ********************/
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
		if (v_keyState == 0) // ù��° Ŭ������ ��� state���� 0�̹Ƿ� ���� ����.
		{

			/******* ��,��,��,�� ���� �׸��ִ� �� �׸� �׷��ֱ� **********/
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

					/*************** ȭ��ǥ�� �׷��ֱ� ****************/
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

		/*********** BombThread start�� �׷��ֱ� *************/
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
						// ���� ���� �ε����� �´� �׸��� �׷��ش�.
					}
				v_model.setBombCheck(false);
			}
		}
		/***************************************************/

		/****************************************/

		/**************************** 1P mapClear�� ��� ********************/

		if (v_model.p1_takeGameStart == true) {
			if (p1_takeStage != 3) {
				/***** �ʱ� �̹��� �ٽ� �׷��ش�. *********/
				g.clearRect(0, 0, 500, 800); // ��� �ʱ�ȭ
				g.setColor(Color.BLACK);

				g.drawImage(toolkit.getImage("image/_stick.png"), 100, 170, 50,
						500, this);
				g.drawImage(toolkit.getImage("image/_stick.png"), 300, 170, 50,
						500, this);

				/*****************************/
				if (enterState == 2) {
					point = choiceRow / 50; // ���� ������ ���� ��ǥ
					if (selectIndex < 5) {
						selectIndex++;
					}
				}
				if (keyState == 2) {

					/******** ù��° Ŭ���� ���� 0���� �Ǿ��ִ� ���� �����ϱ� ���� ����ó�� *********/

					/****** ���� *****/
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

					/***** �� *****/
					for (int i = 0; i < 6; i++) {
						if (taken[p1_takeStage][i] == selectImg[i]) {
							bbcSameCheck++;
						}
					}
					/************/

					/*** ����üũ ***/
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
						if (selectImg[5] != 0)// ���� ��ġ�� ������ ������ �����ִ� ���
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
					/**** ���� �Ȱ� �ִ� ��ġ�� �׷��ش� *****/
					for (int i = 0; i < 6; i++) {
						if(selectImg[i] == 0 )
							g.drawImage(takeImage[0], 275, (450 - (50 * i)), 100, 100, this);
						else
							g.drawImage(image[selectImg[i] + 4], 275, (450 - (50 * i)), 100, 100, this);
					}
					/***************************/
				}

				/*************** random���� ����ڰ� �������� ��ġ�� �׸��� ****************/
				if (p1_takeStage != 3) {
					for (int i = 0; i < 6; i++) {
						g.drawImage(takeImage[taken[p1_takeStage][i]], 75,
								(450 - (50 * i)), 100, 100, this);
					}
				}
				/************************************************************/
				/***************** ��ġ�� ������ ���Ӻ��� ��ܿ� �������� �׸���. *********************/
				for (int i = 0; i < 8; i++) {
					// if(takeImg[i] == 0)���� �����ö��
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
					g.drawImage(toolkit.getImage("image/�г�.png"), 600, 100,
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
					g.drawImage(toolkit.getImage("image/�г�.png"), 600, 100,
							300, 300, this);
				}

				/****** �ٽ� �޴��� ���ư���. *******/
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

		/********************** 2P mapClear�� ��� **********************/

		if (v_model.p2_takeGameStart == true) {
			/***** �ʱ� �̹����� �ٽ� �׷��ش�. *****/
			if (p2_takeStage != 3) {
				g.clearRect(700, 0, 500, 700); // ��� �ʱ�ȭ
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

					/***** �� ******/
					for (int i = 0; i < 6; i++) {
						if (taken[p2_takeStage][i] == v_selectImg[i])
							v_bbcSameCheck++;
					}
					/************/
					if (p2_takeStage == 2 && v_bbcSameCheck == 5)
						v_model.p2_takeStage = 3;

					/****** ����üũ ******/
					if (v_bbcSameCheck == 6) {
						p2_takeStage++;
						for (int i = 0; i < 7; i++)
							v_selectImg[i] = 0;
						v_selectIndex = 0;
						p2_justOne = 0;
						v_model.setV_KeyState(3);
						v_model.setV_EnterState(3);
					} else if (v_bbcSameCheck != 6) {
						if (v_selectImg[5] != 0)// ���� ��ġ�� ������ ������ �����ִ� ���
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
				
					/*************** random���� ����ڰ� �������� ��ġ�� �׸��� ****************/

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
					g.drawImage(toolkit.getImage("image/�г�2.png"), 100, 100,
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
					g.drawImage(toolkit.getImage("image/�г�2.png"), 100, 100,
							300, 300, this);
				}

				/****** �ٽ� �޴��� ���ư���. *******/
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

		/********* 1player �̸�Ƽ�� �����̱� ************/
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

			/********* 2player �̸�Ƽ�� �����̱� ************/
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
			/******** space�� ������ ��� ***********/
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