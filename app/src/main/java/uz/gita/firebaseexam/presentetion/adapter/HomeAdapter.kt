package uz.gita.firebaseexam.presentetion.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.firebaseexam.data.model.BookData
import uz.gita.firebaseexam.databinding.ItemBookBinding


class HomeAdapter : ListAdapter<BookData, HomeAdapter.Holder>(MyDiffUtil) {
    private var clickBookItem: ((data: BookData) -> Unit)? = null

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
                }
            }
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<BookData>() {
        override fun areItemsTheSame(oldItem: BookData, newItem: BookData) =
            oldItem.id == newItem.id

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

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind()


    fun setClickBookItem(block: (data: BookData) -> Unit) {
        clickBookItem = block
    }
}