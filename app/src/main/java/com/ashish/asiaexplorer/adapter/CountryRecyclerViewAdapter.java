package com.ashish.asiaexplorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.asiaexplorer.R;
import com.ashish.asiaexplorer.data.Country;
import com.bumptech.glide.Glide;

import java.util.List;



public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Country> mList;
    private NavController navController;
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
        holder.mCoutry.setText(mList.get(position).getName());
        holder.mPopulation.setText(""+mList.get(position).getPopulation());

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.action_homeFragment_to_countryDetailFragment);
            }
        });

        Glide.with(mContext)
                .load(mList.get(position).getFlag())
                .centerInside()
                .into(holder.mFlagImageView);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mFlagImageView;
        TextView mCoutry,mPopulation;
        AppCompatImageButton btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFlagImageView = itemView.findViewById(R.id.iv_flag);
            mCoutry = itemView.findViewById(R.id.tv_country);
            mPopulation = itemView.findViewById(R.id.tv_population);
            btnMore = itemView.findViewById(R.id.btn_view_more);

        }
    }
}
