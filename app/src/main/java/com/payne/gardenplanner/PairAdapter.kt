package com.payne.gardenplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pair_item_layout.view.*

class PairAdapter(
    private val pairs: MutableList<Pair>
) : RecyclerView.Adapter<PairAdapter.PairViewHolder>() {

    class PairViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {
        return PairViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pair_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PairViewHolder, position: Int) {
        val curPair = pairs[position]
        holder.itemView.apply {
            tvPlant1.text = curPair.plant1
            tvPlant2.text = curPair.plant2

        }
    }

    fun addGoodPair(pair: Pair) {
        pairs.add(pair)
        notifyItemInserted(pairs.size - 1)
    }

    fun addBadPair(pair: Pair) {
        pairs.add(pair)
        notifyItemInserted(pairs.size - 1)
    }

    override fun getItemCount(): Int {
        return pairs.size
    }
}