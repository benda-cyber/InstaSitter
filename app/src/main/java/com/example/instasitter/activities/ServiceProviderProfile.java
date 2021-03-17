package com.example.instasitter.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instasitter.R;
import com.example.instasitter.adapters.CardCustomAdapter;
import com.example.instasitter.adapters.ProfileReviewsCustomAdapter;
import com.example.instasitter.classes.MyData;
import com.example.instasitter.classes.ServiceProviderModel;

import java.util.ArrayList;

public class ServiceProviderProfile extends AppCompatActivity {

    private static ProfileReviewsCustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ServiceProviderModel> data;
    public static View.OnTouchListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_profile_layout);


        recyclerView = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<ServiceProviderModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new ServiceProviderModel(
                    MyData.nameArray[i],
                    MyData.locationArray[i],
                    MyData.serviceTypeArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new ProfileReviewsCustomAdapter(data);
        recyclerView.setAdapter(adapter);



    }

}
