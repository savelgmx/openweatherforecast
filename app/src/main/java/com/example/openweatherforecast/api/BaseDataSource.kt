package com.alialfayed.weathertask.domain.api



import com.example.openweatherforecast.core.utils.NoInternetException
import com.example.openweatherforecast.core.utils.Resource
import com.google.android.gms.common.api.ApiException
import retrofit2.Response


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return Resource.Error(
                msg = response.code().toString() + " " + response.message().toString()
            )
        } catch (e: ApiException) {
            return Resource.Error(msg = e.message.toString())
        } catch (e: NoInternetException) {
            return Resource.Internet()
        } catch (e: Exception) {
            return Resource.Error(msg = e.message.toString())
        }
    }
}

