package hu.bme.aut.android.towerdefenseapp.feature.mainmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import hu.bme.aut.android.towerdefenseapp.ui.theme.easyColor
import hu.bme.aut.android.towerdefenseapp.ui.theme.hardColor
import hu.bme.aut.android.towerdefenseapp.ui.theme.mediumColor

@Composable
fun MainMenuScreen(
    viewModel: MainMenuViewModel = hiltViewModel(),
    onStartGame: (Int) -> Unit = { }
) {
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsState()
    val difficultyOptions = viewModel.difficultyOptions
    val showDifficultyDialog by viewModel.showDifficultyDialog.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top spacer for status bar
            Spacer(modifier = Modifier.statusBarsPadding())

            // Game Title Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "TOWER DEFENSE",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Defend Your Kingdom",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            // Action Buttons Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Start Game Button
                Button(
                    onClick = { onStartGame(1) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 4.dp
                    )
                ) {
                    Text(
                        text = "START GAME",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Select Difficulty Button
                Button(
                    onClick = { viewModel.toggleDifficultyDialog() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = when(selectedDifficulty.level){
                            Difficulty.EASY -> easyColor.copy(alpha = 0.4f)
                            Difficulty.MEDIUM -> mediumColor.copy(alpha = 0.4f)
                            Difficulty.HARD -> hardColor.copy(alpha = 0.4f)
                        },
                        contentColor = when(selectedDifficulty.level){
                            Difficulty.EASY -> easyColor
                            Difficulty.MEDIUM -> mediumColor
                            Difficulty.HARD -> hardColor
                        }
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 2.dp
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = when(selectedDifficulty.level){
                            Difficulty.EASY -> easyColor
                            Difficulty.MEDIUM -> mediumColor
                            Difficulty.HARD -> hardColor
                        }
                    )
                ) {
                    Text(
                        text = "DIFFICULTY: ${selectedDifficulty.title}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                }
            }

            // Bottom spacer (could be used for version info, etc.)
            Spacer(modifier = Modifier.height(32.dp))
        }
        if(showDifficultyDialog){
            DifficultySelectionModal(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(Float.MAX_VALUE),
                difficultyOptions = difficultyOptions,
                onDifficultySelected = { difficulty -> viewModel.onDifficultySelected(difficulty) },
                onDismiss = { viewModel.toggleDifficultyDialog() }
            )
        }
    }
}