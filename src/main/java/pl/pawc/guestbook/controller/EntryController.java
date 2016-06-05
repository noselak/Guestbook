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
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    
  @RequestMapping(value = "/ViewProfile", method = RequestMethod.GET)
    public String viewProfile(@ModelAttribute("Guestbook") Entry entry, ModelMap model, HttpServletRequest request){
    if(request.getSession().getAttribute("nameSession")==null){
      throw new GuestbookException("You are not logged in");
    }
    User user;
    try{
      user = userJDBCTemplate.getUser((String) request.getSession().getAttribute("nameSession"));
    }
    catch(Exception e){
      throw new GuestbookException(e.toString());
    }
    model.addAttribute("command", new User());
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", user);
    return "ViewProfile";
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
      throw new GuestbookException("You are not logged in");
    }
    List<Entry> entries;
    try{
      entries = entryJDBCTemplate.getAllEntries();
    }
    catch(Exception e){
      throw new GuestbookException(e.toString());
    }
    model.addObject("command", new Entry());
    model.addObject("Entries", entries);
    model.addObject("nameSession", request.getSession().getAttribute("nameSession"));
    model.setViewName("Home");
    return model;
  }

  @RequestMapping(value="/logout", method=RequestMethod.POST)
     public String logout(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
     model.addAttribute("command", new Entry());
     request.getSession().removeAttribute("nameSession");
     return "redirect:index";
  }

  @RequestMapping(value = "/addEntry", method=RequestMethod.POST)
    public String addEntry(@ModelAttribute("Guestbook") Entry entry, ModelMap model, HttpServletRequest request){
    //model.addAttribute("name", entry.getName());
    model.addAttribute("message", entry.getMessage());
    try{
      entryJDBCTemplate.addEntry((String) request.getSession().getAttribute("nameSession"), entry.getMessage());
    }
    catch(Exception e){
      throw new GuestbookException(e.toString());
    }
    return "redirect:Home";
  }
    
  @RequestMapping(value = "/updateDetails", method=RequestMethod.POST)
    public String updateDetails(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
    //model.addAttribute("name", entry.getName());
    model.addAttribute("user", new User());
    try{
      userJDBCTemplate.updateLocation((String) request.getSession().getAttribute("nameSession"), user.getLocation());
      userJDBCTemplate.updateEmail((String) request.getSession().getAttribute("nameSession"), user.getEmail());
    }
    catch(Exception e){
      throw new GuestbookException(e.toString());
    }
    return "redirect:ViewProfile";
  }
    
  @RequestMapping(value = "/addUser", method=RequestMethod.POST)
  @ExceptionHandler({GuestbookException.class})
    public String addUser(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
    model.addAttribute("entry", new Entry());
    model.addAttribute("user", new User());
    //model.addAttribute("name", user.getName());
    //model.addAttribute("hashedPass", user.getHashedPass());
    String name = user.getName();
    String pass = user.getHashedPass();
    String hashedPass = String.valueOf(pass.hashCode());

      if(!userJDBCTemplate.checkIfUserExists(name)){
        try{
          userJDBCTemplate.addUser(name, hashedPass);
        }
        catch(Exception e){
          throw new GuestbookException(e.toString());
        }
      }
      else{
        throw new GuestbookException("Username already exists");
      }
    request.getSession().setAttribute("registration", "Registration complete. You may now log in.");
    return "redirect:index";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
      public String login(@ModelAttribute("Guestbook") User user, ModelMap model, HttpServletRequest request){
        model.addAttribute("command", new Entry());
        model.addAttribute("entry", new Entry());
        model.addAttribute("user", new User());
        
        String name = user.getName();
        String pass = user.getHashedPass();
        
        if(!userJDBCTemplate.checkIfUserExists(name)) throw new GuestbookException(("User does not exist"));
        if(userJDBCTemplate.logIn(name, String.valueOf(pass.hashCode()))){
          request.getSession().setAttribute("nameSession", name);
          return "redirect:Home";
        }
        else{
          throw new GuestbookException("Incorrect password");
        }
    }
    
}