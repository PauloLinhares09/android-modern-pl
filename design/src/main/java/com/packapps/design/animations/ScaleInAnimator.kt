package com.packapps.design.animations

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class ScaleInAnimator : DefaultItemAnimator() {

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        holder.itemView.scaleX = 0f
        holder.itemView.scaleY = 0f
        holder.itemView.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(2000) // Definindo a duração da animação para 2000ms
            .setListener(null)
        return true
    }
}
