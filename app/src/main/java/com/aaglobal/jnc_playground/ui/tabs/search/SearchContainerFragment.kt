package com.aaglobal.jnc_playground.ui.tabs.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.company.CompanyDetailsFragment.Companion.COMPANY_FLOW_RESULT_FLAG
import com.aaglobal.jnc_playground.core.utils.addFragmentLossIfNotExist
import com.aaglobal.jnc_playground.databinding.FragmentSearchContainerBinding
import com.aaglobal.jnc_playground.ui.vacancy_list.VacancyListFragment
import com.aaglobal.jnc_playground.ui.vacancy_list.VacancyListRouterSource
import com.aaglobal.jnc_playground.ui.vacancy_list.adapter.VacancyItem
import com.aaglobal.jnc_playground.vacancy.VacancyFragment
import com.aaglobal.jnc_playground.vacancy.VacancyFragmentArgs

class SearchContainerFragment : Fragment(R.layout.fragment_search_container) {

    private var _binding: FragmentSearchContainerBinding? = null
    private val binding get() = _binding!!

    /**
     * Определяет, является ли текущий экран планшетом (≥600dp ширины).
     * На планшете используется layout-sw600dp/fragment_search_container.xml,
     * где есть контейнер для деталей.
     */
    private val isTablet: Boolean
        get() = resources.configuration.screenWidthDp >= 600

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSearchContainerBinding.bind(view)

        // Read result from nested navigation flow
        val companyFlowResult = findNavController().currentBackStackEntry
            ?.savedStateHandle
            ?.remove<Boolean>(COMPANY_FLOW_RESULT_FLAG)

        binding.fragmentSearchContainerTextCompanyFlowResult.text =
            "Company flow result: ${companyFlowResult?.toString()}"

        binding.fragmentSearchContainerButtonOpenCompanyFlow.setOnClickListener {
            findNavController().navigate(resId = R.id.action__SearchContainerFragment__to__CompanyFlow)
        }

        addVacancyListFragment()
    }

    private fun addVacancyListFragment() {
        addFragmentLossIfNotExist(
            fragmentManager = childFragmentManager,
            containerViewId = R.id.fragment_search_container__container__recommend_vacancies,
            fragment = VacancyListFragment.newInstance(
                vacancyType = "search_container",
                vacancyListRouterSource = object : VacancyListRouterSource {
                    override fun navigateToVacancyScreen(item: VacancyItem, sharedView: View) {
                        if (isTablet) {
                            // ─── ПЛАНШЕТ: показываем детали справа, в том же экране ───
                            showDetailsInRightPane(item)
                        } else {
                            // ─── ТЕЛЕФОН: как раньше — переход на отдельный экран с анимацией ───
                            navigateToVacancyScreenPhone(item, sharedView)
                        }
                    }
                }
            )
        )
    }

    /**
     * Планшет: встраиваем VacancyFragment в правый контейнер.
     * Без навигации, без анимации — просто заменяем фрагмент.
     */
    private fun showDetailsInRightPane(item: VacancyItem) {
        val detailsFragment = VacancyFragment().apply {
            arguments = VacancyFragmentArgs(
                vacancyId = "${item.name}|${item.id}"
            ).toBundle()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_search_container__container__details, detailsFragment)
            .commit()
    }

    /**
     * Телефон: стандартная навигация с shared element transition.
     */
    private fun navigateToVacancyScreenPhone(item: VacancyItem, sharedView: View) {
        val transitionName = "vacancy_title_${item.id}"
        val extras = FragmentNavigatorExtras(
            sharedView to transitionName
        )

        findNavController().navigate(
            resId = R.id.action__SearchContainerFragment__to__VacancyFragment,
            args = VacancyFragmentArgs(vacancyId = "${item.name}|${item.id}").toBundle(),
            navOptions = null,
            navigatorExtras = extras
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}