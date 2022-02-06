package com.kverchi.like.service;

import com.kverchi.like.model.Movie;
import com.kverchi.like.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Movie getMovie(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
