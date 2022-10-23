package com.example.model.domain

import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class FollowInfo(
    val followsList: List<EachFollowInfo>,
    val total: String,
    val cursor: String? = null
)

data class EachFollowInfo(
    val followedAt: String,
    val dateForSort : DateForSort,
    val fromId: String,
    val fromLogin: String,
    val fromName: String,
    val toId: String,
    val toLogin: String,
    val toName: String
)

data class DateForSort(
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minutes: Int
) {
    companion object {
        fun utcToDate(utcTime: String): DateForSort {
            val dateTime = OffsetDateTime.parse(utcTime)
            val zoneId = ZoneId.of("Asia/Tokyo")
            val jtc = dateTime.atZoneSameInstant(zoneId).toOffsetDateTime()
            val formatterYear = DateTimeFormatter.ofPattern("yyyy")
            val formatterMonth = DateTimeFormatter.ofPattern("MM")
            val formatterDate = DateTimeFormatter.ofPattern("dd")
            val formatterHours = DateTimeFormatter.ofPattern("HH")
            val formatterMin = DateTimeFormatter.ofPattern("mm")


            return DateForSort(
                jtc.format(formatterYear).toInt(),
                jtc.format(formatterMonth).toInt(),
                jtc.format(formatterDate).toInt(),
                jtc.format(formatterHours).toInt(),
                jtc.format(formatterMin).toInt(),
            )
        }

    }
}
