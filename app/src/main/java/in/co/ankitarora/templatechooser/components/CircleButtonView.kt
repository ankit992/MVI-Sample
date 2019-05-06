package `in`.co.ankitarora.templatechooser.components

import `in`.co.ankitarora.templatechooser.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class CircleButtonView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint()
    private var color: String = context.getString(R.string.default_selector_color)
    private var buttonState: ButtonState =
        ButtonState.NotSelected
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
            ButtonState.Selected -> paint.style = Paint.Style.FILL_AND_STROKE
            ButtonState.NotSelected -> paint.style = Paint.Style.STROKE
        }
        paint.strokeWidth = 4f
        paint.color = Color.parseColor(this.color)
        canvas.drawCircle(x / 2f, y / 2f, radius.toFloat(), paint)
    }

    sealed class ButtonState {
        object Selected : ButtonState()
        object NotSelected : ButtonState()
    }
}