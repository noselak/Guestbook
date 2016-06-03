package pl.pawc.guestbook.POJO;

public class EntryException extends RuntimeException{

  private String message;

  public EntryException(String message){
    this.message=message;
  }	

  public String getMessage(){
    return message;
  }

  public void setMessage(String message){
    this.message=message;
  }

}