package com.example.jennifer.prueba3.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataSource{

    private static FirebaseDatabase newsDatabase = FirebaseDatabase.getInstance();

    public static DatabaseReference getFavorites(){return newsDatabase.getReference("favoritas");}

    public static DatabaseReference getNews(){return newsDatabase.getReference("noticias");}


}
