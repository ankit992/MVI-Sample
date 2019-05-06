package `in`.co.ankitarora.templatechooser.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class CircleButtonView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint()
    private var color: String = "#ffffff"
    private var buttonState: ButtonState =
        ButtonState.NOT_SELECTED
    fun setState(color: String, buttonState: ButtonState) {
        this.color = color
        this.buttonState = buttonState
        paint.isAntiAlias = true
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = width
        val y = height
        val radius = 40
        when (buttonState) {
            ButtonState.SELECTED -> paint.style = Paint.Style.FILL_AND_STROKE
            ButtonState.NOT_SELECTED -> paint.style = Paint.Style.STROKE
        }
//        paint.color = Color.parseColor(color)
//        canvas.drawPaint(paint)
        paint.strokeWidth = 4f
        paint.color = Color.parseColor(this.color)
        canvas.drawCircle(x / 2f, y / 2f, radius.toFloat(), paint)
    }

    sealed class ButtonState {
        object SELECTED : ButtonState()
        object NOT_SELECTED : ButtonState()
    }
}