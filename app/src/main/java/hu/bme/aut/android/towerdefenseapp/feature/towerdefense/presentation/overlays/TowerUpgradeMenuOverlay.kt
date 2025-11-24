package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation.overlays

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
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
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType
import kotlin.math.roundToInt

@Composable
fun TowerUpgradeMenuOverlay(
    spot: TowerSpot,
    tower: Tower,
    scaleX: Float,
    scaleY: Float,
    onLevelUpgraded: () -> Unit,
    onSpecSelected: () -> Unit,
    onDismiss: () -> Unit
) {
    val density = LocalDensity.current

    val centerX = (spot.position.x * scaleX).toInt()
    val centerY = (spot.position.y * scaleY).toInt()

    val rangePx = tower.range * scaleX

    val iconSize = 48.dp
    val iconSizePx = with(density){
        iconSize.toPx()
    }
    val spacingSize = 16.dp
    val spacingPx = with(density){
        spacingSize.toPx()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onDismiss)
    ){
        Box(
            modifier = Modifier
                .requiredSize(with(density){ rangePx.toDp() * 2 })
                .absoluteOffset{ IntOffset(
                    (centerX - rangePx).roundToInt(),
                    (centerY - rangePx).roundToInt()
                )}
                .background(color = Color.Blue.copy(alpha = 0.2f), shape = CircleShape)
        )
        if(tower.level < 3){
            Image(
                painter = painterResource(R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(iconSize)
                    .absoluteOffset{ IntOffset(
                            (centerX - iconSizePx.div(2)).roundToInt(),
                            (centerY - iconSizePx - spacingPx.div(2)).roundToInt()
                    )}
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.DarkGray.copy(alpha = 0.8f))
                    .clickable {
                        onLevelUpgraded()
                    }
            )
        }else{

        }
    }
}