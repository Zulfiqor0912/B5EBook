package uz.gita.B5EBook.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.B5EBook.data.model.BookData

@Entity(tableName = "books")
data class BookEntity(
    val author: String = "",
    val bookUrl: String = "",
    val coverUrl: String = "",
    val genre: String = "",
    val page: String = "",
    val rate: Int = 0,
    val description: String = "",
    val reference: String = "",   // kitob nomi .pdf, .jpg lari bilan
    @PrimaryKey
    val title: String = "",
    val year: String = "",
    val readerPages: Int = 0,
    val save: String = "0"
) {
    fun toData() = BookData(
        author = author,
        bookUrl = bookUrl,
        coverUrl = coverUrl,
        genre = genre,
        page = page,
        rate = rate,
        reference = reference,
        title = title,
        year = year,
        description = description,
        save = save
    )
}