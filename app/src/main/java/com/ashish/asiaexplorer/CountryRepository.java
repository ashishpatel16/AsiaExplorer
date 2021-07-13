package com.ashish.asiaexplorer;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.ashish.asiaexplorer.data.Country;
import com.ashish.asiaexplorer.data.CountryDao;
import com.ashish.asiaexplorer.data.CountryDatabase;

import java.util.List;

public class CountryRepository {

    private String TAG = "RoomDatabase";

    private CountryDao countryDao;
    private LiveData<List<Country>> allCountries;

    public CountryRepository(Application application) {
        CountryDatabase db = CountryDatabase.getInstance(application);
        countryDao = db.countryDao();
        allCountries = countryDao.getAllCountries();
    }



    public void insertCountry(Country country) {
        CountryDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.insertCountry(country);
        });
        Log.i(TAG, "deleteAllCountries: Done Addition");
    }

    public void deleteAllCountries() {
        CountryDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.deleteAllCountries();
        });
        Log.i(TAG, "deleteAllCountries: Done Deletion");
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }
}
