package com.example.prvaapk.data

import com.example.prvaapk.domain.MatchResponse
import com.example.prvaapk.domain.TeamResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/json/3/search_all_teams.php")
    suspend fun getTeams(@Query("l") league: String = "English%20Premier%20League"): TeamResponse

    @GET("api/v1/json/3/eventslast.php")
    suspend fun getMatches(@Query("id") idTeam: String = "133602"): MatchResponse

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}
