package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model

abstract class Enemy(
    var xCoordinate: Float = 0f,
    var yCoordinate: Float = 0f,
    open var speed: Float = 1f,
    open var health: Int = 100,
    open var enemyType: EnemyType = EnemyType.GROUND,
    open var resistanceType: ResistanceType = ResistanceType.NONE
) {
    abstract fun move()
    abstract fun takeDamage(damage: Int)
}

enum class EnemyType {
    GROUND,
    FLYING
}

enum class ResistanceType {
    NONE,
    PHYSICAL_RESIST,
    MAGIC_RESIST
}