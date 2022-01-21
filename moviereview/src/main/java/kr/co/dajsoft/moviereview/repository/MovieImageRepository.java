package kr.co.dajsoft.moviereview.repository;

import kr.co.dajsoft.moviereview.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
