package karpat.guvencan.contactapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import karpat.guvencan.contactapp.data.common.Constant
import karpat.guvencan.contactapp.data.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constant.DB_NAME).build()

    @Singleton
    @Provides
    fun provideContactDao(appDatabase: AppDatabase) = appDatabase.contactDao()

}