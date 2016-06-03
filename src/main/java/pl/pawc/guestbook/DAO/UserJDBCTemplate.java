package pl.pawc.guestbook.DAO;

import javax.sql.DataSource;
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
    throw new UnsupportedOperationException("Not supported yet."); 
  }

  @Override
  public void addUser(String name, String hashedPass) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void updateUser(String name, String location, String email, String hashedPass) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
}