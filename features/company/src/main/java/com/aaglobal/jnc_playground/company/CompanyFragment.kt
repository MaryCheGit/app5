package com.aaglobal.jnc_playground.company

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.company.databinding.FragmentCompanyBinding

class CompanyFragment : Fragment(R.layout.fragment_company) {

    private var _binding: FragmentCompanyBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCompanyBinding.bind(view)

        // We use special interface for navigating from this module
        binding.fragmentCompanyButton.setOnClickListener {
            findNavController().navigate(resId = R.id.action__CompanyFragment__to__CompanyDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}