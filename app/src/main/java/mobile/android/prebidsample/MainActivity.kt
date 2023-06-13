package mobile.android.prebidsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import mobile.android.prebidsample.databinding.ActivityMainBinding
import org.prebid.mobile.BannerAdUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var adview : AdManagerAdView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an ad request.
        adview = AdManagerAdView(this)
        adview?.setAdSizes(AdSize.MEDIUM_RECTANGLE)
        adview?.adUnitId = "/31800665/TribunnewsApp/TopMediumRectangle"
        binding.layerAds.addView(adview)


        val adUnitHeader = BannerAdUnit("1000442-vYC4u9QFs6", 300, 250)

        val adRequest = AdManagerAdRequest.Builder().build()
        adUnitHeader.fetchDemand(adRequest){
            adview?.loadAd(adRequest)
        }

    }

    /** Called when leaving the activity. */
    public override fun onPause() {
        adview?.pause()
        super.onPause()
    }

    /** Called when returning to the activity. */
    public override fun onResume() {
        super.onResume()
        adview?.resume()
    }

    /** Called before the activity is destroyed. */
    public override fun onDestroy() {
        adview?.destroy()
        super.onDestroy()
    }
}