package kr.co.bootpay.pgtestkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun DefaultPayment(v: View?) {
        val intent = Intent(applicationContext, DefaultPaymentActivity::class.java)
        startActivity(intent)
    }

    fun TotalPayment(v: View?) {
        val intent = Intent(applicationContext, TotalPaymentActivity::class.java)
        startActivity(intent)
    }

    fun SubscriptionPayment(v: View?) {
        val intent = Intent(applicationContext, SubscriptionActivity::class.java)
        startActivity(intent)
    }

    fun SubscriptionBootpayPayment(v: View?) {
        val intent = Intent(applicationContext, SubscriptionBootpayActivity::class.java)
        startActivity(intent)
    }

    fun Authentication(v: View?) {
        val intent = Intent(applicationContext, AuthenticationActivity::class.java)
        startActivity(intent)
    }

    fun PasswordPayment(v: View?) {
        val intent = Intent(applicationContext, PasswordPaymentActivity::class.java)
        startActivity(intent)
    }

    fun WebAppPayment(v: View?) {
        val intent = Intent(applicationContext, WebAppActivity::class.java)
        startActivity(intent)
    }

    fun BioPayment(v: View?) {
        val intent = Intent(applicationContext, BioPaymentActivity::class.java)
        startActivity(intent)
    }

    fun PasswordUIPayment(v: View?) {
        val intent = Intent(applicationContext, PasswordUIPaymentActivity::class.java)
        startActivity(intent)
    }
}