package com.aaglobal.jnc_playground.company

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.company.databinding.FragmentCompanyDetailsBinding

class CompanyDetailsFragment : Fragment(R.layout.fragment_company_details) {

    private var _binding: FragmentCompanyDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val COMPANY_FLOW_RESULT_FLAG = "company_flow_result"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCompanyDetailsBinding.bind(view)

        // We use defined graph in feature-module
        binding.fragmentCompanyDetailsButton.setOnClickListener {
            // Here we are inside nested navigation flow
            findNavController().popBackStack(destinationId = R.id.company_flow__nav_graph, inclusive = true)

            // At this line, "navController.currentBackStackEntry" means screen that STARTED current nested flow.
            // So we can send result!
            findNavController().currentBackStackEntry?.savedStateHandle?.set(COMPANY_FLOW_RESULT_FLAG, true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}