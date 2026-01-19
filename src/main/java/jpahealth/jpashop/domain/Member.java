package jpahealth.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
// Setter는 꼭 필요한 경우에만 제한적으로 사용하는 것이 좋으므로 일단 제거합니다.
// import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    // 1. 실제 데이터를 저장할 필드들을 선언합니다.
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열로 저장하는 것이 안전합니다.
    private Role role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //== Setter를 직접 만들어 상태 변경을 제어 ==//
    // Setter를 무분별하게 열어두는 것보다, 의도가 명확한 메서드를 만드는 것이 좋습니다.
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAddress(Address address) {
        this.address = address;
    }
    // 이메일, 이름 등은 가입 시에만 설정되고 바뀌지 않는다고 가정하면 Setter가 필요 없습니다.
    // 만약 변경이 필요하다면 아래와 같이 의도가 명확한 메서드를 만들어줍니다.
    public void changeMemberInfo(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}