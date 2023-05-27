package com.payne.gardenplanner
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat

class GPSMapper(private val context: Context) : LocationListener {

        private val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        private var areaCoordinates: MutableList<Location> = mutableListOf()

        fun startMapping() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
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
//class GPSMapper(private val context: Context) {
//
//    fun getCoordinates(): List<Coordinate> {
//        // Simulated logic to retrieve GPS coordinates
//        // Here you can implement the actual logic to fetch GPS coordinates using the provided context
//        // For demonstration purposes, we'll return some mock coordinates
//        return listOf(
//            Coordinate(37.1234, -122.5678),
//            Coordinate(37.5678, -122.1234),
//            Coordinate(37.9876, -122.4321)
//        )
//    }
//}
//
//data class Point(val latitude: Double, val longitude: Double)
//
//data class Coordinate(val latitude: Double, val longitude: Double)