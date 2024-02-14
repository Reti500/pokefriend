package com.example.pokefriend.di

import com.example.navigator.AppNavigator
import com.example.navigator.DefaultNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator = DefaultNavigatorImp()
}