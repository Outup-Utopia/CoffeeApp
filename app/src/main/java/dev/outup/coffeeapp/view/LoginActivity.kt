package dev.outup.coffeeapp.view

import kotlinx.coroutines.launch
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.outup.coffeeapp.MainActivity
import dev.outup.coffeeapp.R
import dev.outup.coffeeapp.domain.usecase.UserService
import dev.outup.coffeeapp.infrastructure.repository.UserRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class LoginActivity : AppCompatActivity() {
    private val userService = UserService(UserRepositoryImpl)
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private lateinit var loginButton: Button

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.loginButton)

        emailInput = findViewById(R.id.emailEditForLogin)
        passwordInput = findViewById(R.id.passwordEditForLogin)

        loginButton.setOnClickListener {
            scope.launch {
                login()
            }
        }
    }

    private suspend fun login() {
        val email: String = emailInput.text.toString()
        val password: String = passwordInput.text.toString()
        if (!userService.login(email, password)) {
            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
        val intent = Intent(this.applicationContext, MainActivity::class.java)
        startActivity(intent)
        TODO("Main画面に戻る処理が仮で入れられている")
    }
}
