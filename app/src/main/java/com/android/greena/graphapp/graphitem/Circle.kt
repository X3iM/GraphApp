package com.android.greena.graphapp.graphitem

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import kotlin.math.abs
import kotlin.random.Random

class Circle(count: Int) : GraphItem {

    override var name = "Circle$count"

    override var radius = 50F
    override var isHold = false

    var x = 350F
    var y = 350F

    override var color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    override var isTouched = false

    override fun move(x: Float, y: Float) {
        this.x += x
        this.y += y
//        Log.d("circle coord", "x = ${this.x}, y = ${this.y}")
//        Log.d("circle coord", "$this")
    }

    override fun isTouch(eventX: Float, eventY: Float): Circle? {
        val axisX = eventX - x
        val axisY = eventY - y
        if (abs(axisX) <= radius && abs(axisY) <= radius) {
            return this
        }
        return null
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        if (isHold) {
            val strokePaint = Paint()
            strokePaint.isAntiAlias = true
            strokePaint.flags = Paint.ANTI_ALIAS_FLAG
            strokePaint.color = Color.BLUE
            strokePaint.style = Paint.Style.STROKE
            strokePaint.strokeWidth = 8f
            canvas.drawCircle(x, y, radius+3, strokePaint)
        }

        paint.color = color
        canvas.drawCircle(x, y, radius, paint)

//            val path = Path()
//            paint.strokeWidth = 5F
//            paint.color = Color.BLUE
//            path.addCircle(x, y, radius+6, Path.Direction.CW)
//            canvas.drawPath(path, paint)
    }


//    private var color: Int = resources.getColor(R.color.colorAccent, null)

//    init {
//        x = 100F
//        y = 100F
//        layoutParams = FrameLayout.LayoutParams(100, 100)
//    }
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }
//
//    override fun performClick(): Boolean {
//        return super.performClick()
//    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if (event.action and MotionEvent.ACTION_MASK == MotionEvent.ACTION_MOVE) {
////            MotionEvent.ACTION_MOVE -> {
////                Log.d("TAG", "circle coord x = $x, $y")
////                Log.d("TAG", "event coord x = ${event.x}, ${event.y}")
////                Log.d("TAG", "${}, ${}")
//                x = (event.rawX - radius).toInt().toFloat()
//                y = (event.rawY - 200 - radius).toInt().toFloat()
//
//        }
//
//        return true
//    }
//
//    fun changeColor(color: Int) {
//        this.color = color
//        draw(canvas)
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        val paint = Paint()
//        this.canvas = canvas
//        paint.color = color
//        this.canvas.drawCircle(x - radius, y - radius, radius, paint)
//    }

}