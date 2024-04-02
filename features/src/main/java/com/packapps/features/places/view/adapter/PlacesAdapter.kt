package com.packapps.features.places.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.packapps.features.R
import com.packapps.features.places.model.data.PlaceViewData

class PlacesAdapter(private val placesList: List<PlaceViewData>) : RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.place_cardview, parent, false)
        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placesList[position]
        // Configure o holder com os dados do lugar
        holder.tvPlaceName.text = place.venueName
        holder.tvPriceRange.text = place.priceRange
        holder.tvRating.text = place.userRating.toString()
        holder.tvDistance.text = place.distance.toString()
    }

    override fun getItemCount() = placesList.size

    class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlaceName: TextView = itemView.findViewById(R.id.venueNameTextView)
        val tvPriceRange: TextView = itemView.findViewById(R.id.priceRangeTextView)
        val tvRating: TextView = itemView.findViewById(R.id.userRatingTextView)
        val tvDistance: TextView = itemView.findViewById(R.id.distanceTextView)

    }
}
