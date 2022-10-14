package com.example.firstexampleapp.model.weight

import java.util.*


data class WeightState(
    val currentDate: String = "${Calendar.getInstance()[Calendar.DAY_OF_MONTH]}/${Calendar.getInstance()[Calendar.MONTH]+1}/${Calendar.getInstance()[Calendar.YEAR]}",
    val week: Int = 0,
    val weight: String = "0.0",//changed to string
    val changeWeight: Double = 0.0
)