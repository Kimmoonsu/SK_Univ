package kr.ac.skuniv.java.controller;

import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.view.GameBoard;

public class CompareThread extends Thread implements Runnable {
	Model model;
	GameBoard gameBoard;
	
	/*******multiThread작동 중일 경우 다른 Thread에 접근 못하게 막기 위한 State (최종보고서 중요 로직에 쓸것)****/
	private boolean state1 = true;
	private boolean state2 = true;
	private boolean state3 = true;
	private boolean state4 = true;
	/**************************************************************/
	
	/**********각 Thread마다 run되는 횟수를 알려주는 변수*********/
	private int count1 = 0;
	private int count2 = 0;
	private int count3 = 0;
	private int count4 = 0;
	/******************************************************/
	
	public CompareThread(Model model, GameBoard gameBoard) {
		this.model = model;
		this.gameBoard = gameBoard;
	}

	public void run() {
		/********* thread1 ************/
		while(model.getCompareStopCheck())
		{			
		if (model.thread_index == 0 && state1 == true) {
			model.thread1 = 0;
			state1 = false;	//한번 들어오고 더이상 들어오지 못하게 막는다.
			while (count1 < 10) {
				try {
					
					model.compare_count1 = count1;
					/******** 마지막 전에 다시 원래 이미지 출력을 위해 state 변화 준다 *******/
					if (count1 == 9) {
						if(model.thread2 == 10)
						{
							model.setCompareState(false);
							gameBoard.repaint();	
						}
						model.thread1=10;
						return;
					}
					
					/***************************************************/
					gameBoard.repaint();
					count1++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally
				{
					if(model.menuState==MENU.MENU)
					{
						count1 = 10;
					}
				}
			}
		}
		
		/***********************/

		/********* thread2 ************/
		else if (model.thread_index == 1 && state2 == true) {
			model.thread2 = 1;
			state2 = false;
			while (count2 < 10) {
				try {
					model.compare_count2 = count2;
					/******** 마지막 전에 다시 원래 이미지 출력을 위해 state 변화 준다 *******/
					if (count2 == 9) {
						
						if(model.thread3 == 10)
						{
							model.setCompareState(false);
							gameBoard.repaint();
						}
						model.thread2=10;
						return;
					}
					/***************************************************/
					gameBoard.repaint();
					count2++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally
				{
					if(model.menuState==MENU.MENU)
					{
						count2 = 10;
					}
					model.compare_count3 = count3;
				}
			}
		}
		/********* thread3 ************/
		else if (model.thread_index == 2 && state3 == true) {
			model.thread3 = 2;
			state3 = false;
			while (count3 < 10) {
				try {
					
					model.compare_count3 = count3;
					/******** 마지막 전에 다시 원래 이미지 출력을 위해 state 변화 준다 *******/
					if (count3 == 9) {
						if(model.thread4 == 10)
						{
							model.setCompareState(false);
							gameBoard.repaint();
						}
						model.thread3=10;
						return;
					}
					/***************************************************/
					gameBoard.repaint();
					count3++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally
				{
					if(model.menuState==MENU.MENU)
					{
						count3 = 10;
					}
				}
			}
		}
		/********* thread4 ************/
		else if (model.thread_index == 3 && state4 == true) {
			model.thread4 = 3;
			state4 = false;
			while (count4 < 10) {
				try {
					
					model.compare_count4 = count4;
					
					/******** 마지막 전에 다시 원래 이미지 출력을 위해 state 변화 준다 *******/
					if (count4 == 9) {
						if(model.thread1 == 10)
						{
							model.setCompareState(false);
							gameBoard.repaint();
						}
						model.thread4 = 10;
						return;
					}
					/***************************************************/
					gameBoard.repaint();
					count4++;
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally
				{
					if(model.menuState==MENU.MENU)
					{
						count4 = 10;
					}
				}
			}
		}

		/***********************/

		}
		/***********************/
		
	}


}