package imb.gc4.turnero.util;

public class ErrorResponse {
	private String error;
	private String message;
	
	public ErrorResponse(String error, String message) {
		this.error = error;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMenssage(String message) {
		this.message = message;
	}
	
	
}
