package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.UserAccountRepository;
import devti.project.fitness.entities.UserAccount;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService {
	
    private final UserAccountRepository userAccountRepository;
    
    public UserAccount createUserAccount(@Valid UserAccount user) {
    	
        return userAccountRepository.save(user);
        
    }
    
    public UserAccount getUserAccount(Long id) {
    	
    	UserAccount user=userAccountRepository.findById(id).isPresent()?userAccountRepository.findById(id).get():null;
    	
        return user;
    }
    
//    public UserAccount getUserAccountByContact(Contact contact) {
//    	
//    	UserAccount user=userAccountRepository.findByContact(contact).isPresent()?userAccountRepository.findByContact(contact).get():null;
//    	
//    	return user;
//    }
    
    public UserAccount updateUserAccount(@Valid UserAccount user) {
    	
        return userAccountRepository.save(user);
    }
    
    public String deleteeUserAccount(Long id) {
    	
    	userAccountRepository.deleteById(id);
    	
        return "deleted successfully";
    }
    
    public List<UserAccount> getAllUserAccounts() {
    	
        return userAccountRepository.findAll();
    }




}
