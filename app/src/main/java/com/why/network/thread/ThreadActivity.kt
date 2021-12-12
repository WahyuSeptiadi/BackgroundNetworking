package com.why.network.thread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.why.network.R
import com.why.network.databinding.ActivityThreadBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ThreadActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            (R.id.btn_start) -> {
                /** \[PROBLEM]\ simulate process compressing = FREEZE / FORCE CLOSE because Application Not Responding (ANR) */
//                try {
//                    for (i in 0..10) {
//                        Thread.sleep(50000)
//                        val percentage = i * 10
//                        if (percentage == 100) {
//                            binding.tvStatus.setText(R.string.task_completed)
//                        } else {
//                            binding.tvStatus.text =
//                                String.format(getString(R.string.compressing), percentage)
//                        }
//                    }
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }

                /** \[SOLUTION 1] for fix FREEZE / FORCE CLOSE with executor and handler */
                val executor = Executors.newSingleThreadExecutor()
                val handler = Handler(Looper.getMainLooper())

                executor.execute {
                    try {
                        // simulate process in background thread
                        for (i in 0..10) {
                            Thread.sleep(500)
                            val percentage = i * 10
                            handler.post {
                                // update ui in main thread
                                if (percentage == 100) {
                                    binding.tvStatus.setText(R.string.task_completed)
                                } else {
                                    binding.tvStatus.text =
                                        String.format(getString(R.string.compressing), percentage)
                                }
                            }
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }

                /** \[SOLUTION 2] for fix FREEZE / FORCE CLOSE with coroutine */
                lifecycleScope.launch(Dispatchers.Default) {
                    // simulate process in background thread
                    for (i in 0..10) {
                        delay(500)
                        val percentage = i * 10
                        withContext(Dispatchers.Main) {
                            // update ui in main thread
                            if (percentage == 100) {
                                binding.tvStatus.setText(R.string.task_completed)
                            } else {
                                binding.tvStatus.text =
                                    String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                }
            }
        }
    }
}