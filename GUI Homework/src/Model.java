
public class Model {
	// value data
	private int value = 0;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	// button click event method
	public void plusEvent() {
		if (value < 20) 
			this.value += 1;
	}
	public void minusEvent() {
		if (value > 0) this.value -= 1;
	}
	
	
	/*************plus & minus button position data*************/
	private int minus_X = 50;
	private int minus_Y = 300;
	private int plus_X = 500;
	private int plus_Y = 300;
	public int getMinus_X() {
		return minus_X;
	}
	public void setMinus_X(int minus_X) {
		this.minus_X = minus_X;
	}
	public int getMinus_Y() {
		return minus_Y;
	}
	public void setMinus_Y(int minus_Y) {
		this.minus_Y = minus_Y;
	}
	public int getPlus_X() {
		return plus_X;
	}
	public void setPlus_X(int plus_X) {
		this.plus_X = plus_X;
	}
	public int getPlus_Y() {
		return plus_Y;
	}
	public void setPlus_Y(int plus_Y) {
		this.plus_Y = plus_Y;
	}
	/*****************************************************/
	
}
