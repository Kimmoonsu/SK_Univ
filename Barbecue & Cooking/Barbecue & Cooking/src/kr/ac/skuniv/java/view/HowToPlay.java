package kr.ac.skuniv.java.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HowToPlay extends JPanel {
	Toolkit toolkit = getToolkit();
	ImageIcon img = new ImageIcon("image/���ӹ��.png");	// img��ü�� �̹��� ���
	public HowToPlay() {
		
		JFrame frame = new JFrame("How to Play");	// �������� �����.
		frame.setPreferredSize(new Dimension(870, 750));	// ������ ��ġ ����
		frame.setLocation(100, 100);	// ������ ũ�� ����

		Container contentPane = frame.getContentPane(); // content pane return

		JLabel label = new JLabel(img);	// label�� �̹��� ���
		contentPane.add(label, BorderLayout.CENTER);	// label�� ��ġ�� �߾����� ����

		frame.setVisible(true);	// frame���
		frame.pack();	// frame�� ������ ũ��� ����
	}
}
