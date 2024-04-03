package com.packapps.features.place.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.packapps.features.R
import com.packapps.features.place.model.PlaceDetailViewData

class PlaceDetailsAdapter(private val detailsList: List<PlaceDetailViewData>) : RecyclerView.Adapter<PlaceDetailsAdapter.DetailsViewHolder>() {

    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Vincule os elementos da view aqui, exemplo:
        // val venueName: TextView = view.findViewById(R.id.venueName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_detail, parent, false)
        return DetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val detail = detailsList[position]
        // Vincule os dados do lugar ao ViewHolder
    }

    override fun getItemCount() = detailsList.size
}
