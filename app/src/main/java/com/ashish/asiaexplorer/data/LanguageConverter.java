package com.ashish.asiaexplorer.data;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

@ProvidedTypeConverter
public class LanguageConverter implements Serializable {
    @TypeConverter
    public static List<Language> fromString(String value) {
        Type listType = new TypeToken<List<Language>>() {}.getType();
        return new Gson().fromJson(value,listType);
    }

    @TypeConverter
    public static String listToString(List<Language> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
