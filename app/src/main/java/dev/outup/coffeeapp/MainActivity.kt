package dev.outup.coffeeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.outup.coffeeapp.view.LoginActivity
import dev.outup.coffeeapp.view.RegisterUserActivity

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    /**
     * ログイン
     * ログインせずに進む
     * 新規登録
     * A21DC217@dhw.ac.jp  pass:asyera221
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize Firebase Auth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton: Button = findViewById(R.id.LoginBtn)
        loginButton.setOnClickListener {
            val intent = Intent(this.applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
        val createAccountButton: Button = findViewById(R.id.CreateAccountBtn)
        createAccountButton.setOnClickListener {
            val intent = Intent(this.applicationContext, RegisterUserActivity::class.java)
            startActivity(intent)
        }
        // Check if user is signed in (non-null) and update UI accordingly.
        val text: TextView = findViewById(R.id.SecondText)
        val currentUser = auth.currentUser
        if (currentUser != null) {
            text.text = "ログイン済みです"
        } else {
            text.text = "ログインしていません"
        }
    }
}