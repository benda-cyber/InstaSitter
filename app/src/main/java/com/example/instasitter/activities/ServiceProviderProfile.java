package com.example.instasitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instasitter.R;
//import com.example.instasitter.adapters.ProfileReviewsCustomAdapter;
import com.example.instasitter.classes.MyData;
import com.example.instasitter.classes.ServiceProvider;
import com.example.instasitter.classes.ServiceProviderModel;
import com.example.instasitter.classes.User;

import java.util.ArrayList;

public class ServiceProviderProfile extends AppCompatActivity {

//    private static ProfileReviewsCustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ServiceProvider> data;
    public static View.OnTouchListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private ServiceProvider currentProfile;

    TextView textViewName;
    TextView textViewFamilyName;
    TextView textViewPhone;
    TextView textViewEmail;
    TextView textViewLocation;
    TextView textViewServiceType;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);
        textViewName = (TextView) findViewById(R.id.serviceProviderNameInProfile);
        textViewFamilyName = (TextView) findViewById(R.id.serviceProviderFamilyNameInProfile);
        textViewPhone = (TextView) findViewById(R.id.phoneInProfile);
        textViewEmail = (TextView) findViewById(R.id.emailInProfile);
        textViewLocation = (TextView) findViewById(R.id.serviceProviderLocationInProfile);
        textViewServiceType = (TextView) findViewById(R.id.serviceType);
        imageView = (ImageView) findViewById(R.id.profilePicture);


        if (getIntent().hasExtra("service_provider_profile")) {
            Intent intent = getIntent();
            currentProfile  = (ServiceProvider) intent.getSerializableExtra("service_provider_profile");

            textViewName.setText(currentProfile.getName());
            textViewFamilyName.setText(currentProfile.getFamilyName());
            textViewPhone.setText(currentProfile.getPhone());
            textViewEmail.setText(currentProfile.getEmail());
            textViewLocation.setText(currentProfile.getAddress());
            textViewServiceType.setText(currentProfile.getServices());
            Glide.with(ServiceProviderProfile.this).load(currentProfile.getProfilePic()).into(imageView);

            //textViewAbout.setText();
           // ratingBar.setNumStars(MyData.rating[profileId]);

        }

//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        removedItems = new ArrayList<Integer>();

    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
