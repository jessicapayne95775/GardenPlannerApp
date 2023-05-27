package com.payne.gardenplanner

class PlantCompatibility
{

    private val goodPlantPairs = mutableListOf<Pair<String, String>>()
    private val badPlantPairs = mutableListOf<Pair<String, String>>()

    fun addGoodPair(plant1: String, plant2: String) {
        goodPlantPairs.add(Pair(plant1, plant2))
    }

    fun addBadPair(plant1: String, plant2: String) {
        badPlantPairs.add(Pair(plant1, plant2))
    }

    fun isGoodPair(plant1: String, plant2: String): Boolean {
        return goodPlantPairs.contains(Pair(plant1, plant2)) || goodPlantPairs.contains(Pair(plant2, plant1))
    }

    fun isBadPair(plant1: String, plant2: String): Boolean {
        return badPlantPairs.contains(Pair(plant1, plant2)) || badPlantPairs.contains(Pair(plant2, plant1))
    }
}