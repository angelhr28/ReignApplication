package com.example.reignapplication.ui.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reignapplication.databinding.ItemCardBinding
import com.example.reignapplication.domain.model.Hit
import com.example.reignapplication.utils.getDate
import java.util.*


class ItemCardViewHolder(private val binding: ItemCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): ItemCardViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCardBinding.inflate(inflater, parent, false)
            return ItemCardViewHolder(binding)
        }
    }

    fun bind(hit: Hit, listener: (Hit) -> Unit) {
        binding.apply {
            title.text = hit.title
            desc.text = "${hit.author?.capitalize(Locale.ROOT)} - ${hit.createdAt?.getDate()}"
            root.setOnClickListener {
                listener(hit)
            }
        }
    }
}