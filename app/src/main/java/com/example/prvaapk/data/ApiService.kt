package com.example.prvaapk.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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


private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiService {


    @GET("api/v1/json/3/eventslast.php")
    suspend fun getMatches(@Query("id") idTeam: String = "133602"): MatchResponse

    @GET("api/v1/json/3/all_leagues.php")
    suspend fun getAllLeagues(): LeagueResponse

    @GET("api/v1/json/3/search_all_seasons.php")
    suspend fun getAllSeasons(@Query("id") leagueId: String): SeasonResponse

    @GET("api/v1/json/3/lookuptable.php")
    suspend fun getLeagueTable(@Query("l") leagueId: String, @Query("s") season: String): TableResponse
}

object SportsApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
