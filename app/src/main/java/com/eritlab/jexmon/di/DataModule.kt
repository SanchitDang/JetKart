package com.sanapplications.jetkart.di

import com.sanapplications.jetkart.data.demo_db.DemoDB
import com.sanapplications.jetkart.data.repository.ProductRepositoryImp
import com.sanapplications.jetkart.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideProductRepository(demoDB: DemoDB): ProductRepository {
        return ProductRepositoryImp(demoDB)
    }

}