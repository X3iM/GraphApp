package com.android.greena.graphapp

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.*
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import android.widget.Toast
import com.android.greena.graphapp.controllers.HoldController
import com.android.greena.graphapp.controllers.MoverController
import com.android.greena.graphapp.graphitem.Circle
import com.android.greena.graphapp.graphitem.GraphItem
import com.android.greena.graphapp.graphitem.Line

class GraphView(context: Context) : SurfaceView(context), Runnable {

    private var change = true
    private var playing = true
    private var moved = false

    private var prevX = 0F
    private var prevY = 0F

    private var thread: Thread
    private var movedItem: GraphItem? = null

    private var graphMover = MoverController(movedItem, PointF(0F, 0F))
    private var holdController = HoldController()

    private var graphItemsList = mutableListOf<GraphItem>()

    private var canvas: Canvas = Canvas()
    private var paint = Paint()

    init {
        graphItemsList.add(Circle(1))
        graphItemsList.add(Line(1))
        graphItemsList.add(Line(2))
        graphItemsList.add(Circle(2))
        graphItemsList.add(Line(3))
        thread = Thread(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        Log.d("TAG", "event downTime ${event.action}")
//        Log.d("TAG", "event downTime ${event.actionMasked}")
//        Log.d("TAG", "event eventTime ${event.eventTime}")
        Log.d("TAG", "x: ${event.x}, y: ${event.y}")
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                movedItem = isTouchGraphItem(event)
                if (movedItem != null) {
                    holdController = HoldController()
                    holdController.graphItem = movedItem!!
                    holdController.start()
//                    Log.d("THREAD", "start HoldController")
//                    val vibe = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
//                    vibe.vibrate(VibrationEffect.createOneShot(100, 1))
                }

//                Log.d("TAG", "$movedItem")
//                savePreviousTouchCoord(event)
            }
//            MotionEvent.Action_H
            MotionEvent.ACTION_MOVE -> {
                holdController.stop()
                if (movedItem != null && !moved) {
                    graphMover.event = PointF(event.x, event.y)
                    graphMover.isChange = true
                    graphMover.graphItem = movedItem
                    graphMover.start()
//                var graphItem = isTouchGraphItem(event)
//                if (graphItem != null) {
//                    val x = event.x - prevX
//                    val y = event.y - prevY
//                    graphItem.move(x, y)
//
//                    savePreviousTouchCoord(event)
                    change = true
                    moved = true
                } else if (movedItem != null && moved) {
                    graphMover.event = PointF(event.x, event.y)
                    graphMover.isChange = true
                    change = true
                }
            }
            MotionEvent.ACTION_UP -> {
                holdController.stop()
                if (graphMover.running && moved) {
                    moved = false
                    graphMover.stop()
                }
            }
        }
//        Log.d("THREAD", "${graphMover.running}")

//        Log.d("TAG", "x = ${event.x}, y = ${event.y}\ncircle x = ${circleList[0].x}, y = ${circleList[0].y}")
//            when (event.action) {
//                MotionEvent.ACTION_MOVE -> {
//                    x = event.x
//                    x = event.y
//
//                    Log.d("CIRCLE", "$x, $x")
//                }
//                MotionEvent.ACTION_DOWN -> Log.d("CIRCLE", "down")
//            }
        return true
    }

//    private fun distance(x1: Int, y1: Int, x2: Int, y2: Int): Double {
//        return sqrt(abs(x1 - x2).toDouble().pow(2) + abs(y1 - y2).toDouble().pow(2))
//    }

    private fun isTouchGraphItem(event: MotionEvent): GraphItem? {
        for (col in (graphItemsList.size - 1) downTo 0) {
            val l = graphItemsList[col].isTouch(event.x, event.y)
            if (l != null)
                return l
        }
        return null
    }

    override fun run() {
        while (playing) {
//            if (change) {
                draw()
//            }
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawColor(Color.WHITE)
            paint.isAntiAlias = true
            paint.flags = Paint.ANTI_ALIAS_FLAG
            for (graphItem in graphItemsList) {
//                if (line.x1 < line.x2 || line.y1 > line.y2) {
//                    val x = line.x1
//                    val y = line.y1
//                    line.x1 = line.x2
//                    line.y1 = line.y2
//                    line.x2 = x
//                    line.y2 = y
//                }
//
                graphItem.draw(canvas, paint)
//
            }
            change = false
            holder.unlockCanvasAndPost(canvas)
//            Log.d("TAG", "draw")
        }
    }

    fun pause() {
        playing = false
        try {
            thread.join()
        } catch (e: InterruptedException) {
            Toast.makeText(context, "Please restart app", Toast.LENGTH_LONG).show()
        }
    }

    fun resume() {
        playing = true
        thread = Thread(this)
        thread.start()
    }

    fun addNewObject(graphItem: GraphItem) {
        graphItemsList.add(graphItem)
        change = true
    }

    fun deleteObject() {
        graphItemsList.remove(graphItemsList[0])
        change = true
    }

    private fun savePreviousTouchCoord(event: MotionEvent) {
        prevX = event.x
        prevY = event.y
    }

}