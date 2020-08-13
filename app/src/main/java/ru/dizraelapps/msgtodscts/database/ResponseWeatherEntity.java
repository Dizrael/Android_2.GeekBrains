package ru.dizraelapps.msgtodscts.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"data", "day_temp", "night_temp"})})
public class ResponseWeatherEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "data")
    public String data;

    @ColumnInfo(name = "day_temp")
    public double dayTemp;

    @ColumnInfo(name = "night_temp")
    public double nightTemp;
}
