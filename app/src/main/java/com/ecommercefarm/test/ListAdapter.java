package com.ecommercefarm.test;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommercefarm.test.data.Film;
import com.ecommercefarm.test.data.FilmList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>{

    private ViewGroup parent;
    private TextView id,
            title,
            gener,
            isbn,
            description;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent=parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent, false);
        id=view.findViewById(R.id.idTextView);
        title=view.findViewById(R.id.titleTextView);
        gener=view.findViewById(R.id.genreTextView);
        isbn=view.findViewById(R.id.isbnTextView);
        description=view.findViewById(R.id.descriptionTextView);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Film film=FilmList.get(position);
        id.setText(String.valueOf(film.id));
        title.setText(film.title);
        gener.setText(film.genre);
        isbn.setText(film.isbn);
        description.setText(film.description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View dialogoView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

                TextView id,
                        title,
                        gener,
                        isbn,
                        description;

                id=dialogoView.findViewById(R.id.idTextView);
                title=dialogoView.findViewById(R.id.titleTextView);
                gener=dialogoView.findViewById(R.id.genreTextView);
                isbn=dialogoView.findViewById(R.id.isbnTextView);
                description=dialogoView.findViewById(R.id.descriptionTextView);

                id.setText(String.valueOf(film.id));
                title.setText(film.title);
                gener.setText(film.genre);
                isbn.setText(film.isbn);
                description.setText(film.description);

                final AlertDialog.Builder dialogo=new AlertDialog.Builder(view.getContext());
                dialogo.setView(dialogoView).setNegativeButton("Volver",null).
                        setCancelable(true);
                dialogo.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return FilmList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        public Holder(@NonNull View itemView) {

            super(itemView);

        }
    }
}
