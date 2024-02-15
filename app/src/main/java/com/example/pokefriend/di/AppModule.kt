package com.example.pokefriend.di

import android.content.Context
import com.example.location.AndroidLocationManager
import com.example.location.AppLocationManager
import com.example.navigator.AppNavigator
import com.example.navigator.DefaultNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppNavigator(): AppNavigator = DefaultNavigatorImp()

    @Provides
    @Singleton
    fun provideLocationManager(@ApplicationContext context: Context): AppLocationManager =
        AndroidLocationManager(context)
}