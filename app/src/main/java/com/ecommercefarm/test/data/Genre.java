package com.ecommercefarm.test.data;

public enum Genre {
    EMPTY(""),
    FANTASY("fantasy"),
    CRIME("crime"),
    ROMANCE("romance");

    private final String genre;

    Genre(String genre){
        this.genre = genre;
    }

    public String value(){
        return genre;
    }

}
