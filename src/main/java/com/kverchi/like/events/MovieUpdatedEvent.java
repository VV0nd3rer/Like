package com.kverchi.like.events;

import com.kverchi.like.model.Movie;

/*
Event class for movie update.
Spring wraps it in PayloadApplicationEvent  itself.
 */
public class MovieUpdatedEvent {
    private final Movie movie;

    public MovieUpdatedEvent(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
