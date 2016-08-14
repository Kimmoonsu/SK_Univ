package kr.ac.skuniv.java.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.view.HowToPlay;
import kr.ac.skuniv.java.view.ProgramInfo;
import kr.ac.skuniv.java.view.View;


public class MenuClick implements ActionListener {
	/***************************��� ���� ����***********************************/
	Model model;
	/************************************************************************/
	
	/*****************************������***********************************/
	public MenuClick(Model model)	// model��ü�� View Class�� �ִ� Field�� Label�� �����ڸ� ���� ������
	{
		this.model = model;
	}
	/********************************************************************/
	
	/************************Listener************************************/
	public void actionPerformed(ActionEvent e)	// Ŭ���Ͽ��� ��� �߻��ϴ� Listener
	{
		String command = e.getActionCommand();
		if(command.equals("��������"))	// "��������"�� Ŭ���Ͽ��� ���
		{     System.exit(0);	}	// ���α׷� ����
		else if(command.equals("���� ���"))	// "���� ���"�� Ŭ���Ͽ��� ���
		{	new HowToPlay();	}	// HowToPlayView Class ȣ��
		else if(command.equals("���α׷� ����"))	// "���α׷� ����"�� Ŭ���Ͽ��� ���
		{	new ProgramInfo();	}	// ProgramInfoView Class ȣ��
	}
	/********************************************************************/
}
