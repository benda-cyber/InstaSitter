package com.example.instasitter.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instasitter.R;
//import com.example.instasitter.adapters.ProfileReviewsCustomAdapter;
import com.example.instasitter.classes.MyData;
import com.example.instasitter.classes.ServiceProviderModel;

import java.util.ArrayList;

public class ServiceProviderProfile extends AppCompatActivity {

//    private static ProfileReviewsCustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ServiceProviderModel> data;
    public static View.OnTouchListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    ServiceProviderModel currentProfile;

    TextView textViewName;
    TextView textViewLocation;
    TextView textViewServiceType;
    TextView textViewAbout;
    ImageView imageView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);
        textViewName = (TextView) findViewById(R.id.serviceProviderNameInProfile);
        textViewLocation = (TextView) findViewById(R.id.serviceProviderLocationInProfile);
        textViewServiceType = (TextView) findViewById(R.id.serviceType);
        textViewAbout = (TextView) findViewById(R.id.aboutInProfile);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        imageView = (ImageView) findViewById(R.id.profilePicture);


        if (getIntent().hasExtra("service_provider_profile")) {
            currentProfile = getIntent().getParcelableExtra("service_provider_profile");

            textViewName.setText(currentProfile.getName());
            textViewLocation.setText(currentProfile.getLocation());
            textViewServiceType.setText(currentProfile.getServiceType());
            //textViewAbout.setText();
           // ratingBar.setNumStars(MyData.rating[profileId]);
            imageView.setImageResource(currentProfile.getImage());
        }


        recyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());





        removedItems = new ArrayList<Integer>();

//        adapter = new ProfileReviewsCustomAdapter(data);
//        recyclerView.setAdapter(adapter);



    }

}
