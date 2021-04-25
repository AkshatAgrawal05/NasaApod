package gov.nasa.apod.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
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
        setUpViews()
    }

    private fun setUpViews() {
        mainBinding.imageView.setOnClickListener() {
            if (res != null && !res.hdUrl.isNullOrEmpty()) {
                showFullImage(res.hdUrl!!)
            }
        }
    }

    private fun showFullImage(hdUrl: String) {
        val nagDialog = Dialog(
            this,
            R.style.Theme_AppCompat_DialogWhenLarge
        )
        //R.style.Theme_Translucent_NoTitleBar_Fullscreen
        nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        nagDialog.setCancelable(false)
        nagDialog.setContentView(R.layout.image_layout)
        val btnClose: Button = nagDialog.findViewById(R.id.btnIvClose) as Button
        val imageView: ImageView = nagDialog.findViewById(R.id.ivFullImage) as ImageView

        Glide.with(imageView.context)
            .load(hdUrl)
            .placeholder(R.drawable.ic_baseline_cloud_download_24)
            .into(imageView)

        btnClose.setOnClickListener() {
            nagDialog.cancel()
        }

        nagDialog.show()
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