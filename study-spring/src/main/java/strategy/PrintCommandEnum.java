package strategy;

/**
 * 维护字符串与实现接口的关系
 * @author Administrator
 *
 */
public enum PrintCommandEnum {

	STING_VLAUE("打印字符串。。。","stringPrintCommand"),
	
	INTEGER_VLAUE("打印数字。。。","integerPrintCommand"),
	
	BOOLEAN_VALUE("打印boolean。。。","booleanPrintCommand"),
	
	ALL("打印所有。。。","printAllCommend");
	
	
	public static String getCommand(String printCommandEnum) {
		for(PrintCommandEnum value:PrintCommandEnum.values()) {
			if(value.name().equals(printCommandEnum)) {
				return value.getCommand();
			}
		}
		return null;
	}
	
	
	private String msg;
	private String command;
	PrintCommandEnum(String msg,String command){
		this.msg = msg;
		this.command = command;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
}
