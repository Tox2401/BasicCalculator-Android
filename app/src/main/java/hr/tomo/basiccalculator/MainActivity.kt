package hr.tomo.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimal(view: View) {
        val decimalPoint = tvInput?.text?.contains(".")
        if (decimalPoint == false) {
            tvInput?.append(".")
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (!isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
            }
        }
    }

    fun onEqual(view: View) {
        if (tvInput?.text?.last()?.isDigit() == true) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()

                }else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()

                }else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()

                }else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()

                }

            } catch (e: java.lang.ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun isOperatorAdded(value: String) : Boolean {
        if (value.startsWith("-")) {
            return false
        }else{
            return value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }



}