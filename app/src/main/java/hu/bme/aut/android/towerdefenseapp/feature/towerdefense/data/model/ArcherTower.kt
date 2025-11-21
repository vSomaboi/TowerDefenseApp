package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model

import hu.bme.aut.android.towerdefenseapp.R

class ArcherTower() : Tower(
    range = 150f,
    damage = 15,
    fireRate = 1.1f,
    targetType = TargetType.SINGLE_TARGET,
    damageType = DamageType.PHYSICAL,
    iconResourceId = R.drawable.arrow
){
}