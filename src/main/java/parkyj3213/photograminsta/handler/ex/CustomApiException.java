package parkyj3213.photograminsta.handler.ex;

public class CustomApiException extends RuntimeException{
	
	// 객체 구분할 때
	private static final long serialVersionUID = 1L;
	
	public CustomApiException(String message) {
		super(message);
	}
}
