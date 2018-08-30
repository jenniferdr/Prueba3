package com.example.jennifer.prueba3.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jennifer.prueba3.adapters.NewsAdapter;
import com.example.jennifer.prueba3.OnClickNew;
import com.example.jennifer.prueba3.R;
import com.example.jennifer.prueba3.models.GoodNew;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NewsListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<GoodNew, NewsAdapter.NewsViewHolder> mAdapter;
    private OnClickNew onClickNew;

    public NewsListFragment() {
    }

    public void setOnClickNew(OnClickNew onClickNew){
        this.onClickNew = onClickNew;
    }

    public static NewsListFragment newInstance(OnClickNew onClickNew) {
        NewsListFragment fragment = new NewsListFragment();
        fragment.setOnClickNew(onClickNew);
        /*Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_goodnews, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager;
        //DatabaseReference newsDatabase = FirebaseDatabase.getInstance().getReference("noticias");

        mRecyclerView = view.findViewById(R.id.recyclerV);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("noticias")
                .limitToLast(50);

        FirebaseRecyclerOptions<GoodNew> options =
                new FirebaseRecyclerOptions.Builder<GoodNew>()
                        .setQuery(query, GoodNew.class)
                        .build();

        mAdapter = new NewsAdapter(options, onClickNew);
        mRecyclerView.setAdapter(mAdapter);


        /*newsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("onDataChange: ", dataSnapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("onDataChange: ", "error");
            }
        });*/

        /*GoodNew ng = new GoodNew();
        ng.setGoodNew("Se sube la data a firebase");
        ng.setDate("Hoy");
        newsDatabase.child("not01").setValue(ng);*/

    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
