package com.aaglobal.jnc_playground.vacancy

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.vacancy.databinding.FragmentVacancyBinding
import kotlin.random.Random

class VacancyFragment : Fragment(R.layout.fragment_vacancy) {

    private var _binding: FragmentVacancyBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val LOG_TAG = "VacancyFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ─── Shared Element Transition: настройка анимаций ───
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)

        sharedElementReturnTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentVacancyBinding.bind(view)

        // We lose possibility to use delegation `navArgs` =(
        val vacancyId = arguments?.getString("vacancyId")!!

        Log.d(LOG_TAG, "onViewCreated | args: $vacancyId")

        binding.fragmentVacancyTextTitle.text = "Vacancy [id: $vacancyId]"

        // ─── Shared Element Transition: transitionName должен совпадать с тем,

        binding.fragmentVacancyTextTitle.transitionName = extractTransitionName(vacancyId)

        binding.fragmentVacancyButtonNextVacancy.setOnClickListener {
            // As we defined part of navigation graph for this screen,
            // we can use actions and navigation controllers
            val newVacancyId = "${vacancyId}+${Random.nextInt(10)}"
            findNavController().navigate(
                directions = VacancyFragmentDirections.actionVacancyFragmentToVacancyFragment(newVacancyId)
            )
        }

        binding.fragmentVacancyButtonOpenCompanyFlow.setOnClickListener {
            // Navigation through deep link
            val companyFlowUri = "companyflow://company".toUri()
            findNavController().navigate(deepLink = companyFlowUri)
        }
    }

    /**
     * vacancyId приходит в формате "Vacancy #N|id_N".
     * Для transitionName нам нужен только "id_N" — тот же, что использовался
     * в адаптере (item.id).
     */
    private fun extractTransitionName(vacancyId: String): String {
        val id = vacancyId.substringAfter("|", missingDelimiterValue = vacancyId)
        return "vacancy_title_$id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}