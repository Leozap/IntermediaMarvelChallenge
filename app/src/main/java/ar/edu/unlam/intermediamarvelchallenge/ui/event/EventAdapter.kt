package ar.edu.unlam.intermediamarvelchallenge.ui.event

import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unlam.intermediamarvelchallenge.ui.base.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.data.models.Appearance
import ar.edu.unlam.intermediamarvelchallenge.data.models.Event
import ar.edu.unlam.intermediamarvelchallenge.databinding.ViewComicItemBinding
import ar.edu.unlam.intermediamarvelchallenge.databinding.ViewEventItemBinding
import kotlinx.android.synthetic.main.activity_character_detail.view.*
import kotlinx.android.synthetic.main.view_comic_item.view.*
import kotlinx.android.synthetic.main.view_event_item.view.*

class EventAdapter() : BaseAdapter<Event, EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            ViewEventItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_event_item,
                    parent,
                    false
                )
            ), onClickListener
        )


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(list[position])

    }


    class EventViewHolder(
        private val binding: ViewEventItemBinding,
        private val onClickListener: ((Event) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Event) = with(itemView) {

            binding.evento = item

            var childlayoutManager = LinearLayoutManager(
                binding.comicsList.context,
                LinearLayoutManager.VERTICAL, false
            )

            binding.comicsList.apply {
                layoutManager = childlayoutManager
                adapter = ComicsAdapter(item.comics.appearances)

                setRecycledViewPool(recycledViewPool)
                binding.btnArrow.setOnClickListener {
                    if (binding.comicsList.visibility == View.VISIBLE) {
                        binding.comicsList.visibility = View.GONE

                        binding.btnArrow.setBackgroundResource(R.drawable.arrow_down)
                    } else {
                        binding.comicsList.visibility = View.VISIBLE
                        binding.btnArrow.setBackgroundResource(R.drawable.arrow_up)
                    }

                }

            }
        }
    }


}






