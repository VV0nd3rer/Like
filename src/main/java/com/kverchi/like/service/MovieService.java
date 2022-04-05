package com.kverchi.like.service;

import com.kverchi.like.entity.Movie;
import com.kverchi.like.events.ModificationType;
import com.kverchi.like.events.MovieModifyEvent;
import com.kverchi.like.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LoggerService loggerService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
        loggerService.log("Movie with id " + movie.getId() + " was saved.");
        final MovieModifyEvent movieModifyEvent = new MovieModifyEvent(movie, ModificationType.UPDATE);
        applicationEventPublisher.publishEvent(movieModifyEvent);
    }

    @Transactional
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
        loggerService.log("Movie with id " + movie.getId() + " was deleted.");
        final MovieModifyEvent movieModifyEvent = new MovieModifyEvent(movie, ModificationType.DELETE);
        applicationEventPublisher.publishEvent(movieModifyEvent);
    }
}
