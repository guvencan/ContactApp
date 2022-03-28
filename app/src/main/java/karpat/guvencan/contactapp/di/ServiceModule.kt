package karpat.guvencan.contactapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import karpat.guvencan.contactapp.data.remote.ContactService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideContactService(retrofit: Retrofit): ContactService = retrofit.create(ContactService::class.java)

}