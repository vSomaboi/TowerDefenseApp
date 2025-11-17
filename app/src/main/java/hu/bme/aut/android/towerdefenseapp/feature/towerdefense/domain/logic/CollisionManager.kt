package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Enemy
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Projectile
import javax.inject.Inject

class CollisionManager @Inject constructor() {
    /**
     * Check collisions between enemies and projectiles, apply damage or effects,
     * and remove destroyed enemies / used projectiles from the mutable lists.
     */
    fun updateCollisions(deltaTime: Float, enemies: MutableList<Enemy>, projectiles: MutableList<Projectile>) {
        // TODO: iterate projectiles and enemies, detect collisions, apply damage
        // TODO: mark/remove projectiles and enemies as needed
    }
}