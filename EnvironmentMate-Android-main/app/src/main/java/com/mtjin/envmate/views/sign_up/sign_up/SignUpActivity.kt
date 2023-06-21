package com.mtjin.envmate.views.sign_up.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignUpBinding
import com.mtjin.envmate.views.sign_up.phone_auth.PhoneAuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goPhoneAuth.observe(this@SignUpActivity, Observer {
                startActivity(Intent(this@SignUpActivity, PhoneAuthActivity::class.java))
            })

        }
    }
}