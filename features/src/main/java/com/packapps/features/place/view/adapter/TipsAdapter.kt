package com.packapps.features.place.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.packapps.features.R
import com.packapps.features.place.model.PlaceDetailViewData

class TipsAdapter(tips: List<PlaceDetailViewData.Tip>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            private val tips = tips

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer_tip, parent, false)
                return TipViewHolder(view)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val tip = tips[position]
                (holder as TipViewHolder).bind(tip)
            }

            override fun getItemCount(): Int {
                return tips.size
            }

            class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

                fun bind(tip: PlaceDetailViewData.Tip) {
                    itemView.findViewById<TextView>(R.id.tvCretaedAt).text = tip.createdAt
                    itemView.findViewById<TextView>(R.id.tvCustomerReview).text = tip.text

                    val ivCustomPhoto = itemView.findViewById<ImageView>(R.id.ivCustomerPhoto)
                    Glide.with(itemView.context)
                        .load("https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50")
                        .into(ivCustomPhoto)

                }
            }

}
