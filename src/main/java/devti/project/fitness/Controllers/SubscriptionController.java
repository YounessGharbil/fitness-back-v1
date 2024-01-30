	package devti.project.fitness.Controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.ClientService;
import devti.project.fitness.Services.ContactService;
import devti.project.fitness.Services.PackageService;
import devti.project.fitness.Services.RoleService;
import devti.project.fitness.Services.SubscriptionService;
import devti.project.fitness.Services.UserAccountService;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Observation;
import devti.project.fitness.entities.Package;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.UserAccount;
import devti.project.fitness.entities.requests.pack.GetPackageResponse;
import devti.project.fitness.entities.requests.subscription.CreateSubscriptionRequest;
import devti.project.fitness.entities.requests.subscription.GetSubscriptionResponse;
import devti.project.fitness.entities.requests.subscription.UpdateSubscriptionRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Subscription")
@RequiredArgsConstructor
public class SubscriptionController {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
    Calendar calendar = Calendar.getInstance();

	
    private final SubscriptionService subscriptionService;
    private final ContactService contactService;
    private final PackageService packageService;
    private final ClientService clientService;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody CreateSubscriptionRequest createSubscriptionRequest) {
    	
    	Contact contact =contactService.getContact(createSubscriptionRequest.getSubscribedContact_id());
    	
    	System.out.print(contact);
    	
    	Package pack=packageService.getPackage(createSubscriptionRequest.getSubscribedPackage_id());
    	
    	Role role=roleService.getRoleByRole_name("ROLE_CLIENT");
    	
    	UserAccount account=userAccountService.createUserAccount(
    			UserAccount.builder()
    			.password(passwordEncoder.encode("123456"))
    			.role(role)
    			.email(contact.getEmail())
    			.contact(contact)
    			.build());
    	
    	System.out.print(account);

    	
        Date startDate = new Date();
                
        calendar.setTime(startDate);

    	
    	calendar.add(Calendar.MONTH, pack.getDurationInMonths());
    	
    	Date endDate = calendar.getTime();
    	
    	String startDateString = dateFormat.format(startDate);
        String endDateString = dateFormat.format(endDate);
        
        double price =pack.getPrice();
        
        SimpleDateFormat gymIdDateFormat = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
        String gymId = gymIdDateFormat.format(new Date());

    	Subscription sub=subscriptionService
        		.createSubscription(
        				
        		Subscription.builder()
        		.discount(createSubscriptionRequest.getDiscount())
        		.startDate(startDateString)
        		.endDate(endDateString)
        		.priceAfterDiscount(price-price*createSubscriptionRequest.getDiscount()/100)
        		.subscribedContact(contact)
        		.subscribedPackage(pack)
        		.status("active")
        		.build()
        		
        				);
    	
    	List<Subscription> subs=pack.getSubscriptions();
    	subs.add(sub);
    	
    	packageService.updatePackage(pack);
    	
    	pack.setSubscriptions(subs);
    	
    			clientService.createClient(
    			Client.builder()
    			.contact(contact)
    			.observations(new ArrayList<Observation>())
    			.gymid(gymId)
    			.subscription(sub)
    			.userAccount(account)
    			.build()
    			);
    	
       
        return new ResponseEntity<>(sub, HttpStatus.CREATED);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetSubscriptionResponse> getSubscription(@PathVariable Long id) {
    	Subscription sub=subscriptionService.getSubscription(id);
        if(sub==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        
        Package pack=sub.getSubscribedPackage();
        
        GetPackageResponse package_response=
        		GetPackageResponse.builder()
        		.id(pack.getId())
        		.packageName(pack.getPackageName())
        		.price(pack.getPrice())
        		.description(pack.getDescription())
        		.durationInMonths(pack.getDurationInMonths())
        		.paymentType(pack.getDescription())
        		.build();
        
        GetSubscriptionResponse response=GetSubscriptionResponse.builder()
        		.id(sub.getId())
        		.discount(sub.getDiscount())
        		.priceAfterDiscount(sub.getPriceAfterDiscount())
        		.startDate(sub.getStartDate())
        		.endDate(sub.getEndDate())
        		.status(sub.getStatus())
        		.subscribedContact(sub.getSubscribedContact())
        		.subscribedPackage(package_response)
        		.build();
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<GetSubscriptionResponse>> getSubscriptions() {
    	
    	List<Subscription> subscriptions=subscriptionService.getSubscriptions();
    	
    	List<GetSubscriptionResponse> response=new ArrayList<GetSubscriptionResponse>();
    	
    	for(Subscription sub:subscriptions) {
    		
    		Package pack=sub.getSubscribedPackage();
            
            GetPackageResponse package_response=
            		GetPackageResponse.builder()
            		.id(pack.getId())
            		.packageName(pack.getPackageName())
            		.price(pack.getPrice())
            		.description(pack.getDescription())
            		.durationInMonths(pack.getDurationInMonths())
            		.paymentType(pack.getDescription())
            		.build();
    		
    		response.add(
    				GetSubscriptionResponse.builder()
    				.id(sub.getId())
            		.discount(sub.getDiscount())
            		.priceAfterDiscount(sub.getPriceAfterDiscount())
            		.startDate(sub.getStartDate())
            		.endDate(sub.getEndDate())
            		.status(sub.getStatus())
            		.subscribedContact(sub.getSubscribedContact())
            		.subscribedPackage(package_response)
    				.build()
    				);
    		
    	}


        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id,@RequestBody UpdateSubscriptionRequest updateSubscriptionRequest) {
    	System.out.println("----------------------------------------------------");
    	
    	System.out.print(updateSubscriptionRequest);

    	System.out.println("----------------------------------------------------");
    	Subscription sub=subscriptionService.getSubscription(id);
    	
    	
    	double price=sub.getSubscribedPackage().getPrice();
       
        if (updateSubscriptionRequest.getSubscribedPackage_id()!=null) {
        	
        	Package pack =packageService.getPackage(updateSubscriptionRequest.getSubscribedPackage_id());
        	
        	 LocalDate existingEndDate = LocalDate.parse(sub.getEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
             
             LocalDate newEndDate = existingEndDate.plusMonths(pack.getDurationInMonths());
             
             String formattedNewEndDate = newEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

             sub.setStartDate(sub.getEndDate());
             sub.setEndDate(formattedNewEndDate);
        }
        
        if (updateSubscriptionRequest.getStatus() != null) {
        	sub.setStatus(updateSubscriptionRequest.getStatus());
        }
        
        if (updateSubscriptionRequest.getDiscount() > 0) {
        	sub.setDiscount(updateSubscriptionRequest.getDiscount());
        	
        	sub.setPriceAfterDiscount(price-price*updateSubscriptionRequest.getDiscount()/100);
        }


        return new ResponseEntity<>(subscriptionService.updateSubscription(sub),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription( @PathVariable Long id) {
    	
//        clientService.deleteClientBySubscription(sub);
           
        subscriptionService.deleteSubscription(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
    }



}