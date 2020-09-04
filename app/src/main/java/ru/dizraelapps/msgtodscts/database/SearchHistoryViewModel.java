package ru.dizraelapps.msgtodscts.database;


import java.util.List;

// Вспомогательный класс, развязывающий зависимость между Room и RecyclerView
public class SearchHistoryViewModel {

    private final SearchHistoryDao historyDao;

    private List<SearchText> searches;

    public SearchHistoryViewModel(SearchHistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    //Получить всю погоду
    public List<SearchText> getSearches(){
        // Если объекты еще не загружены, загружаем их.
        // Это сделано для того, чтобы не делать запросы к БД каждый раз
        if (searches == null){
            loadSearches();
        }
        return searches;
    }

    //Загружаем погоду в буфер
    public void loadSearches(){
        searches = historyDao.getAllHistory();
    }

    public long getCountSearches(){
        return historyDao.getCountHistory();
    }

    public void addSearch(SearchText searchText){
        historyDao.insertHistory(searchText);
    }

    public void updateSearch(SearchText searchText){
        historyDao.updateHistory(searchText);
    }

    public void removeSearch(long id){
        historyDao.deleteHistoryById(id);
        loadSearches();
    }

}
