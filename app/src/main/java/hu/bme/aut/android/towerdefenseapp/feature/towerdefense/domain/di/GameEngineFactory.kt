package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameEngine

interface GameEngineFactory {
    fun create(map: GameMap): GameEngine
}