package com.example.instasitter.adapters;

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instasitter.R;
import com.example.instasitter.activities.MainActivity;
import com.example.instasitter.activities.ServiceProviderProfile;
import com.example.instasitter.classes.ServiceProviderModel;

import java.util.ArrayList;

public class ProfileReviewsCustomAdapter extends RecyclerView.Adapter<ProfileReviewsCustomAdapter.MyViewHolder> {


        private ArrayList<ServiceProviderModel> dataSet;


        public static class MyViewHolder extends RecyclerView.ViewHolder {

            CardView cardView;
            TextView textViewName;
            TextView textViewRating;
            TextView textViewRaterTitle;
            TextView textViewRaterText;

            View itemView;
            ClipData.Item currentItem;

            public MyViewHolder(View itemView) {
                super(itemView);

                this.cardView = (CardView) itemView.findViewById(R.id.review_card_view);
                this.textViewName = (TextView) itemView.findViewById(R.id.raterName);
                this.textViewRating = (TextView) itemView.findViewById(R.id.raterRating);
                this.textViewRaterTitle = (TextView) itemView.findViewById(R.id.raterTitle);
                this.textViewRaterText = (TextView) itemView.findViewById(R.id.raterReviewText);

                this.itemView = itemView;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        // item clicked
                    }
                });

            }
        }



        public ProfileReviewsCustomAdapter(ArrayList<ServiceProviderModel> data) {
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
            TextView textViewRating = holder.textViewRating;
            TextView textViewRaterTitle = holder.textViewRaterTitle;
            TextView textViewRaterText = holder.textViewRaterText;
            CardView cardView = holder.cardView;

            textViewName.setText(dataSet.get(listPosition).getName());
            textViewRating.setText(dataSet.get(listPosition).getLocation());
            textViewRaterTitle.setText(dataSet.get(listPosition).getServiceType());
            textViewRaterText.setText(dataSet.get(listPosition).getServiceType());

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
