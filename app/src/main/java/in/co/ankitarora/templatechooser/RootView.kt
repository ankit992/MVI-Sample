package `in`.co.ankitarora.templatechooser

import android.content.Context
import android.view.View

interface RootView {
    fun getContext(): Context
    fun updateView(view: View)
    fun currentView(): View
    fun showProgressBar()
}
