package `in`.co.ankitarora.templatechooser

import `in`.co.ankitarora.templatechooser.steps.splash.SplashScreen
import `in`.co.ankitarora.templatechooser.steps.templateChooser.TemplateChooserScreen
import io.reactivex.Observable

data class State(val currentScreen:Screen = SplashScreen(),
                 val splashScreen: SplashScreen = SplashScreen(),
                 val templateChooserScreen: TemplateChooserScreen = TemplateChooserScreen(),
                 val actions: Observable<Action> = Observable.fromIterable(emptyList<Action>()))
