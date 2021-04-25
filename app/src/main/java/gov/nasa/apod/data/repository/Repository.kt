package gov.nasa.apod.data.repository

import android.util.Log
import gov.nasa.apod.data.local.AppDao
import gov.nasa.apod.data.model.ResPotd
import gov.nasa.apod.data.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val appDao: AppDao
) {
    suspend fun getPotd(isConn: Boolean): ResPotd {
        if (isConn) {
            refereshData();
        }
        return appDao.getLastPotd()
    }

    private suspend fun refereshData() {
        try {
            val res = apiService.getPotd()
            if (res.date != null) {
                appDao.delete()
                val rowId = appDao.save(res)
                Log.d("Result", "rowId $rowId")
            }
        } catch (e: Exception) {
            Log.d("Result", e.toString())
        }
    }
}