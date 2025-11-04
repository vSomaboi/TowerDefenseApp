package hu.bme.aut.android.towerdefenseapp.feature.authentication.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.towerdefenseapp.ui.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val registerCallbacks = object : RegisterModalCallbacks {
        override fun onUsernameChange(username: String) {
            _registerState.value = _registerState.value.copy(
                username = username,
                errorMessage = null
            )
        }

        override fun onPasswordChange(password: String) {
            _registerState.value = _registerState.value.copy(
                password = password,
                errorMessage = null
            )
        }

        override fun onConfirmPasswordChange(confirmPassword: String) {
            _registerState.value = _registerState.value.copy(
                confirmPassword = confirmPassword,
                errorMessage = null
            )
        }

        override fun onTogglePasswordVisibility() {
            _registerState.value = _registerState.value.copy(
                isPasswordVisible = !_registerState.value.isPasswordVisible
            )
        }

        override fun onRegister() {
            if (_registerState.value.username.isBlank() ||
                _registerState.value.password.isBlank() ||
                _registerState.value.confirmPassword.isBlank()
            ) {
                _registerState.value = _registerState.value.copy(
                    errorMessage = "Please fill in all fields"
                )
                return
            }

            if (_registerState.value.password != _registerState.value.confirmPassword) {
                _registerState.value = _registerState.value.copy(
                    errorMessage = "Passwords do not match"
                )
                return
            }

            _registerState.value = _registerState.value.copy(isLoading = true)
            viewModelScope.launch {
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}

interface RegisterModalCallbacks {
    fun onUsernameChange(username: String)
    fun onPasswordChange(password: String)
    fun onConfirmPasswordChange(confirmPassword: String)
    fun onTogglePasswordVisibility()
    fun onRegister()
}

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isRegistrationSuccessful: Boolean = false
)