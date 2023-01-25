package com.virus.expressshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.virus.expressshop.databinding.ActivityRegisterActvityBinding

class RegisterActvity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterActvityBinding
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.txtSignIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)

            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val email = binding.email.text.toString()
        val pass = binding.password.text.toString()
        val confirmPassword = binding.confirmpassword.text.toString()
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){
            if (it.isSuccessful) {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this,it.exception.toString() , Toast.LENGTH_SHORT).show()
            }
        }

    }
}