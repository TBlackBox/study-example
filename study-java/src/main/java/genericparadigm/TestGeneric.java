package genericparadigm;

public class TestGeneric {

	public static void main(String[] args) {
		int p1 = 2;
		String p2 = "22";
		new TestGeneric().test(p1, p2);
		
		Point p = new Point();
	}
	
	public <T1,T2> void test(T1 p1,T2 p2) {
		T1 x = p1;
		T2 y = p2;
		if(p1 instanceof Number) {
			System.out.println("true");
		}
	}
	
	public <T1 extends Number,T2 extends CharSequence> void test2(T1 p1,T2 p2) {
		T1 x = p1;
		T2 y = p2;
		if(p1 instanceof Number) {
			System.out.println("true");
		}
	}
	
}
