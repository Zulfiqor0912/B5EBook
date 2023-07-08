package uz.gita.B5EBook.presentetion.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.B5EBook.data.model.AuthorData
import uz.gita.firebaseexam.databinding.ItemAuthorBinding


class AuthorAdapter : ListAdapter<AuthorData, AuthorAdapter.Holder>(AuthorDiffUtil) {
    private var clickItemListener: ((data: AuthorData) -> Unit)? = null

    inner class Holder(private val binding: ItemAuthorBinding) : ViewHolder(binding.root) {
        init {
            binding.itemAuthor.setOnClickListener {
                clickItemListener?.invoke(currentList[adapterPosition])
            }
        }

        fun bind(data: AuthorData) = with(binding) {
            authorFullName.text = data.fullName

            Glide
                .with(root.context)
                .load(data.imageUrl)
                .into(authorImage)
            Unit


        }
    }

    object AuthorDiffUtil : DiffUtil.ItemCallback<AuthorData>() {
        override fun areItemsTheSame(oldItem: AuthorData, newItem: AuthorData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AuthorData, newItem: AuthorData) =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemAuthorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(currentList[position])
    }


    fun setClickItem(block: (data: AuthorData) -> Unit) {
        clickItemListener = block
    }
}