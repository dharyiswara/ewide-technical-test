package com.ewide.test.daryhilmyiswara.preference

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PreferenceModule {

    private var sortPreference: SortPreference? = null

    @Provides
    fun provideSortPreference(@ApplicationContext context: Context): SortPreference {
        val preference = sortPreference ?: buildPreference(context)
        if (sortPreference == null) {
            sortPreference = buildPreference(context)
        }
        return preference
    }

    private fun buildPreference(context: Context): SortPreference {
        return SortPreference(
            context.getSharedPreferences(
                "sort-preference", Context.MODE_PRIVATE
            )
        )
    }

}