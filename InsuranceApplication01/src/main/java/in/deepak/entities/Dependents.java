package in.deepak.entities;

import java.time.LocalDate;

import in.deepak.enums.RelationWithApplicant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dependents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dependents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dependentId;
    private String dependentName;
    private RelationWithApplicant relationWithApplicant;
    private LocalDate dob;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate createdDate;
    
    
}

