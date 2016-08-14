package kr.ac.skuniv.java.model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

public class GameSound {
	AudioClip sound;
	
	/******생성자를 통해 문자열로 된 url을 등록하여 준다**********/
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
	   
	   /*****현재 나오고 있는 노래를 stop해주는 메소드*******/
	   public void stopSound() { sound.stop(); }
	   /**************************************/
}
