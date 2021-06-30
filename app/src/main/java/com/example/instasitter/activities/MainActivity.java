package com.example.instasitter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.instasitter.adapters.CardCustomAdapter;
import com.example.instasitter.R;
import com.example.instasitter.classes.ServiceProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CardCustomAdapter.OnCardListener,FloatingActionButton.OnClickListener{

    private CardCustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ServiceProvider> serviceProviderList;
    public static View.OnTouchListener myOnClickListener;
    private static ArrayList<ServiceProvider> removedItems;
    private static final String TAG = "MainActivity";
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.profiles_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        myRef = FirebaseDatabase.getInstance().getReference();
        serviceProviderList = new ArrayList<>();
        
        removedItems = new ArrayList<>();

        adapter = new CardCustomAdapter(this,serviceProviderList);
        recyclerView.setAdapter(adapter);

        ClearAll();

        GetDataFromFirebase();

    }

    private void GetDataFromFirebase() {
        Query query = myRef.child("service_providers");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ServiceProvider serviceProvider = new ServiceProvider();

                    serviceProvider.setProfilePic(dataSnapshot.child("profilePic").getValue().toString());
                    serviceProvider.setName(dataSnapshot.child("name").getValue().toString());
                    serviceProvider.setAddress(dataSnapshot.child("address").getValue().toString());

                    if(dataSnapshot.child("babysitter").getValue().toString().equals("true")){
                        serviceProvider.setBabysitter(true);
                    }
                    if(dataSnapshot.child("dogwalker").getValue().toString().equals("true")){
                        serviceProvider.setDogwalker(true);
                    }
                    if(!serviceProvider.getServices().equals("Not a Service Provider")) {
                        serviceProviderList.add(serviceProvider);
                    }
                    
                }
                adapter = new CardCustomAdapter(getApplicationContext(), serviceProviderList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }

    private void ClearAll() {
        if(serviceProviderList != null){
            serviceProviderList.clear();

            if(adapter != null){
                adapter.notifyDataSetChanged();
            }
        }
        else{
            serviceProviderList = new ArrayList<>();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
//        return true;
//    }

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

    @Override
    public void onCardClick(int position) {

        Intent intent = new Intent(this,ServiceProviderProfile.class);
        intent.putExtra("service_provider_profile", serviceProviderList.get(position));
        Log.d(TAG, "onCardClick: "+ serviceProviderList.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ServiceProviderProfile.class);
        startActivity(intent);
    }
//
//    public void goToServiceProvider(View view) {
//        Button serviceProviderCard = (Button) view;
//        Intent intent = new Intent(this, ServiceProviderProfile.class);
//        startActivity(intent);

//    }

}