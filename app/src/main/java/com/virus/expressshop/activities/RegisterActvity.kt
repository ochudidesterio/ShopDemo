package com.virus.expressshop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.virus.expressshop.databinding.ActivityRegisterActvityBinding

class RegisterActvity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterActvityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtSignIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}