package pl.pawc.guestbook.POJO;

public class Exception extends RuntimeException{

	private String message;

	public Exception(String message){
		this.message=message;
	}	

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message=message;
	}

}
