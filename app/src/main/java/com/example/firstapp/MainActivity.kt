package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signupTitle = findViewById<TextView>(R.id.signup_title)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        val tvLoginRedirect = findViewById<TextView>(R.id.tvLoginRedirect)

        // Start looping typewriter effect
        startTypeWriterEffect(signupTitle, "Unlock your future with L&P")

        btnSignup.setOnClickListener { view: View? ->
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()
            val errors = mutableListOf<String>()

            if (name.isEmpty()) errors.add("Please enter your name")
            if (email.isEmpty()) errors.add("Please enter your email")
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) errors.add("Invalid email format")
            if (password.isEmpty()) errors.add("Please enter your password")
            else if (password.length < 6) errors.add("Password must be at least 6 characters")
            if (confirmPassword.isEmpty()) errors.add("Please confirm your password")
            else if (password != confirmPassword) errors.add("Passwords do not match")

            if (errors.isNotEmpty()) {
                Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Sign-Up Successful! Redirecting to login...", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
            }
        }

        tvLoginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to start looping typewriter effect
    private fun startTypeWriterEffect(textView: TextView, text: String) {
        val handler = Handler()
        val delay: Long = 100 // 100ms delay per letter

        fun typeWriterEffect(index: Int) {
            if (index <= text.length) {
                textView.text = text.substring(0, index)
                handler.postDelayed({ typeWriterEffect(index + 1) }, delay)
            } else {
                // Restart after 2 seconds
                handler.postDelayed({ typeWriterEffect(0) }, 2000)
            }
        }

        typeWriterEffect(0) // Start the effect
    }
}
