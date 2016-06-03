package pl.pawc.guestbook.DAO;

import pl.pawc.guestbook.POJO.Entry;
import java.util.List;

public interface EntryDAO{

  public Entry getEntry(int id);
  public List<Entry> getAllEntries();
  public void addEntry(String name, String message);
  
}