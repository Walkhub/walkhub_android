package com.semicolon.walkhub.ui.profile.setting.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.semicolon.walkhub.databinding.ChangeClassDialogBinding

class CustomDialog : DialogFragment() {
    private var _binding: ChangeClassDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ChangeClassDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.changeBtn.setOnClickListener {
            val intent = Intent(context, ModifyProfileActivity::class.java)
            intent.putExtra("change", true)
            dismiss()
        }
        binding.noBtn.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}