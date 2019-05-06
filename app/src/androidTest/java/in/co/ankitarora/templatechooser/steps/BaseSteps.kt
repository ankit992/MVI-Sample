package `in`.co.ankitarora.templatechooser.steps

import `in`.co.ankitarora.templatechooser.MainActivity
import `in`.co.ankitarora.templatechooser.helper.ActivityFinisher
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import cucumber.api.java.After
import cucumber.api.java.Before
import org.junit.Rule
import org.junit.runner.RunWith

class BaseSteps {
    @RunWith(AndroidJUnit4ClassRunner::class)
    class BaseSteps {

        @Rule
        private val mActivityTestRule = ActivityTestRule<MainActivity>(
            MainActivity::class.java,
            false, false
        )

        private var mInstrumentationContext: Context? = null
        private var mAppContext: Context? = null
        private var mActivity: Activity? = null

        @Before
        fun setUp() {

            mInstrumentationContext = InstrumentationRegistry.getInstrumentation().context
            mAppContext = InstrumentationRegistry.getInstrumentation().targetContext
            mActivity = mActivityTestRule.launchActivity(Intent()) // Start Activity before each test scenario
        }

        @After
        fun tearDown() {
            ActivityFinisher.finishOpenActivities()
        }
    }
}