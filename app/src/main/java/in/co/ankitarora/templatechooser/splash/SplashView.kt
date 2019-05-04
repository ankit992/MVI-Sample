package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.R
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SplashView: LinearLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet?) : super(context, attributes)


    private val events: PublishSubject<SplashViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<SplashViewEvents> = events.hide().share()

    init{
        this.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        android.view.LayoutInflater.from(context).inflate(R.layout.view_splash, this, true)

    }

}
