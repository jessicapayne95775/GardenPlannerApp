package com.payne.gardenplanner
import kotlin.random.Random

class GardeningTips {

        private val tips = listOf(
            "Water your plants thoroughly but less frequently.",
            "Mulch around your plants to help retain moisture and keep weeds down.",
            "Rotate your crops each year to prevent soil-borne diseases.",
            "Use companion planting to naturally control pests and improve plant growth.",
            "Plant flowers that attract beneficial insects like bees and butterflies.",
            "Prune your plants regularly to promote healthy growth and flowering.",
            "Feed your plants with compost or organic fertilizer for optimal growth.",
            "Harvest your vegetables regularly to encourage continued growth and yield.",
            "Protect your plants from extreme temperatures with shade cloth or frost blankets.",
            "Check your plants for pests and diseases regularly to catch problems early."
        )

        public fun getRandomTip(): String {
            return tips[Random.nextInt(tips.size)]
        }
    }
