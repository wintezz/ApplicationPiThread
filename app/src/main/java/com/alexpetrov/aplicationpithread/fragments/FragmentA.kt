package com.alexpetrov.aplicationpithread.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexpetrov.aplicationpithread.databinding.FragmentABinding
import java.math.BigDecimal

class FragmentA : Fragment() {

    private lateinit var binding: FragmentABinding
    private var resultOne = BigDecimal(3)
    private var counterOne: Double = 0.0
    private var showOne = ""
    private var formula: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding
            .inflate(inflater, container, false)

        oneScreen()

        return binding.root
    }

    private fun oneScreen() {
        val formulaOne = Runnable {
            Thread {
                while (true) {
                    counterOne += 1
                    if (counterOne % 2 == 1.0) {
                        formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                        resultOne += (BigDecimal(4).divide(BigDecimal(formula), 300, 0))

                    } else {
                        formula = counterOne * 2 * (counterOne * 2 + 1) * (counterOne * 2 + 2)
                        resultOne -= (BigDecimal(4).divide(BigDecimal(formula), 300, 0))
                    }

                    showOne = resultOne.toString()
                    if (counterOne % 1000 == 0.0) {
                        val msg = handlerOne.obtainMessage()
                        msg.obj = showOne
                        handlerOne.sendMessage(msg)
                    }
                }

            }.start()
        }
        val threadOne = Thread(formulaOne)
        threadOne.start()
    }

    private var handlerOne: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msgOneFormula: Message) {
            binding.textViewA.text = msgOneFormula.obj.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}