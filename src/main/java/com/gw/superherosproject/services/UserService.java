package com.gw.superherosproject.services;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gw.superherosproject.models.LoginUser;
import com.gw.superherosproject.models.User;
import com.gw.superherosproject.repositories.UserRepository;

    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	// Reject if email is taken (present in database)
    	if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
    		result.rejectValue("email", "Unique", "You cannot email with this email, must use different email");
    	}
    	
    	// Reject if password doesnt match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "Matches", "Your password and confirm passwords must match");
    	}
    	
    	// Return null if results has errors
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	// Hash and set password, save user to database
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	
    	
        return userRepo.save(newUser);
    }
    public User login(LoginUser newLogin, BindingResult result) {
        
    	// Reject if Not Present
    	Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
    	if(!potentialUser.isPresent()) {
    		result.rejectValue("email", "Unique", "Email does not exist");
    		return null;
    	}
    	User user = potentialUser.get();
    	
    	// Reject if BCrypt password match fails
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    		result.rejectValue("password", "Matches", "Password does not match the with email");
    		return null;
    	}
    	
    	// Return null if results has errors

    	if(result.hasErrors()) {
    		return null;
    	} else {
    		// Otherwise, return user object
    		return user;
    	}
    	
    }
    
    // Find one user
    public User oneUser(Long id) {
    	Optional<User> optionalUser = userRepo.findById(id);
    	if(optionalUser.isPresent()) {
    		return optionalUser.get();
    	}else {
    		return null;
    	}
    }
}