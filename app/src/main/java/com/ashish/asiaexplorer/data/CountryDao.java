package com.ashish.asiaexplorer.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries_table ORDER BY LOWER(name) ASC")
    LiveData<List<Country>> getAllCountries();

    @Insert
    void insertCountry(Country country);

    @Query("DELETE FROM countries_table")
    void deleteAllCountries();
}
