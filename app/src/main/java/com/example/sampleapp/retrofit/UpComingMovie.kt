package com.example.sampleapp.retrofit

import androidx.lifecycle.LiveData
import com.example.sampleapp.retrofit.Movie
import com.google.gson.annotations.SerializedName

data class UpComingMovie(
    @SerializedName("page") val page: String,
    @SerializedName("results") val movieList: List<Movie>
)

data class Movie (
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("release_date") val release_date: String
)