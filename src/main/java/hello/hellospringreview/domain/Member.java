package hello.hellospringreview.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    /**
     * GenerationType
     * IDENTITY : 기본 키 생성을 데이터베이스에 위임
     * SEQUENCE : 데이터베이스 Sequence Object 사용 (SEQUENCE OBJECT : 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트)
     * TABLE : 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
     * AUTO : 기본 설정값, DB 벤더에 따라 자동으로 위의 3가지 전략 중 하나를 선택
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
