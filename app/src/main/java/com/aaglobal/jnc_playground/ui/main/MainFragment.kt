package com.aaglobal.jnc_playground.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.databinding.FragmentMainBinding
import com.aaglobal.jnc_playground.extensions.setupWithNavController

/**
 * Main fragment -- container for bottom navigation
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.search__nav_graph,
            R.navigation.favorites__nav_graph,
            R.navigation.responses__nav_graph,
            R.navigation.profile__nav_graph
        )

        // Setup the bottom navigation view with a list of navigation graphs
        binding.fragmentMainBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.fragment_main__nav_host_container,
            intent = requireActivity().intent
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}