package com.payne.gardenplanner

class Plant(val name: String, val description: String, val photo: String, val plantingDate: Date) {
    fun ageInDays(): Long {
        val now = Calendar.getInstance().timeInMillis
        val plantingTime = plantingDate.time
        return TimeUnit.MILLISECONDS.toDays(now - plantingTime)
    }
}