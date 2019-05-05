package `in`.co.ankitarora.templatechooser

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RootView {
    override fun showProgressBar() {
        in_progress.visibility = View.VISIBLE
    }

    override fun getContext(): Context = this

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val (initialState, listOfEventObservables) = buildInitialState()

        Observable.merge(listOfEventObservables)
            .scan(initialState, { state, event ->
                state.currentScreen.reducer().reduce(state, event)
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { state ->
                state.currentScreen.updater().update(state, this)
            }
    }

    override fun currentView(): View = root_layout.getChildAt(0)

    override fun updateView(view: View) {
        root_layout.removeAllViews()
        root_layout.addView(view)
    }

    private fun buildInitialState(): Pair<State, List<Observable<out Event>>> {
        return State().let { it.copy(currentScreen = it.splashScreen) }.let {
            Pair(
                it,
                listOf(
                    it.splashScreen.eventsObservable(),
                    it.templateChooserScreen.eventsObservable(),
                    Observable.just(Event.LoadData)
                )
            )
        }
    }
}
