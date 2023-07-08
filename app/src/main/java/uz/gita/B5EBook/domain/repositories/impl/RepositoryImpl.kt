package uz.gita.B5EBook.domain.repositories.impl

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.source.local.dao.BookDao
import uz.gita.B5EBook.data.source.local.database.BookDatabase
import uz.gita.B5EBook.domain.downloadmanager.AndroidDownloader
import uz.gita.B5EBook.domain.repositories.Repository
import java.io.File

class RepositoryImpl private constructor(
    private val fireStore: FirebaseFirestore = Firebase.firestore,
    private val storageReferences: StorageReference = Firebase.storage.reference,
    private val bookDao: BookDao = BookDatabase.instance().getBookDao()
) : Repository {

    companion object {
        private lateinit var repository: RepositoryImpl

        fun init() {
            if (!::repository.isInitialized) repository = RepositoryImpl()
        }

        fun instance() = repository
    }

    override fun downloadBooks(): Flow<Result<List<BookData>>> = callbackFlow {
        fireStore.collection("books")
            .get()
            .addOnSuccessListener { querySapshot ->
                val list = ArrayList<BookData>()
                querySapshot.forEach { queryDocumentSnapshot ->
                    list.add(
                        BookData(
                            author = queryDocumentSnapshot.get("author") as String,
                            genre = queryDocumentSnapshot.get("ganre") as String,
                            page = queryDocumentSnapshot.get("page") as String,
                            title = queryDocumentSnapshot.get("title") as String,
                            coverUrl = queryDocumentSnapshot.get("imageUrl") as String,
                            bookUrl = queryDocumentSnapshot.get("bookUrl") as String,
                            description = queryDocumentSnapshot.get("info") as String

                        )
                    )
                }
                Log.d("TTT", "${list.size}")
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                Log.d("TTT", "failure")
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun downloadBookData(context: Context, data: BookData): Flow<Result<BookData>> =
        callbackFlow {
            if (File(context.filesDir, data.title).exists()) {
                trySend(Result.success(data))
            } else {
                storageReferences
                    .child(data.reference)
                    .getFile(File(context.filesDir, data.title))
                    .addOnSuccessListener {
                        bookDao.insertBook(data.toEntity())
                        trySend(Result.success(data))
                    }
                    .addOnFailureListener {
                        trySend(Result.failure(it))
                    }
            }
            awaitClose()
        }

    override fun downloadAuthors(): Flow<Result<List<AuthorData>>> = callbackFlow {
        fireStore.collection("authors")
            .get()
            .addOnSuccessListener { querySanpshot ->
                val list = ArrayList<AuthorData>()
                querySanpshot.forEach { queryDocumentSnapshot ->
                    list.add(
                        AuthorData(
                            fullName = queryDocumentSnapshot.get("fullname") as String,
                            imageUrl = queryDocumentSnapshot.get("imageUrl") as String
                        )
                    )

                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }
        awaitClose()
    }

    override fun downloadBook(context: Context, title: String): Flow<Result<File>> = callbackFlow {
        val file = File(context.filesDir, title)
        if (file.exists()) {
            trySend(Result.success(file))
        } else {
            storageReferences.child("books/$title")
                .getFile(file)
                .addOnSuccessListener {
                    trySend(Result.success(file))
                    Log.d("TTTT", "$title.pdf")
                }
                .addOnProgressListener {
                    val progress = it.bytesTransferred * 100 / it.totalByteCount
                    Log.d("TTT", "$progress")
                }
            awaitClose()
        }
    }

    fun downloadBookByDownloader(context: Context, data: BookData): Flow<Result<String>> =
        callbackFlow {
            val download = AndroidDownloader(context)
            download.downloadFile(data.bookUrl)
            awaitClose()
        }
}