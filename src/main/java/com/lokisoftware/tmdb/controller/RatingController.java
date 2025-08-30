package com.lokisoftware.tmdb.controller;

import com.lokisoftware.tmdb.model.Rating;
import com.lokisoftware.tmdb.model.RatingRequest;
import com.lokisoftware.tmdb.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/{name}")
    public ResponseEntity<Rating> getRating(@PathVariable String name){
        Rating rating  = ratingService.fetchRating(name);
        log.info("Returning rating for moive: {}", name);
        return ResponseEntity.ok(rating);
    }

    @PostMapping
    public ResponseEntity<Rating> updateRating(@RequestBody RatingRequest ratingRequest){
        Rating rating = ratingService.updateAverage(ratingRequest.getName(), ratingRequest.getStars());
        log.info("Returning new average for movie: {}", ratingRequest.getName());
        return ResponseEntity.ok(rating);
    }

}
