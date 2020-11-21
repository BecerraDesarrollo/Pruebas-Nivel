package com.ecommercefarm.test.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseData {
    private FireBaseDataEvent event=null;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference datas = database.getReference("messages");
    private final DatabaseReference maintenance = database.getReference("maintenance");

    public void loadAll(){

        Log.i("FirebaseData","Loading... ");

        //Se limpia la lista.
        BookList.clear();
        //Se añade la escucha para gestionar cuando se reciban los datos.
        datas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("FirebaseData","loadAll");
                //Se crea un libro por cada elemento recibido
                for(DataSnapshot data:snapshot.getChildren()){
                    Book book=new Book();
                    book.setId(Integer.valueOf(data.getKey()));
                    book.setTitle((String)data.child("title").getValue());
                    book.setIsbn((long)data.child("isbn").getValue());
                    book.setGenre((String)data.child("genre").getValue());
                    book.setDescription((String)data.child("description").getValue());
                    BookList.add(book);
                    //Log.d("FirebaseData",book.toString());
                }
                //Se genera un evento cuando ya no se reciben más elementos para que se pueda trabajar con la lista.
                if(event!=null){
                    event.loaded();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //En caso de error que se genere un evento para gestionarlo.
                if(event!=null){
                    event.canceled();
                }
                Log.d("FirebaseData","onCancelled");
            }
        });

        Log.i("FirebaseData","Loaded");
    }

    public void loadFilter(Genre genre){

        Log.i("FirebaseData","Loading... ");

        //Se limpia la lista.
        BookList.clear();
        //Se añade la escucha para gestionar cuando se reciban los datos.
        datas.orderByChild("genre").equalTo(genre.value()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("FirebaseData","loadFilter: "+genre.value());
                //Se crea un libro por cada elemento recibido
                for(DataSnapshot data:snapshot.getChildren()){
                    Book book=new Book();
                    book.setId(Integer.valueOf(data.getKey()));
                    book.setTitle((String)data.child("title").getValue());
                    book.setIsbn((long)data.child("isbn").getValue());
                    book.setGenre((String)data.child("genre").getValue());
                    book.setDescription((String)data.child("description").getValue());
                    BookList.add(book);
                    //Log.d("FirebaseData",book.toString());
                }
                //Se genera un evento cuando ya no se reciben más elementos para que se pueda trabajar con la lista.
                if(event!=null){
                    event.loaded();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //En caso de error que se genere un evento para gestionarlo.
                if(event!=null){
                    event.canceled();
                }
                Log.d("FirebaseData","onCancelled");
            }
        });

        Log.i("FirebaseData","Loaded");
    }

    public void service(){
        Log.i("FirebaseData","Service");
        maintenance.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("FirebaseData","Service: "+snapshot.getValue());
                //Se genera un evento en caso de que entre o salga de mantenimiento
                if(snapshot.getValue(Integer.class)==0){
                    if(event!=null)
                        event.maintenance(false);
                }else{
                    if(event!=null)
                        event.maintenance(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setActionListeners(FireBaseDataEvent event){
        this.event=event;
    }


    public interface FireBaseDataEvent{
        //Se crea una interfaz para que se puedan crear eventos
        void loaded();
        void canceled();
        void maintenance(boolean is);
    }
}
