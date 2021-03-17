package com.example.instasitter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.instasitter.R;

public class ServiceProviderExtra extends AppCompatActivity
{


    ImageView imagetoupload, imagetoupload2;
    Button Bphoto, BIDphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_extra);

        imagetoupload = (ImageView) findViewById(R.id.imagetoupload);
        imagetoupload2 = (ImageView) findViewById(R.id.imagetoupload2);

        Bphoto = (Button) findViewById(R.id.Bphoto);
        BIDphoto = (Button) findViewById(R.id.BIDphoto);

        imagetoupload.setOnClickListener(this);
        imagetoupload2.setOnClickListener(this);
    }


}
