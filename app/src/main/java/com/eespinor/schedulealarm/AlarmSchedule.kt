package com.eespinor.schedulealarm

interface AlarmSchedule {
    fun schedule(item: AlarmItem)
    fun cancel(item: AlarmItem)
}