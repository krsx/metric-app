package com.capstone.metricapp.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateKeypoints(inputDateString: String): String {
        if (inputDateString.isEmpty()) {
            return ""
        }

        val inputFormat = DateTimeFormatter.ISO_INSTANT
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        val instant = Instant.parse(inputDateString)

        val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())
        val outputDate = outputFormat.format(Date.from(zonedDateTime.toInstant()))

        val monthAbbreviation = arrayOf(
            "",
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Ags",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
        )
        val monthNumber = zonedDateTime.monthValue

        val formattedMonth = if (monthNumber < 10) {
            "0${monthNumber}"
        } else {
            monthNumber.toString()
        }

        return outputDate.replace("MM", monthAbbreviation[formattedMonth.toInt()])
    }
}