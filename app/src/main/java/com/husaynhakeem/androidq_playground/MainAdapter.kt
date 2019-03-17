package com.husaynhakeem.androidq_playground

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.husaynhakeem.androidq_playground.data.Feature
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter : ListAdapter<Feature, MainAdapter.MainViewHolder>(MainAdapter.MainDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(parent)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.render(getItem(position))

    inner class MainViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)) {

        fun render(feature: Feature) {
            itemView.itemMainTextView.text = itemView.context.getString(feature.labelId)
            itemView.itemMainTextView.setBackgroundColor(ContextCompat.getColor(itemView.context, feature.colorId))
            itemView.setOnClickListener {
                val context = itemView.context
                context.startActivity(Intent(context, feature.destinationClass))
            }
        }
    }

    private class MainDiffUtil : DiffUtil.ItemCallback<Feature>() {
        override fun areItemsTheSame(oldItem: Feature, newItem: Feature) = oldItem.labelId == newItem.labelId

        override fun areContentsTheSame(oldItem: Feature, newItem: Feature) = oldItem == newItem
    }
}