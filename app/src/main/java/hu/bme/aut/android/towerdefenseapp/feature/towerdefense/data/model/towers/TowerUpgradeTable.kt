package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.towers

object TowerUpgradeTable {
    val archerUpgrades = listOf(
        TowerUpgradeStats(damage = 15, range = 150f, fireRate = 1.3f),
        TowerUpgradeStats(damage = 19, range = 170f, fireRate = 1.2f),
        TowerUpgradeStats(damage = 27, range = 190f, fireRate = 1.1f)
    )
}

data class TowerUpgradeStats(
    val damage: Int,
    val range: Float,
    val fireRate: Float
)