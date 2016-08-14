package kr.ac.skuniv.java.controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.ac.skuniv.java.model.GameSound;
import kr.ac.skuniv.java.model.Model;
import kr.ac.skuniv.java.model.Model.MENU;
import kr.ac.skuniv.java.view.GameBoard;

public class CoinThread extends Thread implements Runnable {
	Model model;
	GameBoard gameBoard;
	JFrame frame;
	JLabel timeLabel;
	Container contentPane;
	JPanel rankPanel;
	IORanking ioRanking;
	int result = 0;

	/******** 생성자 ********/
	public CoinThread(Model model, GameBoard gameBoard, JFrame frame,
			JLabel timeLabel, Container contentPane, JPanel rankPanel,
			IORanking ioRanking) {
		this.model = model;
		this.gameBoard = gameBoard;
		this.frame = frame;
		this.timeLabel = timeLabel;
		this.contentPane = contentPane;
		this.rankPanel = rankPanel;
		this.ioRanking = ioRanking;
	}

	/******************/

	/****** Thread 동작 ********/
	public void run() {
		while (model.coin_count <= 45) {
			try {
				gameBoard.repaint();
				model.coin_count++;
				if (model.coin_count > 5 && model.coin_count <= 40)
					Thread.sleep(50);
				else
					Thread.sleep(300);
				/********* 동전 게임 진행 후 score값 계산 ***********/
				if (model.coin_count == 46) {
					
					/*******************같은 그림이 나왔을 경우***********************/
					if (model.getSelectCoin() == model.getCoin()) 
					{
						if (model.tryPass == 3) // 3번 시도 했을 경우 더이상 게임 안한다.
						{
							System.out.println("들어옴");
							model.score += (model.tryPass * 3000);
							timeLabel.setText("Score : " + model.score);
							model.setNewRankCheck(true); // 새로운 게임이 끝났다.
							String name = JOptionPane
									.showInputDialog("이름을입력하여주세요.");
							model.rankTable[model.rankIndex] = "Name : " + name
									+ " Score : " + model.score + '\n';

							/******* coin게임이 끝났을 경우 랭킹이 출력되고 contentPane을 다시 등록 **********/
							//ioRanking.input();
							contentPane.remove(timeLabel);
							contentPane.remove(gameBoard);
							model.menuState = MENU.MENU;
							model.setStopCheck(false);
							model.setCompareState(false);
							model.setCompareStopCheck(false);
							frame.setSize(700, 900);
							contentPane.add(gameBoard, BorderLayout.CENTER);
							model.setSecond(0);
							model.menu_state = 0;
							model.tryPass = 1;
							ioRanking.input();
							/*********************************************************/
							gameBoard.repaint();
							model.setfinishCheck(false);
							
						}
						else if (model.tryPass != 3) // 성공횟수가 3번 이기 전에 계속 할것 인지 묻는다.
						{	
							new GameSound("winner.wav");
							String[] buttons = { "도전", "그만" };
							result = JOptionPane.showOptionDialog(null,
									"성공하였습니다. 도전하시겠습니까?", "도전",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									buttons, "도전");
							if (result == 0) // '도전'을 눌렀을 경우
							{
								model.tryPass++; // 성공 횟수 증가
							} else if (result == 1) // '그만'을 눌렀을 경우
							{
								if (model.tryPass == 2) // 2번 성공
								{
									model.score += (model.tryPass * 2000);
									timeLabel.setText("Score : " + model.score);
								} else if (model.tryPass == 1) // 1번 성공
								{
									model.score += (model.tryPass * 1500);
									timeLabel.setText("Score : " + model.score);
								}
								model.setNewRankCheck(true); // 새로운 게임이 끝났다.
								String name = JOptionPane
										.showInputDialog("이름을입력하여주세요.");
								model.rankTable[model.rankIndex] = "Name : "
										+ name + " Score : " + model.score
										+ '\n';
								
								/******* coin게임이 끝났을 경우 랭킹이 출력되고 contentPane을 다시 등록 **********/
								//
								contentPane.remove(timeLabel);
								contentPane.remove(gameBoard);
								model.menuState = MENU.MENU;
								model.setStopCheck(false);
								model.setCompareState(false);
								model.setCompareStopCheck(false);
								frame.setSize(700, 900);
								contentPane.remove(timeLabel);
								contentPane.remove(gameBoard);
								contentPane.add(gameBoard, BorderLayout.CENTER);
								model.setSecond(0);
								model.menu_state = 0;
								model.tryPass = 1;
								ioRanking.input();
								/************************************************************/
								gameBoard.repaint();
							}
						}
					}	
						/********* 실패했을 경우 ***************/
					else 
					{
						model.score -= 1000;
						timeLabel.setText("Score : " + model.score);
						model.setNewRankCheck(true); // 새로운 게임이 끝났다.
						JOptionPane.showInputDialog(null,
								"게임에 실패하여 Score가 감소하였습니다.", "error",
								JOptionPane.WARNING_MESSAGE);
						String name = JOptionPane
									.showInputDialog("이름을입력하여주세요.");
						model.rankTable[model.rankIndex] = "Name : " + name
								+ " Score : " + model.score + '\n';
							/******* coin게임이 끝났을 경우 랭킹이 출력되고 contentPane을 다시 등록 **********/
						//ioRanking.input();
						contentPane.remove(timeLabel);
						contentPane.remove(gameBoard);
						model.menuState = MENU.MENU;
						model.setStopCheck(false);
						model.setCompareState(false);
						model.setCompareStopCheck(false);
						frame.setSize(700, 900);
						contentPane.add(gameBoard, BorderLayout.CENTER);
						model.setSecond(0);
						model.menu_state = 0;
						model.tryPass = 1;
						ioRanking.input();
						/*********************************************************/
						gameBoard.repaint();
						model.setfinishCheck(false);
					}
					/*********************************/
				}
		
			/******************************************/
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
