package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip()
    {
        val service = binding.costOfService.text.toString()
        if(service=="") return
        val cost=service.toDouble()
        var radio=binding.tipOptions.checkedRadioButtonId
        var perc = when(radio){
            R.id.option_fifteen_percent->0.15
            R.id.option_eighteen_percent->0.18
            else->0.2
        }
        var tip=cost*perc
        var roundUp=binding.roundUpSwitch.isChecked
        if(roundUp)
            tip=kotlin.math.ceil(tip).toDouble()
        var formattedTip= NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}