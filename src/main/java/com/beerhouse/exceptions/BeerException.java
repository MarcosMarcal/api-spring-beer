package com.beerhouse.exceptions;

public class BeerException extends Exception {

    public static final String ALREADY_EXISTS = "This beer already exists.";
    public static final String NOT_EXISTS = "The informed beer does not exist.";

    public BeerException(String message) {
        super(message);
    }
}
