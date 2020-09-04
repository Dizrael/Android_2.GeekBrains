package ru.dizraelapps.msgtodscts.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.dizraelapps.msgtodscts.weather.ResponseWeather;

@Dao
public interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistory(SearchText searchText);

    @Update
    void updateHistory(SearchText searchText);

    @Delete
    void deleteHistory(SearchText searchText);

    @Query("DELETE FROM ssearch_history WHERE id = :id")
    void deleteHistoryById(long id);

    @Query("SELECT * FROM ssearch_history")
    List<SearchText> getAllHistory();

    @Query("SELECT * FROM ssearch_history WHERE id = :id")
    SearchText getHistoryById(long id);

    @Query("SELECT COUNT(id) FROM ssearch_history")
    long getCountHistory();

}
