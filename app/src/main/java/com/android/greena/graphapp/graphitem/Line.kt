package com.android.greena.graphapp.graphitem

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.random.Random

class Line(count: Int) : GraphItem {

    override var name = "Line$count"

    override var radius = 10F

    override var isHold = false

    private var linePartX = 0F
    private var linePartY = 0F

    var x1 = Random.nextInt(800).toFloat()
    var y1 = Random.nextInt(1100).toFloat()

    var x2 = Random.nextInt(800).toFloat()
    var y2 = Random.nextInt(1100).toFloat()

//    var width = sqrt(abs(stopX - startY).pow(2) + abs(stopY - startY).pow(2))

    override var color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    override var isTouched = false

    override fun move(x: Float, y: Float) {
        var partX = abs(1F - linePartX)
        var partY = abs(1F - linePartY)
//        Log.d("TAG", "partX $partX partY $partY")
        if (partX < 0.16 && partY < 0.16) {
            x2 += x
            y2 += y
        } else if (partX > 0.86 && partY > 0.86) {
            x1 += x
            y1 += y
        } else {
            x1 += x
            x2 += x
            y1 += y
            y2 += y
        }
    }

    override fun isTouch(eventX: Float, eventY: Float): Line? {
//        if (isTouched)
        var x = abs(x2 - x1)
        x = if (x <= 0.5) -1F else abs(eventX - x1) / x
        linePartX = x

        /////////////////////////////finish calculation of y/////////////////////////////
        var y = abs(y2 - y1)
        y = if (y <= 0.5 || x.equals(-1F)) x else abs(eventY - y1) / y
        linePartY = y

        val onLine = abs(x - y)

//            Log.d("line touch", "event ${eventX}, $eventY")
//            Log.d("line touch", "line ${x1}, ${y1}    ${x2}, ${y2}")
//            Log.d("line touch", "x = $x, y = $y")
//            if (onLine)

//                (distance(line.startX.toInt(), line.startY.toInt(), event.x.toInt(), event.y.toInt()) +
//                distance(event.x.toInt(), event.y.toInt(), line.stopX.toInt(), line.stopY.toInt())) ==
//                distance(line.startX.toInt(), line.startY.toInt(), line.stopX.toInt(), line.stopY.toInt())

        val oneWay =
            (x1 - radius <= eventX && eventX <= x2 + radius) && (y1 - radius <= eventY && eventY <= y2 + radius)
        val secondWay =
            (x2 - radius <= eventX && eventX <= x1 + radius) && (y2 - radius <= eventY && eventY <= y1 + radius)
        val thirdWay =
            (x2 - radius <= eventX && eventX <= x1 + radius) && (y1 - radius <= eventY && eventY <= y2 + radius)

        if ((oneWay || secondWay || thirdWay) && onLine <= 0.3) {
//            Log.d("line touch", "touch line $x $y")
            return this
        }
//            if (line.startX == line.stopX) {
//                if (event.x == line.startX && event.y >= min(line.startY, line.stopY) && line.startX <= max(line.startY, line.stopY)) {
//                    Log.d("line touch", "touch line")
//                    return line
//                }
//            }
//            val k = abs(line.stopY - line.startY) / (line.stopX - line.startX)
//            val c = line.startY - k * line.startX
////            Log.d("line touch", "x = $x,  y = $y")
//            if (event.y == event.x * k + c) {
//                Log.d("line touch", "touch line")
//                return line
//            }
//            Log.d("circle coord", "circle${circleList.indexOf(circle)} c = ${circle.x}, y = ${circle.x}")
//            if (abs(axisX) <= circle.radius && abs(axisY) <= circle.radius)
//                return circle    }
        return null
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        if (isHold) {
            val strokePaint = Paint()
            strokePaint.isAntiAlias = true
            strokePaint.flags = Paint.ANTI_ALIAS_FLAG
            strokePaint.strokeCap = Paint.Cap.ROUND
            strokePaint.color = Color.BLUE
            strokePaint.style = Paint.Style.STROKE
            strokePaint.strokeWidth = 30f
            canvas.drawLine(x1, y1, x2, y2, strokePaint)
        }

        paint.strokeWidth = 20F
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = color
        canvas.drawLine(x1, y1, x2, y2, paint)
    }

}