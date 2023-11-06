package com.example.myanimelistcleanarchitecture.presentation.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Calendar
import java.util.Locale
import java.util.logging.SimpleFormatter

object CommonUtils {

    fun getCurrentSeason(): String {
        val calendar = Calendar.getInstance().get(Calendar.MONTH)
        Log.d("CheckingSomething", calendar.plus(1).toString())

        return  when (calendar.plus(1)) {
            in (3..5) -> "spring"
            in(6..8) -> "summer"
            in(9..11) -> "fall"
            12,1,2 -> "winter"
            else -> "summer"
        }
    }

    fun getLastSeason(): String {
        val calendar = Calendar.getInstance().get(Calendar.MONTH)
        Log.d("CheckingSomething", calendar.plus(1).toString())

        return  when (calendar.plus(1)) {
            in (3..5) -> "summer"
            in(6..8) -> "fall"
            in(9..11) -> "winter"
            12,1,2 -> "spring"
            else -> "summer"
        }
    }

    fun getNextSeason(): String {
        val calendar = Calendar.getInstance().get(Calendar.MONTH)
        Log.d("CheckingSomething", calendar.plus(1).toString())

        return  when (calendar.plus(1)) {
            in (3..5) -> "winter"
            in(6..8) -> "spring"
            in(9..11) -> "summer"
            12,1,2 -> "fall"
            else -> "summer"
        }
    }


    fun getCurrentYear(): Int {
        val calendar = Calendar.getInstance().get(Calendar.YEAR)
        Log.d("CheckingSomething", calendar.toString())

        return calendar
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDate(date: String): String {
        var simpleFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
        val splitDate = simpleFormat.parse(date)?.toString()?.split(" ")
        return "${splitDate?.get(0)} ${splitDate?.get(1)} ${splitDate?.get(2)}, ${splitDate?.last()}"
    }
}