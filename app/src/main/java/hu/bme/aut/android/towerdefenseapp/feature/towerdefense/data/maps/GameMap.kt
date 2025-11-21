package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps

import android.graphics.PointF
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Enemy

data class GameMap(
    val id: Int,
    val name: String,
    val backgroundImageRes: Int,
    val path: List<Waypoint>,
    val cavePosition: PointF,   // enemy entry point
    val townPosition: PointF,   // target the enemies attack
    val towerSpots: List<TowerSpot>,
    val waveConfig: WaveConfig
)

data class Waypoint(val x: Float, val y: Float)

data class TowerSpot(
    val position: PointF,
    val radius: Float = 70f
)

data class WaveConfig(
    val waves: List<Wave>
)

data class Wave(
    val id: Int,
    val spawnEntries: List<EnemySpawnEntry>,
    val reward: Int = 0,  // optional: how many coins player gets after completing the wave
    val autoStart: Boolean = true, // if false, player must tap "Start Wave"
)

data class EnemySpawnEntry(
    val enemyType: Enemy,        // Goblin, Orc, Boss, etc.
    val count: Int,                  // number of enemies to spawn
    val spawnInterval: Float = 0.5f, // seconds between each spawn
    val delay: Float = 0f            // delay BEFORE this group starts
)