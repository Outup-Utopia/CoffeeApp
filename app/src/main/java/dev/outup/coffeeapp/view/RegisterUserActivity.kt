package dev.outup.coffeeapp.view

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
import kotlinx.coroutines.*

class RegisterUserActivity : AppCompatActivity() {
    private val userService = UserService(UserRepositoryImpl)
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private lateinit var registerButton: Button

    private lateinit var userNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        registerButton = findViewById(R.id.registerButton)

        userNameInput = findViewById(R.id.userNameEditForSignUp)
        emailInput = findViewById(R.id.emailEditForSignUp)
        passwordInput = findViewById(R.id.passwordEditForSignUp)
        confirmPasswordInput = findViewById(R.id.confirmPasswordEdit)

        registerButton.setOnClickListener {
            scope.launch {
                signUp()
            }
        }
    }

    private suspend fun signUp() {
        coroutineScope {
            val userName = userNameInput.text.toString()
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            if (!userService.signUp(userName, email, password)) {
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val intent = Intent(this.applicationContext, MainActivity::class.java)
        startActivity(intent)
        TODO("Main画面に戻る処理が仮で入れられている")
    }
}