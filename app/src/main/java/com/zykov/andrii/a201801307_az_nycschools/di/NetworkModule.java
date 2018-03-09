package com.zykov.andrii.a201801307_az_nycschools.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zykov.andrii.a201801307_az_nycschools.utils.nycschoolservice.NYCSchoolsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by andrii on 3/8/18.
 */

@Module
public class NetworkModule {

    private final String baseURL;

    public NetworkModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient(){
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    NYCSchoolsAPI getSchoolsApi(Retrofit retrofit){
        return retrofit.create(NYCSchoolsAPI.class);
    }

}
