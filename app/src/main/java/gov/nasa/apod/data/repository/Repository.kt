package gov.nasa.apod.data.repository

import gov.nasa.apod.data.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPotd() = apiService.getPotd()
}