package kr.ac.skuniv.java.controller;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.ac.skuniv.java.model.Model;

public class TimeThread extends Thread implements Runnable {
	
	Model model;
	JLabel timeLabel;
	private int second = 0; // 초
	private int minute = 0;	// 분
	private int hour = 0;	// 초
	public TimeThread(Model model, JLabel timeLabel) {
		this.model = model;
		this.timeLabel = timeLabel;
	}
	
	/*************Thread Start*********************/
	public void run() {
		while (model.getStopCheck()) {	// 남은 시간이 0이 아닌 동안 계속 동작
			try {
				// font변경
				timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
				
				/******시, 분, 초 환산********/
				timeLabel.setText(
					(hour/10) + (hour%10) + " : " 
				  + (minute/10) + (minute%10)  + " : " 
				  + (second/10) + (second%10) 
				);
				/***********************/
				
				second++; // 시간에 따라 second(초) 증가
				
				/*****model로 data전송****/
				model.setSecond(second);
				model.setMinute(minute);
				model.setHour(hour);
				/**********************/
				
				/***시, 분, 초, 계산****/
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
				Thread.sleep(1000); // sleep(1000) -> 1초
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	/*******************************************/
}
