package ru.dizraelapps.msgtodscts.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.dizraelapps.msgtodscts.geocoding.ResponseGeocoding;

public interface IGeocoder {
    @GET("/maps/api/geocode/json")
    Call<ResponseGeocoding> getCoordinates(@Query("address") String address, @Query("key") String key);

}
