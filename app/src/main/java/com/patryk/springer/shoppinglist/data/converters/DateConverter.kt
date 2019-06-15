package com.patryk.springer.shoppinglist.data.converters

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Patryk Springer on 2019-06-14.
 */
class DateConverter {

	@TypeConverter
	fun timestampToDate(timestamp: Long?): Date? = if (timestamp == null) null else Date(timestamp)

	@TypeConverter
	fun dateToTimestamp(date: Date?): Long? = date?.time
}