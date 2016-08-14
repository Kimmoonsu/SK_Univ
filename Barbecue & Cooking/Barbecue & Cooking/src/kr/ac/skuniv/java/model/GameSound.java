package kr.ac.skuniv.java.model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

public class GameSound {
	AudioClip sound;
	
	/******�����ڸ� ���� ���ڿ��� �� url�� ����Ͽ� �ش�**********/
	   public GameSound(String url) {
	      try 
	      {
	         File file = new File(url);
	         sound = Applet.newAudioClip(file.toURL());
	         sound.play();
	      }
	         catch(Exception e) {
	         System.out.println(e); 
	      }
	   }
	   /***************************************/
	   
	   /*****���� ������ �ִ� �뷡�� stop���ִ� �޼ҵ�*******/
	   public void stopSound() { sound.stop(); }
	   /**************************************/
}
