package com.android.ankit.foodle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<Food> foodList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.foodRecyclerView);
        FoodAdapter foodAdapter = new FoodAdapter(getContext(), foodList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(foodAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        foodList = new ArrayList<>();
        foodList.add(new Food("Pizza", "100", R.drawable.cheesepizza));
        foodList.add(new Food("Pizza", "100", R.drawable.peppizza));
        foodList.add(new Food("Pizza", "100", R.drawable.pinepizza));
        foodList.add(new Food("IceCream", "100", R.drawable.chocolateicecream));
        foodList.add(new Food("IceCream", "100", R.drawable.vanillaicecream));
        foodList.add(new Food("IceCream", "100", R.drawable.strawberryicecream));
    }
}
