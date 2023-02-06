package dev.outup.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.outup.coffeeapp.domain.usecase.UserService
import dev.outup.coffeeapp.infrastructure.repository.UserRepositoryImpl
import dev.outup.coffeeapp.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val auth: FirebaseAuth = Firebase.auth
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val userService = UserService(UserRepositoryImpl)

    private lateinit var loginJumpButton: Button
    private lateinit var signUpJumpButton: Button
    private lateinit var skipButton: Button

    /**
     * ログイン
     * ログインせずに進む
     * 新規登録
     * A21DC217@dhw.ac.jp  pass:asyera221
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginJumpButton = findViewById(R.id.loginJumpButton)
        signUpJumpButton = findViewById(R.id.signUpJumpButton)
        skipButton = findViewById(R.id.skipButton)

        loginJumpButton.setOnClickListener {
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        signUpJumpButton.setOnClickListener {
            val intent = Intent(this.applicationContext, RegisterUserActivity::class.java)
            startActivity(intent)
        }
        skipButton.setOnClickListener {
            val intent = Intent(this.applicationContext, MainContentsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val text: TextView = findViewById(R.id.SecondText)

        if (auth.currentUser != null) {
            scope.launch {
                text.text = "${userService.getCurrentUserName()} でログイン済みです"
            }
        } else {
            text.text = "ログインしていません"
        }
    }
}