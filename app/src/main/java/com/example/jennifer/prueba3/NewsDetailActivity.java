package com.example.jennifer.prueba3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        final EditText titleNew = findViewById(R.id.titleNewEt);
        final EditText dateNew = findViewById(R.id.dateNewEt);
        Button favBtn = findViewById(R.id.addToFavBtn);
        Button saveBtn = findViewById(R.id.saveNewBtn);
        Button deleteBtn = findViewById(R.id.eraseNewBtn);

        Intent intent = getIntent();
        String titleNewStr = intent.getStringExtra("titleNew");
        String dateNewStr = intent.getStringExtra("dateNew");
        final String idNewStr = intent.getStringExtra("idNew");

        titleNew.setText(titleNewStr);
        dateNew.setText(dateNewStr);

        titleNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodNew goodNew = new GoodNew();
                goodNew.setDate(dateNew.getText().toString());
                goodNew.setGoodNew(titleNew.getText().toString());
                DataSource.getFavorites().child(CurrentUser.getCurrentUser().getUid())
                        .child(idNewStr).setValue(goodNew);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodNew goodNew = new GoodNew();
                goodNew.setDate(dateNew.getText().toString());
                goodNew.setGoodNew(titleNew.getText().toString());
                goodNew.setId(idNewStr);
                DataSource.getNews().child(idNewStr).setValue(goodNew);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSource.getNews().child(idNewStr).removeValue();
                finish();
            }
        });
    }
}
