package ar.edu.unlam.intermediamarvelchallenge.ui.character

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.databinding.FragmentCharacterBinding
import ar.edu.unlam.intermediamarvelchallenge.utils.Status
import ar.edu.unlam.intermediamarvelchallenge.utils.hide
import ar.edu.unlam.intermediamarvelchallenge.utils.isVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_hero_item.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CharacterFragment : Fragment() {
    private lateinit var binding:FragmentCharacterBinding
    private val viewModel: CharacterViewModel by sharedViewModel( )
    private val adapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews()
        binding = FragmentCharacterBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.isVisible()
        adapter.clear()
        setupCharactersList()
        setupPagination()
    }

    private fun setupPagination() {
        binding.listCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadMoreCharacters(adapter.itemCount)
                    viewModel.state.observe(viewLifecycleOwner,{statusCharacter(it)})
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }


    private fun setupCharactersList() {
        binding.listCharacters.adapter = adapter
        viewModel.characters.observe(viewLifecycleOwner, { characters ->
            adapter.addMissingItems(characters)
            adapter.updatedItems(characters)
            viewModel.state.observe(this.viewLifecycleOwner,{statusCharacter(it)})
        })
    }
    private fun statusCharacter(stat: Status) {
        when (stat) {
            Status.SUCCESS -> {Toast.makeText(this.context,
                getString(R.string.ok), Toast.LENGTH_SHORT).show()
                    binding.progressBar.hide()        }
            Status.ERROR -> Toast.makeText(this.context,
                getString(R.string.bad), Toast.LENGTH_SHORT).show()
        }
    }
}
