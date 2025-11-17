package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Enemy
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Projectile
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameEngine internal constructor(
    private val map: GameMap,
    private val waveManager: WaveManager,
    private val collisionManager: CollisionManager,
    private val towerManager: TowerManager
) {
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> get() = _state.asStateFlow()

    private val enemies = mutableListOf<Enemy>()
    private val towers = mutableListOf<Tower>()
    private val projectiles = mutableListOf<Projectile>()

    private var gameLoopJob: Job? = null
    private var lastUpdateTime = 0L

    fun start() {
        if (gameLoopJob != null) return
        gameLoopJob = CoroutineScope(Dispatchers.Default).launch {
            lastUpdateTime = System.currentTimeMillis()
            while (isActive && _state.value.gameState == GameStates.RUNNING) {
                val now = System.currentTimeMillis()
                val deltaTime = (now - lastUpdateTime) / 1000f
                update(deltaTime)
                lastUpdateTime = now
                delay(16L) // ~60 FPS
            }
        }
    }

    fun stop(){
        gameLoopJob?.cancel()
        gameLoopJob = null
    }

    private fun update(deltaTime: Float){
        // Update wave spawning and enemy movement
        waveManager.updateAndSpawnEnemies(deltaTime, enemies)

        // Update towers, targeting, and projectile firing
        towerManager.updateAndFire(deltaTime, towers, enemies, projectiles)

        // Detect collisions and remove destroyed entities
        collisionManager.updateCollisions(deltaTime, enemies, projectiles)
    }
}

enum class GameStates { RUNNING, PAUSED, GAME_OVER, VICTORY }

data class GameState(
    val enemies: List<Enemy> = emptyList(),
    val towers: List<Tower> = emptyList(),
    val projectiles: List<Any> = emptyList(),
    val gameState: GameStates = GameStates.PAUSED
)