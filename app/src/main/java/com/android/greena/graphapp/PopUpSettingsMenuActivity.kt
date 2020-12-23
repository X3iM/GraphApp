package com.android.greena.graphapp;

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.android.greena.graphapp.graphitem.GraphItem
import kotlinx.android.synthetic.main.activity_pop_up_settings_menu.*

class PopUpSettingsMenuActivity() : AppCompatActivity() {

    lateinit var graphItem: GraphItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_settings_menu)

        window.setLayout(windowManager.defaultDisplay.width,
            (windowManager.defaultDisplay.height*.2).toInt()
        )

//        itemName.text = graphItem.name
        seekBar1.min = 25
        seekBar1.max = 100

        window.attributes.y = -600

        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("SEEKBAR", "seekBar1 progress - $progress")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("SEEKBAR", "seekBar1 onStartTrackingTouch")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("SEEKBAR", "seekBar1 onStopTrackingTouch")
            }

        })
    }

}
