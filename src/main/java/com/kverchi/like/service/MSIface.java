package com.kverchi.like.service;

import com.kverchi.like.entity.Movie;
import org.springframework.transaction.annotation.Transactional;

public interface MSIface {

    @MyAnnotation
    @Transactional
    void checkMovie(Movie movie, String rating);

    @Transactional
    void updateMovie(Movie movie);

    Movie getMovie(Long l);
}
