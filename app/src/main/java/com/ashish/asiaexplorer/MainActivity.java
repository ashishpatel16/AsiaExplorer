package com.ashish.asiaexplorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.ashish.asiaexplorer.databinding.ActivityMainBinding;

/***
 * Your assignment is to create an android app(Use Java) to display information about only
 * countries in asia (look for ‘Region’ section in rest api docs) by consuming a rest api and
 * storing the data (using Room Persistence Library) to display when the user is offline. Show
 * Button to Delete entire stored data from Room database.
 *
 * ● Display following attributes - name, capital, flag(display image in app), region,
 * subregion, population, borders & languages.
 *
 * ● Rest API docs - https://restcountries.eu/
 */

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fetch from https://restcountries.eu/rest/v2/region/asia
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
        mNavController = navHostFragment.getNavController();




        // mNavController.navigate(R.id.action_homeFragment_to_countryDetailFragment);
    }
}

/***
[
        {
        "name": "Afghanistan",
        "capital": "Kabul",
        "region": "Asia",
        "subregion": "Southern Asia",
        "population": 27657145,
        "borders": [
        "IRN",
        "PAK",
        "TKM",
        "UZB",
        "TJK",
        "CHN"
        ],
        "languages": [
        {
        "iso639_1": "ps",
        "iso639_2": "pus",
        "name": "Pashto",
        "nativeName": "پښتو"
        },
        {
        "iso639_1": "uz",
        "iso639_2": "uzb",
        "name": "Uzbek",
        "nativeName": "Oʻzbek"
        },
        {
        "iso639_1": "tk",
        "iso639_2": "tuk",
        "name": "Turkmen",
        "nativeName": "Türkmen"
        }
        ]
        }
        ]

 */