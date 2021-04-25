package gov.nasa.apod.data.remote

import gov.nasa.apod.data.model.ResPotd
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.nasa.gov/";
        const val API_KEY = "Rdv9Ww2RAsUswXenvNGeuEgfVfnSIhSp2S9NWecV";
    }

    @GET("planetary/apod")
    suspend fun getPotd(
        @Query("api_key") apiKey: String = API_KEY
    ): ResPotd
}