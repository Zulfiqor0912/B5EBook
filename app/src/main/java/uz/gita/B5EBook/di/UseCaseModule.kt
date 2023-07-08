package uz.gita.B5EBook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.HomeUseCase
import uz.gita.B5EBook.presentetion.ui.screens.home.usecase.impl.HomeUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase
}