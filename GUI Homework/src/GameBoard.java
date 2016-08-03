import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GameBoard extends JPanel {
	private final int WIDTH = 100;
	private final int HEIGHT = 100;
	Toolkit toolkit = getToolkit(); // 이미지를 그리기 위해 Toolkit Class사용
	ImageIcon mainView = new ImageIcon("image/backgroundImage.jpg");
	private Model model;
	Font font = new Font("돋움", Font.PLAIN, 45);
	public GameBoard(Model model) {
		this.model = model;
	}
	
	//Image draw Method
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight()); // frame clear
		g.drawImage(mainView.getImage(), 0,0, getWidth(), getHeight(), this); // background image draw
		g.drawImage(toolkit.getImage("image/minusImage.png"), model.getMinus_X(), model.getMinus_Y(),WIDTH,HEIGHT, this); // minus image draw
		g.drawRect(230, 330, 200, 50); // value rect draw
		g.drawImage(toolkit.getImage("image/plusImage.png"), model.getPlus_X(), model.getPlus_Y(),WIDTH,HEIGHT, this); // plus image draw
		g.setFont(font); // font setting
		g.drawString(""+model.getValue(), 320, 370); // value setting
	}
}
