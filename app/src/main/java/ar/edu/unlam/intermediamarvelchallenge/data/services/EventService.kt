package ar.edu.unlam.intermediamarvelchallenge.data.services
import ar.edu.unlam.intermediamarvelchallenge.data.models.Appearance
import ar.edu.unlam.intermediamarvelchallenge.data.models.Event
import retrofit2.Response
import retrofit2.http.*
import com.google.gson.annotations.SerializedName

interface EventService {

    @GET("events")
    suspend fun getEvents(
        @QueryMap auth: HashMap<String, String>,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<EventResponse>

    @GET("events/{id}/comics")
    suspend fun getComicsByIdEvent(
        @Path ("itemId")itemId:String,auth:HashMap<String,String>):Response<ComicsResponse>
}

data class ComicsResponse(
    val code:Int=0,
    @SerializedName("data")
    val comicsList:ComicsList

)
data class ComicsList(
    @SerializedName("results")
    val comics:List<Appearance>
)
data class EventResponse (
    val code: Int = 0,
    @SerializedName("data")
    val eventsList: EventsList
)

data class EventsList(
    @SerializedName("results")
    val events: List<Event>
)