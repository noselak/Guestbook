package pl.pawc.guestbook.POJO;

public class User {
  
  private int id;
  private String name;
  private String email;
  private String location;
  private String hashedPass;

  public void setId(int id){
    this.id=id;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public void setHashedPass(String hashedPass) {
    this.hashedPass = hashedPass;
  }
  
  public int getId(){
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getLocation() {
    return location;
  }

  public String getHashedPass() {
    return hashedPass;
  }
    
}