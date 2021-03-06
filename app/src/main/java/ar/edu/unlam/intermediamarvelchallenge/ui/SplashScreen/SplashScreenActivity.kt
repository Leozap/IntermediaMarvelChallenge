package ar.edu.unlam.intermediamarvelchallenge.ui.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sp_logo.alpha=0f
        sp_logo.animate().setDuration(1500).alpha(1f).withEndAction {
            val i= Intent(this, LoginActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()

        }
    }
}