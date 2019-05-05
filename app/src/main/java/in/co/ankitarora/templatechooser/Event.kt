package `in`.co.ankitarora.templatechooser

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails

sealed class Event {
    data class TemplateDataLoaded(val templateDetails: List<TemplateDetails>) : Event()
    object LoadData : Event()
    object NavigateToTemplateChooserScreen : Event()
    object GetTemplateData : Event()
    object OnTemplateLoadError : Event()
}
