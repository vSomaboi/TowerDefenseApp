package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Enemy
import javax.inject.Inject

class WaveManager @Inject constructor(

) {
    /**
     * Update existing enemies (movement, states) and spawn new enemies into the
     * provided `enemies` list as waves according to timers/logic.
     */
    fun updateAndSpawnEnemies(deltaTime: Float, enemies: MutableList<Enemy>) {
        // TODO: update position/state of `enemies`
        // TODO: check wave timers and add new Enemy instances to `enemies`
    }
}