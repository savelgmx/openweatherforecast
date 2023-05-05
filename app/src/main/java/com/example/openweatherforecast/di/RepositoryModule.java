package com.example.openweatherforecast.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOpenWeatherMapRepositoryImpl(api: OpenWeatherMapAPI, dao: OpenWeatherMapDao) : OpenWeatherMapRepositoryImpl {
        return OpenWeatherMapRepositoryImpl(api, dao)
    }

}
