package com.example.instasitter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.instasitter.adapters.CardCustomAdapter;
import com.example.instasitter.R;
import com.example.instasitter.classes.ServiceProviderModel;
import com.example.instasitter.classes.MyData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardCustomAdapter.OnCardListener,FloatingActionButton.OnClickListener{

    private static CardCustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ServiceProviderModel> data;
    public static View.OnTouchListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.profiles_recycler_view);
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

        adapter = new CardCustomAdapter(data,this);
        recyclerView.setAdapter(adapter);


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
        intent.putExtra("service_provider_profile", data.get(position));
        Log.d(TAG, "onCardClick: "+data.get(position));
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