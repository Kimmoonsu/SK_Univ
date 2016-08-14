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
	ImageIcon img1 = new ImageIcon("image/�蹮��.png");	// �̹������
	ImageIcon img2 = new ImageIcon("image/������.png");	// �̹������
	public ProgramInfo() {
		
		/************frame ����************************/
		JFrame frame = new JFrame("Info");	// frame���� Info�� �Ѵ�.
		frame.setLocation(300, 100);	// ��ġ ����
		Container contentPane = frame.getContentPane();	
		JButton button1 = new JButton("�蹮��");
		JButton button2 = new JButton("������");
		button1.setPreferredSize(new Dimension(300,300));
		button2.setPreferredSize(new Dimension(300,300));
		button1.setBackground(Color.WHITE);
		button2.setBackground(Color.WHITE);
		contentPane.add(button1, BorderLayout.NORTH);
		contentPane.add(button2, BorderLayout.SOUTH);
		frame.setVisible(true);	// frame�� ���÷����Ѵ�.
		frame.pack();	// frame�� ������ ũ��� �������ش�.
		/***********************************************/
		
		/****************"�蹮��" button Click ActionListener***************/
		ActionListener listener1 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = new JFrame("�蹮��");
				frame.setLocation(300,100);
				Container contentPane = frame.getContentPane();
				JLabel label1 = new JLabel(img1);	// label�� img���
				contentPane.add(label1, BorderLayout.CENTER);	// label�� ��ġ�� �߾����� ����
				frame.setVisible(true);
				frame.pack();
			}
		};
		button1.addActionListener(listener1);
		/*********************************************/
		
		/****************"������" button Click ActionListener***************/
		ActionListener listener2 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = new JFrame("������");
				frame.setLocation(300,100);
				Container contentPane = frame.getContentPane();
				JLabel label2 = new JLabel(img2);	// label�� img���
				contentPane.add(label2, BorderLayout.CENTER);	// label�� ��ġ�� �߾����� ����
				frame.setVisible(true);
				frame.pack();
			}
		};
		button2.addActionListener(listener2);
		/*********************************************/
		
	}
}
