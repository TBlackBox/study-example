package cn.javafroum.studywebsocketstomp.message;

public class ResponseMsg {

	private String responseBody;

	public String getResponseBody() {
		return responseBody;
	}
	
	public ResponseMsg(String responseBody) {
		this.responseBody = responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
}
