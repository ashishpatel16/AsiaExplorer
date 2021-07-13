package com.ashish.asiaexplorer;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ashish.asiaexplorer.data.Country;
import com.ashish.asiaexplorer.data.CountryDao;
import com.ashish.asiaexplorer.data.CountryDatabase;

import java.util.List;

public class CountryRepository {

    private CountryDao countryDao;
    private List<Country> countries;

    public CountryRepository(Application application) {
        CountryDatabase db = CountryDatabase.getInstance(application);
        countryDao = db.countryDao();


    }

    public void insertCountry(Country country) {
        CountryDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.insertCountry(country);
        });
    }

    public void deleteAllCountries() {
        countryDao.deleteAllCountries();
    }

    public List<Country> getCountries() {
        return countries;
    }
}
