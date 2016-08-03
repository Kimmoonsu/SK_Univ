import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller implements MouseListener{
	private Model model;
	private GameBoard gameBoard;
	public Controller(Model model, GameBoard gameBoard) {
		this.model = model;
		this.gameBoard = gameBoard;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int minus_x = model.getMinus_X();
		int minus_y = model.getMinus_Y();
		int plus_x = model.getPlus_X();
		int plus_y = model.getPlus_Y();
		if ((minus_x <= x && minus_y <= y) && (minus_x+100 >= x && minus_y+100 >= y)) {
			model.minusEvent();
		}
		else if ((plus_x <= x && plus_y <= y) && (plus_x+100 >= x && plus_y+100 >= y)) {
			model.plusEvent();
		}
		gameBoard.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
