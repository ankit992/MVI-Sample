package `in`.co.ankitarora.templatechooser

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import `in`.co.ankitarora.templatechooser.steps.templateChooser.TemplateChooserViewInterface
import android.content.Context
import android.view.View

open class TestView(context: Context?) : TemplateChooserViewInterface, View(context) {
    override fun showRetryButton() {

    }

    override fun showTemplates(templateDetails: List<TemplateDetails>) {
    }
}