package com.alexpetrov.aplicationpithread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexpetrov.aplicationpithread.databinding.ActivityMainBinding
import com.alexpetrov.aplicationpithread.fragments.FragmentA
import com.alexpetrov.aplicationpithread.fragments.FragmentB

/* Handler - это механизм, который позволяет работать с очередью сообщений.
  Он привязан к конкретному потоку и работает с его очередью,
  умеет помещать сообщения в очередь. При этом он ставит самого себя
  в качестве получателя этого сообщения. И когда приходит время,
  система достает сообщение из очереди и отправляет его  в Handler на обработку.
  Handler может:

  - реализовать отложенное по времени выполнение кода
  - выполнение кода не в своем потоке*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerA, FragmentA.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerB, FragmentB.newInstance())
            .commit()
    }
}