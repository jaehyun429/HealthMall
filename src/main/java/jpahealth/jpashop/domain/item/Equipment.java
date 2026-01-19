package jpahealth.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
public class Equipment extends Item {
    private String brand;
    private String purpose;

}
