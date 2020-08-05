package ru.dizraelapps.msgtodscts.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.dizraelapps.msgtodscts.weather.ResponseWeather;

public interface IOpenWeather {
    @GET("data/2.5/onecall")
    Call<ResponseWeather> loadWeather(@Query("lat") String latitude, @Query("lon") String longitude,
                                      @Query("exclude") String exclude, @Query("appid") String appid,
                                        @Query("units") String units);
}
