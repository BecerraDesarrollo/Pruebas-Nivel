package com.ecommercefarm.test.data;

public class Film {
    public  int id;
    public  String title,
            isbn,
            genre,
            description;

    public Film(){
        description="Not included";
    }

    @Override
    public String toString(){
        return String.format("Title: %s\n" +
                "ISBN: %s\n" +
                "Description: %s",
                title,isbn,description);
    }
}
