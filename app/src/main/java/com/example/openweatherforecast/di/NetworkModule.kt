import com.example.openweatherforecast.di.NetworkObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAPI() : OpenWeatherMapAPI {
        return NetworkObject.getAPIInstance()
    }

    @Provides
    fun provideOpenWeatherMapRepository(api: OpenWeatherMapAPI, database: OpenWeatherMapDatabase): OpenWeatherMapRepository {
        return OpenWeatherMapRepositoryImpl(api, database)
    }
}
