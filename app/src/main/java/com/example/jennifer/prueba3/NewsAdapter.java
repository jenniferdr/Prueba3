package com.example.jennifer.prueba3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewsAdapter extends FirebaseRecyclerAdapter<GoodNew, NewsAdapter.NewsViewHolder>{

    private OnClickNew onClickNew;

    public NewsAdapter(@NonNull FirebaseRecyclerOptions<GoodNew> options, OnClickNew onClickNew) {
        super(options);
        this.onClickNew = onClickNew;
    }

    @Override
    protected void onBindViewHolder(@NonNull final NewsViewHolder holder, int position, @NonNull final GoodNew model) {
        holder.newsNameTv.setText(model.getGoodNew());
        holder.newsDateTv.setText(model.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNew.onClick(model);

            }
        });
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_list_item, parent, false);

        return new NewsViewHolder(view);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView newsNameTv;
        TextView newsDateTv;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsNameTv = itemView.findViewById(R.id.newsNameTV);
            newsDateTv = itemView.findViewById(R.id.newsDateTV);
        }
    }
}
