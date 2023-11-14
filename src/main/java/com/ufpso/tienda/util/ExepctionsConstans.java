package com.ufpso.tienda.util;

import lombok.Getter;

@Getter
public enum ExepctionsConstans {

    ARTICLE_NOT_FOUND("Article not found"),
    USER_NOT_FOUND( "User not found"),
    ARTICLE_IS_NULL("Article is null"),
    USER_IS_NULL("Usser is null"),
    ADDRESS_IS_NULL("Address is null"),
    ADDRESS_NOT_FOUND("Address not found"),
    CREDENTIAL_INVALID("Invalid username or password");

    private final String message;

    private ExepctionsConstans(String message){
        this.message =  message;
    }

}
