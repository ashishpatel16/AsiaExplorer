package com.ashish.asiaexplorer.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
    private List<Country> allCountries;
    private  RecyclerView recyclerView;
    private NavController mNavController;
    CountryRepository countryRepository;
    LiveData<List<Country>> mLiveData;
    private CountryRecyclerViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);

        allCountries = new ArrayList<>();

        countryRepository = new CountryRepository(getActivity().getApplication());


        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        mNavController = navHostFragment.getNavController();


        initRecyclerView();
        fetchCountries();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void observeCountries() {
        mLiveData = countryRepository.getAllCountries();
        mLiveData.observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                allCountries.addAll(countries);
                Log.i(TAG, "onChanged: Size "+countries.size() +" | "+allCountries.size());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = binding.recViewCountries;
        mAdapter = new CountryRecyclerViewAdapter(requireContext(), allCountries,mNavController);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(mAdapter);
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
                    countryRepository.deleteAllCountries();
                    allCountries.clear();
                    allCountries.addAll(response.body());
                    Log.i(TAG, "onResponse: " + allCountries.size());
                    for(Country c:allCountries) {
                        countryRepository.insertCountry(c);
                    }

                    mAdapter.notifyDataSetChanged();

                }else Log.i(TAG, "onResponse: "+response.message());
                binding.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                binding.progressBar.setVisibility(View.INVISIBLE);
                observeCountries();
            }
        });

    }
}