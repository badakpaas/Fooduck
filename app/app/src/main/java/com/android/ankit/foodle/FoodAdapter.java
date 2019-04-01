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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context mCtx;
    private List<Food> foodList;

    public FoodAdapter(Context mCtx, List<Food> foodList) {
        this.mCtx = mCtx;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.default_card_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.foodTitle.setText(food.getTitle());
        holder.foodCalory.setText(food.getCalory());
        holder.imageView.setImageResource(food.getImage());
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(food.getImage()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView foodTitle, foodCalory;

        public FoodViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodCalory = itemView.findViewById(R.id.foodCalory);
        }
    }

}
