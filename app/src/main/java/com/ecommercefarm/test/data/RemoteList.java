package com.ecommercefarm.test.data;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteList {
    public static RemoteListEvent event;

    public static void load(Filter filter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("RemoteList","Loading...");

                Retrofit retrofit  = new Retrofit.Builder()
                        .baseUrl(RestService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RestService service = retrofit.create(RestService.class);
                Call<List<Book>> films;

                switch (filter){
                    case NOT:
                        films = service.all();
                        break;
                    case YES:
                        films = service.filter();
                        break;
                    default:
                        films = service.all();
                }

                films.enqueue(new Callback<List<Book>>() {
                    @Override
                    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                        if (!response.isSuccessful()){
                            Log.e("RemoteList", "Error loading. Code: " + response.code());
                        }else {
                            BookList.set(response.body());
                            if(event!=null)
                                event.loaded();
                            Log.i("RemoteList", "List loaded");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Book>> call, Throwable t) {
                        for(StackTraceElement er:t.getStackTrace())
                            Log.e("RemoteList", "Error: " + er.toString());
                    }
                });
            }
        }).start();
    }

    public static void setActionListeners(RemoteListEvent event){
        RemoteList.event=event;
    }

    public enum Filter{
        NOT,YES
    }
    public interface RemoteListEvent{
        void loaded();
    }
}
