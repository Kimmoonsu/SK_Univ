package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.view.GameBoard;

public class SnowThread extends Thread implements Runnable {
	Model model;
	GameBoard gameBoard;
	public SnowThread(Model model, GameBoard gameBoard)
	{
		this.model = model;
		this.gameBoard = gameBoard;
	}
	
	/************메뉴 화면에 눈을 뿌려주는 thread 동작 메소드***********************/
	public void run()
	{
		while(model.getSnowCheck())
		{
			try
			{
				gameBoard.repaint();	// GameBoard를 다시 그려준다.
				model.snowCount[0]++; // 첫번째로 생성된 눈이 내려오게 증가해준다.
				
				/*******thread 추가 생성******/
				// 첫번째 thread가 일정 시간이 지나면 두 번째 thread 발생
				if(model.snowCount[0] > 20) 
				{
					model.snowCount[1]++;
				}
				// 두 번째 thread가 일정 시간이 지나면 세 번째 thread 발생
				if(model.snowCount[1] > 20)
				{
					model.snowCount[2]++;
				}
				// 세 번째 thread가 일정 시간이 지나면 네 번째 thread 발생
				if(model.snowCount[2] > 20)
				{
					model.snowCount[3]++;
				}
				// 네 번째 thread가 일정 시간이 지나면 다시 첫 번째 thread 발생
				if(model.snowCount[3] == 20)
					model.snowCount[0]=0;
				
				/**************************/
				
				/****초기화해주고 thread 순차적으로 발생****/
				if(model.snowCount[0] == 20)
					model.snowCount[1] = 0;
				if(model.snowCount[1] == 20)
					model.snowCount[2] = 0;
				if(model.snowCount[2] == 20)
					model.snowCount[3] = 0;
				/*****************************/
				
				Thread.sleep(300);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*************************************************************/
}
