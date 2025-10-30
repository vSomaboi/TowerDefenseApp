package hu.bme.aut.android.towerdefenseapp.feature.authentication.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    val loginCallbacks = object : LoginModalCallbacks {
        override fun onUsernameChange(username: String) {
            _loginState.value = _loginState.value.copy(
                username = username,
                errorMessage = null
            )
        }

        override fun onPasswordChange(password: String) {
            _loginState.value = _loginState.value.copy(
                password = password,
                errorMessage = null
            )
        }

        override fun onTogglePasswordVisibility() {
            _loginState.value = _loginState.value.copy(
                isPasswordVisible = !_loginState.value.isPasswordVisible
            )
        }

        override fun onLogin() {
            if (_loginState.value.username.isBlank() || _loginState.value.password.isBlank()) {
                _loginState.value = _loginState.value.copy(
                    errorMessage = "Please fill in all fields"
                )
                return
            }

            _loginState.value = _loginState.value.copy(isLoading = true)
        }

        override fun onForgotPassword() {
            // Handle forgot password
            println("Forgot password flow")
        }
    }
}

interface LoginModalCallbacks {
    fun onUsernameChange(username: String)
    fun onPasswordChange(password: String)
    fun onTogglePasswordVisibility()
    fun onLogin()
    fun onForgotPassword()
}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false
)