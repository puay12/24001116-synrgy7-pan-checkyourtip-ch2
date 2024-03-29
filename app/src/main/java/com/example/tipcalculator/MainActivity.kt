package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

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

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tip_10 -> {
                selectedTipAmount = 0.1
                buttonClicked(binding.tip10)
            }
            R.id.tip_15 -> {
                selectedTipAmount = 0.15
                buttonClicked(binding.tip15)
            }
            R.id.tip_20 -> {
                selectedTipAmount = 0.2
                buttonClicked(binding.tip20)
            }
            R.id.tip_25 -> {
                selectedTipAmount = 0.25
                buttonClicked(binding.tip25)
            }
            R.id.tip_30 -> {
                selectedTipAmount = 0.3
                buttonClicked(binding.tip30)
            }
            R.id.tip_35 -> {
                selectedTipAmount = 0.35
                buttonClicked(binding.tip35)
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
            roundUpTip()
        } else {
            selectedTip
        }

        return binding.billInput.text.toString().toDouble() * tip
    }

    private fun calculateTotalBill(calculatedTip: Double) : Double {
        return binding.billInput.text.toString().toDouble() + calculatedTip
    }

    private fun roundUpTip() : Double {
        return 1.0
    }

    private fun buttonClicked(nextBtn: MaterialButton) {
        val buttons = arrayListOf<MaterialButton>(
            binding.tip10,
            binding.tip15,
            binding.tip20,
            binding.tip25,
            binding.tip30,
            binding.tip35
        )

        buttons.remove(nextBtn)
        changeButtonStyle(nextBtn)
        buttons.forEach {
            undoButtonStyle(it)
        }
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