package devti.project.fitness.entities;



import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Subscription {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private int discount;
	 
	 private double priceAfterDiscount;
	 
	 private String startDate;
	 
	 private String endDate;
	 
	 private String status;
	 
	 @ManyToOne
	 @JoinColumn(name="subscribedPackage_id")
	 private Package subscribedPackage;

	 @OneToOne
	 @JoinColumn(name="subscribedContact_id")
	 private Contact subscribedContact;
	 
	 @OneToOne
	 @JoinColumn(name="paymentMode_id")
	 private PaymentMode paymentMode;
	 
	 @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
	 private List<Payment> payments; 
	 
	 @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
	 private List<SubscriptionEvent> subscriptionEvents;
	 
}
