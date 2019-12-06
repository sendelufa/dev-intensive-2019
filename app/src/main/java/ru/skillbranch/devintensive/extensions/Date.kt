package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = SECOND * 60


fun Date.formatStd(pattern: String = "HH:mm dd.MM.yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: DateUnit): Date {
    return Date(this.time + value * unit.value)
}

enum class DateUnit(val value: Long) {
    SECOND(1000L),
    MINUTE(SECOND.value * 60),
    HOUR(MINUTE.value * 60),
    DAY(HOUR.value * 24)
}

fun Date.humanizeDiff(){
//TODO учитывать окончания
}