package com.ashish.asiaexplorer.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM countries_table")
    List<Country> getAllCountries();

    @Insert
    void insertCountry(Country country);

    @Query("DELETE FROM countries_table")
    void deleteAllCountries();
}
