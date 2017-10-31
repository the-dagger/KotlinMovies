package com.dagger.kotlinmovies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.google.gson.Gson
import okhttp3.*
import okhttp3.Response
import java.io.IOException

/**
 * Created by harshit on 31/10/17.
 */
class MainFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)


        recyclerView = view.findViewById(R.id.recyclerViewMovies)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        setHasOptionsMenu(true)
        makeNetworkCall(POPULAR)
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_top_rated -> makeNetworkCall(TOP_RATED)
            else -> makeNetworkCall(POPULAR)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeNetworkCall(endpoint: String) {
        val okhttpClient = OkHttpClient()
        val request = Request.Builder().url(BASE_URL + endpoint + "?api_key=" + API_KEY).build()
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
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}