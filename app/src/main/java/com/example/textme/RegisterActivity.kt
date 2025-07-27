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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val regName: EditText = findViewById(R.id.reg_name)
        val regTag: EditText = findViewById(R.id.reg_tag)
        val regEmail: EditText = findViewById(R.id.reg_email)
        val regPass: EditText = findViewById(R.id.reg_pass)
        val regPhone: EditText = findViewById(R.id.reg_phone)
        val regBtn: Button = findViewById(R.id.reg_button)

        regBtn.setOnClickListener {
            val tempName = regName.text.toString().trim()
            val tempTag = regTag.text.toString().trim()
            val tempEmail = regEmail.text.toString().trim()
            val tempPassword = regPass.text.toString().trim()
            val tempPhone = regPhone.text.toString().trim()

            if (tempName.isEmpty()) {
                Toast.makeText(this, "user name can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tempTag.isEmpty()) {
                Toast.makeText(this, "user tag can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tempEmail.isEmpty()) {
                Toast.makeText(this, "user email can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tempPassword.isEmpty()) {
                Toast.makeText(this, "user password can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (tempPhone.isEmpty()) {
                Toast.makeText(this, "user phone number can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val regResponse = MyApp.supabase.auth.signUpWith(Email){
                        email = tempEmail
                        password = tempPassword
                    }

                    if (regResponse == null) {
                        Toast.makeText(this@RegisterActivity, "RegisterActivity.kt: regResponse is null", Toast.LENGTH_LONG).show()
                    }

                    withContext(Dispatchers.IO){
                        val profileInsert = mapOf(
                            "username" to tempName,
                            "tag" to tempTag,
                            "email" to tempEmail,
                            "password" to tempPassword,
                            "phoneNumber" to tempPhone
                        )

                        MyApp.supabase.from("users").insert(profileInsert)

                        Toast.makeText(this@RegisterActivity, "Registration success", Toast.LENGTH_LONG).show()
                        finish()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@RegisterActivity, "RegisterActivity.kt: Registration failure, ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}