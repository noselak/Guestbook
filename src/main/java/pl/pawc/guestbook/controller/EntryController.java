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
import pl.pawc.guestbook.DAO.UserJDBCTemplate;
import pl.pawc.guestbook.POJO.Entry;
import pl.pawc.guestbook.POJO.GuestbookException;
import pl.pawc.guestbook.POJO.User;

@Controller
public class EntryController {

  private EntryJDBCTemplate entryJDBCTemplate;
  private UserJDBCTemplate userJDBCTemplate;

  public void setEntryJDBCTemplate(EntryJDBCTemplate entryJDBCTemplate){
    this.entryJDBCTemplate=entryJDBCTemplate;
  }
  
  public void setUserJDBCTemplate(UserJDBCTemplate userJDBCTemplate){
    this.userJDBCTemplate=userJDBCTemplate;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(@ModelAttribute("Guestbook") Entry entry, ModelMap model ){
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", new User());
    return "index";
  }
    
  @RequestMapping(value = "/UserExists", method = RequestMethod.GET)
    public String userExists(@ModelAttribute("Guestbook") User user, ModelMap model ){
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", new User());
    return "UserExists";
  }   
    
  @RequestMapping(value = "/UserDoesNotExist", method = RequestMethod.GET)
    public String userDoesNotExist(@ModelAttribute("Guestbook") User user, ModelMap model ){
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", new User());
    return "UserDoesNotExist";
  }   

  @RequestMapping(value="/index", method=RequestMethod.GET)
    public ModelAndView list(ModelAndView model) throws IOException{
    model.addObject("entry", new Entry());
    model.addObject("user", new User());
    model.addObject("command", new Entry());
    model.setViewName("index");
    return model;
  }

  @RequestMapping(value="/Home", method=RequestMethod.GET)
    public ModelAndView listEntries(ModelAndView model, HttpServletRequest request) throws IOException{
    if(request.getSession().getAttribute("nameSession")==null){
      return null;
    }
    List<Entry> entries = entryJDBCTemplate.getAllEntries();
    model.addObject("command", new Entry());
    model.addObject("Entries", entries);
    model.setViewName("Home");
    return model;
  }

  @RequestMapping(value="/addName", method=RequestMethod.POST)
     public String addName(@ModelAttribute("Guestbook") Entry entry, ModelMap model, HttpServletRequest request){
     model.addAttribute("command", new Entry());
     request.getSession().setAttribute("nameSession", entry.getName());
     return "redirect:Home";
  }

  @RequestMapping(value = "/addEntry", method=RequestMethod.POST)
    public String addEntry(@ModelAttribute("Guestbook") Entry entry, ModelMap model, HttpServletRequest request){
    //model.addAttribute("name", entry.getName());
    model.addAttribute("message", entry.getMessage());
    entryJDBCTemplate.addEntry((String) request.getSession().getAttribute("nameSession"), entry.getMessage());
    return "redirect:Home";
  }
    
  @RequestMapping(value = "/addUser", method=RequestMethod.POST)
    public String addUser(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", new User());
    //model.addAttribute("name", user.getName());
    //model.addAttribute("hashedPass", user.getHashedPass());
    String name = user.getName();
    String pass = user.getHashedPass();
    String hashedPass = String.valueOf(pass.hashCode());

      if(!userJDBCTemplate.checkIfUserExists(name)){
        userJDBCTemplate.addUser(name, hashedPass);
      }
      else{
        return "redirect:UserExists";
      }
    return "redirect:index";
    }
    
    @RequestMapping(value="/logIn", method=RequestMethod.POST)
      public String logIn(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
        model.addAttribute("command", new Entry());
        model.addAttribute("entry", new Entry());
        model.addAttribute("user", new User());
        
        String name = user.getName();
        String pass = user.getHashedPass();
        
        if(!userJDBCTemplate.checkIfUserExists(name)) return "redirect:UserDoesNotExist";
        if(userJDBCTemplate.logIn(name, String.valueOf(pass.hashCode()))){
          request.getSession().setAttribute("nameSession", name);
          return "redirect:Home";
        }
        else{
          return "IncorrectPassword";
        }
    }
    
}