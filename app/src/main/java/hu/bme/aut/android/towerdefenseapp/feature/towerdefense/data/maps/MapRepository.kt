package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps

class MapRepository {
    fun getMaps(): List<GameMap> = listOf(
        Map1.build()
    )
    fun getMapById(id: Int): GameMap =
        getMaps().firstOrNull { it.id == id } ?: Map1.build()
}