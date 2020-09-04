package ru.dizraelapps.msgtodscts.database;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    private static App instance;

    //Database
    private SearchHistoryDatabase db;

    //Получаем объект приложения
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Сохранем объект приложения (для Синглтона)
        instance = this;

        //Строим базу
        db = Room.databaseBuilder(
                getApplicationContext(),
                SearchHistoryDatabase.class,
                "ssearch_history")
                .allowMainThreadQueries()
                .build();
    }

    //Получаем WeatherDao для составления запросов
    public SearchHistoryDao getHistoryDao(){
        return db.getHistoryDao();
    }
}
