package `in`.co.ankitarora.templatechooser

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails

interface Action {
    data class TemplateDataLoaded(val templateDetails: List<TemplateDetails>) : Action

    object ShowCurrentScreen: Action
    object GetTemplatesData : Action
    object ShowErrorScreen : Action
}
