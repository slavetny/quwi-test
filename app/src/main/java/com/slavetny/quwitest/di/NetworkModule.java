package com.slavetny.quwitest.di;

import com.slavetny.quwitest.data.network.QuwiService;
import com.slavetny.quwitest.domain.constants.Constants;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ActivityComponent.class)
public class NetworkModule {

    @Provides
    @ActivityScoped
    QuwiService provideService(Retrofit retrofit) {
        return retrofit.create(QuwiService.class);
    }

    @Provides
    @ActivityScoped
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}