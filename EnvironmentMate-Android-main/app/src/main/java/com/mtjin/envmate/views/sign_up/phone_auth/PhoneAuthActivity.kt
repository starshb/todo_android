package com.mtjin.envmate.views.sign_up.phone_auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseActivity
import com.mtjin.envmate.databinding.ActivityPhoneAuthBinding
import com.mtjin.envmate.utils.TAG
import com.mtjin.envmate.utils.UserInfo
import com.mtjin.envmate.views.sign_up.user_info.UserInfoActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PhoneAuthActivity : BaseActivity<ActivityPhoneAuthBinding>(R.layout.activity_phone_auth) {

    private lateinit var auth: FirebaseAuth
    private var storedVerificationId = ""
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private val viewModel: PhoneAuthViewModel by viewModels()

    private val callbacks by lazy {
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // 번호인증 혹은 기타 다른 인증(구글로그인, 이메일로그인 등) 끝난 상태
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                showToast("인증코드가 전송되었습니다. 90초 이내에 입력해주세요 :)")
                UserInfo.phoneAuthNum = credential.smsCode.toString()
                binding.phoneAuthEtAuthNum.setText(credential.smsCode.toString())
                binding.phoneAuthEtAuthNum.isEnabled = false
                Handler(Looper.getMainLooper()).postDelayed({
                    verifyPhoneNumberWithCode(credential)
                }, 1000)
            }

            // 번호인증 실패 상태
            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
                showToast("인증실패")
            }

            // 전화번호는 확인 되었으나 인증코드를 입력해야 하는 상태
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")
                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId // verificationId 와 전화번호인증코드 매칭해서 인증하는데 사용예정
                resendToken = token
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
        binding.vm = viewModel
        initViewModelCallback()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            requestPhoneAuth.observe(this@PhoneAuthActivity, Observer { // 인증번호 요청
                UserInfo.tel = viewModel.etPhoneNum.value.toString() // 전화번호 저장
                if (it) {
                    startPhoneNumberVerification(
                        "+82" + viewModel.etPhoneNum.value.toString().substring(1)
                    )
                } else {
                    showToast("전화번호를 입력해주세요")
                }
            })

            requestResendPhoneAuth.observe(this@PhoneAuthActivity, Observer { // 인증번호 재요청
                if (it) {
                    resendVerificationCode(
                        "+82" + viewModel.etPhoneNum.value.toString().substring(1)
                        , resendToken
                    )
                } else {
                    showToast("전화번호를 입력해주세요")
                }
            })

            authComplete.observe(this@PhoneAuthActivity, Observer { // 인증완료 버튼 클릭 시
                // 휴대폰 인증번호로 인증 및 로그인 실행
                // onCodeSent() 에서 받은 vertificationID 와 문자메시지로 전송한 인증코드값으로 Credintial 만든 후 인증 시도
                try {
                    val phoneCredential =
                        PhoneAuthProvider.getCredential(
                            storedVerificationId,
                            viewModel.etAuthNum.value!!
                        )
                    verifyPhoneNumberWithCode(phoneCredential)
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            })
        }
    }

    // 전화번호 인증코드 요청
    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(90L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.phoneAuthBtnAuth.run {
            text = "재전송"
            setTextColor(
                ContextCompat.getColor(
                    this@PhoneAuthActivity, R.color.dark_gray_333333
                )
            )
            background = ContextCompat.getDrawable(
                this@PhoneAuthActivity, R.drawable.bg_btn_stroke_dark_gray_333333_radius_8dp
            )
        }
    }

    // 전화번호 인증코드 재요청
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(90L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    // 전화번호 인증 실행 (onCodeSent() 에서 받은 vertificationID 와
    // 문자로 받은 인증코드로 생성한 PhoneAuthCredential 사용)
    private fun verifyPhoneNumberWithCode(phoneAuthCredential: PhoneAuthCredential) {
        UserInfo.tel = binding.phoneAuthEtPhoneNum.text.toString()
        if (UserInfo.tel.isNotBlank() && UserInfo.phoneAuthNum.isNotBlank() &&
            (UserInfo.tel == binding.phoneAuthEtPhoneNum.text.toString() && UserInfo.phoneAuthNum == binding.phoneAuthEtAuthNum.text.toString())
        ) { // 이전에  인증한 번호와 인증번호인 경우
            showToast("인증 성공")
            UserInfo.tel = binding.phoneAuthEtPhoneNum.text.toString()
            startActivity(Intent(this@PhoneAuthActivity, UserInfoActivity::class.java))
            return
        }
        Firebase.auth.signInWithCredential(phoneAuthCredential)
            .addOnCompleteListener(this@PhoneAuthActivity) { task ->
                if (task.isSuccessful) {
                    showToast("인증 성공")
                    UserInfo.tel = binding.phoneAuthEtPhoneNum.text.toString()
                    binding.phoneAuthEtAuthNum.isEnabled = true
                    startActivity(
                        Intent(this@PhoneAuthActivity, UserInfoActivity::class.java)
                    )
                } else {
                    binding.phoneAuthTvAuthNum.text =
                        getString(R.string.auth_num_wrong_text)
                    binding.phoneAuthTvAuthNum.setTextColor(
                        ContextCompat.getColor(this@PhoneAuthActivity, R.color.red_FF5050)
                    )
                    binding.phoneAuthEtAuthNum.isEnabled = true
                }
            }
    }

}