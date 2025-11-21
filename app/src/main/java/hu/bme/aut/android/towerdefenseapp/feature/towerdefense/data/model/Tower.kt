package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model

import hu.bme.aut.android.towerdefenseapp.R

abstract class Tower(
    var xCoordinate: Float = 0f,
    var yCoordinate: Float = 0f,
    open var range: Float = 100f,
    open var damage: Int = 10,
    open var fireRate: Float = 1f,
    open var targetType: TargetType = TargetType.SINGLE_TARGET,
    open var damageType: DamageType = DamageType.PHYSICAL,
    open var iconResourceId: Int = R.drawable.arrow
) {
}

enum class TargetType {
    SINGLE_TARGET,
    SPLASH
}

enum class DamageType {
    PHYSICAL,
    MAGIC
}