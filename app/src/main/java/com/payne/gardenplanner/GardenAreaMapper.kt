package com.payne.gardenplanner
import android.content.Context
import android.graphics.Point

class GardenAreaMapper(private val context: Context) {

    private val gpsMapper: GPSMapper = GPSMapper(context)

    fun mapGardenArea(): List<Point> {
        // Simulated logic to retrieve garden area coordinates using GPSMapper
        val coordinates = gpsMapper.getAreaCoordinates()
        // Simulated logic to convert coordinates to Points
        val points = coordinates.map { coordinate ->
            Point(coordinate.latitude.toInt(), coordinate.longitude.toInt())
        }
        return points
    }
}