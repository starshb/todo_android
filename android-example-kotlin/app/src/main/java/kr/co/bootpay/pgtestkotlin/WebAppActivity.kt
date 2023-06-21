package kr.co.bootpay.pgtestkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.bootpay.android.webview.BootpayWebView

class WebAppActivity: AppCompatActivity() {
    //    BootpayWebView bootpayWebView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webapp)
        val webView = findViewById<BootpayWebView>(R.id.webview)

        //link your domain
        webView.loadUrl("https://d-cdn.bootapi.com/test/payment/")
    }
}