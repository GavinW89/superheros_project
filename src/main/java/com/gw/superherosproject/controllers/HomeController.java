package com.gw.superherosproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gw.superherosproject.models.LoginUser;
import com.gw.superherosproject.models.SuperHero;
import com.gw.superherosproject.models.User;
import com.gw.superherosproject.services.SuperHeroService;
import com.gw.superherosproject.services.UserService;

@Controller
public class HomeController {
	// Injecting Repos
	private final UserService userService;
	private final SuperHeroService superHeroService;
	
	

	public HomeController(UserService userService, SuperHeroService superHeroService) {
		super();
		this.userService = userService;
		this.superHeroService = superHeroService;
	}

	// Render Login and Registration form
	@GetMapping("/")
	public String loginReg(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "LoginRegistration.jsp";
	}
	// Post For Register
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		
userService.register(newUser,result);
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "LoginRegistration.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", newUser.getId());
        model.addAttribute("user", session.getAttribute("user_id"));
    
        return "redirect:/dashboard";
	}
	
	// Post For Login
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    	User user = userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "LoginRegistration.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", user.getId());
        
        return "redirect:/dashboard";
    }
	
	// Get For Logout
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	    	session.invalidate();
	    	return "redirect:/";
	    }
	
	 // Rendering Dashboard
	 @GetMapping("/dashboard")	
		public String dashboard(Model model,HttpSession session) {
			
			if(session.getAttribute("user_id") != null) {
								
				model.addAttribute("user", userService.oneUser( (Long)session.getAttribute("user_id")));
				model.addAttribute("allSuperHeros", superHeroService.allSuperHero());

				
				return "dashboard.jsp";
			}else {
				return "redirect:/";
			}
			
		}
	 
	 // Rendering newSuperHero jsp
	 @GetMapping("/newSuperHero")
	 public String newSuperHero(@ModelAttribute("superhero") SuperHero superhero, Model model, HttpSession session) {
		 if(session.getAttribute("user_id") != null) {
				
				model.addAttribute("user", userService.oneUser( (Long)session.getAttribute("user_id")));
				
				return "newSuperHero.jsp";
			}else {
				return "redirect:/";
			}
	 }
	 
	 // Post New SuperHero Form
	 @PostMapping("/processSuperHero")
	 public String postingSuperHero(@Valid @ModelAttribute("superhero") SuperHero superhero, BindingResult result, Model model, HttpSession session) {
		 if(result.hasErrors()) {
			 model.addAttribute("user", userService.oneUser((Long)session.getAttribute("user_id")));
			 return "newSuperHero.jsp";
		 }else {
			 superHeroService.createSuperHero(superhero);
			 return "redirect:/dashboard";
		 }
	 }
	 
	 // Render One Super Hero JSP
	 @GetMapping("/oneSuperHero/{id}")
	 public String oneSuperHero(@PathVariable("id") Long id, Model model, HttpSession session) {
		 if(session.getAttribute("user_id") != null) {
				
				model.addAttribute("user", userService.oneUser( (Long)session.getAttribute("user_id")));
				model.addAttribute("superHero", superHeroService.findSuperHero(id));
				return "oneSuperHero.jsp";
			}else {
				return "redirect:/";
			}
	 }
	 
	 // Render Update Super Hero JSP
	 @GetMapping("/updateSuperHero/{id}")
	 public String updateSuperHero(@PathVariable("id") Long id, @ModelAttribute("superhero") SuperHero superhero, Model model, HttpSession session) {
		 if(session.getAttribute("user_id") != null) {
				
				model.addAttribute("user", userService.oneUser( (Long)session.getAttribute("user_id")));
				model.addAttribute("superhero", superHeroService.findSuperHero(id));
				return "updateSuperHero.jsp";
			}else {
				return "redirect:/";
			}
	 }
	 
	 // Put Route For Update Super Hero
	 @RequestMapping(value="/updatingSuperHero/{id}", method=RequestMethod.PUT)
	 public String updatingSuperHero(@Valid @ModelAttribute("superhero") SuperHero superhero, BindingResult result, @PathVariable("id")Long id, HttpSession session, Model model) {
		 if(result.hasErrors()) {
				model.addAttribute("superhero", superHeroService.findSuperHero(id));
				return "updateSuperHero.jsp";
			}else {
				superHeroService.updateSuperHero(superhero);
				return "redirect:/dashboard";
			}
	 }
	 
	 // Delete Route for Super Hero
	 @GetMapping("/deleteSuperHero/{id}")
	 public String delete(@PathVariable("id") Long id, HttpSession session) {
		 if(session.getAttribute("user_id") != null) {
			 superHeroService.delete(id);
			 return "redirect:/dashboard";
		 }else {
			 return "redirect:/";
		 }
	 }
	 
	
}
