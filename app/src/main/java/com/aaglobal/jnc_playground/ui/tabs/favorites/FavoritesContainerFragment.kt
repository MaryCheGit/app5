package com.aaglobal.jnc_playground.ui.tabs.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.core.utils.addFragmentLossIfNotExist
import com.aaglobal.jnc_playground.databinding.FragmentFavoritesContainerBinding
import com.aaglobal.jnc_playground.ui.vacancy_list.VacancyListFragment
import com.aaglobal.jnc_playground.ui.vacancy_list.VacancyListRouterSource
import com.aaglobal.jnc_playground.ui.vacancy_list.adapter.VacancyItem
import com.aaglobal.jnc_playground.vacancy.VacancyFragmentArgs

class FavoritesContainerFragment : Fragment(R.layout.fragment_favorites_container) {

    private var _binding: FragmentFavoritesContainerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavoritesContainerBinding.bind(view)

        binding.fragmentFavoritesContainerButtonOpenDialogs.setOnClickListener {
            findNavController().navigate(resId = R.id.action__FavoritesContainerFragment__to__ABottomSheet)
        }

        addVacancyListFragment()
    }

    private fun addVacancyListFragment() {
        addFragmentLossIfNotExist(
            fragmentManager = childFragmentManager,
            containerViewId = R.id.fragment_favorites_container__container__recommend_vacancies,
            fragment = VacancyListFragment.newInstance(
                vacancyType = "favorites_container",
                vacancyListRouterSource = object : VacancyListRouterSource {
                    override fun navigateToVacancyScreen(item: VacancyItem, sharedView: View) {
                        // ─── Shared Element Transition ───
                        val transitionName = "vacancy_title_${item.id}"
                        val extras = FragmentNavigatorExtras(
                            sharedView to transitionName
                        )

                        findNavController().navigate(
                            resId = R.id.action__FavoritesContainerFragment__to__VacancyFragment,
                            args = VacancyFragmentArgs(vacancyId = "${item.name}|${item.id}").toBundle(),
                            navOptions = null,
                            navigatorExtras = extras
                        )
                    }
                }
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}