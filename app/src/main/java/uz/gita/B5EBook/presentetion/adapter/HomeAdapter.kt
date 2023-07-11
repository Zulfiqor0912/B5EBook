package uz.gita.B5EBook.presentetion.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.B5EBook.data.model.BookData
import uz.gita.B5EBook.data.source.local.LocalStorage
import uz.gita.firebaseexam.databinding.ItemBookBinding


class HomeAdapter :
    ListAdapter<BookData, HomeAdapter.Holder>(MyDiffUtil) {
    private var clickBookItem: ((data: BookData) -> Unit)? = null
    private val localStorage = LocalStorage.getInstance()

    inner class Holder(private val binding: ItemBookBinding) : ViewHolder(binding.root) {

        init {
            binding.itemBook.setOnClickListener {
                clickBookItem?.invoke(getItem(adapterPosition))
            }
        }


        fun bind() {
            Log.d("TTT", "glide")
            Glide.with(binding.root.context)
                .load(currentList[adapterPosition].coverUrl)
                .into(binding.imageBook)

            binding.apply {
                with(currentList[adapterPosition]) {
                    bookName.text = title
                    bookAuthor.text = author
                    itemPages.text = page
                    val currentPage = localStorage?.getBookPage(title)!!
                    if (localStorage.getBookName()
                            .equals(title) && currentPage != 0 && page != ""
                    ) {
                        guideline.setGuidelinePercent(currentPage.toFloat()/page.toFloat())
                        itemBookCurrentPage.text = localStorage.getBookPage(title).toString()
                        Log.d("PPE", readerPages.toString())
                    } else if (!localStorage.getBookName().equals(title)) {
                        startReadingText.visibility = View.INVISIBLE
                        linearLayout.visibility = View.INVISIBLE
                        itemBookCurrentPage.visibility = View.INVISIBLE
                        of.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<BookData>() {
        override fun areItemsTheSame(oldItem: BookData, newItem: BookData) =
            oldItem.title == newItem.title || oldItem.reference == newItem.reference

        override fun areContentsTheSame(oldItem: BookData, newItem: BookData) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        return holder.bind()
    }


    fun setClickBookItem(block: (data: BookData) -> Unit) {
        clickBookItem = block
    }
}