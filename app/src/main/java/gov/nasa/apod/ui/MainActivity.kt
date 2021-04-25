package gov.nasa.apod.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gov.nasa.apod.R
import gov.nasa.apod.data.model.ResPotd
import gov.nasa.apod.databinding.ActivityMainBinding
import gov.nasa.apod.utils.getDateFromMilliSeconds
import gov.nasa.apod.utils.getMillisWithTimeZone
import gov.nasa.apod.utils.isConnected
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var res: ResPotd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpObserver()
        checkInternetAndCallData()
    }

    private fun setUpObserver() {
        viewModel.potd.observe(this@MainActivity, {
//            Log.d("Result","res : ${it.toString()}")
            res = it
            mainBinding.potd = res
        })
    }

    private fun checkInternetAndCallData() {
        var day = Date().getDateFromMilliSeconds(Date().getMillisWithTimeZone())
        var isConn: Boolean = true
        if (!this.isConnected) {
            isConn = false
            Snackbar.make(
                mainBinding.guideline1,
                "We are not connected to the internet, showing you the last image we have.",
                Snackbar.LENGTH_LONG
            ).show()
        }
        Log.d("Result", day)
        viewModel.setDay(day, isConn)
    }
}