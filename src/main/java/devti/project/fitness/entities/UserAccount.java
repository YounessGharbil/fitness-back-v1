package devti.project.fitness.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount implements UserDetails {
	   
		private static final long serialVersionUID = 1L;

	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long Id;

	   @OneToOne
	   private Contact contact;
	   
	   private String email;
	   	   
	   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	   private String password;
	   
	   @ManyToOne
	   @JoinColumn(name = "role_id")
	   private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//
//		return  List.of(new SimpleGrantedAuthority(role.getRolename()));
		 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		    for (Authority authority : role.getAuthorities()) {
		        authorities.add(new SimpleGrantedAuthority(authority.getName()));
		    }
		    return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
