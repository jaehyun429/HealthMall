package jpahealth.jpashop.controller;

import jakarta.validation.Valid;
import jpahealth.jpashop.config.JwtTokenProvider;
import jpahealth.jpashop.domain.Address;
import jpahealth.jpashop.domain.Member;
import jpahealth.jpashop.dto.LoginRequestDto;
import jpahealth.jpashop.dto.LoginResponseDto;
import jpahealth.jpashop.dto.SignupRequestDto;
import jpahealth.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    // 1. AuthenticationManager, JwtTokenProvider를 필드로 선언하고 생성자를 통해 주입받습니다.
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        Address address = new Address(requestDto.getAddress().getCity(), requestDto.getAddress().getStreet(), requestDto.getAddress().getZipcode());
        Member member = new Member();
        member.setName(requestDto.getName());
        member.setEmail(requestDto.getEmail());
        member.setPassword(requestDto.getPassword());
        member.setAddress(address);

        memberService.join(member);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        // 2. 주입받은 authenticationManager를 사용하여 인증을 시도합니다.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );

        // 3. 인증 성공 시, 주입받은 jwtTokenProvider로 토큰을 생성합니다.
        String token = jwtTokenProvider.generateToken(authentication);

        // 4. JWT를 응답 본문에 담아 반환합니다.
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}