package com.erif.countanimation

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.erif.countanimator.CountAnimator
import com.erif.countanimator.format.CountFormat
import com.erif.countanimator.format.CountFormatCurrency
import com.erif.countanimator.format.CountFormatDecimal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt: TextView = findViewById(R.id.txt)
        val btn: Button = findViewById(R.id.btn)

        /*
        val anim = CountAnimator(0, 1200, 3)
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.start()
        */

       /*
        val anim = CountAnimator(1200, 50)
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.start()
        */

        /*val anim = CountAnimator(12000, 2000)
        anim.format(CountFormat.Currency("IDR"))
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.start()*/

        /*
        val anim = CountAnimator(2000, 12000)
        anim.format(CountFormat.Decimal("#,###"))
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.start()
        */

        val anim = CountAnimator(100, 50)
        anim.format(CountFormatCurrency("EUR"))
        anim.doOnUpdate { _, valueStr ->
            txt.text = valueStr
        }
        anim.doOnEnd {
            //txt.setTextColor(ContextCompat.getColor(this, R.color.purple_200))
        }

        btn.setOnClickListener {
            anim.start()
            /*anim.pause()
            anim.stop()
            anim.resume()*/
        }

    }

}