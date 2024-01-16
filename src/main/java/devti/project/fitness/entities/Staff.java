package devti.project.fitness.entities;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	@OneToOne
	private Contact contact;
	private String gymId;
	@OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
	private UserAccount userAccount;
	
}
