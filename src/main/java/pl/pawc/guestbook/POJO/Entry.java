package pl.pawc.guestbook.POJO;

public class Entry{

  private int id;
  private String name;
  private String message;
  private String location;

  public void setLocation(String location) {
    this.location = location;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLocation() {
    return location;
  }

  public String getEmail() {
    return email;
  }
  private String email;

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getMessage(){
    return message;
  }

  public void setId(int id){
    this.id=id;
  }

  public void setName(String name){
    this.name=name;
  }

  public void setMessage(String message){
    this.message=message;
  }

}