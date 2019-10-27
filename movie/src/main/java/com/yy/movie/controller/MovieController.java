package com.yy.movie.controller;

import com.yy.movie.entity.Movie;
import com.yy.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public void addMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") Long id){
        return movieRepository.findById(id).orElse(null);
    }
}
