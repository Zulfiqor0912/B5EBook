package uz.gita.B5EBook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.domain.repositories.impl.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: RepositoryImpl):Repository
}