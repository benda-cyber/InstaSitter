package com.example.instasitter.adapters;

import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.instasitter.R;
import com.example.instasitter.activities.MainActivity;
import com.example.instasitter.activities.ServiceProviderProfile;
import com.example.instasitter.classes.ServiceProvider;

import java.util.ArrayList;

public class CardCustomAdapter extends RecyclerView.Adapter<CardCustomAdapter.MyViewHolder> {

    private static final String TAG = "CardCustomAdapter";
    private Context mContext;
    private ArrayList<ServiceProvider> dataSet;
//    private OnCardListener mOnCardListener;

    public CardCustomAdapter(Context mContext, ArrayList<ServiceProvider> data) {
        this.dataSet = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewLocation = holder.textViewLocation;
        TextView textViewServiceType = holder.textViewServiceType;
        ImageView imageView = holder.imageViewIcon;
        CardView cardView = holder.cardView;
        LinearLayout linearLayout = holder.linearLayout;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewLocation.setText(dataSet.get(listPosition).getAddress());
        textViewServiceType.setText(dataSet.get(listPosition).getServices());


        Glide.with(mContext).load(dataSet.get(listPosition).getProfilePic()).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ServiceProviderProfile.class);
                intent.putExtra("service_provider_profile", dataSet.get(listPosition));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    public class MyViewHolder extends ViewHolder {

        CardView cardView;
        TextView textViewName;
        TextView textViewLocation;
        TextView textViewServiceType;
        ImageView imageViewIcon;
        View itemView;
        Item currentItem;
//        OnCardListener onCardListener;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.textViewName = (TextView) itemView.findViewById(R.id.serviceProviderName);
            this.textViewLocation = (TextView) itemView.findViewById(R.id.serviceProviderLocation);
            this.textViewServiceType = (TextView) itemView.findViewById(R.id.serviceProviderServiceType);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.serviceProviderPhoto);
            this.itemView = itemView;
//            this.onCardListener = onCardListener;
            this.linearLayout = itemView.findViewById(R.id.linear_layout);

//            itemView.setOnClickListener(this);

        }


    }


//    public interface OnCardListener{
//
//        void onCardClick(MyViewHolder profile);
//
//    }



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
