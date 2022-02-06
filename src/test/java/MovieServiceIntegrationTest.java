import com.kverchi.like.LikeApplication;
import com.kverchi.like.model.Movie;
import com.kverchi.like.service.MovieService;
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
public class MovieServiceIntegrationTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void shouldGetMovieById() {
        Movie movie = movieService.getMovie(2L);
        Assertions.assertEquals("Avengers: Endgame", movie.getTitle());
    }
}
