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
	JFrame frame = new JFrame("Barbecue & Cooking"); // frame �̸��� Barbecue & Cooking�� ����
	Model model = new Model(); // �̸��� model�� �ϴ� Model Class�� ��ü ����
	VerseModel v_model = new VerseModel();
	JMenuBar menuBar = new JMenuBar(); // Menu�� ���� Class ��ü menuBar����
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
		
		/****************** menu ���� ********************/
		JMenu game = new JMenu("����(G)"); // "����(G)"��� �޴� ����
		JMenuItem exit = new JMenuItem("��������"); // "��������"��� �޴� Item ����
		game.add(exit); // "��������" �޴� Item ���
		exit.addActionListener(new MenuClick(model)); // "��������"�� Ŭ������ �� �߻��ϴ�
														// Listener���

		JMenu help = new JMenu("����(H)"); // "����(H)" ��� �޴� ����

		JMenuItem how = new JMenuItem("���� ���"); // "���� ���" �̶�� �޴� Item ����
		JMenuItem info = new JMenuItem("���α׷� ����"); // "���α׷� ����" ��� �޴� Item ����
		how.addActionListener(new MenuClick(model)); // "���ӹ��"�� Ŭ������ �� �߻��ϴ�
														// Listener���
		info.addActionListener(new MenuClick(model)); // "���α׷� ����"�� Ŭ������ �� �߻��ϴ�
														// Listener���

		help.add(how); // "���� ���" �޴� Item ���
		help.add(info); // "���α׷� ����" �޴� Item ���
		menuBar.add(game); // "����(G)" �޴��ٵ��
		menuBar.add(help); // "����(H)" �޴��ٵ��

		/************************************************/

		
		/*********************Frame����*******************************/
		frame.setLocation(10, 10); // frame��ġ�� ���������� 10, �Ʒ��� 10 ��ġ�� ����
		frame.setPreferredSize(new Dimension(700, 900)); // frameũ�⸦ 700, 900���� ����
		frame.setJMenuBar(menuBar); // frame�� Menu Bar�� ������ش�.
		timeLabel.setPreferredSize(new Dimension(80, 80));	// �ð��� �˷��ִ� Label�� ũ�⸦ 80, 80 ���� �Ѵ�.
		contentPane.add(gameBoard, BorderLayout.CENTER);	
		
		/****Menuȭ�鿡 ���� �׷��ִ� ��ü ����*****/
		SnowThread snowThread = new SnowThread(model, gameBoard);
		snowThread.start();
		/******************************/
		
		/******Panel�� rankPanel�� ���*****/
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
		
		/****Ranking Class�� ��ü ����*******/
		IORanking ioRanking = new IORanking(model, rankPanel, rankLabel1,
				rankLabel2, rankLabel3, rankLabel4, rankLabel5, rankLabel6,
				rankLabel7);
		/*******************************/
		
		/****rank�ʱ�ȭ �� �Է�******/
		model.setRankTable();
		ioRanking.output();
		/************************/
		
		/*****Listener���********/
		gameBoard.addMouseListener(new Controller(model, gameBoard, frame,
				timeLabel, contentPane, clearLabel, rankPanel, ioRanking,
				rankLabel1, rankLabel2, rankLabel3, rankLabel4, rankLabel5,
				rankLabel6, rankLabel7, gameSound));
		gameBoard.addMouseMotionListener(new Controller(model, gameBoard));
		/************************/
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X��ư�� ������ �� frameâ�� �ݰ� ���ֱ� ���� ����
		frame.pack(); // frame�� ũ�⸦ �ڵ����� ����.
		frame.setVisible(true); // frame�� ���÷��� �Ѵ�.

		/**************************************************************************/

	}
}
