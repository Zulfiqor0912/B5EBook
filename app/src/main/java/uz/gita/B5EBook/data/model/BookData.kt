package uz.gita.B5EBook.data.model

import android.os.Parcel
import android.os.Parcelable
import uz.gita.B5EBook.data.source.local.entities.BookEntity
import java.io.Serializable

data class BookData(
    val author: String = "",
    val bookUrl: String = "",
    val coverUrl: String = "",
    val genre: String = "",
    val page: String = "",
    val rate: Int = 0,
    val reference: String = "",   // kitob nomi .pdf, .jpg lari bilan
    val title: String = "",
    val year: String = "",
    val readerPages: Int = 0,
    val description: String = "",
    val save: String = "0"
) : Serializable {

    fun toEntity(): BookEntity = BookEntity(
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