package com.why.network.service

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.why.network.R
import com.why.network.databinding.ActivityMyServiceBinding

class MyServiceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMyServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener(this)
        binding.btnStartJobIntentService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            (R.id.btn_start_service) -> {
                val mStartServiceIntent = Intent(this, MyService::class.java)
                startService(mStartServiceIntent)
            }
            (R.id.btn_start_job_intent_service) -> {
                val mStartIntentService = Intent(this, MyJobIntentService::class.java)
                mStartIntentService.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
                MyJobIntentService.enqueueWork(this, mStartIntentService)
            }
        }
    }
}