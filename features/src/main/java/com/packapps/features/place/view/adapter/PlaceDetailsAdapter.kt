package com.packapps.features.place.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packapps.features.R
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.view.adapter.OnPlaceClickListener

class PlaceDetailsAdapter(
    private val placesList: MutableList<PlaceViewData>,
    private val placeSelected: PlaceViewData
) : RecyclerView.Adapter<PlaceDetailsAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_place_detail, parent, false)
        return DetailsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val place = placesList[position]

//        Glide.with(holder.itemView.context)
//            .load(place.venueProfileImage)
//            .into(holder.ivPlaceBanner)

        holder.tvPlaceName.text = place.venueName

    }

    override fun getItemCount() = placesList.size

    class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlaceName: TextView = itemView.findViewById(R.id.tvPlaceName)


    }
}
