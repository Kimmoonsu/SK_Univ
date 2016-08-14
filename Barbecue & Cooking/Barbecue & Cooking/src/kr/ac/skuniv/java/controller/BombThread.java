package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.VerseModel;

import kr.ac.skuniv.java.view.VerseBoard;

public class BombThread extends Thread implements Runnable	{
	VerseModel v_model;
	VerseBoard verseBoard;
	
	/******�����ڸ� ���� Class�� �޾ƿ´�.**********/
	public BombThread(VerseModel v_model, VerseBoard verseBoard)
	{
		this.v_model = v_model;
		this.verseBoard = verseBoard;
	}
	/**************************************/
	
	/*****Thread ����*********/
	public void run() {		
		while (v_model.getBombCheck()) 
		{
			while (v_model.count < 10) 
			{
				try 
				{
					v_model.setEnterState(1);	// ��ź�� Ŭ���ϰ� ��ź�׸��� �����ֱ� ����
					v_model.setMap(0, 1, 1, 0);	// ��ź �׸��� �����ش�.
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
