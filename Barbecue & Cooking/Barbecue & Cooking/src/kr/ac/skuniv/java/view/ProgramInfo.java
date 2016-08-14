package kr.ac.skuniv.java.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProgramInfo extends JPanel {
	Toolkit toolkit = getToolkit();	
	ImageIcon img1 = new ImageIcon("image/김문수.png");	// 이미지등록
	ImageIcon img2 = new ImageIcon("image/정수연.png");	// 이미지등록
	public ProgramInfo() {
		
		/************frame 구성************************/
		JFrame frame = new JFrame("Info");	// frame명을 Info라 한다.
		frame.setLocation(300, 100);	// 위치 지정
		Container contentPane = frame.getContentPane();	
		JButton button1 = new JButton("김문수");
		JButton button2 = new JButton("정수연");
		button1.setPreferredSize(new Dimension(300,300));
		button2.setPreferredSize(new Dimension(300,300));
		button1.setBackground(Color.WHITE);
		button2.setBackground(Color.WHITE);
		contentPane.add(button1, BorderLayout.NORTH);
		contentPane.add(button2, BorderLayout.SOUTH);
		frame.setVisible(true);	// frame을 디스플레이한다.
		frame.pack();	// frame을 적절한 크기로 설정해준다.
		/***********************************************/
		
		/****************"김문수" button Click ActionListener***************/
		ActionListener listener1 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = new JFrame("김문수");
				frame.setLocation(300,100);
				Container contentPane = frame.getContentPane();
				JLabel label1 = new JLabel(img1);	// label에 img등록
				contentPane.add(label1, BorderLayout.CENTER);	// label의 위치를 중앙으로 지정
				frame.setVisible(true);
				frame.pack();
			}
		};
		button1.addActionListener(listener1);
		/*********************************************/
		
		/****************"정수연" button Click ActionListener***************/
		ActionListener listener2 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = new JFrame("정수연");
				frame.setLocation(300,100);
				Container contentPane = frame.getContentPane();
				JLabel label2 = new JLabel(img2);	// label에 img등록
				contentPane.add(label2, BorderLayout.CENTER);	// label의 위치를 중앙으로 지정
				frame.setVisible(true);
				frame.pack();
			}
		};
		button2.addActionListener(listener2);
		/*********************************************/
		
	}
}
