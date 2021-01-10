package com.example.esoftwarica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //binding
        loginButton = findViewById(R.id.login_btn)
        usernameEditText = findViewById(R.id.username_input)
        passwordEditText = findViewById(R.id.password_input)

        loginButton.setOnClickListener {
            if(!validated()) return@setOnClickListener

            if (!attemptLogin()) {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()
                usernameEditText.setError("Incorrect username or password")
                return@setOnClickListener
            }

            val dashboardActivity = Intent(this, DashboardActivity::class.java)
            startActivity(dashboardActivity)
            finish()
        }
    }

    private fun attemptLogin(): Boolean {
        if (usernameEditText.text.toString() == "softwarica" && passwordEditText.text.toString() == "coventry") {
            return true
        }
        return false
    }

    private fun validated(): Boolean {
        var validation = true

        if (usernameEditText.text.isEmpty()) {
            usernameEditText.setError("Username is required")
            validation = false
        }
        if (passwordEditText.text.isEmpty()) {
            passwordEditText.setError("Password is required")
            validation = false
        }

        return validation
    }
}