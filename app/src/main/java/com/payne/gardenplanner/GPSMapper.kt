package com.payne.gardenplanner
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class GPSMapper {

    class GardenAreaMapper(private val context: Context) : LocationListener {
        private val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        private var areaCoordinates: MutableList<Location> = mutableListOf()

        fun startMapping() {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }

        fun stopMapping() {
            locationManager.removeUpdates(this)
        }

        fun getAreaCoordinates(): List<Location> {
            return areaCoordinates.toList()
        }

        override fun onLocationChanged(location: Location) {
            areaCoordinates.add(location)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            // Not implemented
        }

        override fun onProviderEnabled(provider: String) {
            // Not implemented
        }

        override fun onProviderDisabled(provider: String) {
            // Not implemented
        }
    }
}