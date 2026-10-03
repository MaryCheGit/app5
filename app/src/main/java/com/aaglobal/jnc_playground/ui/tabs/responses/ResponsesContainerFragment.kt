package com.aaglobal.jnc_playground.ui.tabs.responses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.databinding.FragmentResponsesContainerBinding

class ResponsesContainerFragment : Fragment(R.layout.fragment_responses_container) {

    private var _binding: FragmentResponsesContainerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentResponsesContainerBinding.bind(view)

        binding.fragmentResponsesContainerButtonOpenCompanyFlow.setOnClickListener {
            findNavController().navigate(resId = R.id.action__ResponsesContainerFragment__to__CompanyFlow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}