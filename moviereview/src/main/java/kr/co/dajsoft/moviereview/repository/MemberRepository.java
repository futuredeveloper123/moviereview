package kr.co.dajsoft.moviereview.repository;



import kr.co.dajsoft.moviereview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
