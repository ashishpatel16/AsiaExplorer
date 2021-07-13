package com.ashish.asiaexplorer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashish.asiaexplorer.CountryRepository;
import com.ashish.asiaexplorer.R;
import com.ashish.asiaexplorer.service.RestCountriesApi;
import com.ashish.asiaexplorer.adapter.CountryRecyclerViewAdapter;
import com.ashish.asiaexplorer.databinding.FragmentHomeBinding;
import com.ashish.asiaexplorer.data.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private String TAG = "HomeFragment";
    FragmentHomeBinding binding;
    private List<Country> countries;
    private  RecyclerView recyclerView;
    private NavController mNavController;
    CountryRepository countryRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        countries = new ArrayList();
        initRecyclerView();

        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        mNavController = navHostFragment.getNavController();
        countryRepository = new CountryRepository(getActivity().getApplication());

        if(countryRepository.getCountries() != null) {
            countries = countryRepository.getCountries();
            recyclerView.getAdapter().notifyDataSetChanged();
        }

        fetchCountries();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initRecyclerView() {
        recyclerView = binding.recViewCountries;
        CountryRecyclerViewAdapter adapter = new CountryRecyclerViewAdapter(requireContext(),countries,mNavController);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false));
    }

    private void fetchCountries() {
        binding.progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestCountriesApi apiService = retrofit.create(RestCountriesApi.class);
        Call<List<Country>> call = apiService.getAsianCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, retrofit2.Response<List<Country>> response) {
                if(response.isSuccessful()) {
                    Log.i(TAG, "onResponse: "+response.toString());
                    List<Country> res = response.body();

                    countries = res;

                    for(Country country:countries) {
                        Log.i(TAG, "onResponse: "+country.getFlag());
                        countryRepository.insertCountry(country);
                    }

                    recyclerView.getAdapter().notifyDataSetChanged();

                }
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });

    }
}