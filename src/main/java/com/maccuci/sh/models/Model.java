package com.maccuci.sh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, description;
    private String type;
    private String urlReference;
    private Float currentPrice, bestPrice, worstPrice;
    private Date currentPriceDate, bestPriceDate, worstPriceDate;
    private Integer ratingStars;

    public Model(String name, String description, String type, String urlReference, Float currentPrice, Float bestPrice, Float worstPrice, Date currentPriceDate, Date bestPriceDate, Date worstPriceDate, Integer ratingStars) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.urlReference = urlReference;
        this.currentPrice = currentPrice;
        this.bestPrice = bestPrice;
        this.worstPrice = worstPrice;
        this.currentPriceDate = currentPriceDate;
        this.bestPriceDate = bestPriceDate;
        this.worstPriceDate = worstPriceDate;
        this.ratingStars = ratingStars;
    }

    public Model() {

    }

    public void updateRatingStars(Integer rs) {
        if(rs < 0 || rs > 5) throw new IllegalArgumentException("Rating stars must be between 0 and 5");
        ratingStars = rs;
    }
}
