package com.sriyank.bankapp.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.sriyank.bankapp.R
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        bankImage.animation = AnimationUtils.loadAnimation(this, R.anim.animation)

        Handler().postDelayed({
            var i = Intent(this,Home::class.java).apply{
                startActivity(this)
                finish()
            }
        },3000)

    }
}