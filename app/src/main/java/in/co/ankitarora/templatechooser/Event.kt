package `in`.co.ankitarora.templatechooser

sealed class Event {
    object LoadData : Event()
    object NavigateToTemplateChooserScreen : Event()
}
