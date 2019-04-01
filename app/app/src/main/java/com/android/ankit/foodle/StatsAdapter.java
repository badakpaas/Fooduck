package com.android.ankit.foodle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> {

    private Context mCtx;
    private List<Stats> statsList;

    public StatsAdapter(Context mCtx, List<Stats> statsList) {
        this.mCtx = mCtx;
        this.statsList = statsList;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.default_card_layout, parent, false);
        return new StatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        Stats stat = statsList.get(position);
        holder.foodTitle.setText(stat.getBmi());
        holder.foodCalory.setText(stat.getFoodName());
//        holder.imageView.setImageResource(stat.get());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(food.getImage()));
    }

    @Override
    public int getItemCount() {
        return statsList.size();
    }

    class StatsViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView foodTitle, foodCalory;

        public StatsViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodCalory = itemView.findViewById(R.id.foodCalory);
        }
    }

}
