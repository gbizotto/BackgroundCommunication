package br.gbizotto.backgroundcommunication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import br.gbizotto.backgroundcommunication.api.DarkSkyApi;
import br.gbizotto.backgroundcommunication.model.Forecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class WeatherService extends IntentService {

    public static final String BASE_URL = "https://api.darksky.net/";
    private static final String API_KEY = "<API_KEY>";
    private static final String LATITUDE_LONGITUDE = "37.8267,-122.4233";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        DarkSkyApi darkSkyApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DarkSkyApi.class);

        Call<Forecast> call = darkSkyApi.getForecast(API_KEY, LATITUDE_LONGITUDE);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {

                Log.v(WeatherService.class.getSimpleName(), "request =  "+ call.request().toString());
                Log.v(WeatherService.class.getSimpleName(),"retorno da requisição = " + response.code());
                Forecast forecast = response.body();
                Log.v(WeatherService.class.getSimpleName(),"forecast.toString() = " + forecast.toString());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v(MainActivity.class.getSimpleName(),"Falhou!");
            }
        });

    }
}
