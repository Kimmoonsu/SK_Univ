package kr.ac.skuniv.java.controller;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.ac.skuniv.java.model.Model;

public class TimeThread extends Thread implements Runnable {
	
	Model model;
	JLabel timeLabel;
	private int second = 0; // ��
	private int minute = 0;	// ��
	private int hour = 0;	// ��
	public TimeThread(Model model, JLabel timeLabel) {
		this.model = model;
		this.timeLabel = timeLabel;
	}
	
	/*************Thread Start*********************/
	public void run() {
		while (model.getStopCheck()) {	// ���� �ð��� 0�� �ƴ� ���� ��� ����
			try {
				// font����
				timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
				
				/******��, ��, �� ȯ��********/
				timeLabel.setText(
					(hour/10) + (hour%10) + " : " 
				  + (minute/10) + (minute%10)  + " : " 
				  + (second/10) + (second%10) 
				);
				/***********************/
				
				second++; // �ð��� ���� second(��) ����
				
				/*****model�� data����****/
				model.setSecond(second);
				model.setMinute(minute);
				model.setHour(hour);
				/**********************/
				
				/***��, ��, ��, ���****/
				if(second==60)
				{
					second=0;
					minute++;
					if(minute == 60)
					{
						minute = 0;
						hour++;
					}
				}
				/********************/
				Thread.sleep(1000); // sleep(1000) -> 1��
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/*******************************************/
}
