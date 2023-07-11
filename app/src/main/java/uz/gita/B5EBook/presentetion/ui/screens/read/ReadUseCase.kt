package uz.gita.B5EBook.presentetion.ui.screens.read

import uz.gita.B5EBook.data.model.BookData

interface ReadUseCase {
    fun updateBook(data:BookData)
}