package karpat.guvencan.contactapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karpat.guvencan.contactapp.data.local.AppDatabase
import karpat.guvencan.contactapp.data.local.ContactDao
import karpat.guvencan.contactapp.data.remote.ContactService
import karpat.guvencan.contactapp.repository.ContactRepository
import karpat.guvencan.contactapp.repository.ContactRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideContactRepository(service: ContactService, appDatabase: AppDatabase, contactDao: ContactDao): ContactRepository = ContactRepositoryImpl(service, appDatabase, contactDao)

}
