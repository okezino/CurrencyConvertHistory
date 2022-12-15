package com.example.currencyconverter.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    const val DATE_TIME_FORMAT = "yyyy-MM-dd"



    fun getDayForPastThreeDays(date : (yesterday : String, twoDaysAgo : String, threeDaysAgo : String) -> Unit){
        val dateFormat: DateFormat = SimpleDateFormat(DATE_TIME_FORMAT)
        val cal: Calendar = Calendar.getInstance()
        val cal2: Calendar = Calendar.getInstance()
        val cal3: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        cal2.add(Calendar.DATE, -2)
        cal3.add(Calendar.DATE, -3)
        dateFormat.format(cal.getTime())
        date(dateFormat.format(cal.getTime()), dateFormat.format(cal2.getTime()), dateFormat.format(cal3.getTime()))
    }
}