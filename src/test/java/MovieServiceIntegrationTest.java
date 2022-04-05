import com.kverchi.like.LikeApplication;
import com.kverchi.like.model.Movie;
import com.kverchi.like.service.LoggerService;
import com.kverchi.like.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@ExtendWith(SpringExtension.class)
@SqlGroup({
        @Sql(scripts = "/movie-data.sql"),
        @Sql(scripts = "/movie-data-cleanup.sql", executionPhase = AFTER_TEST_METHOD)
})
@SpringBootTest(classes = LikeApplication.class)
@Slf4j
public class MovieServiceIntegrationTest {
    @Autowired
    private MovieService movieService;

    @MockBean
    private LoggerService loggerService;


    @Test
    public void shouldFindMovieById() {
        Movie movie = movieService.getMovie(2L);
        Assertions.assertEquals("Avengers: Endgame", movie.getTitle());
    }

    @Test
    public void shouldUpdateMovie() {
        //given
        Movie oldMovie = movieService.getMovie(2L);
        Assertions.assertEquals("Avengers: Endgame", oldMovie.getTitle());

        //when
        oldMovie.setTitle("My custom title");
        movieService.updateMovie(oldMovie);

        //then
        Movie updatedMovie = movieService.getMovie(2L);
        Assertions.assertEquals("My custom title", updatedMovie.getTitle());
    }

    @Test
    //should rollback for unchecked exception
    public void shouldFailToUpdateMovie() {
        //given
        Movie oldMovie = movieService.getMovie(2L);
        Assertions.assertEquals("Avengers: Endgame", oldMovie.getTitle());

        //when
        oldMovie.setTitle("My custom title");
        doThrow(new RuntimeException()).when(loggerService).log(anyString());

        try {
            movieService.updateMovie(oldMovie);
        } catch (RuntimeException e) {
            log.info("Exception happened: {}", e.getMessage());
        }
        //then
        Movie updatedMovie = movieService.getMovie(2L);
        Assertions.assertEquals("Avengers: Endgame", updatedMovie.getTitle());
    }
}
