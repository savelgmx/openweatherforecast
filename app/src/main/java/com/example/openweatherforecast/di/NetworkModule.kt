import com.example.openweatherforecast.api.OpenWeatherMapAPI
import com.example.openweatherforecast.api.OpenWeatherMapRepository
import com.example.openweatherforecast.api.OpenWeatherMapRepositoryImpl
import com.example.openweatherforecast.db.OpenWeatherMapDatabase
import com.example.openweatherforecast.di.NetworkObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideAPI() : OpenWeatherMapAPI {
        return NetworkObject.getAPIInstance()
    }

    @Provides
    fun provideOpenWeatherMapRepository(api: OpenWeatherMapAPI, database: OpenWeatherMapDatabase): OpenWeatherMapRepository {
        return OpenWeatherMapRepositoryImpl(api, database.openWeatherMapDao())
    }
}
