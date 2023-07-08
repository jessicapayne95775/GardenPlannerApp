package com.payne.gardenplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.payne.gardenplanner.databinding.MainLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var plantAdapter: PlantAdapter
    private lateinit var gardeningTipsGenerator: GardeningTips
    private lateinit var binding: MainLayoutBinding

    private val initialPlantNameText = "Enter Plant Name"
    private val initialPlantDescriptionText = "Enter Description"
    private val initialSunRequirementText = "Enter Sun Requirements"
    private val initialWaterRequirementText = "Water Requirements"
    private val initialSoilDepthText = "Enter Soil Depth"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        plantAdapter = PlantAdapter(mutableListOf())
        binding.rvPlantList.adapter = plantAdapter
        binding.rvPlantList.layoutManager = LinearLayoutManager(this)

        binding.etPlantName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etPlantName.text.clear()
            }
        }
        binding.etPlantDescription.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etPlantDescription.text.clear()
            }
        }
        binding.etSoilDepth.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etSoilDepth.text.clear()
            }
        }
        binding.etSunRequirement.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etSunRequirement.text.clear()
            }
        }
        binding.etWaterRequirement.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etWaterRequirement.text.clear()
            }
        }

        binding.btnAddPlant.setOnClickListener {
            val plantName = binding.etPlantName.text.toString()
            val plantDescription = binding.etPlantDescription.text.toString()
            val plantSun = binding.etSunRequirement.text.toString()
            val plantWater = binding.etWaterRequirement.text.toString()
            val plantSoilDepth = binding.etSoilDepth.text.toString()

            if (plantName.isNotEmpty()) {
                val plant = Plant(plantName, plantDescription, plantSoilDepth, plantSun, plantWater)
                plantAdapter.addPlant(plant)
                binding.etPlantName.setText(initialPlantNameText)
                binding.etPlantDescription.setText(initialPlantDescriptionText)
                binding.etSunRequirement.setText(initialSunRequirementText)
                binding.etWaterRequirement.setText(initialWaterRequirementText)
                binding.etSoilDepth.setText(initialSoilDepthText)
            }
        }

        binding.btnTips.setOnClickListener {
            val randomTip = GardeningTips().getRandomTip()
            binding.tvGardeningTips.text = randomTip
        }

        binding.btnPairPlanting.setOnClickListener {
            val intent = Intent(this, PairPlantingActivity::class.java)
            startActivity(intent)
        }
    }
}

//class com.payne.gardenplanner.MainActivity : AppCompatActivity() {
//
//    private lateinit var plantAdapter : PlantAdapter
//    private lateinit var gardeningTips: GardeningTips
//    private lateinit var textView: TextView
//    private lateinit var btnDropDown: Button
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_layout)
//        plantAdapter = PlantAdapter(mutableListOf())
//
//        rvPlantList.adapter = plantAdapter
//        rvPlantList.layoutManager = LinearLayoutManager(this)
//
//        btnAddPlant.setOnClickListener {
//            val plantName = etPlantName.text.toString()
//            val plantDescription = etPlantDescription.text.toString()
//            val plantSun = etSunRequirement.text.toString()
//            val plantWater = etWaterRequirement.text.toString()
//            val plantSoilDepth = etSoilDepth.text.toString()
//            if(plantName.isNotEmpty() && plantDescription.isNotEmpty()) {
//                val plant = Plant(plantName, plantDescription, plantSoilDepth, plantSun, plantWater)
//                plantAdapter.addPlant(plant)
//                etPlantName.text.clear()
//                etPlantDescription.text.clear()
//                etSunRequirement.text.clear()
//                etWaterRequirement.text.clear()
//                etSoilDepth.text.clear()
//            }
//        }
//
//        btnTips.setOnClickListener {
//            val randomTip = GardeningTips().getRandomTip()
//            tvGardeningTips.text = randomTip
//        }
//
//        btnPairPlanting.setOnClickListener {
//            val intent = Intent(this, PairPlantingActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//}

//
//    private val CHANNEL_ID = "channel_id"
//    private val NOTIFICATION_ID = 1
//
//    private lateinit var gardeningTipsGenerator: GardeningTips
//
//    private lateinit var plantCompatibilityChecker: PlantCompatibility
//
//    private lateinit var locationManager: LocationManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        gardeningTipsGenerator = GardeningTips
//        ()
//        plantCompatibilityChecker = PlantCompatibility
//        ()
//        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        // Add some example good and bad plant pairs
//        plantCompatibilityChecker.addGoodPair("Tomato", "Basil")
//        plantCompatibilityChecker.addGoodPair("Marigold", "Cabbage")
//        plantCompatibilityChecker.addBadPair("Cucumber", "Potato")
//        plantCompatibilityChecker.addBadPair("Rose", "Garlic")
//
//        // Set up button to display a random gardening tip
//        val tipButton = findViewById<Button>(R.id.tip_button)
//        tipButton.setOnClickListener {
//            val tip = gardeningTipsGenerator.getRandomTip()
//            Toast.makeText(this, tip, Toast.LENGTH_SHORT).show()
//        }
//
//        // Set up button to check compatibility of two plants
//        val compatibilityButton = findViewById<Button>(R.id.compatibility_button)
//        compatibilityButton.setOnClickListener {
//            val plant1 = "Tomato"
//            val plant2 = "Basil"
//            val isGoodPair = plantCompatibilityChecker.isGoodPair(plant1, plant2)
//            val message = if (isGoodPair) "$plant1 and $plant2 are good companions!" else "$plant1 and $plant2 should not be planted together."
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//        }
//
//        // Set up button to check user location
//        val locationButton = findViewById<Button>(R.id.location_button)
//        locationButton.setOnClickListener {
//            if (isLocationEnabled()) {
//                val location = getLocation()
//                val message = "Your location is: (${location.latitude}, ${location.longitude})"
//                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//            } else {
//                requestLocationEnabled()
//            }
//        }
//
//        // Set up button to create a notification
//        val notificationButton = findViewById<Button>(R.id.notification_button)
//        notificationButton.setOnClickListener {
//            createNotification()
//        }
//    }
//
//    private fun isLocationEnabled(): Boolean {
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//    }
//
//    private fun getLocation(): Location {
//        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
//        }
//
//        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
//    }
//
//    private fun requestLocationEnabled() {
//        Toast.makeText(this, "Please enable location services.", Toast.LENGTH_SHORT).show()
//        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//        startActivity(intent)
//    }
//
//    private fun createNotification() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Notification Channel"
//            val description = "Example Notification Channel"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                this.description = description
//            }
//            val notificationManager = getSystemService(NotificationManager::class.java)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_notification)
//            .setContentTitle("Garden Care Notification")
//            .setContentText("Remember to water your plants!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)) {
//            notify(NOTIFICATION_ID, notificationBuilder.build())
//        }
//    }
//}

//class GardenPlannerActivity : AppCompatActivity() {
//
//    private lateinit var gardenPlan: GardenPlan
//    private lateinit var plantListAdapter: PlantListAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_garden_planner)
//
//        // Initialize the garden plan and the plant list adapter
//        gardenPlan = GardenPlan("My Garden", "", mutableListOf())
//        plantListAdapter = PlantListAdapter(gardenPlan.plants)
//
//        // Set up the RecyclerView to display the plant list
//        plantListRecyclerView.apply {
//            layoutManager = LinearLayoutManager(this@GardenPlannerActivity)
//            adapter = plantListAdapter
//        }
//
//        // Set up a button to add a new plant
//        addPlantButton.setOnClickListener {
//            val intent = Intent(this@GardenPlannerActivity, AddPlantActivity::class.java)
//            startActivityForResult(intent, ADD_PLANT_REQUEST_CODE)
//        }
//    }
//
//    // Handle the result of adding a new plant
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == ADD_PLANT_REQUEST_CODE && resultCode == RESULT_OK) {
//            val newPlant = data?.getParcelableExtra<Plant>(EXTRA_PLANT) ?: return
//            gardenPlan.addPlant(newPlant)
//            plantListAdapter.notifyDataSetChanged()
//        }
//    }
//
//    // Set up a button to remove a plant
//    fun removePlant(plant: Plant) {
//        gardenPlan.removePlant(plant)
//        plantListAdapter.notifyDataSetChanged()
//    }
//
//    companion object {
//        const val ADD_PLANT_REQUEST_CODE = 1
//        const val EXTRA_PLANT = "extra_plant"
//    }
//}

