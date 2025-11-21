package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.res.imageResource
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap

@Composable
fun GameMapRenderer(map: GameMap) {
    // Load background image only once
    val bgBitmap = ImageBitmap.imageResource(id = map.backgroundImageRes)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // fallback background color
    ) {
        // Screen (canvas) size
        val screenWidth = size.width
        val screenHeight = size.height

        // Scale the image to completely fill the screen
        val scaleX = screenWidth / bgBitmap.width
        val scaleY = screenHeight / bgBitmap.height

        withTransform({
            scale(scaleX, scaleY)
        }) {
            drawImage(bgBitmap)
        }

        // Later, we will add:
        // - drawPath(map.path)
        // - drawTowerSpots(...)
        // - drawEnemies(...)
    }
}