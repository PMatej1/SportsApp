package com.example.prvaapk.data

import com.example.prvaapk.domain.LeagueResponse
import com.example.prvaapk.domain.MatchResponse
import com.example.prvaapk.domain.SeasonResponse
import com.example.prvaapk.domain.TableResponse
import com.example.prvaapk.domain.TeamResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thesportsdb.com/"

/**
 * Create an HTTP logging interceptor
 */
private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

/**
 * Create an OkHttpClient instance with the logging interceptor
 */
private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Gson converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)  // Set the custom OkHttpClient
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface ApiService {
    @GET("api/v1/json/3/search_all_teams.php")
    suspend fun getTeams(@Query("l") league: String = "English Premier League"): TeamResponse

    @GET("api/v1/json/3/eventslast.php")
    suspend fun getMatches(@Query("id") idTeam: String = "133602"): MatchResponse

    @GET("api/v1/json/3/all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

    @GET("api/v1/json/3/search_all_seasons.php")
    suspend fun getAllSeasons(@Query("id") leagueId: String): SeasonResponse

    @GET("api/v1/json/3/lookuptable.php")
    suspend fun getLeagueTable(@Query("l") leagueId: String, @Query("s") season: String): TableResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object SportsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
