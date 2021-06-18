package ar.edu.unlam.intermediamarvelchallenge.ui.character

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.intermediamarvelchallenge.ui.base.BaseAdapter
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.data.models.Character
import ar.edu.unlam.intermediamarvelchallenge.databinding.ViewHeroItemBinding


class CharacterAdapter : BaseAdapter<Character, CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            ViewHeroItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_hero_item,
                    parent,
                    false
                )
            ), onClickListener
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CharacterViewHolder(
        private val binding: ViewHeroItemBinding,
        private val onClickListener: ((Character) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) = with(itemView) {
            binding.personaje = item
            binding.itemLayout.setOnClickListener {
                val intent = Intent(it.context, CharacterDetailActivity::class.java)
                intent.putExtra("id", item.id)
                it.context.startActivity(intent)
            }
        }
    }
}