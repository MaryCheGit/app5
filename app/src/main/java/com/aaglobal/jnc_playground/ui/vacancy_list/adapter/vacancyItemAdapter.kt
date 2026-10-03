package com.aaglobal.jnc_playground.ui.vacancy_list.adapter

import android.view.View
import android.widget.TextView
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.core.ui.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

fun vacancyItemAdapter(
    clickListener: (VacancyItem, View) -> Unit
) = adapterDelegate<VacancyItem, DisplayableItem>(R.layout.item__vacancy) {

    val container: View = itemView.findViewById(R.id.item_vacancy__container)
    val nameText: TextView = itemView.findViewById(R.id.item_vacancy__text__name)

    container.setOnClickListener {
        clickListener.invoke(item, nameText)
    }

    bind {
        nameText.text = "${item.name} | id: ${item.id}"
        nameText.transitionName = "vacancy_title_${item.id}"
    }
}