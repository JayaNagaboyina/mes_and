package org.mesonet.app.contact

import android.Manifest
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import org.mesonet.app.baseclasses.BaseActivity
import org.mesonet.app.databinding.ContactActivityBinding
import android.content.Intent
import android.net.Uri
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.mesonet.app.R


class ContactActivity: BaseActivity()
{


    override fun NavigateToPage(inFragment: androidx.fragment.app.Fragment, inPushToBackStack: Boolean, inAnimationIn: Int, inAnimationOut: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ContactActivityBinding = DataBindingUtil.setContentView(this, R.layout.contact_activity)

        binding.webButton.setOnClickListener {
            it.isEnabled = false
            val url = "http://www.mesonet.org"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
            it.isEnabled = true
        }

        binding.emailButton.setOnClickListener {
            it.isEnabled = false
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "operator@mesonet.org", null))
            startActivity(Intent.createChooser(emailIntent, "Email OK Mesonet"))
            it.isEnabled = true
        }

        binding.twitterButton.setOnClickListener {
            it.isEnabled = false
            OpenAppWithBackup(Intent.ACTION_VIEW, "twitter://user?screen_name=okmesonet", Intent.ACTION_VIEW, "https://twitter.com/okmesonet")
            it.isEnabled = true
        }

        binding.facebookButton.setOnClickListener {
            it.isEnabled = false
            OpenAppWithBackup(Intent.ACTION_VIEW, "fb://page/101813120731", Intent.ACTION_VIEW, "https://www.facebook.com/mesonet")
            it.isEnabled = true
        }

        binding.phoneButton.setOnClickListener {
            it.isEnabled = false
            mPermissions.RequestPermission(this, Manifest.permission.CALL_PHONE).observeOn(AndroidSchedulers.mainThread()).subscribe(object: SingleObserver<Boolean> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: Boolean) {
                    if (t && ContextCompat.checkSelfPermission(this@ContactActivity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        try {
                            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:4053252541")))
                        } catch (e: Exception) {

                        }
                    }
                    it.isEnabled = true
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
        }

        binding.addressButton.setOnClickListener {
            it.isEnabled = false
            try {
                val gmmIntentUri = Uri.parse("q=120+David+L.+Boren+Blvd.+Norman,+OK+73072")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.`package` = "com.google.android.apps.maps"
                startActivity(mapIntent)
            } catch (e: Exception) {
                val gmmIntentUri = Uri.parse("https://www.google.com/maps?q=120+David+L.+Boren+Blvd.+Norman,+OK+73072")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                startActivity(mapIntent)
            }
            it.isEnabled = true
        }
    }


    fun OpenAppWithBackup(inIntent1: String, inUri1: String, inIntent2: String, inUri2: String )
    {
        try {
            OpenApp(inIntent1, inUri1)
        } catch (exception: ActivityNotFoundException) {
            OpenApp(inIntent2, inUri2)
        }

    }



    fun OpenApp(inIntent: String, inUri: String)
    {
        startActivity( Intent(inIntent, Uri.parse(inUri)))
    }
}