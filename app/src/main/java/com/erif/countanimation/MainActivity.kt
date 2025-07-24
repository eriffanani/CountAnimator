package com.erif.countanimation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.erif.countanimator.CountAnimator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt: TextView = findViewById(R.id.txt)
        val btn: Button = findViewById(R.id.btn)

        val anim = CountAnimator(10, 5)
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        btn.setOnClickListener {
            anim.start()
        }

    }

}