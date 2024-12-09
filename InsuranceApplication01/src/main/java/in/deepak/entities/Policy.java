package in.deepak.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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
@Table(name="policy")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Policy {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyId;
    @Column(name = "post_title",nullable = false,length = 100)
    private String policyName;
    @Column(length = 1000)
    private String description;
    private long coverageAmount;
    private int ruleId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    private LocalDate createdDate;
    
   
}

