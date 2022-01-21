package kr.co.dajsoft.moviereview.repository;

import kr.co.dajsoft.moviereview.entity.Member;
import kr.co.dajsoft.moviereview.entity.Movie;
import kr.co.dajsoft.moviereview.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //영화 정보를 가지고 모든 영화의 모든 리뷰를 가져오는 메서드
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    //member가 지워질때 같이 데이터를 지우는 메서드
    void deleteByMember(Member member);

    void deleteByMovie(Movie movie);
    //movie가 지워질 때  같이 데이터를 지우는 메서드


}

