package devti.project.fitness.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class Contact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	private String dateNaissance;
	private String sexe;
	private String adresse;
	private String codePostal;
	private String ville;
	@NotNull
	private String tel;
	@NotNull
	@Email
	private String email;

}
