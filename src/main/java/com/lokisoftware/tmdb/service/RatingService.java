package com.lokisoftware.tmdb.service;

import com.lokisoftware.tmdb.exception.NotFoundException;
import com.lokisoftware.tmdb.model.Rating;
import com.lokisoftware.tmdb.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Rating updateAverage(String name, double stars){
        Rating rating = ratingRepository.findByName(name);

        if(rating == null){
            rating = new Rating();
            rating.setName(name);
            rating.setAvgRating(stars);
            rating.setCount(1);
        }else {

            int count = rating.getCount();
            double newAverage = (rating.getAvgRating() * count + stars) / (count + 1);

            rating.setAvgRating(newAverage);
            rating.setCount(++count);
        }

        return ratingRepository.save(rating);
    }

    public Rating fetchRating(String name){
        Rating rating = ratingRepository.findByName(name);
        if(rating == null){
            throw new NotFoundException("Movie not found with name: "+ name);
        }
        return rating;
    }
}
