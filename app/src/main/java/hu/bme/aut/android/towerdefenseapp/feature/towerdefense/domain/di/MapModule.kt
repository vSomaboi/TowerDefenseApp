package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.MapRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @Provides
    @Singleton
    fun provideMapRepository(): MapRepository {
        return MapRepository()
    }
}