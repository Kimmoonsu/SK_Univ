import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class View extends JPanel{
	JFrame frame = new JFrame("Plus & Minus"); // frame 이름을 Barbecue & Cooking로 지정
	Toolkit toolkit = getToolkit(); // 이미지를 그리기 위해 Toolkit Class사용
	Model model = new Model(); // 이름을 model로 하는 Model Class의 객체 생성
	JPanel panel = new JPanel();
	
	Container contentPane = frame.getContentPane();
	JLabel label = new JLabel();
	GameBoard gameBoard = new GameBoard(model);

	public View() {
		/*********************Frame구성*******************************/
		frame.setLocation(10, 10); // frame위치를 오른쪽으로 10, 아래로 10 위치로 지정
		frame.setPreferredSize(new Dimension(700, 900)); // frame크기를 700, 900으로 지정
		contentPane.add(gameBoard, BorderLayout.CENTER);
		
//		/******Panel에 rankPanel을 등록*****/
//		JPanel rankPanel = new JPanel();
//		rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS));
//		rankPanel.setPreferredSize(new Dimension(100, 100));
//
//		rankPanel.add(rankLabel1);

		/*****Listener등록********/
		gameBoard.addMouseListener(new Controller(model, gameBoard));	
		
		/************************/		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X버튼을 눌렀을 때 frame창을 닫게 해주기 위해 지정
		frame.pack(); // frame의 크기를 자동으로 설정.
		frame.setVisible(true); // frame을 디스플레이 한다.

		/**************************************************************************/

	}
}