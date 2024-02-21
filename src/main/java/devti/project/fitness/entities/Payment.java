package devti.project.fitness.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import devti.project.fitness.entities.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "payment_tranche_id")
    private PaymentTranche paymentTranche;

    private PaymentMethod paymentMethod;

    private double amount;

    private String paymentDate;
    
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    @JsonIgnore
    private Subscription subscription;

    
}
