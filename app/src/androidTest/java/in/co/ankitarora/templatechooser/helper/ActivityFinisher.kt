package `in`.co.ankitarora.templatechooser.helper

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.test.runner.lifecycle.ActivityLifecycleMonitor
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import java.util.*

class ActivityFinisher private constructor() : Runnable {

    private val activityLifecycleMonitor: ActivityLifecycleMonitor = ActivityLifecycleMonitorRegistry.getInstance()

    override fun run() {
        val activities = ArrayList<Activity>()

        for (stage in EnumSet.range(Stage.CREATED, Stage.STOPPED)) {
            activities.addAll(activityLifecycleMonitor.getActivitiesInStage(stage))
        }

        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    companion object {

        fun finishOpenActivities() {
            Handler(Looper.getMainLooper()).post(ActivityFinisher())
        }
    }
}