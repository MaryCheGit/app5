package com.aaglobal.jnc_playground.ui.dialogs.dialog_b

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.aaglobal.jnc_playground.R
import com.aaglobal.jnc_playground.databinding.DialogBBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BBottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: DialogBBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val LOG_TAG = "BBottomSheetDialog"
    }

    private var callback: OnBackPressedCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // But this will work ¯\_(ツ)_/¯
        return object : BottomSheetDialog(requireContext(), theme) {
            override fun onBackPressed() {
                Log.d(LOG_TAG, "onBackPressed catch when override onCreateDialog")
                moveToABottomSheetDialog()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogBBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogBBottomSheetButtonReturn.setOnClickListener {
            moveToABottomSheetDialog()
        }

        // Not working as expected -- no back pressed callback in this dialog ¯\_(ツ)_/¯
        callback = object : OnBackPressedCallback(enabled = true) {
            override fun handleOnBackPressed() {
                // ...
            }
        }
    }

    private fun moveToABottomSheetDialog() {
        // Тут у тебя в старом коде была навигация к диалогу A —
        // допиши сам, я не видел этого куска на скрине
        findNavController().navigate(resId = R.id.action__BBottomSheet__to__ABottomSheet)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}