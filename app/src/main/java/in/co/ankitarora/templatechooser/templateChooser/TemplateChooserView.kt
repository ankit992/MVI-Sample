package `in`.co.ankitarora.templatechooser.templateChooser

import `in`.co.ankitarora.templatechooser.R
import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_template_chooser.view.*


class TemplateChooserView : LinearLayout, TemplateChooserViewInterface {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes)


    private val events: PublishSubject<TemplateChooserViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<TemplateChooserViewEvents> = events.hide().share()

    override fun showRetryButton() {
        layout_retry.visibility = View.VISIBLE
        templates_layout.visibility = View.GONE
    }

    override fun showTemplates(templateDetails: List<TemplateDetails>) {
        layout_retry.visibility = View.GONE
        templates_layout.visibility = View.VISIBLE
        template_details_view_pager.adapter = TemplatesPagerAdapter(context, templateDetails)
        template_details_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (templateDetails[position].variations[0].iconType == "color")
                    updateBackgroundColor(templateDetails[position].variations[0].getIconColor())
            }

        })
        if (templateDetails[0].variations[0].iconType.equals("color"))
            updateBackgroundColor(templateDetails[0].variations[0].getIconColor())
    }

    private fun updateBackgroundColor(color: String) {
        var colorFrom = Color.TRANSPARENT
        val background = templates_layout.background
        if (background is ColorDrawable)
            colorFrom = background.color
        val colorTo = parseColor(color)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 250
        colorAnimation.addUpdateListener { animator -> templates_layout.setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
    }


    init {
        this.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        android.view.LayoutInflater.from(context).inflate(R.layout.view_template_chooser, this, true)
        Handler().postDelayed(
            { events.onNext(TemplateChooserViewEvents.GetTemplateData) }, 200
        )
        button_retry.setOnClickListener {
            events.onNext(TemplateChooserViewEvents.GetTemplateData)
        }
    }

}
