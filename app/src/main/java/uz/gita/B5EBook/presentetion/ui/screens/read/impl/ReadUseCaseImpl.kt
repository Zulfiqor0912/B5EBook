package uz.gita.B5EBook.presentetion.ui.screens.read.impl

import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.domain.repositories.Repository
import uz.gita.B5EBook.presentetion.ui.screens.read.ReadUseCase
import javax.inject.Inject


class ReadUseCaseImpl @Inject constructor(private val repository: Repository) : ReadUseCase {
    override fun updateBook(data: BookData) {
        repository.updateBook(data)
    }
}