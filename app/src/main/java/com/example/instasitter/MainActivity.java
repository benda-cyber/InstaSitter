package com.example.instasitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toRegisterPage(View view) {

        Button toRegister = (Button) view;

        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void toLoginPage(View view) {

        Button toLogin = (Button) view;
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}