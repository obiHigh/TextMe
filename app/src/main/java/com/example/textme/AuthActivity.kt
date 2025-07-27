package com.example.textme

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val regEmail: EditText = findViewById(R.id.auth_email)
        val regPass: EditText = findViewById(R.id.auth_pass)
        val authBtn: Button = findViewById(R.id.auth_button)

        authBtn.setOnClickListener {
            val tempEmail = regEmail.text.toString().trim()
            val tempPassword = regPass.text.toString().trim()

            if (tempEmail.isEmpty()) {
                Toast.makeText(this, "user email can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tempPassword.isEmpty()) {
                Toast.makeText(this, "user password can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch{
                try {
                    MyApp.supabase.auth.signInWith(Email){
                        email = tempEmail
                        password = tempPassword
                    }

                    Toast.makeText(this@AuthActivity, "Authentication success", Toast.LENGTH_LONG).show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@AuthActivity, "AuthActivity.kt: Authentication failure, ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}