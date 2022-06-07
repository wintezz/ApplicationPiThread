package com.alexpetrov.aplicationpithread.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexpetrov.aplicationpithread.R
import com.alexpetrov.aplicationpithread.databinding.FragmentBBinding

class FragmentB : Fragment() {

    private lateinit var binding: FragmentBBinding
    private var count = 0
    private var i = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding
            .inflate(inflater, container, false)

        Thread {
            binding.chronometer.start()
            binding.buttonPlay.setOnClickListener {
                binding.chronometer.base = SystemClock.elapsedRealtime()
                binding.chronometer.start()
            }

            binding.buttonPause.setOnClickListener {
                binding.chronometer.stop()
            }

            binding.buttonReset.setOnClickListener {
                binding.chronometer.base = SystemClock.elapsedRealtime()
            }

            binding.chronometer.setOnChronometerTickListener {
                val elapsedMillis: Long = (SystemClock.elapsedRealtime() - binding.chronometer.base)
                if (elapsedMillis / i in 20001..20999) {
                    i++
                    if (count == 0) {
                        count = 1
                        binding.constraintLayout.setBackgroundResource(R.color.black)

                    } else {
                        count = 0
                        binding.constraintLayout.setBackgroundResource(R.color.white)
                    }
                }
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentB()
    }
}