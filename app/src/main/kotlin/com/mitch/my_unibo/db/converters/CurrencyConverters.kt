package com.mitch.my_unibo.db.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class CurrencyConverters {

    @TypeConverter
    fun convertBigDecimal(bigDecimal: BigDecimal): String {
        return bigDecimal.toPlainString()
    }

    @TypeConverter
    fun convertToBigDecimal(string: String): BigDecimal {
        return BigDecimal(string)
    }
}
