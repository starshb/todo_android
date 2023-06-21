package com.example.umarry.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umarry.MainActivity
import com.example.umarry.R
import com.example.umarry.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity:AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001 //구글 로그인에 필요한 변수 설정
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = Firebase.auth
        val database = Firebase.database
        val myRef = database.getReference("marryu")
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("904998040873-ikv02cikf2glmmttvcecjq0gk1hc5f77.apps.googleusercontent.com") //values.xml 의 default_web_client_id 값 전체
            .requestEmail()
            .build()



        binding.ToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.loginButton.setOnClickListener {
            // todo db 연동 및 계정 인증
            var email = binding.loginEmail.text.toString()
            var password = binding.loginPassword.text.toString()
            if(email.isNotEmpty()&&password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(
                                this,
                                "이메일과 비밀번호를 확인해주세요",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
//            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        // todo 아래의 간편로그인 api연동
        binding.googleBtn.setOnClickListener {
            googleSignInClient = GoogleSignIn.getClient(this,gso)
            val signInIntent = googleSignInClient!!.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
//
//            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        binding.facebookBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.twitterBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        // todo 계정찾기 페이지로 연결
        binding.Tofind.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
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

    override fun onDestroy() {
        super.onDestroy()
//        firebaseAuthSignOut()
    }

    // 구글 인증 부분 객체에서 id토큰 겟
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{ // 로그인 성공시
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e:ApiException){} // 로그인 실패
        }
    }

    // 구글 로그인 과정
    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    user?.let{

                        val name = user.displayName
                        val email = user.email
                        val displayName = user.displayName
                        val photoUrl = user.photoUrl
                        val emailVerified = user.isEmailVerified
                        val uid = user.uid
                        Log.d("xxxxxx name",name.toString())
                        Log.d("xxxxxx email",email.toString())
                        Log.d("xxxxxx uid",uid.toString())
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                }else {
                    Log.e("xxxxx","구글 로그인 실패",task.exception)
                }
            }
    }
    private fun firebaseAuthSignOut() {
        if(auth.currentUser != null)
            Firebase.auth.signOut()
    }


    private fun reload() {
    }
}