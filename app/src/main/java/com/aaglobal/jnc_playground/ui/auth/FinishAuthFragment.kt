package com.aaglobal.jnc_playground.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.databinding.FragmentFinishAuthBinding
import com.aaglobal.jnc_playground.R

class FinishAuthFragment : Fragment(R.layout.fragment_finish_auth) {

    private var _binding: FragmentFinishAuthBinding? = null
    private val binding get() = _binding!!

    private val finishAuthViewModel: FinishAuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFinishAuthBinding.bind(view)

        binding.fragmentFinishAuthButton.setOnClickListener {
            // Save hasAuthData flag in prefs
            finishAuthViewModel.setFinishAuthFlag()

            // Navigate back from auth flow
            val result = findNavController().popBackStack(destinationId = R.id.auth__nav_graph, inclusive = true)
            if (result.not()) {
                // we can't open new destination with this action
                // --> we opened Auth flow from splash
                // --> need to open main graph
                findNavController().navigate(resId = R.id.MainFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}