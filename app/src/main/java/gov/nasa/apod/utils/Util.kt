package gov.nasa.apod.utils

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*

fun Date.getDateFromMilliSeconds(milliSeconds: Long): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US);
    return simpleDateFormat.format(Date(milliSeconds))
}

val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }

fun Date.getDateOfPreviousDay(): String {
    val millisOf24Hour = 24 * 60 * 60 * 1000
    val previousDay = getMillisWithTimeZone() - millisOf24Hour
    return getDateFromMilliSeconds(previousDay)
}

fun Date.getMillisWithTimeZone(): Long {
    /*val tz = getCustomTimeZone("GMT +00:00")
    TimeZone.setDefault(tz)*/
    TimeZone.setDefault(TimeZone.getTimeZone("Ireland/Dublin"));
    val c = Calendar.getInstance()
    return c.timeInMillis
}