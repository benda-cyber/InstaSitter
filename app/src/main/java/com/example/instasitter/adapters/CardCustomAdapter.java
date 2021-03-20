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

import java.util.ArrayList;

public class CardCustomAdapter extends RecyclerView.Adapter<CardCustomAdapter.MyViewHolder> {

    private ArrayList<ServiceProviderModel> dataSet;
    private MyViewHolder.OnCardListener mOnCardListener;

    public static class MyViewHolder extends ViewHolder implements View.OnClickListener {

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

        public interface OnCardListener{

            void onCardClick(int position);

        }
    }



    public CardCustomAdapter(ArrayList<ServiceProviderModel> data, MyViewHolder.OnCardListener onCardListener) {
        this.dataSet = data;
        this.mOnCardListener = onCardListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view,mOnCardListener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int listPosition) {

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
