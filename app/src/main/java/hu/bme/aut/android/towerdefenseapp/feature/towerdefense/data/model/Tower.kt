package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model

import hu.bme.aut.android.towerdefenseapp.R
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.towers.TowerUpgradeStats

abstract class Tower(
    var spotId: Int = -1,
    var xCoordinate: Float = 0f,
    var yCoordinate: Float = 0f,
    var level: Int = 1,
    open var type: TowerType = TowerType.ARCHER,
    open var range: Float = 100f,
    open var damage: Int = 10,
    open var fireRate: Float = 1f,
    open var targetType: TargetType = TargetType.SINGLE_TARGET,
    open var damageType: DamageType = DamageType.PHYSICAL,
){
    fun applyUpgradeStats(upgradeStats: TowerUpgradeStats){
        this.damage = upgradeStats.damage
        this.range = upgradeStats.range
        this.fireRate = upgradeStats.fireRate
    }
}
enum class TowerType(
    val iconResourceId: Int = R.drawable.arrow,
    val modelResourceIds: List<Int> = listOf(R.drawable.archer_tower)
){
    ARCHER(
        iconResourceId = R.drawable.arrow,
        modelResourceIds = listOf(
            R.drawable.archer_tower
        )
    ),
    MORTAR,
    MAGE(
        iconResourceId = R.drawable.wand,
        modelResourceIds = listOf(
            R.drawable.mage_tower
        )
    ),
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