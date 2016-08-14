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
	
	/************�޴� ȭ�鿡 ���� �ѷ��ִ� thread ���� �޼ҵ�***********************/
	public void run()
	{
		while(model.getSnowCheck())
		{
			try
			{
				gameBoard.repaint();	// GameBoard�� �ٽ� �׷��ش�.
				model.snowCount[0]++; // ù��°�� ������ ���� �������� �������ش�.
				
				/*******thread �߰� ����******/
				// ù��° thread�� ���� �ð��� ������ �� ��° thread �߻�
				if(model.snowCount[0] > 20) 
				{
					model.snowCount[1]++;
				}
				// �� ��° thread�� ���� �ð��� ������ �� ��° thread �߻�
				if(model.snowCount[1] > 20)
				{
					model.snowCount[2]++;
				}
				// �� ��° thread�� ���� �ð��� ������ �� ��° thread �߻�
				if(model.snowCount[2] > 20)
				{
					model.snowCount[3]++;
				}
				// �� ��° thread�� ���� �ð��� ������ �ٽ� ù ��° thread �߻�
				if(model.snowCount[3] == 20)
					model.snowCount[0]=0;
				
				/**************************/
				
				/****�ʱ�ȭ���ְ� thread ���������� �߻�****/
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
