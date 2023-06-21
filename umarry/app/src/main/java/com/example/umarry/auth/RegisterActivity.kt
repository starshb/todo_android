package com.example.umarry.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umarry.MainActivity
import com.example.umarry.R
import com.example.umarry.databinding.ActivityRegisterBinding
import com.example.umarry.utils.FirebaseAuthUtils
import com.example.umarry.utils.FirebaseRef
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity:AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth //인증 객체 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth
        setContentView(binding.root)

        // 회원가입 data 넘김
        binding.signUpButton.setOnClickListener {
            // binding
            // todo db 연동 및 계정 추가
            var name = binding.name.text.toString()
            var nickname = binding.nickname.text.toString()
            var age = binding.age.text.toString()
            var phone = binding.phone.text.toString()
            var birth = binding.birth.text.toString()
            val email :String = binding.registerEmail.text.toString()
            val password : String = binding.registerPassword.text.toString()
            val uid = FirebaseAuthUtils.getUid()
            val userModel = UserDataModel(
                uid,
                name,
                nickname,
                age,
                phone,
                birth,
                email,
                password
            )

            if(email.isNotEmpty() && password.isNotEmpty()) {
                // [START create_user_with_email]
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val uid = FirebaseAuthUtils.getUid()
                            FirebaseRef.memberRef.child(uid).setValue("")

                            Toast.makeText(this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java)) // activity 이동
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                this,
                                "회원가입에 실패했습니다. 다시 한 번 시도해주세요.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
            // [END create_user_with_email]
        }

        binding.registerHeader.setOnClickListener {
            onBackPressed()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }


}