package `in`.co.ankitarora.templatechooser.steps.templateChooser

sealed class TemplateChooserViewEvents {
    object GetTemplateData : TemplateChooserViewEvents()
    data class VariantSelected(val variant: String) : TemplateChooserViewEvents()
}
