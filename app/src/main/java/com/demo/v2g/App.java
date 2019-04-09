package com.demo.v2g;

import android.app.Application;

import com.demo.v2g.network.V2gApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static V2gApi V2gApi;

    @Override
    public void onCreate() {
        super.onCreate();
        V2gApi = initApi().create(V2gApi.class);
    }

    public static V2gApi getV2gApi() {
        return V2gApi;
    }

    // TODO add =>  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    private static Retrofit initApi() {
        return new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build();
    }



    private static OkHttpClient initClient() {
        OkHttpClient.Builder v2gHttpBuilder = initBuilder();
        OkHttpClient client = v2gHttpBuilder.build();
        return client;
    }

    private static OkHttpClient.Builder initBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return builder;
    }

}
