package com.example.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculadora.databinding.ActivityMainBinding
import java.util.function.BinaryOperator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_main)

        binding.ButtonClear.setOnClickListener(){
            binding.editTextNumber.text.clear()
        }

        binding.ButtonDelete.setOnClickListener(){
            binding.editTextNumber.setText(binding.editTextNumber.text.dropLast(1))
        }

        binding.ButtonIgual.setOnClickListener(){
            when(operator){
                '/' -> {
                    binding.editTextNumber.setText("${firstValue / binding.editTextNumber.text.toString().toDouble()}")
                }
                '*' -> {
                    binding.editTextNumber.setText("${firstValue * binding.editTextNumber.text.toString().toDouble()}")
                }
                '+' -> {
                    binding.editTextNumber.setText("${firstValue + binding.editTextNumber.text.toString().toDouble()}")
                }
                '-' -> {
                    binding.editTextNumber.setText("${firstValue - binding.editTextNumber.text.toString().toDouble()}")
                }
                else -> binding.editTextNumber.setText("ERROR")
            }
            operator = '0'
            firstValue = 0.0
        }

        binding.ButtonPorcentaje.setOnClickListener(){
            binding.editTextNumber.setText(porcentaje(operator))
        }
    }

    fun porcentaje(operatorToUse: Char) : String {
        when(operatorToUse){
            '+' -> { return "${firstValue + (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '-' -> { return "${firstValue - (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"}
            '0' -> { return "${binding.editTextNumber.text.toString().toDouble()/100}"}
            else -> {return "ERROR"}
        }
    }

    fun getOperator(view: View){
        val button = view as Button

        operator = button.text.get(0)
        firstValue = binding.editTextNumber.text.toString().toDouble()
        binding.editTextNumber.text.clear()
    }

    fun numberButtonClicked(view: View){
        val button = view as Button

        if(button.id.equals(binding.ButtonDecimal.id)){
            if( !binding.editTextNumber.text.contains(',')) {
                binding.editTextNumber.text.append(button.text)
            }
        }else{
            binding.editTextNumber.text.append(button.text)
        }
    }
}


