package com.dagger.kotlinmovies

/**
 * Created by harshit on 31/10/17.
 */

val BASE_URL = "https://api.themoviedb.org/3/movie/"
val POPULAR = "popular"
val TOP_RATED = "top_rated"
val API_KEY = "97f469b9e89b30f1f7d07e8b35973e56"
val BASE_URL_IMAGE = "http://image.tmdb.org/t/p/w500/"

data class Response(val results: List<Movie>)

data class Movie(val vote_count: Int, val id: Int, val video: Boolean, val vote_average: Float,
                 val title: String, val poster_path: String, val original_language: String,
                 val original_title: String, val backdrop_path: String, val overview: String, val release_date: String)