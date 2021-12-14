package com.why.network

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.why.network.databinding.ActivityMainBinding
import com.why.network.service.ServiceActivity
import com.why.network.thread.ThreadActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnThread goTo ThreadActivity::class.java
        binding.btnService goTo ServiceActivity::class.java
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    private infix fun View.goTo(cls: Class<*>) {
        setOnClickListener { startActivity(cls) }
    }
}