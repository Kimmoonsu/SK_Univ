package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.VerseModel;
import kr.ac.skuniv.java.view.VerseBoard;

//VerseGame�� ������ �� ������ ����Ǿ��ٴ� �̹����� ������ְ� �޴�ȭ������ ���ư��� ���ִ� Class

public class VerseFinishThread extends Thread implements Runnable {
	VerseModel v_model;
	VerseBoard verseBoard;
	int count = 0;
	public VerseFinishThread(VerseModel v_model, VerseBoard verseBoard)
	{
		this.v_model = v_model;
		this.verseBoard = verseBoard;
	}
	
	/******'���� �Ա�' ���� ����� Thread ����***************/
	public void run()
	{
		while (count < 10) {
			try {
				if(count == 1)
					new GameSound("winner.wav");
				if(count == 9)
					new GameSound("mainmenu.wav");
				v_model.finish_count = count;
				verseBoard.repaint();
				count++;
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/********************************************/
	
}


