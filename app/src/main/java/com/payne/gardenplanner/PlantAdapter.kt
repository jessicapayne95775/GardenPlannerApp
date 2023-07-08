package com.payne.gardenplanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.payne.gardenplanner.databinding.PlantItemLayoutBinding

class PlantAdapter(
    private val plants: MutableList<Plant>
) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    class PlantViewHolder(val binding: PlantItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PlantItemLayoutBinding.inflate(inflater, parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val curPlant = plants[position]
        val binding = holder.binding

        binding.apply {
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
                if (tvSunRequirement.visibility == View.VISIBLE) {
                    tvSunRequirement.visibility = View.GONE
                } else {
                    tvSunRequirement.visibility = View.VISIBLE
                }
                if (tvWaterRequirement.visibility == View.VISIBLE) {
                    tvWaterRequirement.visibility = View.GONE
                } else {
                    tvWaterRequirement.visibility = View.VISIBLE
                }
                if (tvSoilDepth.visibility == View.VISIBLE) {
                    tvSoilDepth.visibility = View.GONE
                } else {
                    tvSoilDepth.visibility = View.VISIBLE
                }
                if (tvSunRequirementsTitle.visibility == View.VISIBLE) {
                    tvSunRequirementsTitle.visibility = View.GONE
                } else {
                    tvSunRequirementsTitle.visibility = View.VISIBLE
                }
                if (tvWaterRequirementsTitle.visibility == View.VISIBLE) {
                    tvWaterRequirementsTitle.visibility = View.GONE
                } else {
                    tvWaterRequirementsTitle.visibility = View.VISIBLE
                }
                if (tvSoilDepthTitle.visibility == View.VISIBLE) {
                    tvSoilDepthTitle.visibility = View.GONE
                } else {
                    tvSoilDepthTitle.visibility = View.VISIBLE
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
//import kotlinx.android.synthetic.main.plant_item_layout.view.*
//
//class PlantAdapter(
//    private val plants: MutableList<Plant>
//) : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
//
//    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
//        return PlantViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.plant_item_layout,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
//        val curPlant = plants[position]
//        holder.itemView.apply {
//            tvPlantName.text = curPlant.title
//            tvPlantDescription.text = curPlant.description
//            tvSunRequirement.text = curPlant.sunRequirement
//            tvSoilDepth.text = curPlant.soilDepth
//            tvWaterRequirement.text = curPlant.wateringRequirement
//
//            btnDropDown.setOnClickListener {
//                if (tvPlantDescription.visibility == View.VISIBLE) {
//                    tvPlantDescription.visibility = View.GONE
//                } else {
//                    tvPlantDescription.visibility = View.VISIBLE
//                }
//            }
//        }
//    }
//
//    fun addPlant(plant: Plant) {
//        plants.add(plant)
//        notifyItemInserted(plants.size - 1)
//    }
//
//    override fun getItemCount(): Int {
//        return plants.size
//    }
//}