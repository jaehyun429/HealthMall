package jpahealth.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("F")
@Getter
@Setter
public class Food extends Item {
    private String brand;
    private String purpose;
}
