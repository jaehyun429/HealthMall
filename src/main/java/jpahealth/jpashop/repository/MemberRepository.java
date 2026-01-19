package jpahealth.jpashop.repository;

import jpahealth.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이름으로 회원을 찾는 기능 (Query Method)
    List<Member> findByName(String name);

    // (추가) 이메일로 회원을 찾는 기능 (Optional 반환)
    Optional<Member> findByEmail(String email);



}