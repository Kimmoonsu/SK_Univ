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
	int outCount = 0;	// ��ŷ�� ��ϵ� string�� 0���� ������ֱ� ���� ����
	int[] scoreInt = new int[7];	// score�� ������ ���� ��ȯ�� �迭�� �����ϱ� ���� ����.
	JPanel rankPanel;
	
	/*****������*******/
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
	
	/****StringBuffer�� �޾ƿ� ���ڿ� ���·� �� Score�� ������ ��ȯ�ϴ� �޼ҵ�*******/
	public void scoreToint() {
	   String scoreString = "";
	   for(int i=0; i<=model.rankIndex; i++) 
	   { 
	      scoreString = onlyNum(model.rankTable[i]);
	      scoreInt[i] = Integer.parseInt(scoreString);
	   }
	}
	/**************************************************/
	
	/*****���� ����� ���ڿ����� score�� �������� �޼ҵ�********/
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
	
	/********* ��ŷ���ھ ������������ ���������ϴ� �޼ҵ�  *************/
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
	
	/*********���� ����� ������ txt ���Ͽ� �����ϴ� Method**********/
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
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
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
	
	/*********txt ������ �о ���α׷��� ������ִ� Method*********/
	public void output()
	{
		outCount = 0;	// ����� ó������ �ٽ� ���ֱ� ���� 0���� �ʱ�ȭ
		model.setNewRankCheck(false); 	// 
		model.setRankTable();	// ����Ǿ��ִ� ���ڿ��� �ʱ�ȭ���ش�.
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
				// string�� �����ϱ� ���� ���
				if( ch =='\n')
					outCount++;
			}
			model.rankIndex = outCount;
		}
		catch (FileNotFoundException fnfe)
		{
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch (IOException ioe){
			System.out.println("���� �� �����ϴ�.");
		}
		finally {
			try	{
				reader.close();
			}
			catch (Exception e)	{
				
			}
		}
		
			/******rank�� �ԷµǴ� label�� ������ ������ �ش�.*******/
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
