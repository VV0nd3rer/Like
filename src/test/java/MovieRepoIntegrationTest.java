import com.kverchi.like.LikeApplication;
import com.kverchi.like.model.Movie;
import com.kverchi.like.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Sql(scripts = { "/movie-data.sql" })
@SpringBootTest(classes = LikeApplication.class)
public class MovieRepoIntegrationTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldFindMovieById() {
        Movie movie = movieRepository.findById(2L).get();
        Assertions.assertEquals("Avengers: Endgame", movie.getTitle());
    }

}
