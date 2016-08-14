import java.util.Date;

public class Test1 {
	public static void main(String[] args) {
		Date date1 = new Date();
		String now = ""+date1.getDate()+date1.getHours()+date1.getMinutes()+date1.getSeconds();
		System.out.println("ÇöÀç : " + now);
	}
	
}
