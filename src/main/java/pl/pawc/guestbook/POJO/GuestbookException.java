package pl.pawc.guestbook.POJO;

public class GuestbookException extends RuntimeException{

  private String message;

  public GuestbookException(String message){
    this.message=message;
  }	

  public String getMessage(){
    return message;
  }

  public void setMessage(String message){
    this.message=message;
  }

}