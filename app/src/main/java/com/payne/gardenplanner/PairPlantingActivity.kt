package com.payne.gardenplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.pair_planting_layout.btnBadPairing
import kotlinx.android.synthetic.main.pair_planting_layout.btnGoodPairing
import kotlinx.android.synthetic.main.pair_planting_layout.etPlantName1
import kotlinx.android.synthetic.main.pair_planting_layout.etPlantName2
import kotlinx.android.synthetic.main.pair_planting_layout.rvBadPairs
import kotlinx.android.synthetic.main.pair_planting_layout.rvGoodPairs

class PairPlantingActivity : AppCompatActivity(){
    private lateinit var pairAdapter: PairAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pair_planting_layout)
        pairAdapter = PairAdapter(mutableListOf())

        rvGoodPairs.adapter = pairAdapter
        rvGoodPairs.layoutManager = LinearLayoutManager(this)

        btnGoodPairing.setOnClickListener {
            val plantName1 = etPlantName1.text.toString()
            val plantName2 = etPlantName2.text.toString()
            if(plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
                val pair = Pair(plantName1, plantName2)
                pairAdapter.addGoodPair(pair)
                etPlantName1.text.clear()
                etPlantName2.text.clear()
            }
        }
        rvBadPairs.adapter = pairAdapter
        rvBadPairs.layoutManager = LinearLayoutManager(this)

        btnBadPairing.setOnClickListener {
            val plantName1 = etPlantName1.text.toString()
            val plantName2 = etPlantName2.text.toString()
            if (plantName1.isNotEmpty() && plantName2.isNotEmpty()) {
                val pair = Pair(plantName1, plantName2)
                pairAdapter.addBadPair(pair)
                etPlantName1.text.clear()
                etPlantName2.text.clear()
            }
        }
    }
}
