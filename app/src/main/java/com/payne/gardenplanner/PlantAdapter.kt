package com.payne.gardenplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.plant_item_layout.view.*

class PlantAdapter(
    private val plants: MutableList<Plant>
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.plant_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val curPlant = plants[position]
        holder.itemView.apply {
            tvPlantName.text = curPlant.title
            tvPlantDescription.text = curPlant.description
            tvSunRequirement.text = curPlant.sunRequirement
            tvSoilDepth.text = curPlant.soilDepth
            tvWaterRequirement.text = curPlant.wateringRequirement

            btnDropDown.setOnClickListener {
                if (tvPlantDescription.visibility == View.VISIBLE) {
                    tvPlantDescription.visibility = View.GONE
                } else {
                    tvPlantDescription.visibility = View.VISIBLE
                }
            }
        }
    }

    fun addPlant(plant: Plant) {
        plants.add(plant)
        notifyItemInserted(plants.size - 1)
    }

    override fun getItemCount(): Int {
        return plants.size
    }
}