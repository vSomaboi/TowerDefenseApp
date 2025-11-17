package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps

class MapRepository {
    fun getMaps(): List<GameMap> = listOf(
        //TODO: add the maps here
    )

    fun getMapById(id: Int): GameMap =
        getMaps().first { it.id == id }
}