package ru.dizraelapps.msgtodscts.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ssearch_history",indices = {@Index(value = {"search_query"}, unique = true)})
public class SearchText {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "search_query")
    public String searchText;

    public SearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }
}
