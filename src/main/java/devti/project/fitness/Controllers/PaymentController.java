package devti.project.fitness.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.PaymentService;
import devti.project.fitness.Services.SubscriptionService;
import devti.project.fitness.entities.CardPayment;
import devti.project.fitness.entities.CashPayment;
import devti.project.fitness.entities.CheckPayment;
import devti.project.fitness.entities.Payment;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.requests.payment.CardPaymentRequest;
import devti.project.fitness.entities.requests.payment.CardPaymentResponse;
import devti.project.fitness.entities.requests.payment.CashPaymentRequest;
import devti.project.fitness.entities.requests.payment.CashPaymentResponse;
import devti.project.fitness.entities.requests.payment.CheckPaymentRequest;
import devti.project.fitness.entities.requests.payment.CheckPaymentResponse;
import devti.project.fitness.entities.requests.payment.PaymentResponse;
import devti.project.fitness.entities.requests.subscription.GetPaymentResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Payment")
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	private final SubscriptionService subscriptionService;
	
	@PostMapping("/check-payment")
    public ResponseEntity<CheckPayment> createCheckPayment(@RequestBody CheckPaymentRequest checkPaymentRequest) {
		
		Subscription subscription=subscriptionService.getSubscription(checkPaymentRequest.getSubscriptionid());
	 	
		CheckPayment checkPayment=new CheckPayment();
		
		checkPayment.setAmount(checkPaymentRequest.getAmount());
		checkPayment.setCheckNumber(checkPaymentRequest.getCheckNumber());
		checkPayment.setPaymentDate(checkPaymentRequest.getPaymentDate());
		checkPayment.setPaymentMethod(checkPaymentRequest.getPaymentMethod());
		checkPayment.setPaymentTranche(checkPaymentRequest.getPaymentTranche());
		checkPayment.setSubscription(subscription)	;
		CheckPayment createdCheckPayment=paymentService.createCkeckPayment(checkPayment);
		
        return new ResponseEntity<>(createdCheckPayment, HttpStatus.CREATED);
        
    }
	
	@PostMapping("/cash-payment")
    public ResponseEntity<CashPayment> createCashPayment(@RequestBody CashPaymentRequest cashPaymentRequest) {
		
		
	 	Subscription subscription=subscriptionService.getSubscription(cashPaymentRequest.getSubscriptionid());

		CashPayment cashPayment=new CashPayment();
		
		cashPayment.setPaymentTranche(cashPaymentRequest.getPaymentTranche());
		cashPayment.setPaymentMethod(cashPaymentRequest.getPaymentMethod());
		cashPayment.setPaymentDate(cashPaymentRequest.getPaymentDate());
		cashPayment.setAmount(cashPaymentRequest.getAmount());
		cashPayment.setSubscription(subscription);
		cashPayment.setSubscription(subscription);
		
		CashPayment createdCashPayment=paymentService.createCashPayment(cashPayment);

        return new ResponseEntity<>(createdCashPayment, HttpStatus.CREATED);
        
    }
	
	@PostMapping("/card-payment")
    public ResponseEntity<CardPayment> createCardPayment(@RequestBody CardPaymentRequest cardPaymentRequest) {
		
	 	Subscription subscription=subscriptionService.getSubscription(cardPaymentRequest.getSubscriptionid());
		
		CardPayment cardPayment=new CardPayment();
		cardPayment.setAmount(cardPaymentRequest.getAmount());
		cardPayment.setPaymentDate(cardPaymentRequest.getPaymentDate());
		cardPayment.setPaymentMethod(cardPaymentRequest.getPaymentMethod());
		cardPayment.setPaymentTranche(cardPaymentRequest.getPaymentTranche());
		cardPayment.setTransactionNumber(0);
		cardPayment.setCardCVV(cardPaymentRequest.getCardCVV());
		cardPayment.setCardExpirationDate(cardPaymentRequest.getCardExpirationDate());
		cardPayment.setCardNumber(cardPaymentRequest.getCardNumber());
		cardPayment.setSubscription(subscription);

		CardPayment CreatedCardPayment=paymentService.createCardPayment(cardPayment);
		
        return new ResponseEntity<>(CreatedCardPayment, HttpStatus.CREATED);
        
    }
	
	 @GetMapping
	 public ResponseEntity<List<GetPaymentResponse>> getPayments() {
		 List<Payment> payments=paymentService.getPayments();

		 List<GetPaymentResponse> paymentsResponse=new ArrayList<GetPaymentResponse>();
		 
		 for(Payment p:payments) {
			 paymentsResponse.add(
					 GetPaymentResponse.builder()
					 .paymentTranche(p.getPaymentTranche())
					 .paymentMethod(p.getPaymentMethod())
					 .paymentDate(p.getPaymentDate())
					 .amount(p.getAmount())
					 .subscription(p.getSubscription())
					 .build()
					 );
		 }

	        return new ResponseEntity<>(paymentsResponse,HttpStatus.OK);
	             
	    }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
		 
	        Payment payment=paymentService.getPayment(id);
	        
	        PaymentResponse response;
	        
	        if(payment==null){
	        	
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        if (payment instanceof CardPayment) {
	        	
	            response =CardPaymentResponse.builder().build() ;
	            
	        } else if (payment instanceof CashPayment) {
	        	
	            response =CashPaymentResponse.builder().build();
	            
	        } else if (payment instanceof CheckPayment) {
	        	
	            response =CheckPaymentResponse.builder().build();
	            
	        } else {
	        	
	            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	            
	        }   
	        
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	 

}
