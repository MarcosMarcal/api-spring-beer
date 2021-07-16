package com.beerhouse.exceptions;

public class BadRequestException extends Exception {

    public static final String IRREGULAR_PRICE = "The value entered for the price is not acceptable.";
    public static final String FIELDS_NOT_SENT = "Some fields contain irregular values or were not sent.";
    public static final String NULL_VALUE = "Some informed fields have a null value.";

    public BadRequestException(String message) {
        super(message);
    }
}
