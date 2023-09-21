package com.example.a4_1

import android.content.Context
import com.example.a4_1.Constants.PREF_KEY
import java.text.SimpleDateFormat

object Utils {

    fun getDataFromTimestampWithFormat(
        timestamp: String?,
        fromFomatformat: String?,
        toFormatformat: String?
    ): String {
        var data: Data? = null
        var res = ""
        try {
            var fromat = SimpleDateFormat(fromFomatformat)
            data = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        Log.d("park", "getDataFromTimestampWithFormat data >> $data")

        val df = SimpleDateFormat(toFormatformat)
        res = df.format(data)
        return res
    }

    fun saveLastSearch(context: Context, query: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_KEY, query).apply()
    }

    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }
}