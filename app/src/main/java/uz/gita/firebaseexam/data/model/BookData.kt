package uz.gita.firebaseexam.data.model

import uz.gita.firebaseexam.data.source.local.entities.BookEntity
import java.io.Serializable

data class BookData(
    val author: String = "",
    val bookUrl: String = "",
    val coverUrl: String = "",
    val genre: String = "",
    val id: Int = 0,
    val page: String = "",
    val rate: Int = 0,
    val reference: String = "",   // kitob nomi .pdf, .jpg lari bilan
    val title: String = "",
    val year: String = "",
    val readerPages: Int = 0,
    val description: String = ""
) : Serializable {
    fun toEntity(): BookEntity = BookEntity(
        author = author,
        bookUrl = bookUrl,
        coverUrl = coverUrl,
        genre = genre,
        id = id,
        page = page,
        rate = rate,
        reference = reference,
        title = title,
        year = year
    )
}