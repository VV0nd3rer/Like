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
