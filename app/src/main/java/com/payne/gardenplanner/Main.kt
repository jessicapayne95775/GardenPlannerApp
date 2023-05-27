import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_garden_planner.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id"
    private val NOTIFICATION_ID = 1

    private lateinit var gardeningTipsGenerator: GardeningTipsGenerator
    private lateinit var plantCompatibilityChecker: PlantCompatibilityChecker
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gardeningTipsGenerator = GardeningTipsGenerator()
        plantCompatibilityChecker = PlantCompatibilityChecker()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Add some example good and bad plant pairs
        plantCompatibilityChecker.addGoodPair("Tomato", "Basil")
        plantCompatibilityChecker.addGoodPair("Marigold", "Cabbage")
        plantCompatibilityChecker.addBadPair("Cucumber", "Potato")
        plantCompatibilityChecker.addBadPair("Rose", "Garlic")

        // Set up button to display a random gardening tip
        val tipButton = findViewById<Button>(R.id.tip_button)
        tipButton.setOnClickListener {
            val tip = gardeningTipsGenerator.getRandomTip()
            Toast.makeText(this, tip, Toast.LENGTH_SHORT).show()
        }

        // Set up button to check compatibility of two plants
        val compatibilityButton = findViewById<Button>(R.id.compatibility_button)
        compatibilityButton.setOnClickListener {
            val plant1 = "Tomato"
            val plant2 = "Basil"
            val isGoodPair = plantCompatibilityChecker.isGoodPair(plant1, plant2)
            val message = if (isGoodPair) "$plant1 and $plant2 are good companions!" else "$plant1 and $plant2 should not be planted together."
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        // Set up button to check user location
        val locationButton = findViewById<Button>(R.id.location_button)
        locationButton.setOnClickListener {
            if (isLocationEnabled()) {
                val location = getLocation()
                val message = "Your location is: (${location.latitude}, ${location.longitude})"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            } else {
                requestLocationEnabled()
            }
        }

        // Set up button to create a notification
        val notificationButton = findViewById<Button>(R.id.notification_button)
        notificationButton.setOnClickListener {
            createNotification()
        }
    }

    private fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocation(): Location {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
    }

    private fun requestLocationEnabled() {
        Toast.makeText(this, "Please enable location services.", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Channel"
            val description = "Example Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Garden Care Notification")
            .setContentText("Remember to water your plants!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }
}

class GardenPlannerActivity : AppCompatActivity() {

    private lateinit var gardenPlan: GardenPlan
    private lateinit var plantListAdapter: PlantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garden_planner)

        // Initialize the garden plan and the plant list adapter
        gardenPlan = GardenPlan("My Garden", "", mutableListOf())
        plantListAdapter = PlantListAdapter(gardenPlan.plants)

        // Set up the RecyclerView to display the plant list
        plantListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@GardenPlannerActivity)
            adapter = plantListAdapter
        }

        // Set up a button to add a new plant
        addPlantButton.setOnClickListener {
            val intent = Intent(this@GardenPlannerActivity, AddPlantActivity::class.java)
            startActivityForResult(intent, ADD_PLANT_REQUEST_CODE)
        }
    }

    // Handle the result of adding a new plant
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PLANT_REQUEST_CODE && resultCode == RESULT_OK) {
            val newPlant = data?.getParcelableExtra<Plant>(EXTRA_PLANT) ?: return
            gardenPlan.addPlant(newPlant)
            plantListAdapter.notifyDataSetChanged()
        }
    }

    // Set up a button to remove a plant
    fun removePlant(plant: Plant) {
        gardenPlan.removePlant(plant)
        plantListAdapter.notifyDataSetChanged()
    }

    companion object {
        const val ADD_PLANT_REQUEST_CODE = 1
        const val EXTRA_PLANT = "extra_plant"
    }
}

