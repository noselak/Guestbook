package pl.pawc.guestbook.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pawc.guestbook.POJO.Entry;
import java.util.List;

public class EntryJDBCTemplate implements EntryDAO{

  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;

  public void setDataSource(DataSource dataSource){
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Entry getEntry(int id){
    String SQL = "Select * from Guestbook where id = ?";
    Entry entry = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new EntryMapper());
    return entry;
  }

  @Override
  public List<Entry> getAllEntries(){
    String SQL = "select * from Guestbook order by id DESC";
    List<Entry> entries = jdbcTemplateObject.query(SQL, new EntryMapper());
    return entries;
  }

  @Override
  public void addEntry(String name, String message){
    String SQL = "insert into Guestbook (name, message) values (?, ?)";
    jdbcTemplateObject.update(SQL, new Object[]{name, message} );
  }

}