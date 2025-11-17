package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.CollisionManager
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameEngine
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.TowerManager
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.WaveManager
import javax.inject.Inject

class GameEngineFactoryImpl @Inject constructor(
    private val waveManager: WaveManager,
    private val collisionManager: CollisionManager,
    private val towerManager: TowerManager
) : GameEngineFactory {

    override fun create(map: GameMap): GameEngine {
        return GameEngine(
            map = map,
            waveManager = waveManager,
            collisionManager = collisionManager,
            towerManager = towerManager
        )
    }
}