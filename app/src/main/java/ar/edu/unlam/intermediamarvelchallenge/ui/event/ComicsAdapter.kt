package ar.edu.unlam.intermediamarvelchallenge.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.data.models.Appearance
import ar.edu.unlam.intermediamarvelchallenge.databinding.ViewComicItemBinding
import ar.edu.unlam.intermediamarvelchallenge.ui.base.BaseAdapter
import kotlinx.android.synthetic.main.activity_character_detail.view.*


class ComicsAdapter(private val children: List<Appearance>) : BaseAdapter<Appearance, ComicsAdapter.ComicViewHolder>() {
    var comicList = children.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder =
        ComicViewHolder(
            ViewComicItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_comic_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comicList[position])
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    class ComicViewHolder(
        private val binding: ViewComicItemBinding,
        private val onClickListener: ((Appearance) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Appearance) = with(itemView) {
            binding.titleIssue.text = item.name
            binding.yearIssue.text = item.modified
            binding.root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }

    }


}