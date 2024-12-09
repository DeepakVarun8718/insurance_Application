package in.deepak.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policyrule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruleId;
    private int minAge;
    private int maxAge;
    private int healthStatus;
    private double bmi;
    private LocalDate createdDate;
    
   
    
}

