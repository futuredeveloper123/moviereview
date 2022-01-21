package kr.co.dajsoft.moviereview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie","member"}) //쟤네는 toString만들지말라고 2개니까 중괄호
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long revienum;

    @ManyToOne(fetch = FetchType.LAZY)
    //관계만들어줘야햐니까 movie and memeber있어야함
    private Movie movie;

    //fetch를 설정하지 않으면 review정보를 가져올 때 join을 해서 데이터를 가져옴
    //fetch type을 lazy로 설정하면 처음에는가져오지않음
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade ;
    private String text;
}
