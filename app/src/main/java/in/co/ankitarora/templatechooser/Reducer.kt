package `in`.co.ankitarora.templatechooser

interface Reducer {
    fun reduce(state: State, event: Event): State
}
