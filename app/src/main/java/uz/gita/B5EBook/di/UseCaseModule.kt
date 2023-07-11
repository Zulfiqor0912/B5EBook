package uz.gita.B5EBook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.B5EBook.presentetion.ui.screens.desc.DescUseCase
import uz.gita.B5EBook.presentetion.ui.screens.desc.impl.DescUseCaseImpl
import uz.gita.B5EBook.presentetion.ui.screens.home.HomeUseCase
import uz.gita.B5EBook.presentetion.ui.screens.home.HomeViewModel
import uz.gita.B5EBook.presentetion.ui.screens.home.impl.HomeUseCaseImpl
import uz.gita.B5EBook.presentetion.ui.screens.home.impl.HomeViewModelImpl
import uz.gita.B5EBook.presentetion.ui.screens.read.ReadUseCase
import uz.gita.B5EBook.presentetion.ui.screens.read.impl.ReadUseCaseImpl
import uz.gita.B5EBook.presentetion.ui.screens.saved.SavedUseCase
import uz.gita.B5EBook.presentetion.ui.screens.saved.impl.SavedUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun bindDescUseCase(impl: DescUseCaseImpl):DescUseCase

    @Binds
    fun bindSavedUseCase(impl:SavedUseCaseImpl):SavedUseCase

    @Binds
    fun bindReadViewModel(impl:ReadUseCaseImpl):ReadUseCase

    @Binds
    fun bindHomeViewModel(impl:HomeViewModelImpl):HomeViewModel
}