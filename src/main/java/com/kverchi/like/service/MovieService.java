package com.kverchi.like.service;

import com.kverchi.like.events.MovieUpdatedEvent;
import com.kverchi.like.model.Movie;
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
        final MovieUpdatedEvent movieUpdatedEvent = new MovieUpdatedEvent(movie);
        applicationEventPublisher.publishEvent(movieUpdatedEvent);
    }
}
