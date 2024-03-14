package devti.project.fitness.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role {
	
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 private Long id;
		 
		 private String rolename;
		 
		 @ManyToMany
		 @JoinTable(name = "role_authority",
				 	joinColumns = @JoinColumn(name = "role_id"),
				 	inverseJoinColumns =@JoinColumn(name = "authority_id"))
		 private List<Authority> authorities;

}
