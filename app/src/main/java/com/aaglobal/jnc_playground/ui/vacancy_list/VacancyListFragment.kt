package com.aaglobal.jnc_playground.ui.vacancy_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.core.ui.DisplayableItem
import com.aaglobal.jnc_playground.databinding.FragmentVacancyListBinding
import com.aaglobal.jnc_playground.di.GlobalDI
import com.aaglobal.jnc_playground.ui.vacancy_list.adapter.VacancyItem
import com.aaglobal.jnc_playground.ui.vacancy_list.adapter.vacancyItemAdapter
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class VacancyListFragment : Fragment(R.layout.fragment_vacancy_list) {

    private var _binding: FragmentVacancyListBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARGS_VACANCY_LIST_TYPE = "args_type"

        fun newInstance(vacancyType: String, vacancyListRouterSource: VacancyListRouterSource): VacancyListFragment {
            GlobalDI.putVacancyListRouterSource(vacancyListType = vacancyType, vacancyListRouterSource)

            return VacancyListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGS_VACANCY_LIST_TYPE, vacancyType)
                }
            }
        }
    }

    private val delegationAdapter by lazy {
        ListDelegationAdapter<List<DisplayableItem>>(
            AdapterDelegatesManager<List<DisplayableItem>>().apply {
                addDelegate(
                    vacancyItemAdapter { item, sharedView ->
                        routerSource.navigateToVacancyScreen(item = item, sharedView = sharedView)
                    }
                )
            }
        )
    }

    private val vacancyListType: String get() = requireArguments().getString(ARGS_VACANCY_LIST_TYPE).orEmpty()
    private val routerSource: VacancyListRouterSource by lazy {
        requireNotNull(GlobalDI.fetchVacancyListRouterSource(vacancyListType))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentVacancyListBinding.bind(view)

        binding.fragmentVacancyListRecycler.adapter = delegationAdapter
        binding.fragmentVacancyListTextTitle.text = vacancyListType

        // Заглушка: наполняем список тестовыми данными
        val mockVacancies: List<DisplayableItem> = List(10) { index ->
            VacancyItem(
                id = "id_$index",
                name = "Vacancy #$index"
            )
        }
        delegationAdapter.items = mockVacancies
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Важно: обнуляем adapter, чтобы RecyclerView не удерживал ссылки
        binding.fragmentVacancyListRecycler.adapter = null
        _binding = null
    }
}