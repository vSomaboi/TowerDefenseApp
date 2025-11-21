package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps

import android.graphics.PointF
import hu.bme.aut.android.towerdefenseapp.R

object Map1 {
    fun build() = GameMap(
        id = 1,
        name = "Forest Edge",
        backgroundImageRes = R.drawable.map_1,
        path = emptyList(),
        cavePosition = PointF(0f, 0f),
        gatePosition = PointF(0f, 0f),
        towerSpots = emptyList(),
        waveConfig = WaveConfig(
            waves = emptyList()
        )
    )
}