package com.example.notesapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


class NoteCard : LinearLayout {
    private lateinit var noteTextView: TextView

    // Добавляем атрибут text
    private var noteText: String? = null
    private val textPaint: Paint = Paint()

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
        noteTextView = createNoteTextView()
        addView(noteTextView)

        orientation = VERTICAL
        gravity = Gravity.CENTER
        setBackgroundColor(getResources().getColor(R.color.pink))

        // Создание drawable с углами
        val backgroundDrawable = GradientDrawable()
        backgroundDrawable.cornerRadius = resources.getDimension(R.dimen.iconButtonRadius)
        backgroundDrawable.setColor(ContextCompat.getColor(context, R.color.pink))
        background = backgroundDrawable

        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.NoteCard, defStyle, 0
        )

        minimumHeight = resources.getDimensionPixelSize(R.dimen.noteCardMinHeight)
        noteText = a.getString(R.styleable.NoteCard_text)
        setNoteText(noteText)

        a.recycle()

        // Устанавливаем стандартные параметры для Paint (цвет, размер текста и др.)
        textPaint.color = Color.BLACK
        textPaint.textSize = resources.getDimension(R.dimen.default_text_size)
        textPaint.isAntiAlias = true

    }

    private fun createNoteTextView(): TextView {
        val textView = TextView(context)
        val params = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        )
//        textView.gravity = Gravity.CENTER
        textView.layoutParams = params
        textView.textSize = resources.getDimension(R.dimen.default_text_size)
        textView.setTextColor(resources.getColor(R.color.textBase))
        return textView
    }

    // Метод для установки текста
    fun setNoteText(text: String?) {
        noteTextView.text = text
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Переопределяем onMeasure для учета максимальной высоты
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val maxHeight = resources.getDimensionPixelSize(R.dimen.noteCardMaxHeight)

        val measuredHeight = measuredHeight
        if (maxHeight in 1..<measuredHeight) {
            setMeasuredDimension(measuredWidth, maxHeight)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}