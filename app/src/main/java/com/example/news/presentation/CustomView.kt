package com.example.news.presentation

//https://habr.com/ru/articles/727744/#4.%20%D0%9C%D0%B5%D1%82%D0%BE%D0%B4%D1%8B%20View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.news.R
import java.lang.Integer.min

class CustomView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    private var shapeColor = 0
    private var displayShapeName = false

    private var shapeWidth = 200
    private var shapeHeight = 200
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
            displayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false)


        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            0f + paddingLeft + paddingRight,
            0f + paddingTop + paddingBottom,
            shapeWidth.toFloat(),
            shapeHeight.toFloat(),
            paintShape
        )

        if (displayShapeName) {
            canvas.drawText("Square", 0 + textXOffset, shapeHeight + textYOffset, paintShape)
        }

        println(width)
    }

    private fun setupPaint() {
        paintShape = Paint()
        paintShape.setStyle(Paint.Style.FILL)
        paintShape.setColor(shapeColor)
        paintShape.setTextSize(30f)
    }


    /*    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            // Defines the extra padding for the shape name text
            val textPadding = 100

            // Resolve the width based on our minimum and the measure spec
            val minw = shapeWidth + paddingLeft + paddingRight
            val w = resolveSizeAndState(minw, widthMeasureSpec, 0)

            // Ask for a height that would let the view get as big as it can
            var minh = (shapeHeight + paddingBottom + paddingTop).toInt()
            if (displayShapeName) {
                minh += (textYOffset + textPadding).toInt()
            }

            val h = resolveSizeAndState(minh, heightMeasureSpec, 0)

            // Calling this method determines the measured width and height
            // Retrieve with getMeasuredWidth or getMeasuredHeight methods later
            setMeasuredDimension(w, h)
        }*/

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // EXACTLY  - width/height to 50dp or match_parent.
        // AT_MOST - The parent gives maximum size and the child adapts to it.
        //           This is the case for setting the width/height to the wrap_conten


        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize // Задан конкретный размер для ширины                           --match_parent
            MeasureSpec.AT_MOST -> min(shapeWidth, widthSize) // Размер не должен превышать заданный размер  -- wrap_conten
            else -> shapeWidth // Задать предпочтительный размер, если точного или максимального размера не задано
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize // Задан конкретный размер для высоты
            MeasureSpec.AT_MOST -> min(shapeHeight, heightSize) // Размер не должен превышать заданный размер
            else -> shapeHeight // Задать предпочтительный размер, если точного или максимального размера не задано
        }

        setMeasuredDimension(width, height) // Устанавливаем фактический размер View
    }

    //Метод onSizeChanged() вызывается при изменении размеров View
    // (смена ориентация устройства, изменение размера родительского контейнера)

    // onSizeChanged() is called when your view is first assigned a size,
    // and again if the size of your view changes for any reason.
    // Calculate positions, dimensions, and any other values related to your view's size in onSizeChanged(),
    // instead of recalculating them every time you draw
    /*    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
            shapeWidth = width
            shapeHeight = height
        }*/


}