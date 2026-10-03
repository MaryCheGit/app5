package com.aaglobal.jnc_playground.ui.dialogs.dialog_a

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.databinding.DialogABottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ABottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: DialogABottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ABottomSheetDialogViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogABottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.counter.observe(viewLifecycleOwner, Observer { counter ->
            binding.dialogABottomSheetTextCounter.text = "Counter: $counter"
        })

        binding.dialogABottomSheetButtonIncreaseCounter.setOnClickListener {
            viewModel.increaseCounter()
        }

        binding.dialogABottomSheetButtonOpenNextDialog.setOnClickListener {
            findNavController().navigate(resId = R.id.action__ABottomSheet__to__BBottomSheet)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}