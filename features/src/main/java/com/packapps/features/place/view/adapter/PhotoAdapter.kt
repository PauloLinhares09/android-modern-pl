package com.packapps.features.place.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packapps.features.R
import com.packapps.features.place.model.PlaceDetailViewData

class PhotoAdapter(photos: List<PlaceDetailViewData.Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val photos = photos

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
            return PhotoViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val photo = photos[position]
            (holder as PhotoViewHolder).bind(photo)
        }

        override fun getItemCount(): Int {
            return photos.size
        }

        class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(photo: PlaceDetailViewData.Photo) {
                Glide.with(itemView.context)
                    .load(photo.prefix + "original" + photo.suffix)
                    .into(itemView.rootView as ImageView)
            }
        }

}
