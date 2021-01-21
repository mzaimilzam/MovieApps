package id.co.zaimilzam.movieapps.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.co.zaimilzam.movieapps.HomeActivity
import id.co.zaimilzam.movieapps.R
import id.co.zaimilzam.movieapps.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    companion object {
        const val SPLASH_TIME = 2500
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val topAnim =
            android.view.animation.AnimationUtils.loadAnimation(
                this@SplashScreenActivity,
                R.anim.top_animation
            )

        binding.tvTitleSplash.animation = topAnim


        val handler: Thread = object : Thread() {
            override fun run() {
                super.run()
                try {
                    sleep(SPLASH_TIME.toLong())
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
                    val mIntent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
                    startActivity(mIntent)
                }
            }
        }
        handler.start()
    }
}