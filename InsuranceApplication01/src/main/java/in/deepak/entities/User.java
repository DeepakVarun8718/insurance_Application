package in.deepak.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import in.deepak.enums.EmploymentType;
import in.deepak.enums.Gender;
import in.deepak.enums.IncomeSlab;
import in.deepak.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name="email",length = 30)
    private String email;
    @Column(name="name",length = 25)
    private String name;
    private String password;
    @Column(name="mobileNumber",length = 10)
    private String mobileNumber;
    private String address;
    @Column(name="aadhaarNumber",length = 12,unique = true)
    private String aadhaarNumber;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private IncomeSlab incomeSlab;

    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;
    private Role role;
    private LocalDate createdDate;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Dependents> dependents = new ArrayList<>();
      

    public User(int i, String mail) {
    }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(getRole().name()));
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getUsername();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

      
   
	
}

