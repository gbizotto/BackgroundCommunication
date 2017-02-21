package br.gbizotto.backgroundcommunication.weather;

import android.util.Log;

import java.io.Serializable;

import br.gbizotto.backgroundcommunication.MainActivity;
import br.gbizotto.backgroundcommunication.api.DarkSkyApi;
import br.gbizotto.backgroundcommunication.background.BackgroundCommunication;
import br.gbizotto.backgroundcommunication.background.CommunicationService;
import br.gbizotto.backgroundcommunication.model.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gabriela on 21/02/2017.
 */

public class WeatherCommunication implements BackgroundCommunication, Serializable {

    private static final String BASE_URL = "https://api.darksky.net/";
    private static final String API_KEY = "e890138405ec89f522a93c2749773367";
    private static final String LATITUDE_LONGITUDE = "37.8267,-122.4233";

    @Override
    public void doCommunication() {
        DarkSkyApi darkSkyApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DarkSkyApi.class);

        Call<Forecast> call = darkSkyApi.getForecast(API_KEY, LATITUDE_LONGITUDE);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {

                Log.v(CommunicationService.class.getSimpleName(), "request =  "+ call.request().toString());
                Log.v(CommunicationService.class.getSimpleName(),"retorno da requisição = " + response.code());
                Forecast forecast = response.body();
                if (forecast != null) {
                    Log.v(CommunicationService.class.getSimpleName(), "forecast.toString() = " + forecast.toString());
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v(MainActivity.class.getSimpleName(),"Falhou!");
            }
        });
    }
}
