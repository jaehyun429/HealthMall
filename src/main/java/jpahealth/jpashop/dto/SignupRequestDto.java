package jpahealth.jpashop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jpahealth.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

// SignupRequestDto.java
@Getter
@Setter
public class SignupRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty @Email
    private String email;
    @NotEmpty private String password;
    private Address address;
}
