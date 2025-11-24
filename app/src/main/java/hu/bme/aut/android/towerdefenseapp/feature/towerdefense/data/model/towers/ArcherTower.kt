package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.towers

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.DamageType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TargetType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType

class ArcherTower(
    spotId: Int = -1,
    xCoordinate: Float = 0f,
    yCoordinate: Float = 0f
) : Tower(
    spotId = spotId,
    xCoordinate = xCoordinate,
    yCoordinate = yCoordinate,
    type = TowerType.ARCHER,
    range = 150f,
    damage = 15,
    fireRate = 1.1f,
    targetType = TargetType.SINGLE_TARGET,
    damageType = DamageType.PHYSICAL
)