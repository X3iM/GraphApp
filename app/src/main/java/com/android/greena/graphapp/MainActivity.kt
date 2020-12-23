package com.android.greena.graphapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.greena.graphapp.graphitem.Circle

class MainActivity : AppCompatActivity() {

    private var count = 0

    private lateinit var graphView: GraphView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        graphView = GraphView(this)

        setContentView(graphView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.newCircle -> {
                graphView.addNewObject(Circle(count))
                count++
            }
            R.id.deleteCircle -> graphView.deleteObject()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        Log.d("TAG","touch x = ${event.x}, y = ${event.y}")
        return super.onTouchEvent(event)
    }

    override fun onRestart() {
        super.onRestart()
//        Log.d("TAG", "onRestart")
    }

    override fun onStart() {
        super.onStart()
//        Log.d("TAG", "onStart")
//        Log.d("TAG", "onStart circle1 -> ${circle!!.x}, circle2 -> ${circle2!!.x}, circle3 -> ${circle3!!.x}")
    }


    override fun onStop() {
        super.onStop()
//        Log.d("TAG", "onStop circle1 -> ${circle!!.x}, circle2 -> ${circle2!!.x}, circle3 -> ${circle3!!.x}")
    }

    override fun onResume() {
        super.onResume()
        graphView.resume()
    }

    // Stop the thread in snakeEngine
    override fun onPause() {
        super.onPause()
        graphView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
//        Log.d("TAG", "onDestroy")
    }

}