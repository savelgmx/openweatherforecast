package com.example.openweatherforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.internal.NopCollector.emit
import javax.inject.Inject

@HiltViewModel
class OpenWeatherMapViewModel @Inject constructor(private val repository: OpenWeatherMapRepository) : ViewModel() {

    fun getWeatherForecast(city: String): LiveData<Resource<OpenWeatherMapResponse>> {
        return liveData {
            emit(Resource.Loading())
            when(val status = repository.getWeatherForecast(city)) {
                is Resource.Success -> emit(Resource.Success(status.data))
                is Resource.Error -> {
                    emit(Resource.Error(status.message))
                    emitSource(repository.getWeatherForecastFromDB().map { Resource.Success(it) })
                }
            }
        }
    }

}
