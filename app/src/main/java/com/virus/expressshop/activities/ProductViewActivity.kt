package com.virus.expressshop.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virus.expressshop.databinding.ActivityProductViewBinding

class ProductViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true);



    }


}