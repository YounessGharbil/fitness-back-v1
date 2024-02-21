package devti.project.fitness.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue("CARD")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardPayment extends Payment {

	private String cardNumber; 
    private String cardExpirationDate; 
    private String cardCVV; 
    private int transactionNumber;
    
}

