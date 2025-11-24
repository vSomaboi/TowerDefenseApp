package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.towers

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.DamageType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TargetType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType

class MageTower(
    spotId: Int = -1,
    xCoordinate: Float = 0f,
    yCoordinate: Float = 0f
) : Tower(
    spotId = spotId,
    xCoordinate = xCoordinate,
    yCoordinate = yCoordinate,
    type = TowerType.MAGE,
    range = 170f,
    damage = 25,
    fireRate = 0.8f,
    targetType = TargetType.SINGLE_TARGET,
    damageType = DamageType.MAGIC
)