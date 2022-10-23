package com.example.core.util

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


object DateUtil {

    fun utcToJtc(utcTime: String): String {
        val dateTime = OffsetDateTime.parse(utcTime)
        val zoneId = ZoneId.of("Asia/Tokyo")
        val jtc = dateTime.atZoneSameInstant(zoneId).toOffsetDateTime()
        val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")
        return jtc.format(formatter)
    }

    fun utcToDate(utcTime: String): Date {
        val dateTime = OffsetDateTime.parse(utcTime)
        val zoneId = ZoneId.of("Asia/Tokyo")
        val jtc = dateTime.atZoneSameInstant(zoneId).toOffsetDateTime()
        val formatterYear = DateTimeFormatter.ofPattern("yyyy")
        val formatterMonth = DateTimeFormatter.ofPattern("MM")
        val formatterDate = DateTimeFormatter.ofPattern("dd")
        val formatterHours = DateTimeFormatter.ofPattern("HH")
        val formatterMin = DateTimeFormatter.ofPattern("mm")


        return Date(
            jtc.format(formatterYear).toInt(),
            jtc.format(formatterMonth).toInt(),
            jtc.format(formatterDate).toInt(),
            jtc.format(formatterHours).toInt(),
            jtc.format(formatterMin).toInt(),
        )
    }

    fun format(time: String): String {
        val a = time.replace("h", "時間")
        val b = a.replace("m", "分")
        return b.replace("s", "秒")
    }
}