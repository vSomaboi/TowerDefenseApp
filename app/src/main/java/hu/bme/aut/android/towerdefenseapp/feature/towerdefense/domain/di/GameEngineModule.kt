package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GameEngineModule {

    @Binds
    abstract fun bindGameEngineFactory(
        impl: GameEngineFactoryImpl
    ): GameEngineFactory
}