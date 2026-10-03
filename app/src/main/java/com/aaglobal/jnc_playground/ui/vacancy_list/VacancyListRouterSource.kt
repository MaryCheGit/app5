package com.aaglobal.jnc_playground.ui.vacancy_list

import android.view.View
import com.aaglobal.jnc_playground.ui.vacancy_list.adapter.VacancyItem

interface VacancyListRouterSource {
    fun navigateToVacancyScreen(item: VacancyItem, sharedView: View)
}