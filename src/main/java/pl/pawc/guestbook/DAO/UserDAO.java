package pl.pawc.guestbook.DAO;

import pl.pawc.guestbook.POJO.User;

public interface UserDAO{

  public User getUser(String name);
  public void addUser(String name, String location, String email, String hashedpass);
  public boolean checkIfUserExists(String name);

}