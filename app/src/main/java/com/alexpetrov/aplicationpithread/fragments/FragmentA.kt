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
    private var resultTwo = BigDecimal(4)
    private var counterOne: Double = 0.0
    private var counterTwo: Double = 0.0
    private var showOne = ""
    private var showTwo = ""
    private var formula: Double = 0.0
    private var countTwo = 0
    private var divider = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding
            .inflate(inflater, container, false)

        oneScreen()
        twoScreen()

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

    private fun twoScreen() {
        val formulaTwo = Runnable {
            Thread {
                while (true) {
                    counterTwo += 1
                    if (countTwo % 2 == 0) {
                        resultTwo -= (BigDecimal(4).divide(BigDecimal(divider + 2), 300, 0))

                    } else {
                        resultTwo += (BigDecimal(4).divide(BigDecimal(divider + 2), 300, 0))
                    }

                    showTwo = resultTwo.toString()
                    divider += 2
                    if (counterTwo % 1000 == 0.0) {
                        val msg1 = handlerTwo.obtainMessage()
                        msg1.obj = showTwo
                        handlerTwo.sendMessage(msg1)
                    }
                    countTwo++
                }

            }.start()
        }

        val threadTwo = Thread(formulaTwo)
        threadTwo.start()
    }

    private var handlerOne: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msgOneFormula: Message) {
            binding.textViewA.text = msgOneFormula.obj.toString()
        }
    }

    private var handlerTwo: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msgTwoFormula: Message) {
            binding.textViewB.text = msgTwoFormula.obj.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentA()
    }
}