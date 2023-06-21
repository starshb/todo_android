package com.mtjin.envmate.views.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivitySignInBinding
import com.mtjin.envmate.utils.UserInfo
import com.mtjin.envmate.views.main.MainActivity
import com.mtjin.envmate.views.sign_up.sign_up.SignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            loginResult.observe(this@SignInActivity, Observer {
                if (it) {
                    UserInfo.headerKey = viewModel.key
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                else showToast("로그인 실패")
            })

            goSignUp.observe(this@SignInActivity, Observer {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            })
        }
    }
}