package ar.edu.unlam.intermediamarvelchallenge.ui.event


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.databinding.FragmentEventsBinding
import ar.edu.unlam.intermediamarvelchallenge.utils.Status
import ar.edu.unlam.intermediamarvelchallenge.utils.hide
import ar.edu.unlam.intermediamarvelchallenge.utils.isVisible
import ar.edu.unlam.intermediamarvelchallenge.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_event_item.*
import kotlinx.android.synthetic.main.view_event_item.view.*
import kotlinx.android.synthetic.main.view_hero_item.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class EventsFragment : Fragment() {

    private lateinit var binding:FragmentEventsBinding
    private val viewModel: EventViewModel by sharedViewModel( )
    private val adapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            adapter.clear()



        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.isVisible()
        setupEventList()
        //setupPagination()
        adapter.notifyDataSetChanged()

    }


    /*private fun setupPagination() {
        binding.listEvents.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreEvent(adapter.itemCount)
                    viewModel.statusEvent.observe(viewLifecycleOwner,{status(it)})
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }*/
  /*  private fun showComics(){
        if (binding.listEvents.visibility==View.VISIBLE){
        binding.listEvents.visibility=View.GONE}
        else{binding.listEvents.visibility=View.VISIBLE}
    }*/
    private fun status(stat: Status) {
        when (stat) {
            Status.SUCCESS -> {Toast.makeText(this.context,
                getString(R.string.ok), Toast.LENGTH_SHORT).show()
                    binding.progressBar.hide()}
            Status.ERROR -> Toast.makeText(this.context,
                getString(R.string.bad), Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupEventList() {
        adapter.onClickListener = { event ->
            Picasso.get()
                .load(Uri.parse(event.thumbnail.path))
                .fit()
                .into(image_event_thumbnail)
            text_name.text=event.title
            text_description.text=event.description
            viewModel.loadComic(event.id.toString())
        }
        binding.listEvents.adapter = adapter

        viewModel.event.observe(viewLifecycleOwner, { event ->
            viewModel.statusEvent.observe(this.viewLifecycleOwner,{status(it)})
            viewModel.status.observe(this.viewLifecycleOwner,{status(it)})
            adapter.addMissingItems(event)
            adapter.updatedItems(event)

        })
    }
 /*   private fun toggleLoading(hasResults:Boolean=false){
        if(binding.progressBar.isVisible()){
            binding.progressBar.hide()
        }else {
            binding.progressBar.show()
        }
        if(hasResults){
            binding.listEvents.show()
        }else{binding.listEvents.hide()}

    }*/
}