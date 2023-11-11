package com.ufpso.tienda.util;

import lombok.Getter;

@Getter
public enum ExepctionsConstans {

    ARTICLE_NOT_FOUND("Article not found");

    private  String message;

    private ExepctionsConstans(String message){
        this.message =  message;
    }


}
