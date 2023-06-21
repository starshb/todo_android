package com.example.umarry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.umarry.auth.LoginActivity
import com.example.umarry.utils.FirebaseAuthUtils

class SplashActivity:AppCompatActivity() {

//    private val auth = FirebaseAuth.getInstance() // 로그인정보 가져오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val uid = auth.currentUser?.uid.toString()
        val uid = FirebaseAuthUtils.getUid()

        if( uid == "null") {
            Log.d("xxxxxxxxxuid", uid)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Log.d("xxxxxxxxxuid", uid)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

}