package `in`.co.ankitarora.templatechooser.test

import android.os.Bundle
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
        instrumentationCore.create(bundle)
    }

    override fun onStart() {
        waitForIdleSync()
        instrumentationCore.start()
    }

}