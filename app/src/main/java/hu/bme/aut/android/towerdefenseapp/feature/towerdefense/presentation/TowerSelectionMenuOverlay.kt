package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import hu.bme.aut.android.towerdefenseapp.R
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType
import kotlin.math.roundToInt

@Composable
fun TowerSelectionMenuOverlay(
    spot: TowerSpot,
    scaleX: Float,
    scaleY: Float,
    onTowerSelected: (TowerType) -> Unit,
    onDismiss: () -> Unit
) {
    val centerX = (spot.position.x * scaleX).toInt()
    val centerY = (spot.position.y * scaleY).toInt()

    val iconSize = 48.dp
    val iconSizePx = with(LocalDensity.current){
        iconSize.toPx()
    }
    val spacingSize = 16.dp
    val spacingPx = with(LocalDensity.current){
        spacingSize.toPx()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onDismiss)
    ) {
        val gridItems = List(4){ it }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .requiredSize(iconSize.times(2).plus(spacingSize))
                .absoluteOffset{ IntOffset(
                    (centerX - iconSizePx - spacingPx.div(2)).roundToInt(),
                    (centerY - iconSizePx - spacingPx.div(2)).roundToInt()
                )},
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(gridItems){ index ->
                Image(
                    painter = painterResource(
                        when(index){
                            1 -> R.drawable.arrow
                            2 -> R.drawable.arrow
                            3 -> R.drawable.arrow
                            4 -> R.drawable.arrow
                            else -> R.drawable.arrow
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(iconSize)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.DarkGray.copy(alpha = 0.8f))
                        .clickable {
                            onTowerSelected(
                                when(index){
                                    1 -> TowerType.ARCHER
                                    2 -> TowerType.ARCHER
                                    3 -> TowerType.ARCHER
                                    4 -> TowerType.ARCHER
                                    else -> TowerType.ARCHER
                                }
                            )
                        }
                )
            }
        }

    }
}