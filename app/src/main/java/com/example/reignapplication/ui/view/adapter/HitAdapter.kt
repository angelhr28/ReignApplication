package com.example.reignapplication.ui.view.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.reignapplication.domain.model.Hit
import com.example.reignapplication.ui.view.viewholder.ItemCardViewHolder

class HitAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<ItemCardViewHolder>() {

    private var items: MutableList<Hit> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Hit>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Hit {
        return this.items[position]
    }

    fun delete(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position);
    }

    override fun getItemCount() = this.items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemCardViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) =
        holder.bind(this.items[position], listener)

}