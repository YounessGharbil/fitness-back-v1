package devti.project.fitness.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CHECK")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckPayment extends Payment {

	private String checkNumber; 

}