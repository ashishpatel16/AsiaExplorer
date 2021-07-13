package com.ashish.asiaexplorer.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashish.asiaexplorer.R;
import com.ashish.asiaexplorer.data.Country;
import com.ashish.asiaexplorer.data.Language;
import com.ashish.asiaexplorer.databinding.FragmentCountryDetailBinding;
import com.bumptech.glide.Glide;

public class CountryDetailFragment extends Fragment {

    FragmentCountryDetailBinding binding;
    private String TAG = "DetailFragment";
    private Country mCountry;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail, container, false);

        Bundle b = getArguments();
        mCountry = (Country) b.getSerializable("CountryObject");
        if(b.getSerializable("CountryObject") != null) Log.i(TAG, "onCreateView: "+mCountry.getName());
        else Log.i(TAG, "onCreateView: Not found");

        init();

        return binding.getRoot();
    }

    private void init() {
        binding.tvCountryname.setText(mCountry.getName());
        binding.tvCountryCapital.setText("Capital : " + mCountry.getCapital());
        binding.tvCountryPopulation.setText("Population : "+mCountry.getPopulation());
        binding.tvCountryRegion.setText("Region : " + mCountry.getRegion());
        binding.tvCountrySubregion.setText("Subregion : "+mCountry.getSubregion());
        binding.tvCountryLanguages.setText("Languages : "+getLanguages());
        binding.tvCountryBorders.setText("Borders : "+getBorders());

        Glide.with(getContext())
                .load(mCountry.getFlag())
                .centerInside()
                .into(binding.ivCountryFlag);

    }

    private String getLanguages() {
        String lang = "";
        int size = mCountry.getLanguages().size();
        for(Language language: mCountry.getLanguages()) {
            size--;
            lang += language.getName();
            if(size != 0) lang += " | ";
        }
        return lang;
    }

    private String getBorders() {
        String text = "";
        int size = mCountry.getBorders().size();
        for(String border: mCountry.getBorders()) {
            size--;
            text += border;
            if(size != 0) text += " | ";

        }
        return text;
    }

}