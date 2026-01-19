package jpahealth.jpashop.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}