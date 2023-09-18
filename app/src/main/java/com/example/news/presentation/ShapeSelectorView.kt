package com.example.news.presentation


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.news.R


class ShapeSelectorView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    private var shapeColor = 0
    private val displayShapeName = false

    private val shapeWidth = 100f
    private val shapeHeight = 100f
    private val textXOffset = 0f
    private val textYOffset = 30f
    private lateinit var paintShape: Paint

    init {
        setupAttributes(attrs);
        setupPaint();
    }

    private fun setupAttributes(attrs: AttributeSet) {
        // Obtain a typed array of attributes
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0)
        // Extract custom attributes into member variables
        try {
            shapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK)

        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, shapeWidth, shapeHeight, paintShape)
        if (displayShapeName) {
            canvas.drawText("Square", 0 + textXOffset, shapeHeight + textXOffset, paintShape)
        }
    }

    private fun setupPaint() {
        paintShape = Paint()
        paintShape.setStyle(Paint.Style.FILL)
        paintShape.setColor(shapeColor)
        paintShape.setTextSize(30f)
    }

}