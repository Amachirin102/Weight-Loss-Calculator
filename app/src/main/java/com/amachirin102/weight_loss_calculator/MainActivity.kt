package com.amachirin102.weight_loss_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.amachirin102.weight_loss_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 0
        binding.weightPicker.maxValue = 1500
        binding.weightPicker.value = 100

        binding.feetPicker.minValue = 0
        binding.feetPicker.maxValue = 10
        binding.feetPicker.value = 5

        binding.inchPicker.minValue = 0
        binding.inchPicker.maxValue = 12

        binding.agePicker.minValue = 0
        binding.agePicker.maxValue = 100

        binding.weightPicker.setOnValueChangedListener{_,_,_ ->
            calculateBMR()
        }

        binding.feetPicker.setOnValueChangedListener{_,_,_ ->
            calculateBMR()
        }

        binding.inchPicker.setOnValueChangedListener{_,_,_ ->
            calculateBMR()
        }

        binding.agePicker.setOnValueChangedListener{_,_,_ ->
            calculateBMR()
        }

        binding.radioGender.setOnCheckedChangeListener {_,_ ->
            calculateBMR()
        }
    }

    private fun calculateBMR()
    {
        val feet = binding.feetPicker.value
        val doubleFeet = feet.toDouble()

        val inches = binding.inchPicker.value
        val doubleInches = inches.toDouble()

        val weight = binding.weightPicker.value

        val bmr = weight.toDouble() / ((doubleFeet + doubleInches) * (doubleFeet + doubleInches))

        binding.resultsTV.text = String.format("Your BMR is: %.2f", bmr)
        binding.healthyTV.text = String.format("Considered: %s", healthyMessage(bmr))
    }

    private fun healthyMessage(bmr: Double): String
    {
        if(bmr < 18.5)
            return "Underweight"
        if(bmr < 25.0)
            return "Healthy"
        if(bmr < 30.0)
            return "Overweight"

        return "Obese"
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_male ->
                    if (checked) {

                    }
                R.id.radio_female ->
                    if (checked) {

                    }
            }
        }
    }
}