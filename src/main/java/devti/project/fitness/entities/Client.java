package devti.project.fitness.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Client  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	
	private String gymid;
	
	@OneToOne
	private Subscription subscription;
	
	@OneToMany
    @JsonIgnore
    private List<Observation> observations;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Nullable
	private UserAccount userAccount;
	
}
