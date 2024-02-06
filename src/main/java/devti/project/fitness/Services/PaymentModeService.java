package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.PaymentModeRepository;
import devti.project.fitness.entities.PaymentMode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentModeService {
	
	private final PaymentModeRepository paymentModeRepository;

    public PaymentMode getPaymentMode(Long id) {
    	
    	PaymentMode paymentMode=paymentModeRepository.findById(id).isPresent()?paymentModeRepository.findById(id).get():null;

        return paymentMode;
        
    }
    
    public List<PaymentMode> getPaymentModes() {

        return paymentModeRepository.findAll();

    }
    
	 public PaymentMode createPaymentMode(@Valid PaymentMode paymentMode) {
		 	
	        return paymentModeRepository.save(paymentMode);
	    }
	 
	 public PaymentMode updatePaymentMode(@Valid PaymentMode paymentMode) {	        
	       
	        return paymentModeRepository.save(paymentMode);
	    }
	 
	 public String deletePaymentMode(Long id) {
		 
		 	paymentModeRepository.deleteById(id);
		 	
	        return "deleted successfully";
	        
	    }

}
