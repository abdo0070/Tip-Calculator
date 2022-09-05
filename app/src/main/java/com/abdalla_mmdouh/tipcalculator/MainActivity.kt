package com.abdalla_mmdouh.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateSeekBar()
        calculate()
    }
    private fun updateSeekBar() {
        var startPoint = 0
        var endPoint = 0
        sbPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, p1: Int, p2: Boolean) {
                tvPercent.text = "${p1.toString()}%"
                tipAmount()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                startPoint = seekBar!!.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               endPoint = seekBar!!.progress
            }
        })
    }
    private fun calculate(){
        etBase.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                tipAmount()
            }
        })
    }
    private fun tipAmount(){
        if (etBase.text.isEmpty()){
            tvTipAmount.text = null
            tvTotalAmount.text = null
            return
        }
    val base = getBase()
    val percentage = getPercent()
    val tipAmount = base * percentage
    val totalAmount = base + tipAmount
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalAmount)
    }
    private fun getBase(): Float {
        val base = etBase?.text

        return base.toString().toFloat()
    }
    private fun getPercent(): Float {
        return (sbPercent.progress.toFloat() /100 )
    }
}