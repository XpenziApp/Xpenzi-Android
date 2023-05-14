package com.infinity.coins.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Firebase.auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, LandingActivity::class.java))
        }

        finish()
    }

}