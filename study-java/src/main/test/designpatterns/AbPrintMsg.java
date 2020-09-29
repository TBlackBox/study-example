package designpatterns;

public abstract class AbPrintMsg implements PrintMsg{

	@Override
	public void println(String msg) {
		printcharMsg(msg);
	}
	
	
	abstract void printcharMsg(String msg);
	
}
