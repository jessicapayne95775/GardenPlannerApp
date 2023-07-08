package com.payne.gardenplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.payne.gardenplanner.databinding.PairPlantingLayoutBinding

class PairPlantingActivity : AppCompatActivity() {
    private lateinit var pairAdapter: PairAdapter
    private lateinit var binding: PairPlantingLayoutBinding

    private val initialPlant1Text = "Enter First Plant"
    private val initialPlant2Text = "Enter Second Plant"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PairPlantingLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        pairAdapter = PairAdapter(mutableListOf())

        binding.rvGoodPairs.adapter = pairAdapter
        binding.rvGoodPairs.layoutManager = LinearLayoutManager(this)

        binding.etPlantName1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etPlantName1.text.clear()
            }
        }
        binding.etPlantName2.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etPlantName2.text.clear()
            }
        }

        binding.btnGoodPairing.setOnClickListener {
            val plantName1 = binding.etPlantName1.text.toString()
            val plantName2 = binding.etPlantName2.text.toString()
            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
                val pair = Pair(plantName1, plantName2)
                pairAdapter.addGoodPair(pair)
                binding.etPlantName1.setText(initialPlant1Text)
                binding.etPlantName2.setText(initialPlant2Text)
            }
        }

        binding.rvBadPairs.adapter = pairAdapter
        binding.rvBadPairs.layoutManager = LinearLayoutManager(this)

        binding.btnBadPairing.setOnClickListener {
            val plantName1 = binding.etPlantName1.text.toString()
            val plantName2 = binding.etPlantName2.text.toString()
            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
                val pair = Pair(plantName1, plantName2)
                pairAdapter.addBadPair(pair)
                binding.etPlantName1.setText(initialPlant1Text)
                binding.etPlantName2.setText(initialPlant2Text)
            }
        }
    }
}
//    private lateinit var pairAdapter: PairAdapter
//    private lateinit var binding: PairPlantingLayoutBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = PairPlantingLayoutBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        pairAdapter = PairAdapter(mutableListOf())
//
//        binding.rvGoodPairs.adapter = pairAdapter
//        binding.rvGoodPairs.layoutManager = LinearLayoutManager(this)
//
//        binding.btnGoodPairing.setOnClickListener {
//            val plantName1 = binding.etPlantName1.text.toString()
//            val plantName2 = binding.etPlantName2.text.toString()
//            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
//                val pair = Pair(plantName1, plantName2)
//                pairAdapter.addGoodPair(pair)
//                binding.etPlantName1.text.clear()
//                binding.etPlantName2.text.clear()
//            }
//        }
//
//        binding.rvBadPairs.adapter = pairAdapter
//        binding.rvBadPairs.layoutManager = LinearLayoutManager(this)
//
//        binding.btnBadPairing.setOnClickListener {
//            val plantName1 = binding.etPlantName1.text.toString()
//            val plantName2 = binding.etPlantName2.text.toString()
//            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
//                val pair = Pair(plantName1, plantName2)
//                pairAdapter.addBadPair(pair)
//                binding.etPlantName1.text.clear()
//                binding.etPlantName2.text.clear()
//            }
//        }
//    }
//}
//import kotlinx.android.synthetic.main.pair_planting_layout.btnBadPairing
//import kotlinx.android.synthetic.main.pair_planting_layout.btnGoodPairing
//import kotlinx.android.synthetic.main.pair_planting_layout.etPlantName1
//import kotlinx.android.synthetic.main.pair_planting_layout.etPlantName2
//import kotlinx.android.synthetic.main.pair_planting_layout.rvBadPairs
//import kotlinx.android.synthetic.main.pair_planting_layout.rvGoodPairs
//
//class PairPlantingActivity : AppCompatActivity(){
//    private lateinit var pairAdapter: PairAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.pair_planting_layout)
//        pairAdapter = PairAdapter(mutableListOf())
//
//        rvGoodPairs.adapter = pairAdapter
//        rvGoodPairs.layoutManager = LinearLayoutManager(this)
//
//        btnGoodPairing.setOnClickListener {
//            val plantName1 = etPlantName1.text.toString()
//            val plantName2 = etPlantName2.text.toString()
//            if(plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
//                val pair = Pair(plantName1, plantName2)
//                pairAdapter.addGoodPair(pair)
//                etPlantName1.text.clear()
//                etPlantName2.text.clear()
//            }
//        }
//        rvBadPairs.adapter = pairAdapter
//        rvBadPairs.layoutManager = LinearLayoutManager(this)
//
//        btnBadPairing.setOnClickListener {
//            val plantName1 = etPlantName1.text.toString()
//            val plantName2 = etPlantName2.text.toString()
//            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
//                val pair = Pair(plantName1, plantName2)
//                pairAdapter.addBadPair(pair)
//                etPlantName1.text.clear()
//                etPlantName2.text.clear()
//            }
//        }
//    }
//}
