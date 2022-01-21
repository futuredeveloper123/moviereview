package kr.co.dajsoft.moviereview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name="m_member") // 테이블 이름 만들어주기 ,,,,
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long mid;
    private String email;
    private String pw;
    private String nickname;

}
