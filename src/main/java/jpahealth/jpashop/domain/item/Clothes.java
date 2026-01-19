package jpahealth.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("C")
@Getter
@Setter
public class Clothes extends Item{

    private String brand;
    private String fiber;
}
