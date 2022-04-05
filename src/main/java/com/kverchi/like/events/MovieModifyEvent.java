package com.kverchi.like.events;

import com.kverchi.like.entity.Movie;

/*
Event class for movie update.
Spring wraps it in PayloadApplicationEvent  itself.
 */
public class MovieModifyEvent implements ModifyEvent<Movie> {
    private final Movie movie;
    private final ModificationType modificationType;

    public MovieModifyEvent(Movie movie, ModificationType modificationType) {
        this.movie = movie;
        this.modificationType = modificationType;
    }


    @Override
    public Movie getMedia() {
        return movie;
    }

    @Override
    public ModificationType getModificationType() {
        return modificationType;
    }
}
