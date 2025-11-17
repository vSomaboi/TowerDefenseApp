package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Enemy
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Projectile
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import javax.inject.Inject

class TowerManager @Inject constructor() {
    /**
     * Update towers (cooldowns, targeting) and append newly fired projectiles
     * into the provided `projectiles` list.
     */
    fun updateAndFire(
        deltaTime: Float,
        towers: List<Tower>,
        enemies: List<Enemy>,
        projectiles: MutableList<Projectile>
    ) {
        // TODO: update tower timers
        // TODO: select targets from `enemies`
        // TODO: create and add Projectile instances to `projectiles` when firing
    }
}