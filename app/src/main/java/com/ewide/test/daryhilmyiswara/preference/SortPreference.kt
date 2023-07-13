package com.ewide.test.daryhilmyiswara.preference

import android.content.SharedPreferences

class SortPreference(
    private val preference: SharedPreferences
) {

    companion object {
        private const val KEY_IS_SORT_ASCENDING = "KEY_IS_SORT_ASCENDING"
    }

    fun isSortAscending(): Boolean = preference.getBoolean(KEY_IS_SORT_ASCENDING, true)

    fun setIsSortAscending(isSortAscending: Boolean) {
        preference.edit().putBoolean(KEY_IS_SORT_ASCENDING, isSortAscending).apply()
    }

}