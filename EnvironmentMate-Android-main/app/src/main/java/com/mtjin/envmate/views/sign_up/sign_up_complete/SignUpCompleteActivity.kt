package com.mtjin.envmate.views.sign_up.sign_up_complete

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignUpCompleteBinding
import com.mtjin.envmate.views.sign_in.SignInActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpCompleteActivity :
    BaseActivity<ActivitySignUpCompleteBinding>(R.layout.activity_sign_up_complete) {
    private val viewModel: SignUpCompleteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goLogin.observe(this@SignUpCompleteActivity, Observer {
                val intent = Intent(this@SignUpCompleteActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }
}