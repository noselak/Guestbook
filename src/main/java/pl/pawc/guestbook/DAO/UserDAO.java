package pl.pawc.guestbook.DAO;

import pl.pawc.guestbook.POJO.User;

public interface UserDAO{

  public User getUser(String name);
  public void addUser(String name, String hashedPass);
  public void updateLocation(String name, String location);
  public void updateEmail(String name, String email);
  public boolean checkIfUserExists(String name);
  public boolean logIn(String name, String hashedPass);
  public void updatePassword(String name, String hashedPass);

}