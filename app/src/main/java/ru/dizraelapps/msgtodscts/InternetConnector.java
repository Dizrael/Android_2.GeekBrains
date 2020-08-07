package ru.dizraelapps.msgtodscts;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class InternetConnector {

    private HttpsURLConnection urlConnection = null;
    private URL url;

    public InternetConnector(String city) throws MalformedURLException {
        String KEY = "d4a256e168a940fb210b109445d77de4";
        String url = "https://api.openweathermap.org/data/2.5/forecast/daily?q=" + city +
                "&appid=" + KEY + "&units=metric" + "&cnt=7";
        this.url = new URL(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String initConnection(){
        String outputResult = null;
        try {
            urlConnection = (HttpsURLConnection) this.url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10_000);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            outputResult = in.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return outputResult;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getWeatherData(){
        return initConnection();
    }
}
