package hu.bme.aut.android.towerdefenseapp.feature.mainmenu

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.towerdefenseapp.ui.theme.easyColor
import hu.bme.aut.android.towerdefenseapp.ui.theme.hardColor
import hu.bme.aut.android.towerdefenseapp.ui.theme.mediumColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(

) : ViewModel() {
    private val _selectedDifficulty = MutableStateFlow(DifficultyOption())
    val selectedDifficulty: StateFlow<DifficultyOption> = _selectedDifficulty.asStateFlow()

    private val _showDifficultyDialog = MutableStateFlow(false)
    val showDifficultyDialog: StateFlow<Boolean> = _showDifficultyDialog.asStateFlow()

    val difficultyOptions = listOf(
        DifficultyOption(
            level = Difficulty.EASY,
            title = "Easy",
            description = "Gentle introduction to tower defense",
            color = easyColor
        ),
        DifficultyOption(
            level = Difficulty.MEDIUM,
            title = "Medium",
            description = "Balanced challenge for experienced players",
            color = mediumColor
        ),
        DifficultyOption(
            level = Difficulty.HARD,
            title = "Hard",
            description = "Intense battle for expert defenders",
            color = hardColor
        )
    )

    fun onDifficultySelected(difficulty: Difficulty) {
        val selectedOption = difficultyOptions.find { it.level == difficulty }
        selectedOption?.let {
            _selectedDifficulty.value = it
        }
        _showDifficultyDialog.value = false
    }

    fun toggleDifficultyDialog() {
        _showDifficultyDialog.value = !_showDifficultyDialog.value
    }
}

enum class Difficulty {
    EASY, MEDIUM, HARD
}
data class DifficultyOption(
    val level: Difficulty = Difficulty.EASY,
    val title: String = "Easy",
    val description: String = "Gentle introduction to tower defense",
    val color: Color = easyColor
)