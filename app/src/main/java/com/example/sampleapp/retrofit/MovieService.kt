package com.example.sampleapp.retrofit

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
//"https://api.themoviedb.org/3/movie/upcoming?api_key=93852933922b9db90a3b1f240b5d5d96&language=ko-KR&page=1"
interface MovieService {
    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") api_key : String="93852933922b9db90a3b1f240b5d5d96",
        @Query("language") language : String="en-US", //korean: ko-KR
        @Query("page") page : Int =1
    ): Call<UpComingMovie>
}