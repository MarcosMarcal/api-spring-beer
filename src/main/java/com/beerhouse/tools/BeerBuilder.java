package com.beerhouse.tools;

import com.beerhouse.persistence.relational.entities.Beer;

public class BeerBuilder {

    private Beer beer;

    private BeerBuilder() {}

    public static BeerBuilder aBeer(){
        BeerBuilder builder = new BeerBuilder();
        builder.beer = new Beer();
        builder.beer.setName("ANY BEER");
        builder.beer.setIngredients("ANY INGREDIENTS");
        builder.beer.setAlcoholContent("TO MUCH ALCOHOL");
        builder.beer.setPrice(10.0);
        builder.beer.setCategory("NICE BEER");
        return builder;
    }

    public static BeerBuilder aRedBeer(){
        BeerBuilder builder = new BeerBuilder();
        builder.beer = new Beer();
        builder.beer.setName("ANY RED BEER");
        builder.beer.setIngredients("RED INGREDIENTS");
        builder.beer.setAlcoholContent("15 ALCOHOL");
        builder.beer.setPrice(10.0);
        builder.beer.setCategory("RED LOVE");
        return builder;
    }

    public static BeerBuilder aBlueBeer(){
        BeerBuilder builder = new BeerBuilder();
        builder.beer = new Beer();
        builder.beer.setName("ANY BLUE BEER");
        builder.beer.setIngredients("BLUE INGREDIENTS");
        builder.beer.setAlcoholContent("10 ALCOHOL");
        builder.beer.setPrice(10.0);
        builder.beer.setCategory("BLUE OCEAN");
        return builder;
    }

    public BeerBuilder named(String name) {
        beer.setName(name);
        return this;
    }

    public BeerBuilder costing(double price) {
        beer.setPrice(price);
        return this;
    }

    public Beer now() {
        return beer;
    }

}
