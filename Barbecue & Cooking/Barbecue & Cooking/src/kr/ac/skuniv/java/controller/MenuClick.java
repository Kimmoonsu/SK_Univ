package kr.ac.skuniv.java.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.view.HowToPlay;
import kr.ac.skuniv.java.view.ProgramInfo;
import kr.ac.skuniv.java.view.View;


public class MenuClick implements ActionListener {
	/***************************멤버 변수 설정***********************************/
	Model model;
	/************************************************************************/
	
	/*****************************생성자***********************************/
	public MenuClick(Model model)	// model객체와 View Class에 있는 Field와 Label을 생성자를 통해 가져옴
	{
		this.model = model;
	}
	/********************************************************************/
	
	/************************Listener************************************/
	public void actionPerformed(ActionEvent e)	// 클릭하였을 경우 발생하는 Listener
	{
		String command = e.getActionCommand();
		if(command.equals("게임종료"))	// "게임종료"을 클릭하였을 경우
		{     System.exit(0);	}	// 프로그램 종료
		else if(command.equals("게임 방법"))	// "게임 방법"을 클릭하였을 경우
		{	new HowToPlay();	}	// HowToPlayView Class 호출
		else if(command.equals("프로그램 정보"))	// "프로그램 정보"을 클릭하였을 경우
		{	new ProgramInfo();	}	// ProgramInfoView Class 호출
	}
	/********************************************************************/
}
