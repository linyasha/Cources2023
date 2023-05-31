package dev.lynko.cources2023

import android.content.Context
import android.graphics.Canvas
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView @JvmOverloads constructor(
    context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
): View(context, attr, defStyleAttr, defStyleRes) {


    private var customText: String? = null
    private var customSize: Int? = null

    init {
        val ta = context.obtainStyledAttributes(attr, R.styleable.MyView)
        customText = ta.getString(R.styleable.MyView_customText)
        customSize = ta.getString(R.styleable.MyView_customSize)?.toIntOrNull()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return super.onTouchEvent(event)
    }

}







//    : View {
//    constructor(context: Context) : super(context)
//    constructor(context: Context, attr: AttributeSet) : super(context, attr)
//    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int) : super(context, attr, defStyleAttr)
//    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attr, defStyleAttr, defStyleRes)
//}