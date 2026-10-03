package com.aaglobal.jnc_playground.ui.tabs.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.databinding.FragmentProfileContainerBinding

class ProfileContainerFragment : Fragment(R.layout.fragment_profile_container) {

    private var _binding: FragmentProfileContainerBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileContainerBinding.bind(view)

        binding.fragmentProfileContainerButtonOpenAuthFlow.setOnClickListener {
            Navigation.findNavController(
                activity = requireActivity(),
                viewId = R.id.activity_root__fragment__nav_host
            ).navigate(resId = R.id.action__MainFragment__to__AuthFlow)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}