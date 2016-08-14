package kr.ac.skuniv.java.controller;

import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.ac.skuniv.java.model.Model;

public class IORanking {
	Model model;
	JLabel rankLabel1;
	JLabel rankLabel2;
	JLabel rankLabel3;
	JLabel rankLabel4;
	JLabel rankLabel5;
	JLabel rankLabel6;
	JLabel rankLabel7;
	int outCount = 0;	// 랭킹에 기록될 string을 0부터 출력해주기 위한 변수
	int[] scoreInt = new int[7];	// score를 정수형 으로 반환후 배열에 저장하기 위해 선언.
	JPanel rankPanel;
	
	/*****생성자*******/
	public IORanking(Model model, JPanel rankPanel, JLabel rankLabel1, JLabel rankLabel2, JLabel rankLabel3, JLabel rankLabel4, JLabel rankLabel5, JLabel rankLabel6, JLabel rankLabel7)
	{
		this.model = model;
		this.rankPanel = rankPanel;
		this.rankLabel1 = rankLabel1;
		this.rankLabel2 = rankLabel2;
		this.rankLabel3 = rankLabel3;
		this.rankLabel4 = rankLabel4;
		this.rankLabel5 = rankLabel5;
		this.rankLabel6 = rankLabel6;
		this.rankLabel7 = rankLabel7;
	}
	/****************/
	
	/****StringBuffer로 받아온 문자열 형태로 된 Score를 정수로 변환하는 메소드*******/
	public void scoreToint() {
	   String scoreString = "";
	   for(int i=0; i<=model.rankIndex; i++) 
	   { 
	      scoreString = onlyNum(model.rankTable[i]);
	      scoreInt[i] = Integer.parseInt(scoreString);
	   }
	}
	/**************************************************/
	
	/*****현재 저장된 문자열에서 score만 가져오는 메소드********/
	String onlyNum(String str) {
		if (str == null)
			return "";
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				stringBuffer.append(str.charAt(i));
			}
		}
		return stringBuffer.toString();
	}
	/****************************************************/
	
	/********* 랭킹스코어를 내림차순으로 버블정렬하는 메소드  *************/
	public void sortRanking() {
		System.out.println("rankIndex: " + model.rankIndex);
		for (int i = model.rankIndex; i >= 0; i--) {
			for (int j = 0; j <= i; j++)
				if (scoreInt[j] < scoreInt[j + 1]) {
					int temp = scoreInt[j];
					scoreInt[j] = scoreInt[j + 1];
					scoreInt[j + 1] = temp;
					String s_temp = model.rankTable[j];
					model.rankTable[j] = model.rankTable[j + 1];
					model.rankTable[j + 1] = s_temp;
				}
		}
	}
	/*************************************************/
	
	/*********현재 저장된 내용을 txt 파일에 저장하는 Method**********/
	public void input()
	{
		FileWriter out = null;
		scoreToint();
		sortRanking();
		try 
		{
			out = new FileWriter("ranking.txt");
			sortRanking();
			for (int cnt = 0; cnt < model.rankTable.length; cnt++)
			{
				out.write(model.rankTable[cnt]);
			}
			
		}
		catch (IOException ioe)
		{
			System.out.println("파일로 출력할 수 없습니다.");
		}
		finally	{
			try	{
				out.close();
			}
			catch( Exception e)
			{
				
			}
		}
			model.rankIndex++;
	}
	/*****************************************************/
	
	/*********txt 파일을 읽어서 프로그램에 출력해주는 Method*********/
	public void output()
	{
		outCount = 0;	// 출력을 처음부터 다시 해주기 위해 0으로 초기화
		model.setNewRankCheck(false); 	// 
		model.setRankTable();	// 저장되어있는 문자열을 초기화해준다.
		FileReader reader = null;
		try	{
			reader = new FileReader("ranking.txt");
			
			while (true)
			{
				int data = reader.read();
				if (data == -1)
					break;
				char ch = (char)data;
				model.rankTable[outCount] += ch;
				// string을 구분하기 위해 사용
				if( ch =='\n')
					outCount++;
			}
			model.rankIndex = outCount;
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch (IOException ioe){
			System.out.println("읽을 수 없습니다.");
		}
		finally {
			try	{
				reader.close();
			}
			catch (Exception e)	{
				
			}
		}
		
			/******rank이 입력되는 label의 내용을 변경해 준다.*******/
		    rankLabel1.setFont(new Font("", 0, 20));
		    rankLabel2.setFont(new Font("", 0, 20));
		    rankLabel3.setFont(new Font("", 0, 20));
		    rankLabel4.setFont(new Font("", 0, 20));
		    rankLabel5.setFont(new Font("", 0, 20));
		    rankLabel6.setFont(new Font("", 0, 20));
		    rankLabel1.setPreferredSize(new Dimension(300,150));
		    rankLabel2.setPreferredSize(new Dimension(300,150));
		    rankLabel3.setPreferredSize(new Dimension(300,150));
		    rankLabel4.setPreferredSize(new Dimension(300,150));
		    rankLabel5.setPreferredSize(new Dimension(300,150));
		    rankLabel6.setPreferredSize(new Dimension(300,150));

			rankLabel1.setText(1 + " " + model.rankTable[0]);
			rankLabel2.setText(2 + " " + model.rankTable[1]);
			rankLabel3.setText(3 + " " + model.rankTable[2]);
			rankLabel4.setText(4 + " " + model.rankTable[3]);
			rankLabel5.setText(5 + " " + model.rankTable[4]);
			rankLabel6.setText(6 + " " + model.rankTable[5]);
	    	/***************************************************/
	}
	/***************************************************/
	
}
