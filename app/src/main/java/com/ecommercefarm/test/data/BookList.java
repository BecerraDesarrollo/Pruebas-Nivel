package com.ecommercefarm.test.data;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private static List<Book> list=new ArrayList();

    public static void add(Book book){
        list.add(book);
    }

    public static Book get(int index){
        return list.get(index);
    }

    public static void set(List<Book> books){
        list= books;
    }

    public static int size(){
        return list.size();
    }

    public static List<Book> getAll(){
        return list;
    }

    public static void clear(){
        list.clear();
    }
}
