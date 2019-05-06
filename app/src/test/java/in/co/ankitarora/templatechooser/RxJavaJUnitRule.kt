package `in`.co.ankitarora.templatechooser

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


class RxSchedulerRule : TestRule {


    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()

                RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { (Schedulers.trampoline()) }
                RxJavaPlugins.setComputationSchedulerHandler { (Schedulers.trampoline()) }
                RxJavaPlugins.setNewThreadSchedulerHandler{ (Schedulers.trampoline()) }

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}