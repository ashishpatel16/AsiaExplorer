package com.ashish.asiaexplorer.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries_table")
public class Country implements Serializable {

	@PrimaryKey
	@SerializedName("name")
	private String name;

	@SerializedName("capital")
	private String capital;

	@TypeConverters(LanguageConverter.class)
	@SerializedName("languages")
	private List<Language> languages;

	@SerializedName("borders")
	private List<String> borders;

	@SerializedName("subregion")
	private String subregion;

	@SerializedName("region")
	private String region;

	@SerializedName("population")
	private int population;

	@SerializedName("flag")
	private String flag;

	public String getCapital(){
		return capital;
	}

	public List<Language> getLanguages(){
		return languages;
	}

	public List<String> getBorders(){
		return borders;
	}

	public String getSubregion(){
		return subregion;
	}

	public String getName(){
		return name;
	}

	public String getRegion(){
		return region;
	}

	public int getPopulation(){
		return population;
	}

	public String getFlag(){
		return flag;
	}
}