package `in`.co.ankitarora.templatechooser.steps.splash

import `in`.co.ankitarora.templatechooser.R
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_splash.view.*

class SplashView: LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes)


    private val events: PublishSubject<SplashViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<SplashViewEvents> = events.hide().share()

    init{
        android.view.LayoutInflater.from(context).inflate(R.layout.view_splash, this, true)
        this.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        button_template_chooser.setOnClickListener {
            events.onNext(SplashViewEvents.NavigateToTemplateChooserScreen)
        }
    }

}
