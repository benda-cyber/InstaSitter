package com.example.instasitter.adapters;

import android.content.ClipData.Item;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.instasitter.R;
import com.example.instasitter.activities.ServiceProviderProfile;
import com.example.instasitter.activities.MainActivity;
import com.example.instasitter.classes.ServiceProviderModel;

import java.util.ArrayList;

public class CardCustomAdapter extends RecyclerView.Adapter<CardCustomAdapter.MyViewHolder> {

    private ArrayList<ServiceProviderModel> dataSet;


    public static class MyViewHolder extends ViewHolder {

        CardView cardView;
        TextView textViewName;
        TextView textViewLocation;
        TextView textViewServiceType;
        ImageView imageViewIcon;
        View itemView;
        Item currentItem;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.textViewName = (TextView) itemView.findViewById(R.id.serviceProviderName);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.serviceProviderLocation);
            this.textViewServiceType = (TextView) itemView.findViewById(R.id.serviceProviderServiceType);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.serviceProviderPhoto);
            this.itemView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    // item clicked
                }
            });

        }
    }



    public CardCustomAdapter(ArrayList<ServiceProviderModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnTouchListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewLocation = holder.textViewLocation;
        TextView textViewServiceType = holder.textViewServiceType;
        ImageView imageView = holder.imageViewIcon;
        CardView cardView = holder.cardView;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewLocation.setText(dataSet.get(listPosition).getLocation());
        textViewServiceType.setText(dataSet.get(listPosition).getServiceType());
        imageView.setImageResource(dataSet.get(listPosition).getImage());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                removeAt(listPosition);
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServiceProviderProfile.class);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void removeAt(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataSet.size());
    }

}
