package pl.pawc.guestbook.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import pl.pawc.guestbook.DAO.EntryJDBCTemplate;
import pl.pawc.guestbook.POJO.Entry;

@Controller
public class EntryController {

	 private EntryJDBCTemplate entryJDBCTemplate;

	 public void setEntryJDBCTemplate(EntryJDBCTemplate entryJDBCTemplate){
	 	this.entryJDBCTemplate=entryJDBCTemplate;
   }
   
	@RequestMapping(value="/Home", method=RequestMethod.GET)
        public ModelAndView listEntries(ModelAndView model) throws IOException{
        List<Entry> entries = entryJDBCTemplate.getAllEntries();
        model.addObject("command", new Entry());
        model.addObject("Entries", entries);
        model.setViewName("Home");
        return model;
    }
        
     @RequestMapping(value="/index", method=RequestMethod.GET)
        public ModelAndView list(ModelAndView model) throws IOException{
        model.addObject("command", new Entry());
        model.setViewName("index");
        return model;
    }
   
      @RequestMapping(value = "/addEntry", method=RequestMethod.POST)
        public String addEntry(@ModelAttribute("Guestbook") Entry entry, ModelMap model ){
          model.addAttribute("name", entry.getName());
          model.addAttribute("message", entry.getMessage());
          entryJDBCTemplate.addEntry(entry.getName(), entry.getMessage());
          return "redirect:Home";
        }
        
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }


      @RequestMapping(value="/addName", method=RequestMethod.POST)
      public String addName(@ModelAttribute("Guestbook") Entry entry, ModelMap model, HttpServletRequest request){
         request.getSession().setAttribute("nameSession", entry.getName());
         return "Home";
      }
}