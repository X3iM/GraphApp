package com.android.greena.graphapp.graphitem

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.GestureDetector

interface GraphItem {

    var name: String

    var color: Int
    var radius: Float

    var isTouched: Boolean
    var isHold: Boolean

    fun move(x: Float, y: Float)
    fun isTouch(eventX: Float, eventY: Float): GraphItem?
    fun draw(canvas: Canvas, paint: Paint)

}