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

	/******** ������ ********/
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

	/****** Thread ���� ********/
	public void run() {
		while (model.coin_count <= 45) {
			try {
				gameBoard.repaint();
				model.coin_count++;
				if (model.coin_count > 5 && model.coin_count <= 40)
					Thread.sleep(50);
				else
					Thread.sleep(300);
				/********* ���� ���� ���� �� score�� ��� ***********/
				if (model.coin_count == 46) {
					
					/*******************���� �׸��� ������ ���***********************/
					if (model.getSelectCoin() == model.getCoin()) 
					{
						if (model.tryPass == 3) // 3�� �õ� ���� ��� ���̻� ���� ���Ѵ�.
						{
							System.out.println("����");
							model.score += (model.tryPass * 3000);
							timeLabel.setText("Score : " + model.score);
							model.setNewRankCheck(true); // ���ο� ������ ������.
							String name = JOptionPane
									.showInputDialog("�̸����Է��Ͽ��ּ���.");
							model.rankTable[model.rankIndex] = "Name : " + name
									+ " Score : " + model.score + '\n';

							/******* coin������ ������ ��� ��ŷ�� ��µǰ� contentPane�� �ٽ� ��� **********/
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
						else if (model.tryPass != 3) // ����Ƚ���� 3�� �̱� ���� ��� �Ұ� ���� ���´�.
						{	
							new GameSound("winner.wav");
							String[] buttons = { "����", "�׸�" };
							result = JOptionPane.showOptionDialog(null,
									"�����Ͽ����ϴ�. �����Ͻðڽ��ϱ�?", "����",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									buttons, "����");
							if (result == 0) // '����'�� ������ ���
							{
								model.tryPass++; // ���� Ƚ�� ����
							} else if (result == 1) // '�׸�'�� ������ ���
							{
								if (model.tryPass == 2) // 2�� ����
								{
									model.score += (model.tryPass * 2000);
									timeLabel.setText("Score : " + model.score);
								} else if (model.tryPass == 1) // 1�� ����
								{
									model.score += (model.tryPass * 1500);
									timeLabel.setText("Score : " + model.score);
								}
								model.setNewRankCheck(true); // ���ο� ������ ������.
								String name = JOptionPane
										.showInputDialog("�̸����Է��Ͽ��ּ���.");
								model.rankTable[model.rankIndex] = "Name : "
										+ name + " Score : " + model.score
										+ '\n';
								
								/******* coin������ ������ ��� ��ŷ�� ��µǰ� contentPane�� �ٽ� ��� **********/
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
						/********* �������� ��� ***************/
					else 
					{
						model.score -= 1000;
						timeLabel.setText("Score : " + model.score);
						model.setNewRankCheck(true); // ���ο� ������ ������.
						JOptionPane.showInputDialog(null,
								"���ӿ� �����Ͽ� Score�� �����Ͽ����ϴ�.", "error",
								JOptionPane.WARNING_MESSAGE);
						String name = JOptionPane
									.showInputDialog("�̸����Է��Ͽ��ּ���.");
						model.rankTable[model.rankIndex] = "Name : " + name
								+ " Score : " + model.score + '\n';
							/******* coin������ ������ ��� ��ŷ�� ��µǰ� contentPane�� �ٽ� ��� **********/
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
