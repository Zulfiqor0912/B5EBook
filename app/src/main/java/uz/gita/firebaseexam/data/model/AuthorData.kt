package uz.gita.firebaseexam.data.model

data class AuthorData(
    val id: Int = 0,
    val fullName: String,
    val description: String = "",
    val imageUrl: String
)