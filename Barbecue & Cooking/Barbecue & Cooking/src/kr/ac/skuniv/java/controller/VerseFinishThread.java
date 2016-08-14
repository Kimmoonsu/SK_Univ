package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.VerseModel;
import kr.ac.skuniv.java.view.VerseBoard;

//VerseGame이 끝났을 때 게임이 종료되었다는 이미지를 출력해주고 메뉴화면으로 돌아가게 해주는 Class

public class VerseFinishThread extends Thread implements Runnable {
	VerseModel v_model;
	VerseBoard verseBoard;
	int count = 0;
	public VerseFinishThread(VerseModel v_model, VerseBoard verseBoard)
	{
		this.v_model = v_model;
		this.verseBoard = verseBoard;
	}
	
	/******'둘이 먹기' 게임 종료시 Thread 동작***************/
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


