package `in`.co.ankitarora.templatechooser

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.content.Context
import android.view.View
import io.reactivex.Observable

interface RootView {
    fun getContext(): Context
    fun updateView(view: View)
    fun currentView(): View
    fun showProgressBar()
    fun getTemplatesData(): Observable<List<TemplateDetails>>
    fun hideProgressBar()
}
