package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView // Declare properly

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvSignupRedirect = findViewById<TextView>(R.id.tvSignupRedirect)

        // Initialize and start typewriter effect
        tvWelcome = findViewById(R.id.tvWelcome)
        startTypeWriterEffect(tvWelcome, "Class is in Session—Let’s Go!")

        btnLogin.setOnClickListener {
            val email = etLoginEmail.text.toString().trim()
            val password = etLoginPassword.text.toString().trim()

            val errors = mutableListOf<String>()

            if (email.isEmpty()) {
                errors.add("⚠ Please enter your email")
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                errors.add("⚠ Invalid email format")
            }

            if (password.isEmpty()) {
                errors.add("⚠ Please enter your password")
            } else if (password.length < 6) {
                errors.add("⚠ Password must be at least 6 characters")
            }

            if (errors.isNotEmpty()) {
                // Show all errors at once
                Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "✅ Login Successful!", Toast.LENGTH_SHORT).show()
                // Proceed with actual authentication logic
            }
        }

        tvSignupRedirect.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startTypeWriterEffect(textView: TextView, text: String) {
        val handler = Handler()
        var index = 0
        val delay: Long = 100 // 100ms delay per letter

        val runnable = object : Runnable {
            override fun run() {
                if (index <= text.length) {
                    textView.text = text.substring(0, index)
                    index++
                    handler.postDelayed(this, delay)
                } else {
                    // Restart effect after 2 seconds
                    index = 0
                    handler.postDelayed(this, 2000)
                }
            }
        }
        handler.post(runnable)
    }
}
