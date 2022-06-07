package com.semicolon.walkhub.ui.profile.setting.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ChangeClassDialogBinding

class SchoolDialog(
    private val context: Context,
    private val onYesClick: () -> Unit = {},
    private val onNoClick: () -> Unit = {}
) {

    fun callDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding: ChangeClassDialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.change_class_dialog,
            null,
            false
        )
        dialog.setContentView(binding.root)

        binding.changeBtn.setOnClickListener {
            onYesClick()
            dialog.dismiss()
        }
        binding.noBtn.setOnClickListener {
            onNoClick()
            dialog.dismiss()
        }
        dialog.show()
    }
}