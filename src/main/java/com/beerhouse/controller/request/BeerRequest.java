package com.beerhouse.controller.request;

import com.beerhouse.models.Beer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeerRequest {

    private Long id;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private Double price;
    private String category;

    public Beer asBeer() {
        var beer = new Beer();
        beer.setName(this.name);
        beer.setIngredients(this.ingredients);
        beer.setAlcoholContent(this.alcoholContent);
        beer.setPrice(this.price);
        beer.setCategory(this.category);
        return beer;
    }

}
