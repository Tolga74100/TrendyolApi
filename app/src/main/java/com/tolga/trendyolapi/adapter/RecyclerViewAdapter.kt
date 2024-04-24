package com.tolga.trendyolapi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolga.trendyolapi.databinding.RecyclerRowBinding
import com.tolga.trendyolapi.model.Brand
import com.tolga.trendyolapi.model.TrendyolBrandsModel

class RecyclerViewAdapter (private val trendyolList : ArrayList<Brand>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(brand: Brand)
    }

    private val colors: Array<String> = arrayOf(
        "#13bd27",
        "#29c1e1",
        "#b129e1",
        "#d3df13",
        "#f6bd0c",
        "#a1fb93",
        "#0d9de3",
        "#ffe48f"
    )

    class RowHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RowHolder(binding)
    }

    override fun getItemCount(): Int {
        return trendyolList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(trendyolList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.binding.textId.text = trendyolList.get(position).id.toString()
        holder.binding.textName.text = trendyolList.get(position).name

    }
}