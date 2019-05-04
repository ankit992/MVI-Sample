package `in`.co.ankitarora.templatechooser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun currentView(): View = root_layout.getChildAt(0)

    fun updateView(view:View) {
        root_layout.removeAllViews()
        root_layout.addView(view)
    }

    open protected fun buildInitialState():Pair<State, List<Observable<out Event>>>{
        return State().let { it.copy(currentScreen = it.splashScreen) }.let {
            Pair(it, listOf(it.splashScreen.eventsObservable(), it.homeScreen.eventsObservable(), Observable.just(Event.LoadData)))
        }
    }
}
