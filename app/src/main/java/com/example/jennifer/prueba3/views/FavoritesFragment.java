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

import com.example.jennifer.prueba3.util.CurrentUser;
import com.example.jennifer.prueba3.adapters.NewsAdapter;
import com.example.jennifer.prueba3.OnClickNew;
import com.example.jennifer.prueba3.R;
import com.example.jennifer.prueba3.models.GoodNew;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<GoodNew, NewsAdapter.NewsViewHolder> mAdapter;
    private OnClickNew onClickNew;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public void setOnClick(OnClickNew onClick){
        this.onClickNew = onClick;
    }

    public static FavoritesFragment newInstance(OnClickNew onClickNew) {
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setOnClick(onClickNew);
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goodnews, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager;

        mRecyclerView = view.findViewById(R.id.recyclerV);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("favoritas")
                .child(CurrentUser.getCurrentUser().getUid())
                .limitToLast(50);

        FirebaseRecyclerOptions<GoodNew> options =
                new FirebaseRecyclerOptions.Builder<GoodNew>()
                        .setQuery(query, GoodNew.class)
                        .build();

        mAdapter = new NewsAdapter(options, onClickNew);
        mRecyclerView.setAdapter(mAdapter);
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
