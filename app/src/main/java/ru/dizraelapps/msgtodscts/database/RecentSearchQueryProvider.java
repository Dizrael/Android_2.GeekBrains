package ru.dizraelapps.msgtodscts.database;

import android.content.SearchRecentSuggestionsProvider;

public class RecentSearchQueryProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "ru.dizraelapps.msgtodscts.database.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public RecentSearchQueryProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
