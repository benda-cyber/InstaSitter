package com.example.instasitter.adapters;

import android.content.ClipData.Item;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.instasitter.R;
import com.example.instasitter.activities.ServiceProviderProfile;
import com.example.instasitter.activities.MainActivity;
import com.example.instasitter.classes.ServiceProviderModel;
import com.example.instasitter.classes.User;

import java.util.ArrayList;

public class CardCustomAdapter extends RecyclerView.Adapter<CardCustomAdapter.MyViewHolder> {

    private static final String TAG = "CardCustomAdapter";
    private ArrayList<ServiceProviderModel> dataSet;
    private OnCardListener mOnCardListener;

    public CardCustomAdapter(ArrayList<ServiceProviderModel> data, OnCardListener onCardListener) {
        this.dataSet = data;
        this.mOnCardListener = onCardListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view,mOnCardListener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewLocation = holder.textViewLocation;
        TextView textViewServiceType = holder.textViewServiceType;
        ImageView imageView = holder.imageViewIcon;
        CardView cardView = holder.cardView;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewLocation.setText(dataSet.get(listPosition).getLocation());
        textViewServiceType.setText(dataSet.get(listPosition).getServiceType());
        imageView.setImageResource(dataSet.get(listPosition).getImage());

    }

    public class MyViewHolder extends ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView textViewName;
        TextView textViewLocation;
        TextView textViewServiceType;
        ImageView imageViewIcon;
        View itemView;
        Item currentItem;
        OnCardListener onCardListener;

        public MyViewHolder(@NonNull View itemView, OnCardListener onCardListener) {
            super(itemView);

            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.textViewName = (TextView) itemView.findViewById(R.id.serviceProviderName);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.serviceProviderLocation);
            this.textViewServiceType = (TextView) itemView.findViewById(R.id.serviceProviderServiceType);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.serviceProviderPhoto);
            this.itemView = itemView;
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }


    }


    public interface OnCardListener{

        void onCardClick(int position);

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
