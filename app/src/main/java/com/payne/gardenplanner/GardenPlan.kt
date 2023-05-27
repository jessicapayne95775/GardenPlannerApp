package com.payne.gardenplanner

class GardenPlan (val name: String, val layout: String, val plants: MutableList<Plant>) {
    fun addPlant(plant: Plant) {
        plants.add(plant)
    }
    fun removePlant(plant: Plant) {
        plants.remove(plant)
    }
}