package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di

import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.ArcherTower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType

object TowerFactory {
    fun create(
        type: TowerType,
        spot: TowerSpot
    ): Tower {
        return when(type){
            TowerType.ARCHER -> ArcherTower(
                spotId = spot.id,
                xCoordinate = spot.position.x,
                yCoordinate = spot.position.y
            )
            else -> ArcherTower(
                spotId = spot.id,
                xCoordinate = spot.position.x,
                yCoordinate = spot.position.y
            )
        }
    }
}