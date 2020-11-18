package com.ecommercefarm.test.data;

import java.util.ArrayList;
import java.util.List;

public class FilmList {
    private static List<Film> list=new ArrayList();

    public static void add(Film film){
        list.add(film);
    }

    public static Film get(int index){
        return list.get(index);
    }

    public static void set(List<Film> films){
        list=films;
    }

    public static int size(){
        return list.size();
    }

    public static List<Film> getAll(){
        return list;
    }
}
