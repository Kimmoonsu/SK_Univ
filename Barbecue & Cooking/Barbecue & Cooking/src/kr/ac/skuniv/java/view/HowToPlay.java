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
	ImageIcon img = new ImageIcon("image/게임방법.png");	// img객체에 이미지 등록
	public HowToPlay() {
		
		JFrame frame = new JFrame("How to Play");	// 프레임을 만든다.
		frame.setPreferredSize(new Dimension(870, 750));	// 프레임 위치 지정
		frame.setLocation(100, 100);	// 프레임 크기 지정

		Container contentPane = frame.getContentPane(); // content pane return

		JLabel label = new JLabel(img);	// label에 이미지 등록
		contentPane.add(label, BorderLayout.CENTER);	// label의 위치를 중앙으로 지정

		frame.setVisible(true);	// frame등록
		frame.pack();	// frame을 적절한 크기로 지정
	}
}
