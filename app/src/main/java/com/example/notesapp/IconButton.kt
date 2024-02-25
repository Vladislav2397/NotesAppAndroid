package com.example.notesapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

/**
 * TODO: document your custom view class.
 */
class IconButton : View {

    private var _name: String? = ""

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.IconButton, defStyle, 0
        )

        // Создание drawable с углами
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.cornerRadius = resources.getDimension(R.dimen.iconButtonRadius)
        backgroundDrawable.setColor(ContextCompat.getColor(context, R.color.controlSurface))
        background = backgroundDrawable

        _name = a.getString(R.styleable.IconButton_icon)

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Переопределяем onMeasure для учета максимальной высоты
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = resources.getDimensionPixelSize(R.dimen.iconButtonSize)

        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}