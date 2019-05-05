package `in`.co.ankitarora.templatechooser.splash

import `in`.co.ankitarora.templatechooser.Action
import `in`.co.ankitarora.templatechooser.Event
import `in`.co.ankitarora.templatechooser.Reducer
import `in`.co.ankitarora.templatechooser.State
import io.reactivex.Observable

class SplashReducer : Reducer {
    override fun reduce(state: State, event: Event): State {
        return when (event) {
            is Event.LoadData -> state.copy(actions = Observable.just(Action.ShowCurrentScreen))
            is Event.NavigateToTemplateChooserScreen -> state.copy(
                currentScreen = state.templateChooserScreen
            )
        }
    }
}
