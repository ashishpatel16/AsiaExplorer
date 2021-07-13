package com.ashish.asiaexplorer.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries_table")
public class Country implements Serializable {

	public void set_key(int _key) {
		this._key = _key;
	}

	@PrimaryKey(autoGenerate = true)
	private int _key;

	public int get_key() {
		return _key;
	}

	@NonNull
	@SerializedName("name")
	private String name;

	@SerializedName("capital")
	private String capital;

	@TypeConverters(LanguageConverter.class)
	@SerializedName("languages")
	private List<Language> languages;

	@TypeConverters(BorderConverter.class)
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

	public void setName(String name) {
		this.name = name;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public void setBorders(List<String> borders) {
		this.borders = borders;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}