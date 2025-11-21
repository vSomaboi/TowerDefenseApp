package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.MapRepository
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di.GameEngineFactory
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameEngine
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val mapRepository: MapRepository,
    private val engineFactory: GameEngineFactory
) : ViewModel() {
    private var engine: GameEngine? = null

    val gameState: StateFlow<GameState>?
        get() = engine?.state

    val map: GameMap?
        get() = engine?.map

    fun loadMap(mapId: Int) {
        val map = mapRepository.getMapById(mapId)
        engine = engineFactory.create(map)
    }
}