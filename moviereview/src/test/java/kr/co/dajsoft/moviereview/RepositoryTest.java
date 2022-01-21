package kr.co.dajsoft.moviereview;

import kr.co.dajsoft.moviereview.entity.Member;
import kr.co.dajsoft.moviereview.entity.Movie;
import kr.co.dajsoft.moviereview.entity.MovieImage;
import kr.co.dajsoft.moviereview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import kr.co.dajsoft.moviereview.repository.MemberRepository;
import kr.co.dajsoft.moviereview.repository.MovieImageRepository;
import kr.co.dajsoft.moviereview.repository.MovieRepository;
import kr.co.dajsoft.moviereview.repository.ReviewRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    //영화정보 100개를 삽입하는 메서드
    //@Test
    @Transactional
    @Commit
    public void insertMovie() {

        Random r = new Random();

        //for(int i=0; i<=100; i++) 이거 아래거랑 같은 거임 ,,
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie" + i)
                    .build();
            movieRepository.save(movie);

            int count = r.nextInt(5);
            for (int j = 0; j < count; j = j+1) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .imgName("Test" + j + ".png")
                        .movie(movie)
                        .build();
                movieImageRepository.save(movieImage);
            }

        });

    }

   //@Test
    public void insertMember() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .pw("1234")
                    .nickname("USER" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    //리뷰데이터 200 개 삽입하는 메서드
    //리뷰는 항상 멤버와 무비가 있어야하는데 반드시 존재하는 데이터 여야함
    //@Test
    public void reviewTest(){
        Random r = new Random();
        for(int i =1; i<=200; i++){
            //review는 member와 movie에 존재하는 데이터를 기반으로 생성 되어야함
            Long mid = (long) r.nextInt(100)+1;
            Long bno = (long) r.nextInt(100)+1;

            Member member = Member.builder()
                    .mid(mid)
                    .build();
            Movie movie = Movie.builder()
                    .mno(bno)
                    .build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade(r.nextInt(5)+1)
                    .text("review"+i)
                    .build();
            reviewRepository.save(review);
        }
    }
    //영화 목록 가져오는 메서드
    //@Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));

        }
    }
    //특정 영화에 대한 정보를 가져오는 메서드
    //@Test
    public void testGetMovie(){
        List<Object[]> result = movieRepository.getMovieWithAll(52L);
        for(Object[] r: result ){
            System.out.println(Arrays.toString(r));
        }
    }
    //특정 영화에 해당하는 모든 리뷰 가져오기
    @Test
    public void testGetReviews() {
        List<Review> list =
                reviewRepository.findByMovie(
                        Movie.builder()
                                .mno(34L)
                                .build());
        for (Review r : list) {
            //회원의 이메일 출력 -> 이코드는 에러가 발생 !!!
            //review에는  memeber가 lazy loading으로 설정되었기 때문에 review를 가져오는 시점에는 member정보가 없음
            System.out.println(list);
        }
    }
}
