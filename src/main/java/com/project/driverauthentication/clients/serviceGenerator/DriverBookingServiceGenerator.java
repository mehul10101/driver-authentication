package com.project.driverauthentication.clients.serviceGenerator;

import com.project.driverauthentication.clients.DriverBookingService;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class DriverBookingServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Retrofit retrofitDriver = new Retrofit.Builder().baseUrl("http://localhost:7030")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    public static DriverBookingService getService(){
        return retrofitDriver.create(DriverBookingService.class);
    }

}
