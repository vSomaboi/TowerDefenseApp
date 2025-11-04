package hu.bme.aut.android.towerdefenseapp.ui.util

sealed class UiEvent {
    data object Success : UiEvent()
    data class Notification(val message: String) : UiEvent()
}