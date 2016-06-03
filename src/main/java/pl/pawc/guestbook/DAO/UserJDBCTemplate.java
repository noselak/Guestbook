package pl.pawc.guestbook.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pawc.guestbook.POJO.User;

public class UserJDBCTemplate implements UserDAO{

private DataSource dataSource;
private JdbcTemplate jdbcTemplateObject;

  public void setDataSource(DataSource dataSource){
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public User getUser(String name) {
    String SQL = "Select * from GuestbookUser where name = ?";
    User user = jdbcTemplateObject.queryForObject(SQL, new Object[]{name}, new UserMapper());
    return user;
  }

  @Override
  public void addUser(String name, String location, String email, String hashedPass) {
    String SQL = "insert into GuestbookUser (name, location, emil, hashedpass) values (?, ?, ?, ?)";
    jdbcTemplateObject.update(SQL, new Object[]{name, location, email, hashedPass.hashCode()} );
  }

  @Override
  public boolean checkIfUserExists(String name) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}