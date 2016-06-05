package pl.pawc.guestbook.DAO;

import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pawc.guestbook.POJO.User;

public class UserJDBCTemplate implements UserDAO {
  
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;

  public void setDataSource(DataSource dataSource){
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public User getUser(String name) {
    String SQL = "Select * from GuestbookUser where name = ?";
    try{
    User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, new UserMapper());
    return user;
    }
    catch(EmptyResultDataAccessException e){
      return null;
    }
  }

  @Override
  public void addUser(String name, String hashedPass) {
    String SQL = "insert into GuestbookUser (name, hashedPass) values (?, ?)";
    jdbcTemplateObject.update(SQL, new Object[]{name, hashedPass} );
  }

  @Override
  public void updateLocation(String name, String location) {
    String SQL = "update GuestbookUser set location = ? where name = ?";
    jdbcTemplateObject.update( SQL, new Object[]{location, name} );
  }
  
  @Override
  public void updateEmail(String name, String email) {
    String SQL = "update GuestbookUser set email = ? where name = ?";
    jdbcTemplateObject.update( SQL, new Object[]{email, name} );
  }

  @Override
  public boolean checkIfUserExists(String name) {
    User user;
    user = getUser(name);
    if(user==null) return false;
    if(name.equals(user.getName())) return true;
    else return false;
  }

  @Override
  public boolean logIn(String name, String hashedPass) {
    User user = getUser(name);
    if(hashedPass.equals(user.getHashedPass())) return true;
    return false;
  }
  
}