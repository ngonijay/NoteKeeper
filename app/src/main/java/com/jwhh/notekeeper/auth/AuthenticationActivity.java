package com.jwhh.notekeeper.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.jwhh.notekeeper.R;
import com.jwhh.notekeeper.auth.login.LoginFragment;

public class AuthenticationActivity extends AppCompatActivity {


    private FrameLayout authFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        authFrameLayout = findViewById(R.id.authFrameLayout);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.authFrameLayout, LoginFragment.class, null)
                .commit();
    }
}