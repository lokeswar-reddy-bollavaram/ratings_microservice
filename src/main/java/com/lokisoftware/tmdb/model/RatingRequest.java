package com.lokisoftware.tmdb.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RatingRequest {
    private String name;
    private double stars;
}
