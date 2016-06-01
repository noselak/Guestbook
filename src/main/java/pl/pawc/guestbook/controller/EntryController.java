package pl.pawc.guestbook.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import pl.pawc.guestbook.DAO.EntryJDBCTemplate;
import pl.pawc.guestbook.POJO.Entry;
import pl.pawc.guestbook.POJO.EntryException;

@Controller
public class EntryController {

	 private EntryJDBCTemplate entryJDBCTemplate;

	 public void setEntryJDBCTemplate(EntryJDBCTemplate entryJDBCTemplate){
	 	this.entryJDBCTemplate=entryJDBCTemplate;
   }
   
	@RequestMapping(value="/Home", method=RequestMethod.GET)
        public ModelAndView listContact(ModelAndView model) throws IOException{
        List<Entry> entries = entryJDBCTemplate.getAllEntries();
        model.addObject("Entries", entries);
        model.setViewName("Home");
        return model;
    }
        
     @RequestMapping(value="/showBean", method=RequestMethod.GET)
     public String showBean(ModelMap model){
       Entry entry = new Entry();
       entry.setId(1);
       entry.setName("test name");
       entry.setMessage("test message");
       model.addAttribute("entry", entry);
       model.addAttribute("simpleNumber", 34);
       return "ResultPage";
     }

	 @RequestMapping(value="/redirect", method=RequestMethod.GET)
	 public String redirect(ModelMap model){
			return "SuccessPage";
	 }
   
      @RequestMapping(value="/addEntry", method=RequestMethod.GET)
      @ExceptionHandler({EntryException.class})
      public String addEntry(){
        Entry entry = new Entry();
        entry.setName("test name");
        entry.setMessage("test message");

          entryJDBCTemplate.addEntry(entry);

        return "SuccessPage";
      }

}