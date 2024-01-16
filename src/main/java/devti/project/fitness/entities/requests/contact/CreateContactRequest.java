package devti.project.fitness.entities.requests.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactRequest {
	
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String sexe;
	private String adresse;
	private String codePostal;
	private String pays;
	private String ville;
	private String tel;
	private String email;

}
