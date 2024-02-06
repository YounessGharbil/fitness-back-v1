package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import devti.project.fitness.Repositories.PaymentTrancheRepository;
import devti.project.fitness.entities.PaymentTranche;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTrancheService {
	
	private final PaymentTrancheRepository paymentTrancheRepository;

    public PaymentTranche getPaymentTranche(Long id) {
    	
    	PaymentTranche paymentTranche=paymentTrancheRepository.findById(id).isPresent()?paymentTrancheRepository.findById(id).get():null;

        return paymentTranche;
        
    }
    
    public List<PaymentTranche> getPaymentTranches() {

        return paymentTrancheRepository.findAll();

    }
    
	 public PaymentTranche createPaymentTranche(@Valid PaymentTranche paymentTranche) {
		 	
	        return paymentTrancheRepository.save(paymentTranche);
	    }
	 
	 public PaymentTranche updatePaymentTranche(@Valid PaymentTranche paymentTranche) {	        
	       
	        return paymentTrancheRepository.save(paymentTranche);
	    }
	 
	 public String deletePaymentTranche(Long id) {
		 
		 	paymentTrancheRepository.deleteById(id);
		 	
	        return "deleted successfully";
	        
	    }

}
