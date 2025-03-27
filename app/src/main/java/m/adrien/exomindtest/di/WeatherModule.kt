package m.adrien.exomindtest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.adrien.exomindtest.data.managerimpl.WeatherDataManagerWebImpl
import m.adrien.exomindtest.domain.datamanager.WeatherDataManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherModule {
    @Singleton
    @Binds
    abstract fun bindWeatherDataManager(
        manager: WeatherDataManagerWebImpl
    ): WeatherDataManager
}