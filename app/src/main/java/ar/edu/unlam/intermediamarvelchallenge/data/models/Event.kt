package ar.edu.unlam.intermediamarvelchallenge.data.models
import ar.edu.unlam.intermediamarvelchallenge.data.models.Thumbnail
import com.google.gson.annotations.SerializedName

data class Event(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val start: String = "",
    val thumbnail: Thumbnail=Thumbnail(),
    val comics:Appearances=Appearances()
)

