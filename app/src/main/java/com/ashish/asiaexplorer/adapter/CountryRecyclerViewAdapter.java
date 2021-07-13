package com.ashish.asiaexplorer.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.asiaexplorer.R;
import com.ashish.asiaexplorer.data.Country;
import com.bumptech.glide.Glide;

import java.util.List;



public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {
    private final Context mContext;
    private final List<Country> mList;
    private final NavController navController;
    public CountryRecyclerViewAdapter(Context mContext, List<Country> mList, NavController navController) {
        this.mContext = mContext;
        this.mList = mList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public CountryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCountry.setText(mList.get(position).getName());
        holder.mPopulation.setText(""+mList.get(position).getPopulation());

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("CountryObject",mList.get(position));
                navController.navigate(R.id.action_homeFragment_to_countryDetailFragment,bundle);
            }
        });

        Glide.with(mContext)
                .load(mList.get(position).getFlag())
                .centerInside()
                .into(holder.mFlagImageView);

    }

    @Override
    public int getItemCount() {
        Log.i("HomeFragment", "getItemCount: "+mList.size());
        return mList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mFlagImageView;
        TextView mCountry,mPopulation;
        AppCompatImageButton btnMore;
        ConstraintLayout mLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFlagImageView = itemView.findViewById(R.id.iv_flag);
            mCountry = itemView.findViewById(R.id.tv_country);
            mPopulation = itemView.findViewById(R.id.tv_population);
            btnMore = itemView.findViewById(R.id.btn_view_more);
            mLayout = itemView.findViewById(R.id.row_layout);
        }
    }
}
