package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import hu.bme.aut.android.towerdefenseapp.R
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap

@Composable
fun GameMapRenderer(
    map: GameMap,
    onMapTap: (Offset) -> Unit = {},
    onScaleComputed: (scaleX: Float, scaleY: Float) -> Unit = { _, _ -> },
    onCanvasSizeComputed: (IntSize) -> Unit = {}
) {
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }
    // Load background image only once
    val bgBitmap = ImageBitmap.imageResource(id = map.backgroundImageRes)
    val towerBaseBitmap = ImageBitmap.imageResource(id = R.drawable.tower_base)
    val caveBitmap = ImageBitmap.imageResource(id = R.drawable.cave)
    val townBitmap = ImageBitmap.imageResource(id = R.drawable.town)

    val towerSpotScale = 1.5f
    val caveScale = 3f
    val townScale = 2f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { newSize ->
                canvasSize = newSize
                onCanvasSizeComputed(newSize)
            }
            .pointerInput(canvasSize) {
                detectTapGestures { tapOffset ->
                    val scaleX = canvasSize.width.toFloat() / bgBitmap.width
                    val scaleY = canvasSize.height.toFloat() / bgBitmap.height
                    onScaleComputed(scaleX, scaleY)
                    val offset = Offset(
                        x = tapOffset.x / scaleX,
                        y = tapOffset.y / scaleY
                    )
                    onMapTap(offset)
                }
            }
    ){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black) // fallback background color
        ) {
            // Screen (canvas) size
            val screenWidth = canvasSize.width.toFloat()
            val screenHeight = canvasSize.height.toFloat()

            // Scale the image to completely fill the screen
            val scaleX = screenWidth / bgBitmap.width
            val scaleY = screenHeight / bgBitmap.height

            withTransform({
                scale(scaleX, scaleY)
            }) {
                drawImage(bgBitmap)
            }

            map.towerSpots.forEach { spot ->
                val screenX = spot.position.x * scaleX
                val screenY = spot.position.y * scaleY

                val imageWidth = towerBaseBitmap.width * scaleX * towerSpotScale
                val imageHeight = towerBaseBitmap.height * scaleY * towerSpotScale

                drawImage(
                    image = towerBaseBitmap,
                    dstOffset = IntOffset(
                        (screenX - imageWidth / 2).toInt(),   // center the image
                        (screenY - imageHeight / 2).toInt()
                    ),
                    dstSize = IntSize(
                        imageWidth.toInt(),
                        imageHeight.toInt()
                    )
                )
            }

            map.cavePosition.let {
                val screenX = it.x * scaleX
                val screenY = it.y * scaleY

                val imageWidth = caveBitmap.width * scaleX * caveScale
                val imageHeight = caveBitmap.height * scaleY * caveScale

                drawImage(
                    image = caveBitmap,
                    dstOffset = IntOffset(
                        (screenX - imageWidth / 2).toInt(),   // center the image
                        (screenY - imageHeight / 2).toInt()
                    ),
                    dstSize = IntSize(
                        imageWidth.toInt(),
                        imageHeight.toInt()
                    )
                )
            }

            map.townPosition.let {
                val screenX = it.x * scaleX
                val screenY = it.y * scaleY

                val imageWidth = townBitmap.width * scaleX * townScale
                val imageHeight = townBitmap.height * scaleY * townScale

                drawImage(
                    image = townBitmap,
                    dstOffset = IntOffset(
                        (screenX - imageWidth / 2).toInt(),   // center the image
                        (screenY - imageHeight / 2).toInt()
                    ),
                    dstSize = IntSize(
                        imageWidth.toInt(),
                        imageHeight.toInt()
                    )
                )
            }
        }
    }
}