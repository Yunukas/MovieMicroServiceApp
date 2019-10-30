package com.yy.movie.controller;

import com.yy.movie.entity.Movie;
import com.yy.movie.repository.MovieRepository;
import com.yy.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public void addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") Long id){
        return movieService.getMovieWithId(id);
    }
}
