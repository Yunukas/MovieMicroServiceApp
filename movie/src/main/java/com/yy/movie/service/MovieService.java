package com.yy.movie.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yy.movie.entity.Movie;
import com.yy.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @HystrixCommand
    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    @HystrixCommand
    public Movie getMovieWithId(Long id){
        return movieRepository.findById(id).orElse(null);
    }
}
