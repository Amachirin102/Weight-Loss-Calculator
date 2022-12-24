package com.amachirin102.weight_loss_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val inches = binding.inchPicker.value
        val pounds = binding.weightPicker.value
        val age = binding.agePicker.value
        val genderId = binding.radioGender.checkedRadioButtonId
        val gender = resources.getResourceEntryName(genderId)
        val kilograms = pounds * 0.45359237
        val cm = (((feet*12) + inches) * 2.54)

        if(gender == "radio_male") {
            val bmr = (10 * kilograms) + (6.25 * cm) - (5 * age) + 5
            binding.resultsTV.text = String.format("Your Basal Metabolic Rate is:" +
                    " %.2f kcal/day", bmr)
            binding.hintTV.text = String.format("Men's average BMR tends to be around 1600 - 1800")

        }else{
            val bmr = (10 * kilograms) + (6.25 * cm) - (5 * age) - 161
            binding.resultsTV.text = String.format("Your Basal Metabolic Rate is:" +
                    " %.2f kcal/day", bmr)
            binding.hintTV.text = String.format("Women's average BMR tends to be around 1550")
        }
    }
}