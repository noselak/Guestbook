  package pl.pawc.guestbook.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pl.pawc.guestbook.POJO.Entry;

public class EntryMapper implements RowMapper<Entry>{

  public Entry mapRow(ResultSet rs, int rowNum) throws SQLException{

    Entry entry = new Entry();
    entry.setId(rs.getInt("id"));
    entry.setName(rs.getString("name"));
    entry.setMessage(rs.getString("message"));
    entry.setEmail(rs.getString("email"));
    entry.setLocation(rs.getString("location"));
    return entry;

  }

}