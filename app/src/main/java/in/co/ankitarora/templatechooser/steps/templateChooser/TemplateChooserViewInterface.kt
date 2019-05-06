package `in`.co.ankitarora.templatechooser.steps.templateChooser

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails

interface TemplateChooserViewInterface {
    fun showRetryButton()
    fun showTemplates(templateDetails: List<TemplateDetails>)

}
