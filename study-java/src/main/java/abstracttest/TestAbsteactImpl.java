package abstracttest;

public class TestAbsteactImpl extends testAbsteact{

	@Override
	public String getName() {
		int a = 2;
		assert a == 32:"我都不想说什么了";
		System.out.println("dd");
		return null;
	}
	
	public static void main(String[] args) {
		new TestAbsteactImpl().getName();
	}

}
