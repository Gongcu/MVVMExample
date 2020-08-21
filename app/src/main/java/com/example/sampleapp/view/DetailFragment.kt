package com.example.sampleapp.view

import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sampleapp.R
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {
    val baseUrl ="https://image.tmdb.org/t/p/w500/"
    val twoDepArgs by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.detail_title.text=twoDepArgs.title
        view.detail_release_date.text="(${twoDepArgs.releaseDate})"
        view.detail_overview.text=twoDepArgs.overview
        Glide.with(view.detail_poster.context).load(baseUrl+twoDepArgs.posterPath).error(R.drawable.ic_launcher_background).into(view.detail_poster)
        if(!twoDepArgs.isAdult)
            view.detail_adult.visibility=View.GONE
    }
}