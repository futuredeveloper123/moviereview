package entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private int grade ;
    private String text;
}
