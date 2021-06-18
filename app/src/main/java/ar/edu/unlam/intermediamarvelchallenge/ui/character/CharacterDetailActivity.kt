package ar.edu.unlam.intermediamarvelchallenge.ui.character


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unlam.intermediamarvelchallenge.R
import ar.edu.unlam.intermediamarvelchallenge.data.models.Character
import ar.edu.unlam.intermediamarvelchallenge.ui.event.ComicsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.android.viewmodel.ext.android.viewModel



class CharacterDetailActivity : AppCompatActivity() {
private val viewModel:CharacterDetailViewModel by viewModel()
    private lateinit var adapter:ComicsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)
        btn_close.setOnClickListener {
            this@CharacterDetailActivity.finish()
        }
     if(intent.hasExtra(ID)){
            getCharacter(intent.extras!!.getInt(ID,0))
        }else{
            Toast.makeText(this@CharacterDetailActivity,
                "Ha ocurrido un error en la respuesta",Toast.LENGTH_LONG).show()
        }
    }
private fun getCharacter(idCharacter: Int){
        viewModel.loadCharacter(idCharacter)
    viewModel._characters.observe(this, Observer { setInfoCharacter(it) })
}

    private fun setInfoCharacter(it: Character) {

        setImage(it.thumbnail.path,it.thumbnail.extension)
        name_character.text=it.name
        checkDescriptionContent(it.description)

        adapter=ComicsAdapter(it.comics.appearances.toMutableList())
        list_comics.layoutManager= LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        if (adapter.itemCount>0) {
            list_comics.adapter = adapter
            empty_recycler.visibility= View.GONE

        }else{
            empty_recycler.visibility=View.VISIBLE

        }
    }

    private fun setImage(path:String, extension:String){

        val url="${path}.${extension}"

        Picasso.get()
            .load(url.replace("http", "https"))
            .into(image_character_thumbnail_detail)

    }
    private fun checkDescriptionContent(description:String){
        if (description.isNotEmpty()){
            character_description.text=description
        }else{
            character_description.text=getString(R.string.no_description_message)
        }


    }

    companion object{
    val ID:String="id"
}

}