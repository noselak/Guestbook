package pl.pawc.guestbook.DAO;

import javax.sql.DataSource;
import pl.pawc.guestbook.POJO.Entry;
import java.util.List;

public interface EntryDAO{

	public Entry getEntry(int id);
	public List<Entry> getAllEntries();
	public void addEntry(String name, String message);

}