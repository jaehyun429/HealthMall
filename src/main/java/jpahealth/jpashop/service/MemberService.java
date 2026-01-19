package jpahealth.jpashop.service;

import jpahealth.jpashop.domain.Member;
import jpahealth.jpashop.domain.Role;
import jpahealth.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException; // 명확한 예외 사용
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        // 1. 이름 -> 이메일로 중복 검증 변경
        validateDuplicateMemberByEmail(member);

        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        member.setRole(Role.ROLE_USER);
        memberRepository.save(member);
        return member.getId();
    }

    // 1-1. 중복 검증 메서드 변경 (이메일 기준)
    private void validateDuplicateMemberByEmail(Member member) {
        // MemberRepository에 findByEmail 메서드 추가 필요
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 가입된 이메일입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단일 회원 조회
     */
    // 2. findOne -> findById로 변경하고 Optional 처리
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다. ID: " + memberId));
    }
}