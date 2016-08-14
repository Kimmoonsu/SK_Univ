package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.VerseModel;

import kr.ac.skuniv.java.view.VerseBoard;

public class BombThread extends Thread implements Runnable	{
	VerseModel v_model;
	VerseBoard verseBoard;
	
	/******생성자를 통해 Class를 받아온다.**********/
	public BombThread(VerseModel v_model, VerseBoard verseBoard)
	{
		this.v_model = v_model;
		this.verseBoard = verseBoard;
	}
	/**************************************/
	
	/*****Thread 동작*********/
	public void run() {		
		while (v_model.getBombCheck()) 
		{
			while (v_model.count < 10) 
			{
				try 
				{
					v_model.setEnterState(1);	// 폭탄을 클릭하고 폭탄그림을 지워주기 위해
					v_model.setMap(0, 1, 1, 0);	// 폭탄 그림을 지워준다.
					if(v_model.count==9)
						v_model.setV_EnterState(1);
					Thread.sleep(1000);
					v_model.count++;						
					verseBoard.repaint();
				} 
				catch (InterruptedException e) 
				{
					System.out.println(e.getMessage());
				}
			}
			v_model.setBombCheck(false);
		}
	}
	/**********************/
}
