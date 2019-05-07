package `in`.co.ankitarora.templatechooser.test

import `in`.co.ankitarora.templatechooser.UriIdlingResourceSingleton
import android.os.Bundle
import androidx.test.espresso.IdlingRegistry
import androidx.test.runner.AndroidJUnitRunner
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore


@CucumberOptions(
    features = arrayOf("features"),
    tags = arrayOf("@templateChooser"),
    glue = arrayOf("in.co.ankitarora.templatechooser.steps"),
    format = arrayOf(
        "pretty"),
    monochrome = true)
class CucumberTestRunner() : AndroidJUnitRunner() {

    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(bundle: Bundle) {
        super.onCreate(bundle)
        IdlingRegistry.getInstance().register(UriIdlingResourceSingleton.uriIdlingResource)
        instrumentationCore.create(bundle)
    }

    override fun onStart() {
        waitForIdleSync()
        instrumentationCore.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        IdlingRegistry.getInstance().unregister(UriIdlingResourceSingleton.uriIdlingResource)
    }

}