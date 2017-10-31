package com.dagger.kotlinmovies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import okhttp3.*
import okhttp3.Response
import java.io.IOException

/**
 * Created by harshit on 31/10/17.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)
        val okhttpClient = OkHttpClient()
        val request = Request.Builder().url(BASE_URL + POPULAR + "?api_key=" + API_KEY).build()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewMovies)
        recyclerView.layoutManager = GridLayoutManager(context,2)


        okhttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
                val gson = Gson()
                val movieList = gson.fromJson(response!!.body()!!.string(), com.dagger.kotlinmovies.Response::class.java)
                val movieAdapter = MovieAdapter(movieList.results)
                recyclerView.post {
                    recyclerView.adapter = movieAdapter
                }
            }

        })

        return view
    }
}