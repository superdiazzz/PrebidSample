package mobile.android.prebidsample

import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import org.prebid.mobile.Host
import org.prebid.mobile.PrebidMobile
import org.prebid.mobile.api.exceptions.InitError
import org.prebid.mobile.rendering.listeners.SdkInitializationListener

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf("ABCDEF012345")).build()
        )

        // Initialize the Mobile Ads SDK with an empty completion listener.
        MobileAds.initialize(this) {}

        initPrebidSDK()
    }

    private fun initPrebidSDK() {
        PrebidMobile.setPrebidServerAccountId("Tr4424EtwA")
        val custom = Host.CUSTOM
        custom.hostUrl = "https://hb-rc.jixie.io/openrtb2/auction"
        PrebidMobile.setPrebidServerHost(custom)

        PrebidMobile.initializeSdk(applicationContext, object : SdkInitializationListener {
            override fun onSdkInit() {
                println("SDK initialized successfully!")
            }
            override fun onSdkFailedToInit(error: InitError?) {
                println("SDK initialized error: ${error?.error} ")
            }
        })
    }
}