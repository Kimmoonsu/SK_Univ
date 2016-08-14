package kr.ac.skuniv.java.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import kr.ac.skuniv.java.controller.Controller;
import kr.ac.skuniv.java.controller.IORanking;
import kr.ac.skuniv.java.controller.MenuClick;
import kr.ac.skuniv.java.controller.SnowThread;
import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.VerseModel;

public class View {
	JFrame frame = new JFrame("Barbecue & Cooking"); // frame 이름을 Barbecue & Cooking로 지정
	Model model = new Model(); // 이름을 model로 하는 Model Class의 객체 생성
	VerseModel v_model = new VerseModel();
	JMenuBar menuBar = new JMenuBar(); // Menu를 생성 Class 객체 menuBar생성
	JLabel timeLabel = new JLabel("00 : 00 : 00", SwingConstants.CENTER);
	JLabel clearLabel = new JLabel("Clear", SwingConstants.CENTER);
	GameBoard gameBoard = new GameBoard(model, timeLabel);
	JButton button1 = new JButton("Mouse");
	JButton button2 = new JButton("KeyBoard");
	JPanel panel = new JPanel();
	ImageIcon mainView = new ImageIcon("image/mainView.png");
	Container contentPane = frame.getContentPane();
	JLabel rankLabel1 = new JLabel();
	JLabel rankLabel2 = new JLabel();
	JLabel rankLabel3 = new JLabel();
	JLabel rankLabel4 = new JLabel();
	JLabel rankLabel5 = new JLabel();
	JLabel rankLabel6 = new JLabel();
	JLabel rankLabel7 = new JLabel();

	public View() {
		
		/****************** menu 생성 ********************/
		JMenu game = new JMenu("게임(G)"); // "게임(G)"라는 메뉴 생성
		JMenuItem exit = new JMenuItem("게임종료"); // "게임종료"라는 메뉴 Item 생성
		game.add(exit); // "게임종료" 메뉴 Item 등록
		exit.addActionListener(new MenuClick(model)); // "게임종료"를 클릭했을 때 발생하는
														// Listener등록

		JMenu help = new JMenu("도움말(H)"); // "도움말(H)" 라는 메뉴 생성

		JMenuItem how = new JMenuItem("게임 방법"); // "게임 방법" 이라는 메뉴 Item 생성
		JMenuItem info = new JMenuItem("프로그램 정보"); // "프로그램 정보" 라는 메뉴 Item 생성
		how.addActionListener(new MenuClick(model)); // "게임방법"을 클릭했을 때 발생하는
														// Listener등록
		info.addActionListener(new MenuClick(model)); // "프로그램 정보"를 클릭했을 때 발생하는
														// Listener등록

		help.add(how); // "게임 방법" 메뉴 Item 등록
		help.add(info); // "프로그램 정보" 메뉴 Item 등록
		menuBar.add(game); // "게임(G)" 메뉴바등록
		menuBar.add(help); // "도움말(H)" 메뉴바등록

		/************************************************/

		
		/*********************Frame구성*******************************/
		frame.setLocation(10, 10); // frame위치를 오른쪽으로 10, 아래로 10 위치로 지정
		frame.setPreferredSize(new Dimension(700, 900)); // frame크기를 700, 900으로 지정
		frame.setJMenuBar(menuBar); // frame에 Menu Bar를 등록해준다.
		timeLabel.setPreferredSize(new Dimension(80, 80));	// 시간을 알려주는 Label의 크기를 80, 80 으로 한다.
		contentPane.add(gameBoard, BorderLayout.CENTER);	
		
		/****Menu화면에 눈을 그려주는 객체 생성*****/
		SnowThread snowThread = new SnowThread(model, gameBoard);
		snowThread.start();
		/******************************/
		
		/******Panel에 rankPanel을 등록*****/
		JPanel rankPanel = new JPanel();
		rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS));
		rankPanel.setPreferredSize(new Dimension(100, 100));

		rankPanel.add(rankLabel1);
		rankPanel.add(rankLabel2);
		rankPanel.add(rankLabel3);
		rankPanel.add(rankLabel4);
		rankPanel.add(rankLabel5);
		rankPanel.add(rankLabel6);
		rankPanel.add(rankLabel7);
		/******************************/

		GameSound gameSound = new GameSound("mainmenu.wav");
		
		/****Ranking Class의 객체 생성*******/
		IORanking ioRanking = new IORanking(model, rankPanel, rankLabel1,
				rankLabel2, rankLabel3, rankLabel4, rankLabel5, rankLabel6,
				rankLabel7);
		/*******************************/
		
		/****rank초기화 후 입력******/
		model.setRankTable();
		ioRanking.output();
		/************************/
		
		/*****Listener등록********/
		gameBoard.addMouseListener(new Controller(model, gameBoard, frame,
				timeLabel, contentPane, clearLabel, rankPanel, ioRanking,
				rankLabel1, rankLabel2, rankLabel3, rankLabel4, rankLabel5,
				rankLabel6, rankLabel7, gameSound));
		gameBoard.addMouseMotionListener(new Controller(model, gameBoard));
		/************************/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X버튼을 눌렀을 때 frame창을 닫게 해주기 위해 지정
		frame.pack(); // frame의 크기를 자동으로 설정.
		frame.setVisible(true); // frame을 디스플레이 한다.

		/**************************************************************************/

	}
}
