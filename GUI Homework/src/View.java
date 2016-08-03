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
	JFrame frame = new JFrame("Plus & Minus"); // frame �̸��� Barbecue & Cooking�� ����
	Toolkit toolkit = getToolkit(); // �̹����� �׸��� ���� Toolkit Class���
	Model model = new Model(); // �̸��� model�� �ϴ� Model Class�� ��ü ����
	JPanel panel = new JPanel();
	
	Container contentPane = frame.getContentPane();
	JLabel label = new JLabel();
	GameBoard gameBoard = new GameBoard(model);

	public View() {
		/*********************Frame����*******************************/
		frame.setLocation(10, 10); // frame��ġ�� ���������� 10, �Ʒ��� 10 ��ġ�� ����
		frame.setPreferredSize(new Dimension(700, 900)); // frameũ�⸦ 700, 900���� ����
		contentPane.add(gameBoard, BorderLayout.CENTER);
		
//		/******Panel�� rankPanel�� ���*****/
//		JPanel rankPanel = new JPanel();
//		rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS));
//		rankPanel.setPreferredSize(new Dimension(100, 100));
//
//		rankPanel.add(rankLabel1);

		/*****Listener���********/
		gameBoard.addMouseListener(new Controller(model, gameBoard));	
		
		/************************/		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X��ư�� ������ �� frameâ�� �ݰ� ���ֱ� ���� ����
		frame.pack(); // frame�� ũ�⸦ �ڵ����� ����.
		frame.setVisible(true); // frame�� ���÷��� �Ѵ�.

		/**************************************************************************/

	}
}