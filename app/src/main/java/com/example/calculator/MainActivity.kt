package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var input = ""
    private var operator = ""
    private var firstNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Daftar ID tombol yang ada di layout
        val buttonIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnTambah, R.id.btnKurang, R.id.btnKali, R.id.btnBagi,
            R.id.btnClear, R.id.btnHasil
        )

        // Mengatur click listener untuk setiap tombol
        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { buttonClick(it as Button) }
        }
    }

    // Fungsi yang dipanggil saat tombol ditekan
    private fun buttonClick(button: Button) {
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> {  // Reset semua variabel saat tombol Clear ditekan
                input = ""
                operator = ""
                firstNumber = 0.0
                tvDisplay.text = "0"
            }
            "+", "-", "x", ":" -> {  // Menyimpan operator dan angka pertama
                operator = buttonText
                firstNumber = input.toDoubleOrNull() ?: 0.0
                input = ""
            }
            "Hasil" -> {  // Melakukan perhitungan saat tombol Hasil ditekan
                if (operator.isNotEmpty()) {
                    val secondNumber = input.toDoubleOrNull() ?: 0.0
                    val result = when (operator) {
                        "+" -> firstNumber + secondNumber
                        "-" -> firstNumber - secondNumber
                        "x" -> firstNumber * secondNumber
                        ":" -> if (secondNumber != 0.0) firstNumber / secondNumber else {
                            tvDisplay.text = "Error"
                            return
                        }
                        else -> 0.0
                    }
                    tvDisplay.text = result.toString()
                    input = result.toString()
                    operator = ""
                }
            }
            else -> {  // Menambahkan angka yang ditekan ke input
                input += buttonText
                tvDisplay.text = input
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Started", "STARTED")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Started", "PAUSED")
    }



}
