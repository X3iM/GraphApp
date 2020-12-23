package com.android.greena.graphapp.controllers

import android.content.Context
import android.content.Intent
import android.graphics.PointF
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.android.greena.graphapp.PopUpSettingsMenuActivity
import com.android.greena.graphapp.graphitem.GraphItem
import com.android.greena.graphapp.utils.Constants.Companion.HOLD_TIME
import kotlin.properties.Delegates

class HoldController() : Runnable {

    var running = false

    lateinit var graphItem: GraphItem

    private var currentTime by Delegates.notNull<Long>()

    private lateinit var thread: Thread

    override fun run() {
        currentTime = System.nanoTime()
        while (running) {
            if (System.nanoTime() - currentTime >= HOLD_TIME) {
//                Toast.makeText()
                graphItem.isHold = true
                Log.d("THREAD - HoldController", "stop")
                stop()
            }
        }
    }

    fun start() {
        running = true
        thread = Thread(this)
        thread.start()
    }

    fun stop() {
        running = false
    }
}