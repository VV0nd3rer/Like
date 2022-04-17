package com.kverchi.like.service;

import com.kverchi.like.entity.Movie;
import com.kverchi.like.events.ModificationType;
import com.kverchi.like.events.MovieModifyEvent;
import com.kverchi.like.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    private final LoggerService loggerService;

    private final ApplicationEventPublisher applicationEventPublisher;

//    Option 1
//    @Autowired
//    private MovieService self;

    /**
     * In proxy mode (which is the default),
     * only external method calls coming in through the proxy are intercepted.
     * This means that self-invocation (in effect, a method within the target object calling another method of the target object)
     * does not lead to an actual transaction at runtime even if the invoked method is marked with @Transactional
     * https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction
     */
//    Option 2
//    But calling method updateMovie() will ignore @Transaction annotation on it (updateMovie method)
//    @Transactional
    public void checkMovie(Movie movie, String rating) {
        Movie originalMovie = movieRepository.findById(movie.getId()).orElse(null);
        if(originalMovie != null && originalMovie.getRating().equals(rating) && originalMovie.getDescription().isEmpty()) {
            movie.setDescription("parental guidance suggested");
//            This won't work - only external method calls coming in through the proxy are intercepted
//            updateMovie(movie);

//            Option 1
//            You could use external method call instead, but it's not best solution
//            (forces to use field injection)
//            see https://codete.com/blog/5-common-spring-transactional-pitfalls
//            And it's difficult to test
//            https://stackoverflow.com/questions/63187187/self-injection-in-spring-and-testing-it-using-mockito
//            self.updateMovie(movie);
        }
    }

    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);

        final MovieModifyEvent movieModifyEvent = new MovieModifyEvent(movie, ModificationType.UPDATE);
        applicationEventPublisher.publishEvent(movieModifyEvent);

        loggerService.log("Movie with id " + movie.getId() + " was saved.");
    }

    @Transactional
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);

        final MovieModifyEvent movieModifyEvent = new MovieModifyEvent(movie, ModificationType.DELETE);
        applicationEventPublisher.publishEvent(movieModifyEvent);

        loggerService.log("Movie with id " + movie.getId() + " was deleted.");
    }
}
