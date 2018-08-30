package com.example.jennifer.prueba3;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if( CurrentUser.getCurrentUser() != null){
            logged();
        }else{
            signIn();
        }
    }

    public void signIn(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                    new AuthUI.IdpConfig.EmailBuilder().build()/*,
                                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                                    new AuthUI.IdpConfig.TwitterBuilder().build(),
                                    new AuthUI.IdpConfig.GitHubBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().build()*/))
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Log.d("Logued>","OK");
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                logged();
            } else {
                Log.d("Logued2>","Error");
            }
        }
    }

    private void logged(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
