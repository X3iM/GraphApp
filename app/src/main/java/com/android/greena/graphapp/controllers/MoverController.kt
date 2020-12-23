package com.android.greena.graphapp.controllers

import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import com.android.greena.graphapp.graphitem.GraphItem

class MoverController(var graphItem: GraphItem?, var event: PointF) : Runnable {

    var isChange = true

    private var prevX = event.x
    private var prevY = event.y

    private lateinit var thread: Thread

    var running = true

    override fun run() {
        running = true
        prevX = event.x
        prevY = event.y

        while (running) {
            if (isChange) {
                val x = event.x - prevX
                val y = event.y - prevY
//            Log.d("THREAD", "x = ${x} y = ${y}")
//                Log.d("THREAD", "prevX = ${prevX} prevY = ${prevY}")
//                Log.d("THREAD", "eventX = ${event.x} eventY = ${event.y}")
                graphItem?.move(x, y)
//            Log.d("THREAD", "move")

                savePreviousTouchCoord()
                isChange = false
            }
//            savePreviousTouchCoord()
//            change = true
        }
    }

    fun start() {
        thread = Thread(this)
        thread.start()
    }

    fun stop() {
        Log.d("THREAD", "MoverController")
        running = false
    }

    private fun savePreviousTouchCoord() {
        prevX = event.x
        prevY = event.y
    }

}