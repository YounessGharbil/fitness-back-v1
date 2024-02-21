package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.PaymentModeRepository;
import devti.project.fitness.Repositories.PaymentRepository;
import devti.project.fitness.Repositories.PaymentTrancheRepository;
import devti.project.fitness.entities.CardPayment;
import devti.project.fitness.entities.CashPayment;
import devti.project.fitness.entities.CheckPayment;
import devti.project.fitness.entities.Payment;
import devti.project.fitness.entities.PaymentMode;
import devti.project.fitness.entities.PaymentTranche;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PaymentTrancheRepository paymentTrancheRespository;
	private final PaymentModeRepository paymentModeRepository;

	
	 public CheckPayment createCkeckPayment(@Valid CheckPayment checkPayment) {

		 	PaymentTranche paymentTranche=checkPayment.getPaymentTranche();
		 	PaymentMode paymentMode =paymentModeRepository.findByPaymentTranchesId(paymentTranche.getId());

		 	paymentTranche.setTranchePaid(true);
		 	paymentTranche.setPaymentMode(paymentMode);

		 	paymentTrancheRespository.save(paymentTranche);

	        return paymentRepository.save(checkPayment);
	    }
	 
	 public CashPayment createCashPayment(@Valid CashPayment cashPayment) {
		 
		 	PaymentTranche paymentTranche=cashPayment.getPaymentTranche();
		 	
		 	PaymentMode paymentMode =paymentModeRepository.findByPaymentTranchesId(paymentTranche.getId());
		 	
		 	paymentTranche.setTranchePaid(true);
		 	paymentTranche.setPaymentMode(paymentMode);
		 	paymentTrancheRespository.save(paymentTranche);
		 	
	        return paymentRepository.save(cashPayment);
	    }
	 
	 public CardPayment createCardPayment(@Valid CardPayment cardPayment) {
		 
		 	PaymentTranche paymentTranche=cardPayment.getPaymentTranche();
		 	PaymentMode paymentMode =paymentModeRepository.findByPaymentTranchesId(paymentTranche.getId());

		 	paymentTranche.setTranchePaid(true);
		 	paymentTranche.setPaymentMode(paymentMode);
		 	paymentTrancheRespository.save(paymentTranche);
		 	
	        return paymentRepository.save(cardPayment);
	    }
	 
	 public Payment getPayment(Long id) {
		 
		 Payment payment=paymentRepository.findById(id).isPresent()?paymentRepository.findById(id).get():null;

	        return payment;
	    }
	    
	    public List<Payment> getPayments() {

	        return paymentRepository.findAll();

	    }
	
	

}
