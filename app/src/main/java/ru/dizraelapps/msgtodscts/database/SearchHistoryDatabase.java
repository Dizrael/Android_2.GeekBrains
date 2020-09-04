package ru.dizraelapps.msgtodscts.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.dizraelapps.msgtodscts.weather.ResponseWeather;

@Database(entities = {SearchText.class}, version = 2)
public abstract class SearchHistoryDatabase extends RoomDatabase {
    public abstract SearchHistoryDao getHistoryDao();
}
