package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model

import hu.bme.aut.android.towerdefenseapp.R

abstract class Tower(
    var spotId: Int = -1,
    var xCoordinate: Float = 0f,
    var yCoordinate: Float = 0f,
    open var type: TowerType = TowerType.ARCHER,
    open var range: Float = 100f,
    open var damage: Int = 10,
    open var fireRate: Float = 1f,
    open var targetType: TargetType = TargetType.SINGLE_TARGET,
    open var damageType: DamageType = DamageType.PHYSICAL,
)
enum class TowerType(
    var iconResourceId: Int = R.drawable.arrow,
    var modelResourceId: Int = R.drawable.archer_tower
){
    ARCHER(
        iconResourceId = R.drawable.arrow,
        modelResourceId = R.drawable.archer_tower
    ),
    MORTAR,
    MAGE,
    BARRACKS
}
enum class TargetType {
    SINGLE_TARGET,
    SPLASH
}
enum class DamageType {
    PHYSICAL,
    MAGIC
}