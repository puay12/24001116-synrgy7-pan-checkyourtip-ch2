package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import kotlin.math.round

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var selectedTipAmount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        binding.calculateButton.setOnClickListener(this)
        binding.tip10.setOnClickListener(this)
        binding.tip15.setOnClickListener(this)
        binding.tip20.setOnClickListener(this)
        binding.tip25.setOnClickListener(this)
        binding.tip30.setOnClickListener(this)
        binding.tip35.setOnClickListener(this)
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tip_10 -> {
                selectedTipAmount = 0.1
                changeButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip30)
                undoButtonStyle(binding.tip35)

            }
            R.id.tip_15 -> {
                selectedTipAmount = 0.15
                changeButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip30)
                undoButtonStyle(binding.tip35)
            }
            R.id.tip_20 -> {
                selectedTipAmount = 0.2
                changeButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip30)
                undoButtonStyle(binding.tip35)
            }
            R.id.tip_25 -> {
                selectedTipAmount = 0.25
                changeButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip30)
                undoButtonStyle(binding.tip35)
            }
            R.id.tip_30 -> {
                selectedTipAmount = 0.3
                changeButtonStyle(binding.tip30)
                undoButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip35)
            }
            R.id.tip_35 -> {
                selectedTipAmount = 0.35
                changeButtonStyle(binding.tip35)
                undoButtonStyle(binding.tip10)
                undoButtonStyle(binding.tip15)
                undoButtonStyle(binding.tip20)
                undoButtonStyle(binding.tip25)
                undoButtonStyle(binding.tip30)
            }
            R.id.calculate_button -> {
                val tip = calculateTip(selectedTipAmount)
                binding.calculatedTip.text = "Rp ${tip.toInt()},-"
                binding.totalBill.text = "Rp ${calculateTotalBill(tip).toInt()},-"
                binding.responseImg.setImageDrawable(getDrawable(R.drawable.img_thankyou))
            }
        }
    }

    private fun calculateTip(selectedTip: Double) : Double {
        val tip = if (binding.roundUpTip.isChecked) {
            roundUpTip(selectedTip)
        } else {
            selectedTip
        }

        return binding.billInput.text.toString().toDouble() * tip
    }

    private fun calculateTotalBill(calculatedTip: Double) : Double {
        return binding.billInput.text.toString().toDouble() + calculatedTip
    }

    private fun roundUpTip(selectedTip: Double) : Double {
        return round(selectedTip)
    }

    private fun undoButtonStyle(former: MaterialButton) {
        former.backgroundTintList = getColorStateList(R.color.background_light)
        former.setTextColor(getColor(R.color.primary_purple))
    }

    private fun changeButtonStyle(after: MaterialButton) {
        after.backgroundTintList = getColorStateList(R.color.primary_purple)
        after.setTextColor(getColor(R.color.text_light))
    }
}