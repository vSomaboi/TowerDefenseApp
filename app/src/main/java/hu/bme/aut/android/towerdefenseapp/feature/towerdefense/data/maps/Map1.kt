package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps

import android.graphics.PointF
import hu.bme.aut.android.towerdefenseapp.R

object Map1 {
    fun build() = GameMap(
        id = 1,
        name = "Forest Edge",
        backgroundImageRes = R.drawable.map_1,
        path = emptyList(),
        cavePosition = PointF(1300f, 0f),
        townPosition = PointF(50f, 500f),
        towerSpots = listOf(
            TowerSpot(PointF(300f, 525f)),
            TowerSpot(PointF(700f, 480f)),
            TowerSpot(PointF(1050f, 450f)),
            TowerSpot(PointF(1500f, 400f)),
            TowerSpot(PointF(500f, 850f)),
            TowerSpot(PointF(1275f, 800f))
        ),
        waveConfig = WaveConfig(
            waves = emptyList()
        )
    )
}